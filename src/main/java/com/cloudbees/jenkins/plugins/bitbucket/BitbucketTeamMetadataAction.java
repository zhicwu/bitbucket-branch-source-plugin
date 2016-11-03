/*
 * The MIT License
 *
 * Copyright 2016 CloudBees, Inc.
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

import com.cloudbees.jenkins.plugins.bitbucket.api.BitbucketHref;
import com.cloudbees.jenkins.plugins.bitbucket.api.BitbucketTeam;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Util;
import java.io.IOException;
import java.util.Map;
import jenkins.branch.MetadataAction;
import jenkins.branch.OrganizationFolder;

/**
 * Invisible {@link OrganizationFolder} property that
 * retains information about Bitbucket team.
 */
public class BitbucketTeamMetadataAction extends MetadataAction {
    @NonNull
    private final String name;
    @CheckForNull
    private final String displayName;
    @CheckForNull
    private final String avatarUrl;
    @CheckForNull
    private final String teamUrl;

    public BitbucketTeamMetadataAction(@NonNull BitbucketTeam team) throws IOException {
        this.name = team.getName();
        this.displayName = team.getDisplayName();
        Map<String, BitbucketHref> links = team.getLinks();
        this.avatarUrl = getLink(links, "avatar");
        this.teamUrl = getLink(links, "html");
    }

    private static String getLink(Map<String, BitbucketHref> links, String name) {
        if (links == null) {
            return null;
        }
        BitbucketHref href = links.get(name);
        return href == null ? null : href.getHref();
    }

    public BitbucketTeamMetadataAction(@NonNull String name, @CheckForNull String displayName,
                                       @CheckForNull String avatarUrl, @CheckForNull String teamUrl) {
        this.name = name;
        this.displayName = displayName;
        this.avatarUrl = avatarUrl;
        this.teamUrl = teamUrl;
    }

    /**
     * {@inheritDoc}
     */
    @CheckForNull
    @Override
    public String getObjectDisplayName() {
        return Util.fixEmpty(displayName);
    }

// TODO when bitbucket supports serving avatars with a size request - currently only works if using gravatar or server
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public String getFolderIconImageOf(String size) {
//        if (avatarUrl == null) {
//            // fall back to the generic github org icon
//            String image = folderIconClassNameImageOf(getFolderIconClassName(), size);
//            return image != null
//                    ? image
//                    : (Stapler.getCurrentRequest().getContextPath() + Hudson.RESOURCE_PATH
//                            + "/plugin/cloudbees-bitbucket-branch-source/images/" + size + "/bitbucket-logo.png");
//        } else {
//            String[] xy = size.split("x");
//            if (xy.length == 0) return avatarUrl;
//            if (avatarUrl.contains("?")) return avatarUrl + "&s=" + xy[0];
//            else return avatarUrl + "?s=" + xy[0];
//        }
//    }
//

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFolderIconClassName() {
        // TODO when bitbucket supports serving avatars with a size request
        // return avatarUrl == null ? "icon-bitbucket-logo" : null;
        return "icon-bitbucket-logo";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFolderIconDescription() {
        return Messages.BitbucketTeamMetadataAction_IconDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BitbucketTeamMetadataAction that = (BitbucketTeamMetadataAction) o;

        if (!name.equals(that.name)) {
            return false;
        }
        if (displayName != null ? !displayName.equals(that.displayName) : that.displayName != null) {
            return false;
        }
        if (avatarUrl != null ? !avatarUrl.equals(that.avatarUrl) : that.avatarUrl != null) {
            return false;
        }
        return teamUrl != null ? teamUrl.equals(that.teamUrl) : that.teamUrl == null;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (avatarUrl != null ? avatarUrl.hashCode() : 0);
        result = 31 * result + (teamUrl != null ? teamUrl.hashCode() : 0);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "BitbucketTeamMetadataAction{" +
                "name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", teamUrl='" + teamUrl + '\'' +
                '}';
    }

    public String getTeamUrl() {
        return teamUrl;
    }
}
