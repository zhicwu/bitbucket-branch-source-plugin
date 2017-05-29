/*
 * The MIT License
 *
 * Copyright (c) 2017, CloudBees, Inc.
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
package com.cloudbees.jenkins.plugins.bitbucket.endpoints;

import com.cloudbees.plugins.credentials.common.StandardUsernamePasswordCredentials;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import hudson.Util;
import hudson.util.FormValidation;
import java.net.MalformedURLException;
import java.net.URL;
import javax.annotation.Nonnull;
import org.apache.commons.lang.StringUtils;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

/**
 * Represents a Bitbucket Server instance.
 *
 * @since 2.2.0
 */
public class BitbucketServerEndpoint extends AbstractBitbucketEndpoint {

    /**
     * Optional name to use to describe the end-point.
     */
    @CheckForNull
    private final String displayName;

    /**
     * The URL of this Bitbucket Server.
     */
    @NonNull
    private final String serverUrl;

    /**
     * @param displayName   Optional name to use to describe the end-point.
     * @param serverUrl     The URL of this Bitbucket Server
     * @param manageHooks   {@code true} if and only if Jenkins is supposed to auto-manage hooks for this end-point.
     * @param credentialsId The {@link StandardUsernamePasswordCredentials#getId()} of the credentials to use for
     *                      auto-management of hooks.
     */
    @DataBoundConstructor
    public BitbucketServerEndpoint(@CheckForNull String displayName, @NonNull String serverUrl, boolean manageHooks,
                                   @CheckForNull String credentialsId) {
        super(manageHooks, credentialsId);
        this.displayName = Util.fixEmpty(displayName);
        this.serverUrl = StringUtils.defaultString(serverUrl).replaceAll("/$", "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDisplayName() {
        return displayName;
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public String getServerUrl() {
        return serverUrl;
    }

    /**
     * Our descriptor.
     */
    @Extension
    public static class DescriptorImpl extends AbstractBitbucketEndpointDescriptor {
        /**
         * {@inheritDoc}
         */
        @Nonnull
        @Override
        public String getDisplayName() {
            return Messages.BitbucketServerEndpoint_displayName();
        }

        /**
         * Checks that the supplied URL is valid.
         *
         * @param value the URL to check.
         * @return the validation results.
         */
        public static FormValidation doCheckServerUrl(@QueryParameter String value) {
            try {
                new URL(value);
            } catch (MalformedURLException e) {
                return FormValidation.error("Invalid URL: " + e.getMessage());
            }
            return FormValidation.ok();
        }

    }
}
