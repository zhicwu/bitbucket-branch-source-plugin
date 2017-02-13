package com.cloudbees.jenkins.plugins.bitbucket.api;

import com.cloudbees.plugins.credentials.common.StandardUsernamePasswordCredentials;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import hudson.ExtensionList;
import hudson.ExtensionPoint;

public abstract class BitbucketApiFactory implements ExtensionPoint {

    protected abstract boolean isMatch(@Nullable String serverUrl);

    @NonNull
    protected abstract BitbucketApi create(@Nullable String serverUrl,
                                                @Nullable StandardUsernamePasswordCredentials credentials,
                                                @NonNull String owner,
                                                @CheckForNull String repository);

    @NonNull
    public static BitbucketApi newInstance(@Nullable String serverUrl,
                                           @Nullable StandardUsernamePasswordCredentials credentials,
                                           @NonNull String owner,
                                           @CheckForNull String repository) {
        for (BitbucketApiFactory factory: ExtensionList.lookup(BitbucketApiFactory.class)) {
            if (factory.isMatch(serverUrl)) {
                return factory.create(serverUrl, credentials, owner, repository);
            }
        }
        throw new IllegalStateException("Unsupported Bitbucket server URL: " + serverUrl);
    }
}
