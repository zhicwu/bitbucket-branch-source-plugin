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
package com.cloudbees.jenkins.plugins.bitbucket.client.branch;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.cloudbees.jenkins.plugins.bitbucket.api.BitbucketBranch;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BitbucketCloudBranch implements BitbucketBranch {

    @JsonProperty("raw_node")
    private String rawNode;

    private String name;

    //Bitbucket cloud signals timestamps formatted after "2017-08-30 07:45:04+00:00"
    @JsonProperty("utctimestamp")
    private String date;

    // Needed for compatibility with different Bitbucket API JSON messages
    private String branch;

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    
    public String getRawNode() {
        return rawNode;
    }

    @Override
    public String getName() {
        return name != null ? name : branch;
    }

    public String getDate() {
        return date;
    }

    @Override
    public long getDateMillis() {
        final SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssXXX");
        try {
            return dateParser.parse(date).getTime();
        } catch (ParseException e) {
            return 0;
        }
    }

    public void setRawNode(String rawNode) {
        this.rawNode = rawNode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
