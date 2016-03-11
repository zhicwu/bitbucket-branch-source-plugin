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
package com.cloudbees.jenkins.plugins.bitbucket;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang.StringUtils;

import edu.umd.cs.findbugs.annotations.CheckForNull;

public abstract class RepositoryUriResolver {

    private boolean server = false;
    private URL url;

    public RepositoryUriResolver(String baseUrl) throws MalformedURLException {
        if (baseUrl != null) {
            this.url = new URL(baseUrl);
        }
        if (!StringUtils.isBlank(baseUrl)) {
            server = true;
        }
    }

    public abstract String getRepositoryUri(String owner, String repository, RepositoryType type);

    public String getUrlAsString() {
        return url == null ? "https://bitbucket.org" : url.toExternalForm();
    }

    @CheckForNull
    public URL getUrl() {
        return url;
    }

    /**
     * @return the authority part of the {@link #url} (which is host:port)
     */
    public String getAuthority() {
        if (isServer()) {
            return url.getAuthority();
        }
        return "bitbucket.org";
    }

    /**
     * @return the host part of the {@link #url}
     */
    public String getHost() {
        if (isServer()) {
            return url.getHost();
        }
        return "bitbucket.org";
    }

    protected boolean isServer() {
        return server;
    }

}
