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

import org.junit.Test;
import static org.junit.Assert.*;

import java.net.MalformedURLException;

public class UriResolverTest {

    @Test
    public void httpUriResolver() throws MalformedURLException {
        HttpsRepositoryUriResolver r = new HttpsRepositoryUriResolver(null);
        assertEquals("https://bitbucket.org/user1/repo1.git", r.getRepositoryUri("user1", "repo1", RepositoryType.GIT));
        assertEquals("https://bitbucket.org/user1/repo1", r.getRepositoryUri("user1", "repo1", RepositoryType.MERCURIAL));
        r = new HttpsRepositoryUriResolver("http://localhost:1234");
        assertEquals("http://localhost:1234/scm/user2/repo2.git", r.getRepositoryUri("user2", "repo2", RepositoryType.GIT));
        r = new HttpsRepositoryUriResolver("http://192.168.1.100:1234");
        assertEquals("http://192.168.1.100:1234/scm/user2/repo2.git", r.getRepositoryUri("user2", "repo2", RepositoryType.GIT));
    }

    @Test
    public void sshUriResolver() throws MalformedURLException {
        SshRepositoryUriResolver r = new SshRepositoryUriResolver(null, -1);
        assertEquals("git@bitbucket.org:user1/repo1.git", r.getRepositoryUri("user1", "repo1", RepositoryType.GIT));
        assertEquals("ssh://hg@bitbucket.org/user1/repo1", r.getRepositoryUri("user1", "repo1", RepositoryType.MERCURIAL));
        r = new SshRepositoryUriResolver("http://localhost:1234", 7999);
        assertEquals("ssh://git@localhost:7999/user2/repo2.git", r.getRepositoryUri("user2", "repo2", RepositoryType.GIT));
        r = new SshRepositoryUriResolver("http://myserver", 7999);
        assertEquals("ssh://git@myserver:7999/user2/repo2.git", r.getRepositoryUri("user2", "repo2", RepositoryType.GIT));
    }

    @Test(expected = IllegalStateException.class)
    public void httpUriResolverIllegalStates() throws MalformedURLException {
        HttpsRepositoryUriResolver r = new HttpsRepositoryUriResolver("http://localhost:1234");
        // Mercurial is not supported by Bitbucket Server
        r.getRepositoryUri("user1", "repo1", RepositoryType.MERCURIAL);
    }

    @Test(expected = IllegalStateException.class)
    public void sshUriResolverIllegalStates() throws MalformedURLException {
        SshRepositoryUriResolver r = new SshRepositoryUriResolver("http://localhost:1234", 7999);
        // Mercurial is not supported by Bitbucket Server
        r.getRepositoryUri("user1", "repo1", RepositoryType.MERCURIAL);
    }
}
