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

public class SshRepositoryUriResolver extends RepositoryUriResolver {

    /**
     * Bitbucket server uses a different port for SSH clones.
     * -1 for Bitbucket cloud.
     */
    private int sshPort;

    public SshRepositoryUriResolver(String baseUrl, int sshPort) throws MalformedURLException {
        super(baseUrl);
        if (sshPort > 0) {
            this.sshPort = sshPort;
        } else {
            URL url = getUrl();
            if (url != null) {
                if (url.getPort() > 0){
                    this.sshPort = url.getPort();
                } else {
                    this.sshPort = url.getDefaultPort();
                }
            }
        }
    }

    @Override
    public String getRepositoryUri(String owner, String repository, RepositoryType type) {
        if (type == RepositoryType.MERCURIAL) {
            if (isServer()) {
                throw new IllegalStateException("Mercurial is not supported by Bitbucket Server (https://jira.atlassian.com/browse/BSERV-2469)");
            }
            return "ssh://hg@" + getAuthority() + "/" + owner + "/" + repository;
        } else {
            // Defaults to git
            if (isServer()) {
                return "ssh://git@" + getHost() + ":" + sshPort + "/" + owner + "/" + repository + ".git";
            } else {
                return "git@" + getHost() + ":" + owner + "/" + repository + ".git";
            }
        }
    }

}
