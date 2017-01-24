/*
 * The MIT License
 *
 * Copyright (c) 2016, CloudBees, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.cloudbees.jenkins.plugins.bitbucket.server.client;

import com.cloudbees.jenkins.plugins.bitbucket.api.*;
import com.cloudbees.jenkins.plugins.bitbucket.client.repository.UserRoleInRepository;
import com.cloudbees.jenkins.plugins.bitbucket.hooks.BitbucketSCMSourcePushHookReceiver;
import com.cloudbees.jenkins.plugins.bitbucket.server.client.branch.BitbucketServerBranch;
import com.cloudbees.jenkins.plugins.bitbucket.server.client.branch.BitbucketServerBranches;
import com.cloudbees.jenkins.plugins.bitbucket.server.client.branch.BitbucketServerCommit;
import com.cloudbees.jenkins.plugins.bitbucket.server.client.pullrequest.BitbucketServerPullRequest;
import com.cloudbees.jenkins.plugins.bitbucket.server.client.pullrequest.BitbucketServerPullRequests;
import com.cloudbees.jenkins.plugins.bitbucket.server.client.repository.*;
import com.cloudbees.plugins.credentials.common.StandardUsernamePasswordCredentials;
import hudson.ProxyConfiguration;
import hudson.util.Secret;
import jenkins.model.Jenkins;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Bitbucket API client.
 * Developed and test with Bitbucket 4.3.2
 */
public class BitbucketServerAPIClient implements BitbucketApi {

    private static final Logger LOGGER = Logger.getLogger(BitbucketServerAPIClient.class.getName());
    private static final String API_BASE_PATH = "/rest/api/1.0";
    private static final String API_REPOSITORIES_PATH = API_BASE_PATH + "/projects/%s/repos?start=%s";
    private static final String API_REPOSITORY_PATH = API_BASE_PATH + "/projects/%s/repos/%s";
    private static final String API_DEFAULT_BRANCH_PATH = API_BASE_PATH + "/projects/%s/repos/%s/branches/default";
    private static final String API_BRANCHES_PATH = API_BASE_PATH + "/projects/%s/repos/%s/branches?start=%s";
    private static final String API_PULL_REQUESTS_PATH = API_BASE_PATH + "/projects/%s/repos/%s/pull-requests?start=%s";
    private static final String API_PULL_REQUEST_PATH = API_BASE_PATH + "/projects/%s/repos/%s/pull-requests/%s";
    private static final String API_BROWSE_PATH = API_REPOSITORY_PATH + "/browse/%s?at=%s";
    private static final String API_COMMITS_PATH = API_REPOSITORY_PATH + "/commits/%s";
    private static final String API_PROJECT_PATH = API_BASE_PATH + "/projects/%s";
    private static final String API_COMMIT_COMMENT_PATH = API_REPOSITORY_PATH + "/commits/%s/comments";

    private static final String WEBHOOK_BASE_PATH = "/rest/webhook/1.0";
    private static final String WEBHOOK_REPOSITORY_PATH = WEBHOOK_BASE_PATH + "/projects/%s/repos/%s/configurations";
    private static final String WEBHOOK_REPOSITORY_CONFIG_PATH = WEBHOOK_REPOSITORY_PATH + "/%s";

    private static final String API_COMMIT_STATUS_PATH = "/rest/build-status/1.0/commits/%s";

    private static final int MAX_PAGES = 100;

    /**
     * Repository owner or project name.
     */
    private String owner;

    /**
     * The repository that this object is managing.
     */
    private String repositoryName;

    /**
     * Indicates if the client is using user-centric API endpoints or project API otherwise.
     */
    private boolean userCentric = false;

    /**
     * Credentials to access API services.
     * Almost @NonNull (but null is accepted for anonymous access).
     */
    private UsernamePasswordCredentials credentials;

    private String baseURL;

    public BitbucketServerAPIClient(String baseURL, String username, String password, String owner, String repositoryName, boolean userCentric) {
        if (!StringUtils.isBlank(username) && !StringUtils.isBlank(password)) {
            this.credentials = new UsernamePasswordCredentials(username, password);
        }
        this.userCentric = userCentric;
        this.owner = owner;
        this.repositoryName = repositoryName;
        this.baseURL = baseURL;
    }

    public BitbucketServerAPIClient(String baseURL, String owner, String repositoryName, StandardUsernamePasswordCredentials creds, boolean userCentric) {
        if (creds != null) {
            this.credentials = new UsernamePasswordCredentials(creds.getUsername(), Secret.toString(creds.getPassword()));
        }
        this.userCentric = userCentric;
        this.owner = owner;
        this.repositoryName = repositoryName;
        this.baseURL = baseURL;
    }

    public BitbucketServerAPIClient(String baseURL, String owner, StandardUsernamePasswordCredentials creds, boolean userCentric) {
        this(baseURL, owner, null, creds, userCentric);
    }

    /**
     * Bitbucket Server manages two top level entities, owner and/or project.
     * Only one of them makes sense for a specific client object.
     */
    @Override
    public String getOwner() {
        return owner;
    }

    /**
     * In Bitbucket server the top level entity is the Project, but the JSON API accepts users as a replacement
     * of Projects in most of the URLs (it's called user centric API).
     *
     * This method returns the appropriate string to be placed in request URLs taking into account if this client
     * object was created as a user centric instance or not.
     *
     * @return the ~user or project
     */
    public String getUserCentricOwner() {
        return userCentric ? "~" + owner : owner;
    }

    /** {@inheritDoc} */
    @Override
    public String getRepositoryName() {
        return repositoryName;
    }

    /** {@inheritDoc} */
    @Override
    public List<BitbucketServerPullRequest> getPullRequests() {
        String url = String.format(API_PULL_REQUESTS_PATH, getUserCentricOwner(), repositoryName, 0);

        try {
            List<BitbucketServerPullRequest> pullRequests = new ArrayList<>();
            Integer pageNumber = 1;
            String response = getRequest(url);
            BitbucketServerPullRequests page = parse(response, BitbucketServerPullRequests.class);
            pullRequests.addAll(page.getValues());
            while (!page.isLastPage() && pageNumber < MAX_PAGES) {
                pageNumber++;
                response = getRequest(String.format(API_PULL_REQUESTS_PATH, getUserCentricOwner(), repositoryName, page.getNextPageStart()));
                page = parse(response, BitbucketServerPullRequests.class);
                pullRequests.addAll(page.getValues());
            }
            return pullRequests;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "invalid pull requests response", e);
        }
        return Collections.emptyList();
    }

    /** {@inheritDoc} */
    @Override
    public BitbucketPullRequest getPullRequestById(Integer id) {
        String response = getRequest(String.format(API_PULL_REQUEST_PATH, getUserCentricOwner(), repositoryName, id));
        try {
            return parse(response, BitbucketServerPullRequest.class);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "invalid pull request response.", e);
        }
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public BitbucketRepository getRepository() {
        if (repositoryName == null) {
            return null;
        }
        String response = getRequest(String.format(API_REPOSITORY_PATH, getUserCentricOwner(), repositoryName));
        try {
            return parse(response, BitbucketServerRepository.class);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "invalid repository response.", e);
        }
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public void postCommitComment(String hash, String comment) {
        try {
            postRequest(String.format(API_COMMIT_COMMENT_PATH, getUserCentricOwner(), repositoryName, hash), new NameValuePair[]{ new NameValuePair("text", comment) });
        } catch (UnsupportedEncodingException e) {
            LOGGER.log(Level.SEVERE, "Encoding error", e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void postBuildStatus(BitbucketBuildStatus status) {
        try {
            postRequest(String.format(API_COMMIT_STATUS_PATH, status.getHash()), serialize(status));
        } catch (UnsupportedEncodingException e) {
            LOGGER.log(Level.SEVERE, "Encoding error", e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Build Status serialization error", e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean checkPathExists(String branch, String path) {
        int status = getRequestStatus(String.format(API_BROWSE_PATH, getUserCentricOwner(), repositoryName, path, branch));
        return status == HttpStatus.SC_OK;
    }

    @Override
    public String getDefaultBranch() {
        String response = getRequest(String.format(API_DEFAULT_BRANCH_PATH, getUserCentricOwner(), repositoryName));
        try {
            return parse(response, BitbucketServerBranch.class).getName();
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "invalid commit response.", e);
        }
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public List<BitbucketServerBranch> getBranches() {
        String url = String.format(API_BRANCHES_PATH, getUserCentricOwner(), repositoryName, 0);

        try {
            List<BitbucketServerBranch> branches = new ArrayList<>();
            Integer pageNumber = 1;
            String response = getRequest(url);
            BitbucketServerBranches page = parse(response, BitbucketServerBranches.class);
            branches.addAll(page.getValues());
            while (!page.isLastPage() && pageNumber < MAX_PAGES) {
                pageNumber++;
                response = getRequest(String.format(API_BRANCHES_PATH, getUserCentricOwner(), repositoryName, page.getNextPageStart()));
                page = parse(response, BitbucketServerBranches.class);
                branches.addAll(page.getValues());
            }
            return branches;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "invalid branches response", e);
        }
        return Collections.emptyList();
    }

    /** {@inheritDoc} */
    @Override
    public BitbucketCommit resolveCommit(String hash) {
        String response = getRequest(String.format(API_COMMITS_PATH, getUserCentricOwner(), repositoryName, hash));
        try {
            return parse(response, BitbucketServerCommit.class);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "invalid commit response.", e);
        }
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public String resolveSourceFullHash(BitbucketPullRequest pull) {
        return pull.getSource().getCommit().getHash();
    }

    @Override
    public void registerCommitWebHook() {
        try {
            putRequest(String.format(WEBHOOK_REPOSITORY_PATH, getUserCentricOwner(), repositoryName), serialize(getHook()));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "cannot register webhook", e);
        }
    }

    private BitbucketWebHook getHook() {
        BitbucketServerWebhook hooks = new BitbucketServerWebhook();
        hooks.setActive(true);
        hooks.setDescription("Jenkins hooks");
        hooks.setUrl(Jenkins.getActiveInstance().getRootUrl() + BitbucketSCMSourcePushHookReceiver.FULL_PATH);
        return hooks;
    }

    @Override
    public void removeCommitWebHook(BitbucketWebHook hook) {
        deleteRequest(String.format(WEBHOOK_REPOSITORY_CONFIG_PATH, getUserCentricOwner(), repositoryName, hook.getUuid()));
    }

    @Override
    public List<BitbucketServerWebhook> getWebHooks() {
        String response = getRequest(String.format(WEBHOOK_REPOSITORY_PATH, getUserCentricOwner(), repositoryName));
        try {
            return parse(response, BitbucketServerWebhooks.class);
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    /**
     * There is no such Team concept in Bitbucket Server but Project.
     */
    @Override
    public BitbucketTeam getTeam() {
        if (userCentric) {
            return null;
        } else {
            String response = getRequest(String.format(API_PROJECT_PATH, getOwner()));
            try {
                return parse(response, BitbucketServerProject.class);
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, "invalid project response.", e);
            }
            return null;
        }
    }

    /**
     * The role parameter is ignored for Bitbucket Server.
     */
    @Override
    public List<BitbucketServerRepository> getRepositories(UserRoleInRepository role) {
        String url = String.format(API_REPOSITORIES_PATH, getUserCentricOwner(), 0);

        try {
            List<BitbucketServerRepository> repositories = new ArrayList<>();
            Integer pageNumber = 1;
            String response = getRequest(url);
            BitbucketServerRepositories page = parse(response, BitbucketServerRepositories.class);
            repositories.addAll(page.getValues());
            while (!page.isLastPage() && pageNumber < MAX_PAGES) {
                pageNumber++;
                response = getRequest(String.format(API_REPOSITORIES_PATH, getUserCentricOwner(), page.getNextPageStart()));
                page = parse(response, BitbucketServerRepositories.class);
                repositories.addAll(page.getValues());
            }
            return repositories;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "invalid branches response", e);
        }
        return Collections.emptyList();
    }

    /** {@inheritDoc} */
    @Override
    public List<BitbucketServerRepository> getRepositories() {
        return getRepositories(null);
    }

    @Override
    public boolean isPrivate() {
        BitbucketRepository repo = getRepository();
        return repo != null && repo.isPrivate();
    }


    private <T> T parse(String response, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response, clazz);
    }

    private String getRequest(String path) {
        GetMethod httpget = new GetMethod(this.baseURL + path);
        HttpClient client = getHttpClient(getMethodHost(httpget));
        client.getState().setCredentials(AuthScope.ANY, credentials);
        client.getParams().setAuthenticationPreemptive(true);
        String response = null;
        InputStream responseBodyAsStream = null;
        try {
            client.executeMethod(httpget);
            responseBodyAsStream = httpget.getResponseBodyAsStream();
            response = IOUtils.toString(responseBodyAsStream, "UTF-8");
            if (httpget.getStatusCode() != HttpStatus.SC_OK) {
                throw new BitbucketRequestException(httpget.getStatusCode(), "HTTP request error. Status: " + httpget.getStatusCode() + ": " + httpget.getStatusText() + ".\n" + response);
            }
        } catch (IOException e) {
            throw new BitbucketRequestException(0, "Communication error: " + e, e);
        } finally {
            httpget.releaseConnection();
            if (responseBodyAsStream != null) {
                IOUtils.closeQuietly(responseBodyAsStream);
            }
        }
        if (response == null) {
            throw new BitbucketRequestException(0, "HTTP request error " + httpget.getStatusCode() + ":" + httpget.getStatusText());
        }
        return response;
    }

    private HttpClient getHttpClient(String host) {
        HttpClient client = new HttpClient();

        client.getParams().setConnectionManagerTimeout(10 * 1000);
        client.getParams().setSoTimeout(60 * 1000);

        setClientProxyParams(host, client);
        return client;
    }

    private static void setClientProxyParams(String host, HttpClient client) {
        Jenkins jenkins = Jenkins.getInstance();
        ProxyConfiguration proxyConfig = null;
        if (jenkins != null) {
            proxyConfig = jenkins.proxy;
        }

        Proxy proxy = Proxy.NO_PROXY;
        if (proxyConfig != null) {
            proxy = proxyConfig.createProxy(host);
        }

        if (proxy.type() != Proxy.Type.DIRECT) {
            final InetSocketAddress proxyAddress = (InetSocketAddress) proxy.address();
            LOGGER.fine("Jenkins proxy: " + proxy.address());
            client.getHostConfiguration().setProxy(proxyAddress.getHostString(), proxyAddress.getPort());
            String username = proxyConfig.getUserName();
            String password = proxyConfig.getPassword();
            if (username != null && !"".equals(username.trim())) {
                LOGGER.fine("Using proxy authentication (user=" + username + ")");
                client.getState().setProxyCredentials(AuthScope.ANY,
                        new UsernamePasswordCredentials(username, password));
            }
        }
    }

    private int getRequestStatus(String path) {
        GetMethod httpget = new GetMethod(this.baseURL + path);
        HttpClient client = getHttpClient(getMethodHost(httpget));
        client.getState().setCredentials(AuthScope.ANY, credentials);
        client.getParams().setAuthenticationPreemptive(true);
        try {
            client.executeMethod(httpget);
            return httpget.getStatusCode();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Communication error", e);
        } finally {
            httpget.releaseConnection();
        }
        return -1;
    }

    private static String getMethodHost(HttpMethod method) {
        try {
            return method.getURI().getHost();
        } catch (URIException e) {
            throw new IllegalStateException("Could not obtain host part for method " + method, e);
        }
    }

    private <T> String serialize(T o) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(o);
    }

    private String postRequest(String path, NameValuePair[] params) throws UnsupportedEncodingException {
        PostMethod httppost = new PostMethod(this.baseURL + path);
        httppost.setRequestEntity(new StringRequestEntity(nameValueToJson(params), "application/json", "UTF-8"));
        return doRequest(httppost);
    }

    private String postRequest(String path, String content) throws UnsupportedEncodingException {
        PostMethod httppost = new PostMethod(this.baseURL + path);
        httppost.setRequestEntity(new StringRequestEntity(content, "application/json", "UTF-8"));
        return doRequest(httppost);
    }

    private String putRequest(String path, String content) throws UnsupportedEncodingException {
        PutMethod httppost = new PutMethod(this.baseURL + path);
        httppost.setRequestEntity(new StringRequestEntity(content, "application/json", "UTF-8"));
        return doRequest(httppost);
    }

    private String deleteRequest(String path) {
        DeleteMethod httpDelete = new DeleteMethod(this.baseURL + path);
        return doRequest(httpDelete);
    }

    private String nameValueToJson(NameValuePair[] params) {
        JSONObject o = new JSONObject();
        for (NameValuePair pair : params) {
            o.put(pair.getName(), pair.getValue());
        }
        return o.toString();
    }

    private String doRequest(HttpMethod httpMethod) {
        HttpClient client = getHttpClient(getMethodHost(httpMethod));
        client.getState().setCredentials(AuthScope.ANY, credentials);
        client.getParams().setAuthenticationPreemptive(true);
        String response = null;
        InputStream responseBodyAsStream = null;
        try {
            client.executeMethod(httpMethod);
            if (httpMethod.getStatusCode() == HttpStatus.SC_NO_CONTENT) {
                // 204, no content
                return "";
            }
            responseBodyAsStream = httpMethod.getResponseBodyAsStream();
            if (responseBodyAsStream != null) {
                response = IOUtils.toString(responseBodyAsStream, "UTF-8");
            }
            if (httpMethod.getStatusCode() != HttpStatus.SC_OK && httpMethod.getStatusCode() != HttpStatus.SC_CREATED) {
                throw new BitbucketRequestException(httpMethod.getStatusCode(), "HTTP request error. Status: " + httpMethod.getStatusCode() + ": " + httpMethod.getStatusText() + ".\n" + response);
            }
        } catch (IOException e) {
            throw new BitbucketRequestException(0, "Communication error: " + e, e);
        } finally {
            httpMethod.releaseConnection();
            if (responseBodyAsStream != null) {
                IOUtils.closeQuietly(responseBodyAsStream);
            }
        }
        if (response == null) {
            throw new BitbucketRequestException(0, "HTTP request error");
        }
        return response;

    }

}
