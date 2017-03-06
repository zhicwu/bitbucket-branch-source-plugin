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
package com.cloudbees.jenkins.plugins.bitbucket.client.events;

import com.cloudbees.jenkins.plugins.bitbucket.api.BitbucketPullRequestEvent;
import com.cloudbees.jenkins.plugins.bitbucket.client.BitbucketCloudWebhookPayload;
import org.junit.Test;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class BitbucketCloudPullRequestEventTest {
    @Test
    public void createPayloadOrigin() throws Exception {
        BitbucketPullRequestEvent event = BitbucketCloudWebhookPayload.pullRequestEventFromPayload("{\n"
                + "  \"pullrequest\": {\n"
                + "    \"type\": \"pullrequest\",\n"
                + "    \"description\": \"\",\n"
                + "    \"links\": {\n"
                + "      \"decline\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/1/decline\"\n"
                + "      },\n"
                + "      \"commits\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/1/commits\"\n"
                + "      },\n"
                + "      \"self\": {\n"
                + "        \"href\": \"https://api.bitbucket.org/2.0/repositories/cloudbeers/temp/pullrequests/1\"\n"
                + "      },\n"
                + "      \"comments\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/1/comments\"\n"
                + "      },\n"
                + "      \"merge\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/1/merge\"\n"
                + "      },\n"
                + "      \"html\": {\n"
                + "        \"href\": \"https://bitbucket.org/cloudbeers/temp/pull-requests/1\"\n"
                + "      },\n"
                + "      \"activity\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/1/activity\"\n"
                + "      },\n"
                + "      \"diff\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/1/diff\"\n"
                + "      },\n"
                + "      \"approve\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/1/approve\"\n"
                + "      },\n"
                + "      \"statuses\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/1/statuses\"\n"
                + "      }\n"
                + "    },\n"
                + "    \"author\": {\n"
                + "      \"username\": \"stephenc\",\n"
                + "      \"display_name\": \"Stephen Connolly\",\n"
                + "      \"type\": \"user\",\n"
                + "      \"uuid\": \"{f70f195e-ade1-4961-9f89-547541377b80}\",\n"
                + "      \"links\": {\n"
                + "        \"self\": {\n"
                + "          \"href\": \"https://api.bitbucket.org/2.0/users/stephenc\"\n"
                + "        },\n"
                + "        \"html\": {\n"
                + "          \"href\": \"https://bitbucket.org/stephenc/\"\n"
                + "        },\n"
                + "        \"avatar\": {\n"
                + "          \"href\": \"https://bitbucket.org/account/stephenc/avatar/32/\"\n"
                + "        }\n"
                + "      }\n"
                + "    },\n"
                + "    \"close_source_branch\": false,\n"
                + "    \"reviewers\": [],\n"
                + "    \"destination\": {\n"
                + "      \"commit\": {\n"
                + "        \"hash\": \"f612156eff2c\",\n"
                + "        \"links\": {\n"
                + "          \"self\": {\n"
                + "            \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/commit/f612156eff2c\"\n"
                + "          }\n"
                + "        }\n"
                + "      },\n"
                + "      \"branch\": {\n"
                + "        \"name\": \"master\"\n"
                + "      },\n"
                + "      \"repository\": {\n"
                + "        \"links\": {\n"
                + "          \"self\": {\n"
                + "            \"href\": \"https://api.bitbucket.org/2.0/repositories/cloudbeers/temp\"\n"
                + "          },\n"
                + "          \"html\": {\n"
                + "            \"href\": \"https://bitbucket.org/cloudbeers/temp\"\n"
                + "          },\n"
                + "          \"avatar\": {\n"
                + "            \"href\": \"https://bitbucket.org/cloudbeers/temp/avatar/32/\"\n"
                + "          }\n"
                + "        },\n"
                + "        \"type\": \"repository\",\n"
                + "        \"name\": \"temp\",\n"
                + "        \"full_name\": \"cloudbeers/temp\",\n"
                + "        \"uuid\": \"{708c9715-0ecc-4a5d-87ed-63c4ba48ea06}\"\n"
                + "      }\n"
                + "    },\n"
                + "    \"comment_count\": 0,\n"
                + "    \"updated_on\": \"2017-03-06T10:38:57.370280+00:00\",\n"
                + "    \"source\": {\n"
                + "      \"commit\": {\n"
                + "        \"hash\": \"a72355f35fde\",\n"
                + "        \"links\": {\n"
                + "          \"self\": {\n"
                + "            \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/commit/a72355f35fde\"\n"
                + "          }\n"
                + "        }\n"
                + "      },\n"
                + "      \"branch\": {\n"
                + "        \"name\": \"foo\"\n"
                + "      },\n"
                + "      \"repository\": {\n"
                + "        \"links\": {\n"
                + "          \"self\": {\n"
                + "            \"href\": \"https://api.bitbucket.org/2.0/repositories/cloudbeers/temp\"\n"
                + "          },\n"
                + "          \"html\": {\n"
                + "            \"href\": \"https://bitbucket.org/cloudbeers/temp\"\n"
                + "          },\n"
                + "          \"avatar\": {\n"
                + "            \"href\": \"https://bitbucket.org/cloudbeers/temp/avatar/32/\"\n"
                + "          }\n"
                + "        },\n"
                + "        \"type\": \"repository\",\n"
                + "        \"name\": \"temp\",\n"
                + "        \"full_name\": \"cloudbeers/temp\",\n"
                + "        \"uuid\": \"{708c9715-0ecc-4a5d-87ed-63c4ba48ea06}\"\n"
                + "      }\n"
                + "    },\n"
                + "    \"reason\": \"\",\n"
                + "    \"state\": \"OPEN\",\n"
                + "    \"task_count\": 0,\n"
                + "    \"created_on\": \"2017-03-06T10:38:57.345797+00:00\",\n"
                + "    \"participants\": [],\n"
                + "    \"id\": 1,\n"
                + "    \"title\": \"README.md edited online with Bitbucket\",\n"
                + "    \"merge_commit\": null,\n"
                + "    \"closed_by\": null\n"
                + "  },\n"
                + "  \"actor\": {\n"
                + "    \"username\": \"stephenc\",\n"
                + "    \"display_name\": \"Stephen Connolly\",\n"
                + "    \"type\": \"user\",\n"
                + "    \"uuid\": \"{f70f195e-ade1-4961-9f89-547541377b80}\",\n"
                + "    \"links\": {\n"
                + "      \"self\": {\n"
                + "        \"href\": \"https://api.bitbucket.org/2.0/users/stephenc\"\n"
                + "      },\n"
                + "      \"html\": {\n"
                + "        \"href\": \"https://bitbucket.org/stephenc/\"\n"
                + "      },\n"
                + "      \"avatar\": {\n"
                + "        \"href\": \"https://bitbucket.org/account/stephenc/avatar/32/\"\n"
                + "      }\n"
                + "    }\n"
                + "  },\n"
                + "  \"repository\": {\n"
                + "    \"website\": \"\",\n"
                + "    \"scm\": \"git\",\n"
                + "    \"uuid\": \"{708c9715-0ecc-4a5d-87ed-63c4ba48ea06}\",\n"
                + "    \"links\": {\n"
                + "      \"self\": {\n"
                + "        \"href\": \"https://api.bitbucket.org/2.0/repositories/cloudbeers/temp\"\n"
                + "      },\n"
                + "      \"html\": {\n"
                + "        \"href\": \"https://bitbucket.org/cloudbeers/temp\"\n"
                + "      },\n"
                + "      \"avatar\": {\n"
                + "        \"href\": \"https://bitbucket.org/cloudbeers/temp/avatar/32/\"\n"
                + "      }\n"
                + "    },\n"
                + "    \"project\": {\n"
                + "      \"links\": {\n"
                + "        \"self\": {\n"
                + "          \"href\": \"https://api.bitbucket.org/2.0/teams/cloudbeers/projects/DUB\"\n"
                + "        },\n"
                + "        \"html\": {\n"
                + "          \"href\": \"https://bitbucket.org/account/user/cloudbeers/projects/DUB\"\n"
                + "        },\n"
                + "        \"avatar\": {\n"
                + "          \"href\": \"https://bitbucket.org/account/user/cloudbeers/projects/DUB/avatar/32\"\n"
                + "        }\n"
                + "      },\n"
                + "      \"type\": \"project\",\n"
                + "      \"uuid\": \"{7f08415d-57d0-4172-8978-059345fa0369}\",\n"
                + "      \"key\": \"DUB\",\n"
                + "      \"name\": \"dublin\"\n"
                + "    },\n"
                + "    \"full_name\": \"cloudbeers/temp\",\n"
                + "    \"owner\": {\n"
                + "      \"username\": \"cloudbeers\",\n"
                + "      \"display_name\": \"cloudbeers\",\n"
                + "      \"type\": \"team\",\n"
                + "      \"uuid\": \"{5e429024-1f85-425f-8955-8af0c35d1b41}\",\n"
                + "      \"links\": {\n"
                + "        \"self\": {\n"
                + "          \"href\": \"https://api.bitbucket.org/2.0/teams/cloudbeers\"\n"
                + "        },\n"
                + "        \"html\": {\n"
                + "          \"href\": \"https://bitbucket.org/cloudbeers/\"\n"
                + "        },\n"
                + "        \"avatar\": {\n"
                + "          \"href\": \"https://bitbucket.org/account/cloudbeers/avatar/32/\"\n"
                + "        }\n"
                + "      }\n"
                + "    },\n"
                + "    \"type\": \"repository\",\n"
                + "    \"is_private\": true,\n"
                + "    \"name\": \"temp\"\n"
                + "  }\n"
                + "}");
        assertThat(event.getRepository(), notNullValue());
        assertThat(event.getRepository().getScm(), is("git"));
        assertThat(event.getRepository().getFullName(), is("cloudbeers/temp"));
        assertThat(event.getRepository().getOwner().getDisplayName(), is("cloudbeers"));
        assertThat(event.getRepository().getOwner().getUsername(), is("cloudbeers"));
        assertThat(event.getRepository().getRepositoryName(), is("temp"));
        assertThat(event.getRepository().isPrivate(), is(true));
        assertThat(event.getRepository().getLinks(), notNullValue());
        assertThat(event.getRepository().getLinks().get("self"), notNullValue());
        assertThat(event.getRepository().getLinks().get("self").getHref(),
                is("https://api.bitbucket.org/2.0/repositories/cloudbeers/temp"));

        assertThat(event.getPullRequest(), notNullValue());
        assertThat(event.getPullRequest().getTitle(), is("README.md edited online with Bitbucket"));
        assertThat(event.getPullRequest().getAuthorLogin(), is("stephenc"));
        assertThat(event.getPullRequest().getLink(),
                is("https://bitbucket.org/cloudbeers/temp/pull-requests/1"));

        assertThat(event.getPullRequest().getDestination(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository().getScm(), is("git"));
        assertThat(event.getPullRequest().getDestination().getRepository().getFullName(), is("cloudbeers/temp"));
        assertThat(event.getPullRequest().getDestination().getRepository().getOwner().getDisplayName(),
                is("cloudbeers"));
        assertThat(event.getPullRequest().getDestination().getRepository().getOwner().getUsername(), is("cloudbeers"));
        assertThat(event.getPullRequest().getDestination().getRepository().getRepositoryName(), is("temp"));
        assertThat(event.getPullRequest().getDestination().getRepository().isPrivate(), is(true));
        assertThat(event.getPullRequest().getDestination().getRepository().getLinks(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository().getLinks().get("self"), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository().getLinks().get("self").getHref(),
                is("https://api.bitbucket.org/2.0/repositories/cloudbeers/temp"));
        assertThat(event.getPullRequest().getDestination().getBranch(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getBranch().getName(), is("master"));
        assertThat(event.getPullRequest().getDestination().getBranch().getRawNode(), 
                anyOf(is("f612156eff2c"), is("f612156eff2c958f52f8e6e20c71f396aeaeaff4")));
        assertThat(event.getPullRequest().getDestination().getCommit(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getCommit().getHash(),
                anyOf(is("f612156eff2c"), is("f612156eff2c958f52f8e6e20c71f396aeaeaff4")));

        assertThat(event.getPullRequest().getSource(), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository(), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository().getScm(), is("git"));
        assertThat(event.getPullRequest().getSource().getRepository().getFullName(), is("cloudbeers/temp"));
        assertThat(event.getPullRequest().getSource().getRepository().getOwner().getDisplayName(), is("cloudbeers"));
        assertThat(event.getPullRequest().getSource().getRepository().getOwner().getUsername(), is("cloudbeers"));
        assertThat(event.getPullRequest().getSource().getRepository().getRepositoryName(), is("temp"));
        assertThat(event.getPullRequest().getSource().getRepository().isPrivate(), is(true));
        assertThat(event.getPullRequest().getSource().getRepository().getLinks(), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository().getLinks().get("self"), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository().getLinks().get("self").getHref(),
                is("https://api.bitbucket.org/2.0/repositories/cloudbeers/temp"));

        assertThat(event.getPullRequest().getSource().getBranch(), notNullValue());
        assertThat(event.getPullRequest().getSource().getBranch().getName(), is("foo"));
        assertThat(event.getPullRequest().getSource().getBranch().getRawNode(),
                anyOf(is("a72355f35fde"), is("a72355f35fde2ad4f5724a279b970ef7b6729131")));
        assertThat(event.getPullRequest().getSource().getCommit(), notNullValue());
        assertThat(event.getPullRequest().getSource().getCommit().getHash(),
                anyOf(is("a72355f35fde"), is("a72355f35fde2ad4f5724a279b970ef7b6729131")));
    }

    @Test
    public void createPayloadFork() throws Exception {
        BitbucketPullRequestEvent event = BitbucketCloudWebhookPayload.pullRequestEventFromPayload("{\n"
                + "  \"pullrequest\": {\n"
                + "    \"merge_commit\": null,\n"
                + "    \"description\": \"description of PR\",\n"
                + "    \"links\": {\n"
                + "      \"decline\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/decline\"\n"
                + "      },\n"
                + "      \"commits\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/commits\"\n"
                + "      },\n"
                + "      \"self\": {\n"
                + "        \"href\": \"https://api.bitbucket.org/2.0/repositories/cloudbeers/temp/pullrequests/3\"\n"
                + "      },\n"
                + "      \"comments\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/comments\"\n"
                + "      },\n"
                + "      \"merge\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/merge\"\n"
                + "      },\n"
                + "      \"html\": {\n"
                + "        \"href\": \"https://bitbucket.org/cloudbeers/temp/pull-requests/3\"\n"
                + "      },\n"
                + "      \"activity\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/activity\"\n"
                + "      },\n"
                + "      \"diff\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/diff\"\n"
                + "      },\n"
                + "      \"approve\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/approve\"\n"
                + "      },\n"
                + "      \"statuses\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/statuses\"\n"
                + "      }\n"
                + "    },\n"
                + "    \"title\": \"Forking for PR\",\n"
                + "    \"close_source_branch\": false,\n"
                + "    \"reviewers\": [],\n"
                + "    \"destination\": {\n"
                + "      \"commit\": {\n"
                + "        \"hash\": \"1986c2284946\",\n"
                + "        \"links\": {\n"
                + "          \"self\": {\n"
                + "            \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/commit/1986c2284946\"\n"
                + "          }\n"
                + "        }\n"
                + "      },\n"
                + "      \"branch\": {\n"
                + "        \"name\": \"master\"\n"
                + "      },\n"
                + "      \"repository\": {\n"
                + "        \"links\": {\n"
                + "          \"self\": {\n"
                + "            \"href\": \"https://api.bitbucket.org/2.0/repositories/cloudbeers/temp\"\n"
                + "          },\n"
                + "          \"html\": {\n"
                + "            \"href\": \"https://bitbucket.org/cloudbeers/temp\"\n"
                + "          },\n"
                + "          \"avatar\": {\n"
                + "            \"href\": \"https://bitbucket.org/cloudbeers/temp/avatar/32/\"\n"
                + "          }\n"
                + "        },\n"
                + "        \"type\": \"repository\",\n"
                + "        \"name\": \"temp\",\n"
                + "        \"full_name\": \"cloudbeers/temp\",\n"
                + "        \"uuid\": \"{708c9715-0ecc-4a5d-87ed-63c4ba48ea06}\"\n"
                + "      }\n"
                + "    },\n"
                + "    \"comment_count\": 0,\n"
                + "    \"closed_by\": null,\n"
                + "    \"source\": {\n"
                + "      \"commit\": {\n"
                + "        \"hash\": \"1c48041a96db\",\n"
                + "        \"links\": {\n"
                + "          \"self\": {\n"
                + "            \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/stephenc/temp-fork/commit/1c48041a96db\"\n"
                + "          }\n"
                + "        }\n"
                + "      },\n"
                + "      \"branch\": {\n"
                + "        \"name\": \"master\"\n"
                + "      },\n"
                + "      \"repository\": {\n"
                + "        \"links\": {\n"
                + "          \"self\": {\n"
                + "            \"href\": \"https://api.bitbucket.org/2.0/repositories/stephenc/temp-fork\"\n"
                + "          },\n"
                + "          \"html\": {\n"
                + "            \"href\": \"https://bitbucket.org/stephenc/temp-fork\"\n"
                + "          },\n"
                + "          \"avatar\": {\n"
                + "            \"href\": \"https://bitbucket.org/stephenc/temp-fork/avatar/32/\"\n"
                + "          }\n"
                + "        },\n"
                + "        \"type\": \"repository\",\n"
                + "        \"name\": \"temp-fork\",\n"
                + "        \"full_name\": \"stephenc/temp-fork\",\n"
                + "        \"uuid\": \"{f58c57d2-28b0-44f4-8c30-ea83e4825381}\"\n"
                + "      }\n"
                + "    },\n"
                + "    \"participants\": [],\n"
                + "    \"state\": \"OPEN\",\n"
                + "    \"task_count\": 0,\n"
                + "    \"created_on\": \"2017-03-06T11:52:20.092358+00:00\",\n"
                + "    \"reason\": \"\",\n"
                + "    \"updated_on\": \"2017-03-06T11:52:20.120024+00:00\",\n"
                + "    \"author\": {\n"
                + "      \"username\": \"stephenc\",\n"
                + "      \"display_name\": \"Stephen Connolly\",\n"
                + "      \"type\": \"user\",\n"
                + "      \"uuid\": \"{f70f195e-ade1-4961-9f89-547541377b80}\",\n"
                + "      \"links\": {\n"
                + "        \"self\": {\n"
                + "          \"href\": \"https://api.bitbucket.org/2.0/users/stephenc\"\n"
                + "        },\n"
                + "        \"html\": {\n"
                + "          \"href\": \"https://bitbucket.org/stephenc/\"\n"
                + "        },\n"
                + "        \"avatar\": {\n"
                + "          \"href\": \"https://bitbucket.org/account/stephenc/avatar/32/\"\n"
                + "        }\n"
                + "      }\n"
                + "    },\n"
                + "    \"type\": \"pullrequest\",\n"
                + "    \"id\": 3\n"
                + "  },\n"
                + "  \"actor\": {\n"
                + "    \"username\": \"stephenc\",\n"
                + "    \"display_name\": \"Stephen Connolly\",\n"
                + "    \"type\": \"user\",\n"
                + "    \"uuid\": \"{f70f195e-ade1-4961-9f89-547541377b80}\",\n"
                + "    \"links\": {\n"
                + "      \"self\": {\n"
                + "        \"href\": \"https://api.bitbucket.org/2.0/users/stephenc\"\n"
                + "      },\n"
                + "      \"html\": {\n"
                + "        \"href\": \"https://bitbucket.org/stephenc/\"\n"
                + "      },\n"
                + "      \"avatar\": {\n"
                + "        \"href\": \"https://bitbucket.org/account/stephenc/avatar/32/\"\n"
                + "      }\n"
                + "    }\n"
                + "  },\n"
                + "  \"repository\": {\n"
                + "    \"website\": \"\",\n"
                + "    \"scm\": \"git\",\n"
                + "    \"name\": \"temp\",\n"
                + "    \"links\": {\n"
                + "      \"self\": {\n"
                + "        \"href\": \"https://api.bitbucket.org/2.0/repositories/cloudbeers/temp\"\n"
                + "      },\n"
                + "      \"html\": {\n"
                + "        \"href\": \"https://bitbucket.org/cloudbeers/temp\"\n"
                + "      },\n"
                + "      \"avatar\": {\n"
                + "        \"href\": \"https://bitbucket.org/cloudbeers/temp/avatar/32/\"\n"
                + "      }\n"
                + "    },\n"
                + "    \"project\": {\n"
                + "      \"links\": {\n"
                + "        \"self\": {\n"
                + "          \"href\": \"https://api.bitbucket.org/2.0/teams/cloudbeers/projects/DUB\"\n"
                + "        },\n"
                + "        \"html\": {\n"
                + "          \"href\": \"https://bitbucket.org/account/user/cloudbeers/projects/DUB\"\n"
                + "        },\n"
                + "        \"avatar\": {\n"
                + "          \"href\": \"https://bitbucket.org/account/user/cloudbeers/projects/DUB/avatar/32\"\n"
                + "        }\n"
                + "      },\n"
                + "      \"type\": \"project\",\n"
                + "      \"name\": \"dublin\",\n"
                + "      \"key\": \"DUB\",\n"
                + "      \"uuid\": \"{7f08415d-57d0-4172-8978-059345fa0369}\"\n"
                + "    },\n"
                + "    \"full_name\": \"cloudbeers/temp\",\n"
                + "    \"owner\": {\n"
                + "      \"username\": \"cloudbeers\",\n"
                + "      \"display_name\": \"cloudbeers\",\n"
                + "      \"type\": \"team\",\n"
                + "      \"uuid\": \"{5e429024-1f85-425f-8955-8af0c35d1b41}\",\n"
                + "      \"links\": {\n"
                + "        \"self\": {\n"
                + "          \"href\": \"https://api.bitbucket.org/2.0/teams/cloudbeers\"\n"
                + "        },\n"
                + "        \"html\": {\n"
                + "          \"href\": \"https://bitbucket.org/cloudbeers/\"\n"
                + "        },\n"
                + "        \"avatar\": {\n"
                + "          \"href\": \"https://bitbucket.org/account/cloudbeers/avatar/32/\"\n"
                + "        }\n"
                + "      }\n"
                + "    },\n"
                + "    \"type\": \"repository\",\n"
                + "    \"is_private\": true,\n"
                + "    \"uuid\": \"{708c9715-0ecc-4a5d-87ed-63c4ba48ea06}\"\n"
                + "  }\n"
                + "}");
        assertThat(event.getRepository(), notNullValue());
        assertThat(event.getRepository().getScm(), is("git"));
        assertThat(event.getRepository().getFullName(), is("cloudbeers/temp"));
        assertThat(event.getRepository().getOwner().getDisplayName(), is("cloudbeers"));
        assertThat(event.getRepository().getOwner().getUsername(), is("cloudbeers"));
        assertThat(event.getRepository().getRepositoryName(), is("temp"));
        assertThat(event.getRepository().isPrivate(), is(true));
        assertThat(event.getRepository().getLinks(), notNullValue());
        assertThat(event.getRepository().getLinks().get("self"), notNullValue());
        assertThat(event.getRepository().getLinks().get("self").getHref(),
                is("https://api.bitbucket.org/2.0/repositories/cloudbeers/temp"));

        assertThat(event.getPullRequest(), notNullValue());
        assertThat(event.getPullRequest().getTitle(), is("Forking for PR"));
        assertThat(event.getPullRequest().getAuthorLogin(), is("stephenc"));
        assertThat(event.getPullRequest().getLink(),
                is("https://bitbucket.org/cloudbeers/temp/pull-requests/3"));

        assertThat(event.getPullRequest().getDestination(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository().getScm(), is("git"));
        assertThat(event.getPullRequest().getDestination().getRepository().getFullName(), is("cloudbeers/temp"));
        assertThat(event.getPullRequest().getDestination().getRepository().getOwner().getDisplayName(),
                is("cloudbeers"));
        assertThat(event.getPullRequest().getDestination().getRepository().getOwner().getUsername(), is("cloudbeers"));
        assertThat(event.getPullRequest().getDestination().getRepository().getRepositoryName(), is("temp"));
        assertThat(event.getPullRequest().getDestination().getRepository().isPrivate(), is(true));
        assertThat(event.getPullRequest().getDestination().getRepository().getLinks(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository().getLinks().get("self"), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository().getLinks().get("self").getHref(),
                is("https://api.bitbucket.org/2.0/repositories/cloudbeers/temp"));
        assertThat(event.getPullRequest().getDestination().getBranch(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getBranch().getName(), is("master"));
        assertThat(event.getPullRequest().getDestination().getBranch().getRawNode(),
                anyOf(is("1986c2284946"), is("1986c228494671574242f99b62d1a00a4bfb69a5")));
        assertThat(event.getPullRequest().getDestination().getCommit(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getCommit().getHash(),
                anyOf(is("1986c2284946"), is("1986c228494671574242f99b62d1a00a4bfb69a5")));

        assertThat(event.getPullRequest().getSource(), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository(), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository().getScm(), is("git"));
        assertThat(event.getPullRequest().getSource().getRepository().getFullName(), is("stephenc/temp-fork"));
        assertThat(event.getPullRequest().getSource().getRepository().getOwner().getDisplayName(), is("Stephen Connolly"));
        assertThat(event.getPullRequest().getSource().getRepository().getOwner().getUsername(), is("stephenc"));
        assertThat(event.getPullRequest().getSource().getRepository().getRepositoryName(), is("temp-fork"));
        assertThat(event.getPullRequest().getSource().getRepository().isPrivate(), is(true));
        assertThat(event.getPullRequest().getSource().getRepository().getLinks(), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository().getLinks().get("self"), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository().getLinks().get("self").getHref(),
                is("https://api.bitbucket.org/2.0/repositories/stephenc/temp-fork"));

        assertThat(event.getPullRequest().getSource().getBranch(), notNullValue());
        assertThat(event.getPullRequest().getSource().getBranch().getName(), is("master"));
        assertThat(event.getPullRequest().getSource().getBranch().getRawNode(),
                anyOf(is("1c48041a96db"), is("1c48041a96db4c98620609260c21ff5fbc9640c2")));
        assertThat(event.getPullRequest().getSource().getCommit(), notNullValue());
        assertThat(event.getPullRequest().getSource().getCommit().getHash(),
                anyOf(is("1c48041a96db"), is("1c48041a96db4c98620609260c21ff5fbc9640c2")));
    }

    @Test
    public void updatePayload_newCommit() throws Exception {
        BitbucketPullRequestEvent event = BitbucketCloudWebhookPayload.pullRequestEventFromPayload("{\n"
                + "  \"pullrequest\": {\n"
                + "    \"type\": \"pullrequest\",\n"
                + "    \"description\": \"description of PR\",\n"
                + "    \"links\": {\n"
                + "      \"decline\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/decline\"\n"
                + "      },\n"
                + "      \"commits\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/commits\"\n"
                + "      },\n"
                + "      \"self\": {\n"
                + "        \"href\": \"https://api.bitbucket.org/2.0/repositories/cloudbeers/temp/pullrequests/3\"\n"
                + "      },\n"
                + "      \"comments\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/comments\"\n"
                + "      },\n"
                + "      \"merge\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/merge\"\n"
                + "      },\n"
                + "      \"html\": {\n"
                + "        \"href\": \"https://bitbucket.org/cloudbeers/temp/pull-requests/3\"\n"
                + "      },\n"
                + "      \"activity\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/activity\"\n"
                + "      },\n"
                + "      \"diff\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/diff\"\n"
                + "      },\n"
                + "      \"approve\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/approve\"\n"
                + "      },\n"
                + "      \"statuses\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/statuses\"\n"
                + "      }\n"
                + "    },\n"
                + "    \"author\": {\n"
                + "      \"username\": \"stephenc\",\n"
                + "      \"display_name\": \"Stephen Connolly\",\n"
                + "      \"type\": \"user\",\n"
                + "      \"uuid\": \"{f70f195e-ade1-4961-9f89-547541377b80}\",\n"
                + "      \"links\": {\n"
                + "        \"self\": {\n"
                + "          \"href\": \"https://api.bitbucket.org/2.0/users/stephenc\"\n"
                + "        },\n"
                + "        \"html\": {\n"
                + "          \"href\": \"https://bitbucket.org/stephenc/\"\n"
                + "        },\n"
                + "        \"avatar\": {\n"
                + "          \"href\": \"https://bitbucket.org/account/stephenc/avatar/32/\"\n"
                + "        }\n"
                + "      }\n"
                + "    },\n"
                + "    \"close_source_branch\": false,\n"
                + "    \"reviewers\": [],\n"
                + "    \"destination\": {\n"
                + "      \"commit\": {\n"
                + "        \"hash\": \"1986c2284946\",\n"
                + "        \"links\": {\n"
                + "          \"self\": {\n"
                + "            \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/commit/1986c2284946\"\n"
                + "          }\n"
                + "        }\n"
                + "      },\n"
                + "      \"branch\": {\n"
                + "        \"name\": \"master\"\n"
                + "      },\n"
                + "      \"repository\": {\n"
                + "        \"links\": {\n"
                + "          \"self\": {\n"
                + "            \"href\": \"https://api.bitbucket.org/2.0/repositories/cloudbeers/temp\"\n"
                + "          },\n"
                + "          \"html\": {\n"
                + "            \"href\": \"https://bitbucket.org/cloudbeers/temp\"\n"
                + "          },\n"
                + "          \"avatar\": {\n"
                + "            \"href\": \"https://bitbucket.org/cloudbeers/temp/avatar/32/\"\n"
                + "          }\n"
                + "        },\n"
                + "        \"type\": \"repository\",\n"
                + "        \"uuid\": \"{708c9715-0ecc-4a5d-87ed-63c4ba48ea06}\",\n"
                + "        \"full_name\": \"cloudbeers/temp\",\n"
                + "        \"name\": \"temp\"\n"
                + "      }\n"
                + "    },\n"
                + "    \"comment_count\": 0,\n"
                + "    \"updated_on\": \"2017-03-06T11:57:30.649819+00:00\",\n"
                + "    \"source\": {\n"
                + "      \"commit\": {\n"
                + "        \"hash\": \"63e3d18dca4c\",\n"
                + "        \"links\": {\n"
                + "          \"self\": {\n"
                + "            \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/stephenc/temp-fork/commit/63e3d18dca4c\"\n"
                + "          }\n"
                + "        }\n"
                + "      },\n"
                + "      \"branch\": {\n"
                + "        \"name\": \"master\"\n"
                + "      },\n"
                + "      \"repository\": {\n"
                + "        \"links\": {\n"
                + "          \"self\": {\n"
                + "            \"href\": \"https://api.bitbucket.org/2.0/repositories/stephenc/temp-fork\"\n"
                + "          },\n"
                + "          \"html\": {\n"
                + "            \"href\": \"https://bitbucket.org/stephenc/temp-fork\"\n"
                + "          },\n"
                + "          \"avatar\": {\n"
                + "            \"href\": \"https://bitbucket.org/stephenc/temp-fork/avatar/32/\"\n"
                + "          }\n"
                + "        },\n"
                + "        \"type\": \"repository\",\n"
                + "        \"uuid\": \"{f58c57d2-28b0-44f4-8c30-ea83e4825381}\",\n"
                + "        \"full_name\": \"stephenc/temp-fork\",\n"
                + "        \"name\": \"temp-fork\"\n"
                + "      }\n"
                + "    },\n"
                + "    \"reason\": \"\",\n"
                + "    \"state\": \"OPEN\",\n"
                + "    \"task_count\": 0,\n"
                + "    \"created_on\": \"2017-03-06T11:52:20.092358+00:00\",\n"
                + "    \"participants\": [],\n"
                + "    \"id\": 3,\n"
                + "    \"title\": \"Forking for PR\",\n"
                + "    \"merge_commit\": null,\n"
                + "    \"closed_by\": null\n"
                + "  },\n"
                + "  \"actor\": {\n"
                + "    \"username\": \"stephenc\",\n"
                + "    \"display_name\": \"Stephen Connolly\",\n"
                + "    \"type\": \"user\",\n"
                + "    \"uuid\": \"{f70f195e-ade1-4961-9f89-547541377b80}\",\n"
                + "    \"links\": {\n"
                + "      \"self\": {\n"
                + "        \"href\": \"https://api.bitbucket.org/2.0/users/stephenc\"\n"
                + "      },\n"
                + "      \"html\": {\n"
                + "        \"href\": \"https://bitbucket.org/stephenc/\"\n"
                + "      },\n"
                + "      \"avatar\": {\n"
                + "        \"href\": \"https://bitbucket.org/account/stephenc/avatar/32/\"\n"
                + "      }\n"
                + "    }\n"
                + "  },\n"
                + "  \"repository\": {\n"
                + "    \"website\": \"\",\n"
                + "    \"scm\": \"git\",\n"
                + "    \"name\": \"temp\",\n"
                + "    \"links\": {\n"
                + "      \"self\": {\n"
                + "        \"href\": \"https://api.bitbucket.org/2.0/repositories/cloudbeers/temp\"\n"
                + "      },\n"
                + "      \"html\": {\n"
                + "        \"href\": \"https://bitbucket.org/cloudbeers/temp\"\n"
                + "      },\n"
                + "      \"avatar\": {\n"
                + "        \"href\": \"https://bitbucket.org/cloudbeers/temp/avatar/32/\"\n"
                + "      }\n"
                + "    },\n"
                + "    \"project\": {\n"
                + "      \"links\": {\n"
                + "        \"self\": {\n"
                + "          \"href\": \"https://api.bitbucket.org/2.0/teams/cloudbeers/projects/DUB\"\n"
                + "        },\n"
                + "        \"html\": {\n"
                + "          \"href\": \"https://bitbucket.org/account/user/cloudbeers/projects/DUB\"\n"
                + "        },\n"
                + "        \"avatar\": {\n"
                + "          \"href\": \"https://bitbucket.org/account/user/cloudbeers/projects/DUB/avatar/32\"\n"
                + "        }\n"
                + "      },\n"
                + "      \"type\": \"project\",\n"
                + "      \"uuid\": \"{7f08415d-57d0-4172-8978-059345fa0369}\",\n"
                + "      \"key\": \"DUB\",\n"
                + "      \"name\": \"dublin\"\n"
                + "    },\n"
                + "    \"full_name\": \"cloudbeers/temp\",\n"
                + "    \"owner\": {\n"
                + "      \"username\": \"cloudbeers\",\n"
                + "      \"display_name\": \"cloudbeers\",\n"
                + "      \"type\": \"team\",\n"
                + "      \"uuid\": \"{5e429024-1f85-425f-8955-8af0c35d1b41}\",\n"
                + "      \"links\": {\n"
                + "        \"self\": {\n"
                + "          \"href\": \"https://api.bitbucket.org/2.0/teams/cloudbeers\"\n"
                + "        },\n"
                + "        \"html\": {\n"
                + "          \"href\": \"https://bitbucket.org/cloudbeers/\"\n"
                + "        },\n"
                + "        \"avatar\": {\n"
                + "          \"href\": \"https://bitbucket.org/account/cloudbeers/avatar/32/\"\n"
                + "        }\n"
                + "      }\n"
                + "    },\n"
                + "    \"type\": \"repository\",\n"
                + "    \"is_private\": true,\n"
                + "    \"uuid\": \"{708c9715-0ecc-4a5d-87ed-63c4ba48ea06}\"\n"
                + "  }\n"
                + "}");
        assertThat(event.getRepository(), notNullValue());
        assertThat(event.getRepository().getScm(), is("git"));
        assertThat(event.getRepository().getFullName(), is("cloudbeers/temp"));
        assertThat(event.getRepository().getOwner().getDisplayName(), is("cloudbeers"));
        assertThat(event.getRepository().getOwner().getUsername(), is("cloudbeers"));
        assertThat(event.getRepository().getRepositoryName(), is("temp"));
        assertThat(event.getRepository().isPrivate(), is(true));
        assertThat(event.getRepository().getLinks(), notNullValue());
        assertThat(event.getRepository().getLinks().get("self"), notNullValue());
        assertThat(event.getRepository().getLinks().get("self").getHref(),
                is("https://api.bitbucket.org/2.0/repositories/cloudbeers/temp"));

        assertThat(event.getPullRequest(), notNullValue());
        assertThat(event.getPullRequest().getTitle(), is("Forking for PR"));
        assertThat(event.getPullRequest().getAuthorLogin(), is("stephenc"));
        assertThat(event.getPullRequest().getLink(),
                is("https://bitbucket.org/cloudbeers/temp/pull-requests/3"));

        assertThat(event.getPullRequest().getDestination(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository().getScm(), is("git"));
        assertThat(event.getPullRequest().getDestination().getRepository().getFullName(), is("cloudbeers/temp"));
        assertThat(event.getPullRequest().getDestination().getRepository().getOwner().getDisplayName(),
                is("cloudbeers"));
        assertThat(event.getPullRequest().getDestination().getRepository().getOwner().getUsername(), is("cloudbeers"));
        assertThat(event.getPullRequest().getDestination().getRepository().getRepositoryName(), is("temp"));
        assertThat(event.getPullRequest().getDestination().getRepository().isPrivate(), is(true));
        assertThat(event.getPullRequest().getDestination().getRepository().getLinks(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository().getLinks().get("self"), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository().getLinks().get("self").getHref(),
                is("https://api.bitbucket.org/2.0/repositories/cloudbeers/temp"));
        assertThat(event.getPullRequest().getDestination().getBranch(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getBranch().getName(), is("master"));
        assertThat(event.getPullRequest().getDestination().getBranch().getRawNode(),
                anyOf(is("1986c2284946"), is("1986c228494671574242f99b62d1a00a4bfb69a5")));
        assertThat(event.getPullRequest().getDestination().getCommit(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getCommit().getHash(),
                anyOf(is("1986c2284946"), is("1986c228494671574242f99b62d1a00a4bfb69a5")));

        assertThat(event.getPullRequest().getSource(), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository(), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository().getScm(), is("git"));
        assertThat(event.getPullRequest().getSource().getRepository().getFullName(), is("stephenc/temp-fork"));
        assertThat(event.getPullRequest().getSource().getRepository().getOwner().getDisplayName(), is("Stephen Connolly"));
        assertThat(event.getPullRequest().getSource().getRepository().getOwner().getUsername(), is("stephenc"));
        assertThat(event.getPullRequest().getSource().getRepository().getRepositoryName(), is("temp-fork"));
        assertThat(event.getPullRequest().getSource().getRepository().isPrivate(), is(true));
        assertThat(event.getPullRequest().getSource().getRepository().getLinks(), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository().getLinks().get("self"), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository().getLinks().get("self").getHref(),
                is("https://api.bitbucket.org/2.0/repositories/stephenc/temp-fork"));

        assertThat(event.getPullRequest().getSource().getBranch(), notNullValue());
        assertThat(event.getPullRequest().getSource().getBranch().getName(), is("master"));
        assertThat(event.getPullRequest().getSource().getBranch().getRawNode(),
                anyOf(is("63e3d18dca4c"), is("63e3d18dca4c61e6b9e31eb6036802c7730fa2b3")));
        assertThat(event.getPullRequest().getSource().getCommit(), notNullValue());
        assertThat(event.getPullRequest().getSource().getCommit().getHash(),
                anyOf(is("63e3d18dca4c"), is("63e3d18dca4c61e6b9e31eb6036802c7730fa2b3")));
    }

    @Test
    public void updatePayload_newDestination() throws Exception {
        BitbucketPullRequestEvent event = BitbucketCloudWebhookPayload.pullRequestEventFromPayload("{\n"
                + "  \"pullrequest\": {\n"
                + "    \"merge_commit\": null,\n"
                + "    \"description\": \"description of PR\",\n"
                + "    \"links\": {\n"
                + "      \"decline\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/decline\"\n"
                + "      },\n"
                + "      \"commits\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/commits\"\n"
                + "      },\n"
                + "      \"self\": {\n"
                + "        \"href\": \"https://api.bitbucket.org/2.0/repositories/cloudbeers/temp/pullrequests/3\"\n"
                + "      },\n"
                + "      \"comments\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/comments\"\n"
                + "      },\n"
                + "      \"merge\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/merge\"\n"
                + "      },\n"
                + "      \"html\": {\n"
                + "        \"href\": \"https://bitbucket.org/cloudbeers/temp/pull-requests/3\"\n"
                + "      },\n"
                + "      \"activity\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/activity\"\n"
                + "      },\n"
                + "      \"diff\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/diff\"\n"
                + "      },\n"
                + "      \"approve\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/approve\"\n"
                + "      },\n"
                + "      \"statuses\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/statuses\"\n"
                + "      }\n"
                + "    },\n"
                + "    \"title\": \"Forking for PR\",\n"
                + "    \"close_source_branch\": false,\n"
                + "    \"reviewers\": [],\n"
                + "    \"destination\": {\n"
                + "      \"commit\": {\n"
                + "        \"hash\": \"1986c2284946\",\n"
                + "        \"links\": {\n"
                + "          \"self\": {\n"
                + "            \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/commit/1986c2284946\"\n"
                + "          }\n"
                + "        }\n"
                + "      },\n"
                + "      \"branch\": {\n"
                + "        \"name\": \"stable\"\n"
                + "      },\n"
                + "      \"repository\": {\n"
                + "        \"links\": {\n"
                + "          \"self\": {\n"
                + "            \"href\": \"https://api.bitbucket.org/2.0/repositories/cloudbeers/temp\"\n"
                + "          },\n"
                + "          \"html\": {\n"
                + "            \"href\": \"https://bitbucket.org/cloudbeers/temp\"\n"
                + "          },\n"
                + "          \"avatar\": {\n"
                + "            \"href\": \"https://bitbucket.org/cloudbeers/temp/avatar/32/\"\n"
                + "          }\n"
                + "        },\n"
                + "        \"type\": \"repository\",\n"
                + "        \"uuid\": \"{708c9715-0ecc-4a5d-87ed-63c4ba48ea06}\",\n"
                + "        \"full_name\": \"cloudbeers/temp\",\n"
                + "        \"name\": \"temp\"\n"
                + "      }\n"
                + "    },\n"
                + "    \"comment_count\": 0,\n"
                + "    \"closed_by\": null,\n"
                + "    \"source\": {\n"
                + "      \"commit\": {\n"
                + "        \"hash\": \"63e3d18dca4c\",\n"
                + "        \"links\": {\n"
                + "          \"self\": {\n"
                + "            \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/stephenc/temp-fork/commit/63e3d18dca4c\"\n"
                + "          }\n"
                + "        }\n"
                + "      },\n"
                + "      \"branch\": {\n"
                + "        \"name\": \"master\"\n"
                + "      },\n"
                + "      \"repository\": {\n"
                + "        \"links\": {\n"
                + "          \"self\": {\n"
                + "            \"href\": \"https://api.bitbucket.org/2.0/repositories/stephenc/temp-fork\"\n"
                + "          },\n"
                + "          \"html\": {\n"
                + "            \"href\": \"https://bitbucket.org/stephenc/temp-fork\"\n"
                + "          },\n"
                + "          \"avatar\": {\n"
                + "            \"href\": \"https://bitbucket.org/stephenc/temp-fork/avatar/32/\"\n"
                + "          }\n"
                + "        },\n"
                + "        \"type\": \"repository\",\n"
                + "        \"uuid\": \"{f58c57d2-28b0-44f4-8c30-ea83e4825381}\",\n"
                + "        \"full_name\": \"stephenc/temp-fork\",\n"
                + "        \"name\": \"temp-fork\"\n"
                + "      }\n"
                + "    },\n"
                + "    \"participants\": [],\n"
                + "    \"state\": \"OPEN\",\n"
                + "    \"task_count\": 0,\n"
                + "    \"created_on\": \"2017-03-06T11:52:20.092358+00:00\",\n"
                + "    \"reason\": \"\",\n"
                + "    \"updated_on\": \"2017-03-06T11:59:39.916949+00:00\",\n"
                + "    \"author\": {\n"
                + "      \"username\": \"stephenc\",\n"
                + "      \"display_name\": \"Stephen Connolly\",\n"
                + "      \"type\": \"user\",\n"
                + "      \"uuid\": \"{f70f195e-ade1-4961-9f89-547541377b80}\",\n"
                + "      \"links\": {\n"
                + "        \"self\": {\n"
                + "          \"href\": \"https://api.bitbucket.org/2.0/users/stephenc\"\n"
                + "        },\n"
                + "        \"html\": {\n"
                + "          \"href\": \"https://bitbucket.org/stephenc/\"\n"
                + "        },\n"
                + "        \"avatar\": {\n"
                + "          \"href\": \"https://bitbucket.org/account/stephenc/avatar/32/\"\n"
                + "        }\n"
                + "      }\n"
                + "    },\n"
                + "    \"type\": \"pullrequest\",\n"
                + "    \"id\": 3\n"
                + "  },\n"
                + "  \"actor\": {\n"
                + "    \"username\": \"stephenc\",\n"
                + "    \"display_name\": \"Stephen Connolly\",\n"
                + "    \"type\": \"user\",\n"
                + "    \"uuid\": \"{f70f195e-ade1-4961-9f89-547541377b80}\",\n"
                + "    \"links\": {\n"
                + "      \"self\": {\n"
                + "        \"href\": \"https://api.bitbucket.org/2.0/users/stephenc\"\n"
                + "      },\n"
                + "      \"html\": {\n"
                + "        \"href\": \"https://bitbucket.org/stephenc/\"\n"
                + "      },\n"
                + "      \"avatar\": {\n"
                + "        \"href\": \"https://bitbucket.org/account/stephenc/avatar/32/\"\n"
                + "      }\n"
                + "    }\n"
                + "  },\n"
                + "  \"repository\": {\n"
                + "    \"website\": \"\",\n"
                + "    \"scm\": \"git\",\n"
                + "    \"name\": \"temp\",\n"
                + "    \"links\": {\n"
                + "      \"self\": {\n"
                + "        \"href\": \"https://api.bitbucket.org/2.0/repositories/cloudbeers/temp\"\n"
                + "      },\n"
                + "      \"html\": {\n"
                + "        \"href\": \"https://bitbucket.org/cloudbeers/temp\"\n"
                + "      },\n"
                + "      \"avatar\": {\n"
                + "        \"href\": \"https://bitbucket.org/cloudbeers/temp/avatar/32/\"\n"
                + "      }\n"
                + "    },\n"
                + "    \"project\": {\n"
                + "      \"key\": \"DUB\",\n"
                + "      \"type\": \"project\",\n"
                + "      \"name\": \"dublin\",\n"
                + "      \"links\": {\n"
                + "        \"self\": {\n"
                + "          \"href\": \"https://api.bitbucket.org/2.0/teams/cloudbeers/projects/DUB\"\n"
                + "        },\n"
                + "        \"html\": {\n"
                + "          \"href\": \"https://bitbucket.org/account/user/cloudbeers/projects/DUB\"\n"
                + "        },\n"
                + "        \"avatar\": {\n"
                + "          \"href\": \"https://bitbucket.org/account/user/cloudbeers/projects/DUB/avatar/32\"\n"
                + "        }\n"
                + "      },\n"
                + "      \"uuid\": \"{7f08415d-57d0-4172-8978-059345fa0369}\"\n"
                + "    },\n"
                + "    \"full_name\": \"cloudbeers/temp\",\n"
                + "    \"owner\": {\n"
                + "      \"username\": \"cloudbeers\",\n"
                + "      \"display_name\": \"cloudbeers\",\n"
                + "      \"type\": \"team\",\n"
                + "      \"uuid\": \"{5e429024-1f85-425f-8955-8af0c35d1b41}\",\n"
                + "      \"links\": {\n"
                + "        \"self\": {\n"
                + "          \"href\": \"https://api.bitbucket.org/2.0/teams/cloudbeers\"\n"
                + "        },\n"
                + "        \"html\": {\n"
                + "          \"href\": \"https://bitbucket.org/cloudbeers/\"\n"
                + "        },\n"
                + "        \"avatar\": {\n"
                + "          \"href\": \"https://bitbucket.org/account/cloudbeers/avatar/32/\"\n"
                + "        }\n"
                + "      }\n"
                + "    },\n"
                + "    \"type\": \"repository\",\n"
                + "    \"is_private\": true,\n"
                + "    \"uuid\": \"{708c9715-0ecc-4a5d-87ed-63c4ba48ea06}\"\n"
                + "  }\n"
                + "}");
        assertThat(event.getRepository(), notNullValue());
        assertThat(event.getRepository().getScm(), is("git"));
        assertThat(event.getRepository().getFullName(), is("cloudbeers/temp"));
        assertThat(event.getRepository().getOwner().getDisplayName(), is("cloudbeers"));
        assertThat(event.getRepository().getOwner().getUsername(), is("cloudbeers"));
        assertThat(event.getRepository().getRepositoryName(), is("temp"));
        assertThat(event.getRepository().isPrivate(), is(true));
        assertThat(event.getRepository().getLinks(), notNullValue());
        assertThat(event.getRepository().getLinks().get("self"), notNullValue());
        assertThat(event.getRepository().getLinks().get("self").getHref(),
                is("https://api.bitbucket.org/2.0/repositories/cloudbeers/temp"));

        assertThat(event.getPullRequest(), notNullValue());
        assertThat(event.getPullRequest().getTitle(), is("Forking for PR"));
        assertThat(event.getPullRequest().getAuthorLogin(), is("stephenc"));
        assertThat(event.getPullRequest().getLink(),
                is("https://bitbucket.org/cloudbeers/temp/pull-requests/3"));

        assertThat(event.getPullRequest().getDestination(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository().getScm(), is("git"));
        assertThat(event.getPullRequest().getDestination().getRepository().getFullName(), is("cloudbeers/temp"));
        assertThat(event.getPullRequest().getDestination().getRepository().getOwner().getDisplayName(),
                is("cloudbeers"));
        assertThat(event.getPullRequest().getDestination().getRepository().getOwner().getUsername(), is("cloudbeers"));
        assertThat(event.getPullRequest().getDestination().getRepository().getRepositoryName(), is("temp"));
        assertThat(event.getPullRequest().getDestination().getRepository().isPrivate(), is(true));
        assertThat(event.getPullRequest().getDestination().getRepository().getLinks(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository().getLinks().get("self"), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository().getLinks().get("self").getHref(),
                is("https://api.bitbucket.org/2.0/repositories/cloudbeers/temp"));
        assertThat(event.getPullRequest().getDestination().getBranch(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getBranch().getName(), is("stable"));
        assertThat(event.getPullRequest().getDestination().getBranch().getRawNode(),
                anyOf(is("1986c2284946"), is("1986c228494671574242f99b62d1a00a4bfb69a5")));
        assertThat(event.getPullRequest().getDestination().getCommit(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getCommit().getHash(),
                anyOf(is("1986c2284946"), is("1986c228494671574242f99b62d1a00a4bfb69a5")));

        assertThat(event.getPullRequest().getSource(), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository(), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository().getScm(), is("git"));
        assertThat(event.getPullRequest().getSource().getRepository().getFullName(), is("stephenc/temp-fork"));
        assertThat(event.getPullRequest().getSource().getRepository().getOwner().getDisplayName(), is("Stephen Connolly"));
        assertThat(event.getPullRequest().getSource().getRepository().getOwner().getUsername(), is("stephenc"));
        assertThat(event.getPullRequest().getSource().getRepository().getRepositoryName(), is("temp-fork"));
        assertThat(event.getPullRequest().getSource().getRepository().isPrivate(), is(true));
        assertThat(event.getPullRequest().getSource().getRepository().getLinks(), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository().getLinks().get("self"), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository().getLinks().get("self").getHref(),
                is("https://api.bitbucket.org/2.0/repositories/stephenc/temp-fork"));

        assertThat(event.getPullRequest().getSource().getBranch(), notNullValue());
        assertThat(event.getPullRequest().getSource().getBranch().getName(), is("master"));
        assertThat(event.getPullRequest().getSource().getBranch().getRawNode(),
                anyOf(is("63e3d18dca4c"), is("63e3d18dca4c61e6b9e31eb6036802c7730fa2b3")));
        assertThat(event.getPullRequest().getSource().getCommit(), notNullValue());
        assertThat(event.getPullRequest().getSource().getCommit().getHash(),
                anyOf(is("63e3d18dca4c"), is("63e3d18dca4c61e6b9e31eb6036802c7730fa2b3")));
    }

    @Test
    public void updatePayload_newDestinationCommit() throws Exception {
        BitbucketPullRequestEvent event = BitbucketCloudWebhookPayload.pullRequestEventFromPayload("{\n"
                + "  \"pullrequest\": {\n"
                + "    \"type\": \"pullrequest\",\n"
                + "    \"description\": \"description of PR\",\n"
                + "    \"links\": {\n"
                + "      \"decline\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/decline\"\n"
                + "      },\n"
                + "      \"commits\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/commits\"\n"
                + "      },\n"
                + "      \"self\": {\n"
                + "        \"href\": \"https://api.bitbucket.org/2.0/repositories/cloudbeers/temp/pullrequests/3\"\n"
                + "      },\n"
                + "      \"comments\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/comments\"\n"
                + "      },\n"
                + "      \"merge\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/merge\"\n"
                + "      },\n"
                + "      \"html\": {\n"
                + "        \"href\": \"https://bitbucket.org/cloudbeers/temp/pull-requests/3\"\n"
                + "      },\n"
                + "      \"activity\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/activity\"\n"
                + "      },\n"
                + "      \"diff\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/diff\"\n"
                + "      },\n"
                + "      \"approve\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/approve\"\n"
                + "      },\n"
                + "      \"statuses\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/statuses\"\n"
                + "      }\n"
                + "    },\n"
                + "    \"title\": \"Forking for PR\",\n"
                + "    \"close_source_branch\": false,\n"
                + "    \"reviewers\": [],\n"
                + "    \"destination\": {\n"
                + "      \"commit\": {\n"
                + "        \"hash\": \"5449b752db4f\",\n"
                + "        \"links\": {\n"
                + "          \"self\": {\n"
                + "            \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/commit/5449b752db4f\"\n"
                + "          }\n"
                + "        }\n"
                + "      },\n"
                + "      \"branch\": {\n"
                + "        \"name\": \"master\"\n"
                + "      },\n"
                + "      \"repository\": {\n"
                + "        \"full_name\": \"cloudbeers/temp\",\n"
                + "        \"type\": \"repository\",\n"
                + "        \"name\": \"temp\",\n"
                + "        \"links\": {\n"
                + "          \"self\": {\n"
                + "            \"href\": \"https://api.bitbucket.org/2.0/repositories/cloudbeers/temp\"\n"
                + "          },\n"
                + "          \"html\": {\n"
                + "            \"href\": \"https://bitbucket.org/cloudbeers/temp\"\n"
                + "          },\n"
                + "          \"avatar\": {\n"
                + "            \"href\": \"https://bitbucket.org/cloudbeers/temp/avatar/32/\"\n"
                + "          }\n"
                + "        },\n"
                + "        \"uuid\": \"{708c9715-0ecc-4a5d-87ed-63c4ba48ea06}\"\n"
                + "      }\n"
                + "    },\n"
                + "    \"created_on\": \"2017-03-06T11:52:20.092358+00:00\",\n"
                + "    \"closed_by\": null,\n"
                + "    \"source\": {\n"
                + "      \"commit\": {\n"
                + "        \"hash\": \"63e3d18dca4c\",\n"
                + "        \"links\": {\n"
                + "          \"self\": {\n"
                + "            \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/stephenc/temp-fork/commit/63e3d18dca4c\"\n"
                + "          }\n"
                + "        }\n"
                + "      },\n"
                + "      \"branch\": {\n"
                + "        \"name\": \"master\"\n"
                + "      },\n"
                + "      \"repository\": {\n"
                + "        \"full_name\": \"stephenc/temp-fork\",\n"
                + "        \"type\": \"repository\",\n"
                + "        \"name\": \"temp-fork\",\n"
                + "        \"links\": {\n"
                + "          \"self\": {\n"
                + "            \"href\": \"https://api.bitbucket.org/2.0/repositories/stephenc/temp-fork\"\n"
                + "          },\n"
                + "          \"html\": {\n"
                + "            \"href\": \"https://bitbucket.org/stephenc/temp-fork\"\n"
                + "          },\n"
                + "          \"avatar\": {\n"
                + "            \"href\": \"https://bitbucket.org/stephenc/temp-fork/avatar/32/\"\n"
                + "          }\n"
                + "        },\n"
                + "        \"uuid\": \"{f58c57d2-28b0-44f4-8c30-ea83e4825381}\"\n"
                + "      }\n"
                + "    },\n"
                + "    \"state\": \"OPEN\",\n"
                + "    \"comment_count\": 0,\n"
                + "    \"task_count\": 0,\n"
                + "    \"participants\": [],\n"
                + "    \"reason\": \"\",\n"
                + "    \"updated_on\": \"2017-03-06T12:02:59.640844+00:00\",\n"
                + "    \"author\": {\n"
                + "      \"username\": \"stephenc\",\n"
                + "      \"display_name\": \"Stephen Connolly\",\n"
                + "      \"type\": \"user\",\n"
                + "      \"uuid\": \"{f70f195e-ade1-4961-9f89-547541377b80}\",\n"
                + "      \"links\": {\n"
                + "        \"self\": {\n"
                + "          \"href\": \"https://api.bitbucket.org/2.0/users/stephenc\"\n"
                + "        },\n"
                + "        \"html\": {\n"
                + "          \"href\": \"https://bitbucket.org/stephenc/\"\n"
                + "        },\n"
                + "        \"avatar\": {\n"
                + "          \"href\": \"https://bitbucket.org/account/stephenc/avatar/32/\"\n"
                + "        }\n"
                + "      }\n"
                + "    },\n"
                + "    \"merge_commit\": null,\n"
                + "    \"id\": 3\n"
                + "  },\n"
                + "  \"actor\": {\n"
                + "    \"username\": \"stephenc\",\n"
                + "    \"display_name\": \"Stephen Connolly\",\n"
                + "    \"type\": \"user\",\n"
                + "    \"uuid\": \"{f70f195e-ade1-4961-9f89-547541377b80}\",\n"
                + "    \"links\": {\n"
                + "      \"self\": {\n"
                + "        \"href\": \"https://api.bitbucket.org/2.0/users/stephenc\"\n"
                + "      },\n"
                + "      \"html\": {\n"
                + "        \"href\": \"https://bitbucket.org/stephenc/\"\n"
                + "      },\n"
                + "      \"avatar\": {\n"
                + "        \"href\": \"https://bitbucket.org/account/stephenc/avatar/32/\"\n"
                + "      }\n"
                + "    }\n"
                + "  },\n"
                + "  \"repository\": {\n"
                + "    \"website\": \"\",\n"
                + "    \"scm\": \"git\",\n"
                + "    \"name\": \"temp\",\n"
                + "    \"links\": {\n"
                + "      \"self\": {\n"
                + "        \"href\": \"https://api.bitbucket.org/2.0/repositories/cloudbeers/temp\"\n"
                + "      },\n"
                + "      \"html\": {\n"
                + "        \"href\": \"https://bitbucket.org/cloudbeers/temp\"\n"
                + "      },\n"
                + "      \"avatar\": {\n"
                + "        \"href\": \"https://bitbucket.org/cloudbeers/temp/avatar/32/\"\n"
                + "      }\n"
                + "    },\n"
                + "    \"project\": {\n"
                + "      \"key\": \"DUB\",\n"
                + "      \"type\": \"project\",\n"
                + "      \"name\": \"dublin\",\n"
                + "      \"links\": {\n"
                + "        \"self\": {\n"
                + "          \"href\": \"https://api.bitbucket.org/2.0/teams/cloudbeers/projects/DUB\"\n"
                + "        },\n"
                + "        \"html\": {\n"
                + "          \"href\": \"https://bitbucket.org/account/user/cloudbeers/projects/DUB\"\n"
                + "        },\n"
                + "        \"avatar\": {\n"
                + "          \"href\": \"https://bitbucket.org/account/user/cloudbeers/projects/DUB/avatar/32\"\n"
                + "        }\n"
                + "      },\n"
                + "      \"uuid\": \"{7f08415d-57d0-4172-8978-059345fa0369}\"\n"
                + "    },\n"
                + "    \"full_name\": \"cloudbeers/temp\",\n"
                + "    \"owner\": {\n"
                + "      \"username\": \"cloudbeers\",\n"
                + "      \"display_name\": \"cloudbeers\",\n"
                + "      \"type\": \"team\",\n"
                + "      \"uuid\": \"{5e429024-1f85-425f-8955-8af0c35d1b41}\",\n"
                + "      \"links\": {\n"
                + "        \"self\": {\n"
                + "          \"href\": \"https://api.bitbucket.org/2.0/teams/cloudbeers\"\n"
                + "        },\n"
                + "        \"html\": {\n"
                + "          \"href\": \"https://bitbucket.org/cloudbeers/\"\n"
                + "        },\n"
                + "        \"avatar\": {\n"
                + "          \"href\": \"https://bitbucket.org/account/cloudbeers/avatar/32/\"\n"
                + "        }\n"
                + "      }\n"
                + "    },\n"
                + "    \"type\": \"repository\",\n"
                + "    \"is_private\": true,\n"
                + "    \"uuid\": \"{708c9715-0ecc-4a5d-87ed-63c4ba48ea06}\"\n"
                + "  }\n"
                + "}");
        assertThat(event.getRepository(), notNullValue());
        assertThat(event.getRepository().getScm(), is("git"));
        assertThat(event.getRepository().getFullName(), is("cloudbeers/temp"));
        assertThat(event.getRepository().getOwner().getDisplayName(), is("cloudbeers"));
        assertThat(event.getRepository().getOwner().getUsername(), is("cloudbeers"));
        assertThat(event.getRepository().getRepositoryName(), is("temp"));
        assertThat(event.getRepository().isPrivate(), is(true));
        assertThat(event.getRepository().getLinks(), notNullValue());
        assertThat(event.getRepository().getLinks().get("self"), notNullValue());
        assertThat(event.getRepository().getLinks().get("self").getHref(),
                is("https://api.bitbucket.org/2.0/repositories/cloudbeers/temp"));

        assertThat(event.getPullRequest(), notNullValue());
        assertThat(event.getPullRequest().getTitle(), is("Forking for PR"));
        assertThat(event.getPullRequest().getAuthorLogin(), is("stephenc"));
        assertThat(event.getPullRequest().getLink(),
                is("https://bitbucket.org/cloudbeers/temp/pull-requests/3"));

        assertThat(event.getPullRequest().getDestination(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository().getScm(), is("git"));
        assertThat(event.getPullRequest().getDestination().getRepository().getFullName(), is("cloudbeers/temp"));
        assertThat(event.getPullRequest().getDestination().getRepository().getOwner().getDisplayName(),
                is("cloudbeers"));
        assertThat(event.getPullRequest().getDestination().getRepository().getOwner().getUsername(), is("cloudbeers"));
        assertThat(event.getPullRequest().getDestination().getRepository().getRepositoryName(), is("temp"));
        assertThat(event.getPullRequest().getDestination().getRepository().isPrivate(), is(true));
        assertThat(event.getPullRequest().getDestination().getRepository().getLinks(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository().getLinks().get("self"), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository().getLinks().get("self").getHref(),
                is("https://api.bitbucket.org/2.0/repositories/cloudbeers/temp"));
        assertThat(event.getPullRequest().getDestination().getBranch(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getBranch().getName(), is("master"));
        assertThat(event.getPullRequest().getDestination().getBranch().getRawNode(),
                anyOf(is("5449b752db4f"), is("5449b752db4fa7ca0e2329d7f70122e2a82856cc")));
        assertThat(event.getPullRequest().getDestination().getCommit(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getCommit().getHash(),
                anyOf(is("5449b752db4f"), is("5449b752db4fa7ca0e2329d7f70122e2a82856cc")));

        assertThat(event.getPullRequest().getSource(), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository(), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository().getScm(), is("git"));
        assertThat(event.getPullRequest().getSource().getRepository().getFullName(), is("stephenc/temp-fork"));
        assertThat(event.getPullRequest().getSource().getRepository().getOwner().getDisplayName(), is("Stephen Connolly"));
        assertThat(event.getPullRequest().getSource().getRepository().getOwner().getUsername(), is("stephenc"));
        assertThat(event.getPullRequest().getSource().getRepository().getRepositoryName(), is("temp-fork"));
        assertThat(event.getPullRequest().getSource().getRepository().isPrivate(), is(true));
        assertThat(event.getPullRequest().getSource().getRepository().getLinks(), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository().getLinks().get("self"), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository().getLinks().get("self").getHref(),
                is("https://api.bitbucket.org/2.0/repositories/stephenc/temp-fork"));

        assertThat(event.getPullRequest().getSource().getBranch(), notNullValue());
        assertThat(event.getPullRequest().getSource().getBranch().getName(), is("master"));
        assertThat(event.getPullRequest().getSource().getBranch().getRawNode(),
                anyOf(is("63e3d18dca4c"), is("63e3d18dca4c61e6b9e31eb6036802c7730fa2b3")));
        assertThat(event.getPullRequest().getSource().getCommit(), notNullValue());
        assertThat(event.getPullRequest().getSource().getCommit().getHash(),
                anyOf(is("63e3d18dca4c"), is("63e3d18dca4c61e6b9e31eb6036802c7730fa2b3")));
    }

    @Test
    public void rejectedPayload() throws Exception {
        BitbucketPullRequestEvent event = BitbucketCloudWebhookPayload.pullRequestEventFromPayload("{\n"
                + "  \"pullrequest\": {\n"
                + "    \"merge_commit\": null,\n"
                + "    \"description\": \"description of PR\",\n"
                + "    \"links\": {\n"
                + "      \"decline\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/decline\"\n"
                + "      },\n"
                + "      \"commits\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/commits\"\n"
                + "      },\n"
                + "      \"self\": {\n"
                + "        \"href\": \"https://api.bitbucket.org/2.0/repositories/cloudbeers/temp/pullrequests/3\"\n"
                + "      },\n"
                + "      \"comments\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/comments\"\n"
                + "      },\n"
                + "      \"merge\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/merge\"\n"
                + "      },\n"
                + "      \"html\": {\n"
                + "        \"href\": \"https://bitbucket.org/cloudbeers/temp/pull-requests/3\"\n"
                + "      },\n"
                + "      \"activity\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/activity\"\n"
                + "      },\n"
                + "      \"diff\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/diff\"\n"
                + "      },\n"
                + "      \"approve\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/approve\"\n"
                + "      },\n"
                + "      \"statuses\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/3/statuses\"\n"
                + "      }\n"
                + "    },\n"
                + "    \"title\": \"Forking for PR\",\n"
                + "    \"close_source_branch\": false,\n"
                + "    \"reviewers\": [],\n"
                + "    \"destination\": {\n"
                + "      \"commit\": {\n"
                + "        \"hash\": \"5449b752db4f\",\n"
                + "        \"links\": {\n"
                + "          \"self\": {\n"
                + "            \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/commit/5449b752db4f\"\n"
                + "          }\n"
                + "        }\n"
                + "      },\n"
                + "      \"branch\": {\n"
                + "        \"name\": \"master\"\n"
                + "      },\n"
                + "      \"repository\": {\n"
                + "        \"full_name\": \"cloudbeers/temp\",\n"
                + "        \"type\": \"repository\",\n"
                + "        \"name\": \"temp\",\n"
                + "        \"links\": {\n"
                + "          \"self\": {\n"
                + "            \"href\": \"https://api.bitbucket.org/2.0/repositories/cloudbeers/temp\"\n"
                + "          },\n"
                + "          \"html\": {\n"
                + "            \"href\": \"https://bitbucket.org/cloudbeers/temp\"\n"
                + "          },\n"
                + "          \"avatar\": {\n"
                + "            \"href\": \"https://bitbucket.org/cloudbeers/temp/avatar/32/\"\n"
                + "          }\n"
                + "        },\n"
                + "        \"uuid\": \"{708c9715-0ecc-4a5d-87ed-63c4ba48ea06}\"\n"
                + "      }\n"
                + "    },\n"
                + "    \"comment_count\": 0,\n"
                + "    \"closed_by\": {\n"
                + "      \"username\": \"stephenc\",\n"
                + "      \"display_name\": \"Stephen Connolly\",\n"
                + "      \"type\": \"user\",\n"
                + "      \"uuid\": \"{f70f195e-ade1-4961-9f89-547541377b80}\",\n"
                + "      \"links\": {\n"
                + "        \"self\": {\n"
                + "          \"href\": \"https://api.bitbucket.org/2.0/users/stephenc\"\n"
                + "        },\n"
                + "        \"html\": {\n"
                + "          \"href\": \"https://bitbucket.org/stephenc/\"\n"
                + "        },\n"
                + "        \"avatar\": {\n"
                + "          \"href\": \"https://bitbucket.org/account/stephenc/avatar/32/\"\n"
                + "        }\n"
                + "      }\n"
                + "    },\n"
                + "    \"source\": {\n"
                + "      \"commit\": {\n"
                + "        \"hash\": \"63e3d18dca4c\",\n"
                + "        \"links\": {\n"
                + "          \"self\": {\n"
                + "            \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/stephenc/temp-fork/commit/63e3d18dca4c\"\n"
                + "          }\n"
                + "        }\n"
                + "      },\n"
                + "      \"branch\": {\n"
                + "        \"name\": \"master\"\n"
                + "      },\n"
                + "      \"repository\": {\n"
                + "        \"full_name\": \"stephenc/temp-fork\",\n"
                + "        \"type\": \"repository\",\n"
                + "        \"name\": \"temp-fork\",\n"
                + "        \"links\": {\n"
                + "          \"self\": {\n"
                + "            \"href\": \"https://api.bitbucket.org/2.0/repositories/stephenc/temp-fork\"\n"
                + "          },\n"
                + "          \"html\": {\n"
                + "            \"href\": \"https://bitbucket.org/stephenc/temp-fork\"\n"
                + "          },\n"
                + "          \"avatar\": {\n"
                + "            \"href\": \"https://bitbucket.org/stephenc/temp-fork/avatar/32/\"\n"
                + "          }\n"
                + "        },\n"
                + "        \"uuid\": \"{f58c57d2-28b0-44f4-8c30-ea83e4825381}\"\n"
                + "      }\n"
                + "    },\n"
                + "    \"participants\": [],\n"
                + "    \"state\": \"DECLINED\",\n"
                + "    \"task_count\": 0,\n"
                + "    \"created_on\": \"2017-03-06T11:52:20.092358+00:00\",\n"
                + "    \"reason\": \"\",\n"
                + "    \"updated_on\": \"2017-03-06T12:05:11.484201+00:00\",\n"
                + "    \"author\": {\n"
                + "      \"username\": \"stephenc\",\n"
                + "      \"display_name\": \"Stephen Connolly\",\n"
                + "      \"type\": \"user\",\n"
                + "      \"uuid\": \"{f70f195e-ade1-4961-9f89-547541377b80}\",\n"
                + "      \"links\": {\n"
                + "        \"self\": {\n"
                + "          \"href\": \"https://api.bitbucket.org/2.0/users/stephenc\"\n"
                + "        },\n"
                + "        \"html\": {\n"
                + "          \"href\": \"https://bitbucket.org/stephenc/\"\n"
                + "        },\n"
                + "        \"avatar\": {\n"
                + "          \"href\": \"https://bitbucket.org/account/stephenc/avatar/32/\"\n"
                + "        }\n"
                + "      }\n"
                + "    },\n"
                + "    \"type\": \"pullrequest\",\n"
                + "    \"id\": 3\n"
                + "  },\n"
                + "  \"actor\": {\n"
                + "    \"username\": \"stephenc\",\n"
                + "    \"display_name\": \"Stephen Connolly\",\n"
                + "    \"type\": \"user\",\n"
                + "    \"uuid\": \"{f70f195e-ade1-4961-9f89-547541377b80}\",\n"
                + "    \"links\": {\n"
                + "      \"self\": {\n"
                + "        \"href\": \"https://api.bitbucket.org/2.0/users/stephenc\"\n"
                + "      },\n"
                + "      \"html\": {\n"
                + "        \"href\": \"https://bitbucket.org/stephenc/\"\n"
                + "      },\n"
                + "      \"avatar\": {\n"
                + "        \"href\": \"https://bitbucket.org/account/stephenc/avatar/32/\"\n"
                + "      }\n"
                + "    }\n"
                + "  },\n"
                + "  \"repository\": {\n"
                + "    \"website\": \"\",\n"
                + "    \"scm\": \"git\",\n"
                + "    \"name\": \"temp\",\n"
                + "    \"links\": {\n"
                + "      \"self\": {\n"
                + "        \"href\": \"https://api.bitbucket.org/2.0/repositories/cloudbeers/temp\"\n"
                + "      },\n"
                + "      \"html\": {\n"
                + "        \"href\": \"https://bitbucket.org/cloudbeers/temp\"\n"
                + "      },\n"
                + "      \"avatar\": {\n"
                + "        \"href\": \"https://bitbucket.org/cloudbeers/temp/avatar/32/\"\n"
                + "      }\n"
                + "    },\n"
                + "    \"project\": {\n"
                + "      \"key\": \"DUB\",\n"
                + "      \"type\": \"project\",\n"
                + "      \"uuid\": \"{7f08415d-57d0-4172-8978-059345fa0369}\",\n"
                + "      \"links\": {\n"
                + "        \"self\": {\n"
                + "          \"href\": \"https://api.bitbucket.org/2.0/teams/cloudbeers/projects/DUB\"\n"
                + "        },\n"
                + "        \"html\": {\n"
                + "          \"href\": \"https://bitbucket.org/account/user/cloudbeers/projects/DUB\"\n"
                + "        },\n"
                + "        \"avatar\": {\n"
                + "          \"href\": \"https://bitbucket.org/account/user/cloudbeers/projects/DUB/avatar/32\"\n"
                + "        }\n"
                + "      },\n"
                + "      \"name\": \"dublin\"\n"
                + "    },\n"
                + "    \"full_name\": \"cloudbeers/temp\",\n"
                + "    \"owner\": {\n"
                + "      \"username\": \"cloudbeers\",\n"
                + "      \"display_name\": \"cloudbeers\",\n"
                + "      \"type\": \"team\",\n"
                + "      \"uuid\": \"{5e429024-1f85-425f-8955-8af0c35d1b41}\",\n"
                + "      \"links\": {\n"
                + "        \"self\": {\n"
                + "          \"href\": \"https://api.bitbucket.org/2.0/teams/cloudbeers\"\n"
                + "        },\n"
                + "        \"html\": {\n"
                + "          \"href\": \"https://bitbucket.org/cloudbeers/\"\n"
                + "        },\n"
                + "        \"avatar\": {\n"
                + "          \"href\": \"https://bitbucket.org/account/cloudbeers/avatar/32/\"\n"
                + "        }\n"
                + "      }\n"
                + "    },\n"
                + "    \"type\": \"repository\",\n"
                + "    \"is_private\": true,\n"
                + "    \"uuid\": \"{708c9715-0ecc-4a5d-87ed-63c4ba48ea06}\"\n"
                + "  }\n"
                + "}");
        assertThat(event.getRepository(), notNullValue());
        assertThat(event.getRepository().getScm(), is("git"));
        assertThat(event.getRepository().getFullName(), is("cloudbeers/temp"));
        assertThat(event.getRepository().getOwner().getDisplayName(), is("cloudbeers"));
        assertThat(event.getRepository().getOwner().getUsername(), is("cloudbeers"));
        assertThat(event.getRepository().getRepositoryName(), is("temp"));
        assertThat(event.getRepository().isPrivate(), is(true));
        assertThat(event.getRepository().getLinks(), notNullValue());
        assertThat(event.getRepository().getLinks().get("self"), notNullValue());
        assertThat(event.getRepository().getLinks().get("self").getHref(),
                is("https://api.bitbucket.org/2.0/repositories/cloudbeers/temp"));

        assertThat(event.getPullRequest(), notNullValue());
        assertThat(event.getPullRequest().getTitle(), is("Forking for PR"));
        assertThat(event.getPullRequest().getAuthorLogin(), is("stephenc"));
        assertThat(event.getPullRequest().getLink(),
                is("https://bitbucket.org/cloudbeers/temp/pull-requests/3"));

        assertThat(event.getPullRequest().getDestination(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository().getScm(), is("git"));
        assertThat(event.getPullRequest().getDestination().getRepository().getFullName(), is("cloudbeers/temp"));
        assertThat(event.getPullRequest().getDestination().getRepository().getOwner().getDisplayName(),
                is("cloudbeers"));
        assertThat(event.getPullRequest().getDestination().getRepository().getOwner().getUsername(), is("cloudbeers"));
        assertThat(event.getPullRequest().getDestination().getRepository().getRepositoryName(), is("temp"));
        assertThat(event.getPullRequest().getDestination().getRepository().isPrivate(), is(true));
        assertThat(event.getPullRequest().getDestination().getRepository().getLinks(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository().getLinks().get("self"), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository().getLinks().get("self").getHref(),
                is("https://api.bitbucket.org/2.0/repositories/cloudbeers/temp"));
        assertThat(event.getPullRequest().getDestination().getBranch(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getBranch().getName(), is("master"));
        assertThat(event.getPullRequest().getDestination().getBranch().getRawNode(),
                anyOf(is("5449b752db4f"), is("5449b752db4fa7ca0e2329d7f70122e2a82856cc")));
        assertThat(event.getPullRequest().getDestination().getCommit(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getCommit().getHash(),
                anyOf(is("5449b752db4f"), is("5449b752db4fa7ca0e2329d7f70122e2a82856cc")));

        assertThat(event.getPullRequest().getSource(), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository(), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository().getScm(), is("git"));
        assertThat(event.getPullRequest().getSource().getRepository().getFullName(), is("stephenc/temp-fork"));
        assertThat(event.getPullRequest().getSource().getRepository().getOwner().getDisplayName(),
                is("Stephen Connolly"));
        assertThat(event.getPullRequest().getSource().getRepository().getOwner().getUsername(), is("stephenc"));
        assertThat(event.getPullRequest().getSource().getRepository().getRepositoryName(), is("temp-fork"));
        assertThat(event.getPullRequest().getSource().getRepository().isPrivate(), is(true));
        assertThat(event.getPullRequest().getSource().getRepository().getLinks(), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository().getLinks().get("self"), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository().getLinks().get("self").getHref(),
                is("https://api.bitbucket.org/2.0/repositories/stephenc/temp-fork"));

        assertThat(event.getPullRequest().getSource().getBranch(), notNullValue());
        assertThat(event.getPullRequest().getSource().getBranch().getName(), is("master"));
        assertThat(event.getPullRequest().getSource().getBranch().getRawNode(),
                anyOf(is("63e3d18dca4c"), is("63e3d18dca4c61e6b9e31eb6036802c7730fa2b3")));
        assertThat(event.getPullRequest().getSource().getCommit(), notNullValue());
        assertThat(event.getPullRequest().getSource().getCommit().getHash(),
                anyOf(is("63e3d18dca4c"), is("63e3d18dca4c61e6b9e31eb6036802c7730fa2b3")));
    }

    @Test
    public void fulfilledPayload() throws Exception {
        BitbucketPullRequestEvent event = BitbucketCloudWebhookPayload.pullRequestEventFromPayload("{\n"
                + "  \"pullrequest\": {\n"
                + "    \"merge_commit\": {\n"
                + "      \"hash\": \"1986c2284946\",\n"
                + "      \"links\": {\n"
                + "        \"self\": {\n"
                + "          \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/commit/1986c2284946\"\n"
                + "        }\n"
                + "      }\n"
                + "    },\n"
                + "    \"description\": \"\",\n"
                + "    \"links\": {\n"
                + "      \"decline\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/2/decline\"\n"
                + "      },\n"
                + "      \"commits\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/2/commits\"\n"
                + "      },\n"
                + "      \"self\": {\n"
                + "        \"href\": \"https://api.bitbucket.org/2.0/repositories/cloudbeers/temp/pullrequests/2\"\n"
                + "      },\n"
                + "      \"comments\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/2/comments\"\n"
                + "      },\n"
                + "      \"merge\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/2/merge\"\n"
                + "      },\n"
                + "      \"html\": {\n"
                + "        \"href\": \"https://bitbucket.org/cloudbeers/temp/pull-requests/2\"\n"
                + "      },\n"
                + "      \"activity\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/2/activity\"\n"
                + "      },\n"
                + "      \"diff\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/2/diff\"\n"
                + "      },\n"
                + "      \"approve\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/2/approve\"\n"
                + "      },\n"
                + "      \"statuses\": {\n"
                + "        \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/pullrequests/2/statuses\"\n"
                + "      }\n"
                + "    },\n"
                + "    \"title\": \"README.md edited online with Bitbucket\",\n"
                + "    \"close_source_branch\": true,\n"
                + "    \"reviewers\": [],\n"
                + "    \"destination\": {\n"
                + "      \"commit\": {\n"
                + "        \"hash\": \"f612156eff2c\",\n"
                + "        \"links\": {\n"
                + "          \"self\": {\n"
                + "            \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/commit/f612156eff2c\"\n"
                + "          }\n"
                + "        }\n"
                + "      },\n"
                + "      \"branch\": {\n"
                + "        \"name\": \"master\"\n"
                + "      },\n"
                + "      \"repository\": {\n"
                + "        \"full_name\": \"cloudbeers/temp\",\n"
                + "        \"type\": \"repository\",\n"
                + "        \"name\": \"temp\",\n"
                + "        \"links\": {\n"
                + "          \"self\": {\n"
                + "            \"href\": \"https://api.bitbucket.org/2.0/repositories/cloudbeers/temp\"\n"
                + "          },\n"
                + "          \"html\": {\n"
                + "            \"href\": \"https://bitbucket.org/cloudbeers/temp\"\n"
                + "          },\n"
                + "          \"avatar\": {\n"
                + "            \"href\": \"https://bitbucket.org/cloudbeers/temp/avatar/32/\"\n"
                + "          }\n"
                + "        },\n"
                + "        \"uuid\": \"{708c9715-0ecc-4a5d-87ed-63c4ba48ea06}\"\n"
                + "      }\n"
                + "    },\n"
                + "    \"comment_count\": 0,\n"
                + "    \"closed_by\": {\n"
                + "      \"username\": \"stephenc\",\n"
                + "      \"display_name\": \"Stephen Connolly\",\n"
                + "      \"type\": \"user\",\n"
                + "      \"uuid\": \"{f70f195e-ade1-4961-9f89-547541377b80}\",\n"
                + "      \"links\": {\n"
                + "        \"self\": {\n"
                + "          \"href\": \"https://api.bitbucket.org/2.0/users/stephenc\"\n"
                + "        },\n"
                + "        \"html\": {\n"
                + "          \"href\": \"https://bitbucket.org/stephenc/\"\n"
                + "        },\n"
                + "        \"avatar\": {\n"
                + "          \"href\": \"https://bitbucket.org/account/stephenc/avatar/32/\"\n"
                + "        }\n"
                + "      }\n"
                + "    },\n"
                + "    \"source\": {\n"
                + "      \"commit\": {\n"
                + "        \"hash\": \"a72355f35fde\",\n"
                + "        \"links\": {\n"
                + "          \"self\": {\n"
                + "            \"href\": \"https://api.bitbucket"
                + ".org/2.0/repositories/cloudbeers/temp/commit/a72355f35fde\"\n"
                + "          }\n"
                + "        }\n"
                + "      },\n"
                + "      \"branch\": {\n"
                + "        \"name\": \"foo\"\n"
                + "      },\n"
                + "      \"repository\": {\n"
                + "        \"full_name\": \"cloudbeers/temp\",\n"
                + "        \"type\": \"repository\",\n"
                + "        \"name\": \"temp\",\n"
                + "        \"links\": {\n"
                + "          \"self\": {\n"
                + "            \"href\": \"https://api.bitbucket.org/2.0/repositories/cloudbeers/temp\"\n"
                + "          },\n"
                + "          \"html\": {\n"
                + "            \"href\": \"https://bitbucket.org/cloudbeers/temp\"\n"
                + "          },\n"
                + "          \"avatar\": {\n"
                + "            \"href\": \"https://bitbucket.org/cloudbeers/temp/avatar/32/\"\n"
                + "          }\n"
                + "        },\n"
                + "        \"uuid\": \"{708c9715-0ecc-4a5d-87ed-63c4ba48ea06}\"\n"
                + "      }\n"
                + "    },\n"
                + "    \"participants\": [],\n"
                + "    \"state\": \"MERGED\",\n"
                + "    \"task_count\": 0,\n"
                + "    \"created_on\": \"2017-03-06T10:41:26.966918+00:00\",\n"
                + "    \"reason\": \"\",\n"
                + "    \"updated_on\": \"2017-03-06T10:41:59.551194+00:00\",\n"
                + "    \"author\": {\n"
                + "      \"username\": \"stephenc\",\n"
                + "      \"display_name\": \"Stephen Connolly\",\n"
                + "      \"type\": \"user\",\n"
                + "      \"uuid\": \"{f70f195e-ade1-4961-9f89-547541377b80}\",\n"
                + "      \"links\": {\n"
                + "        \"self\": {\n"
                + "          \"href\": \"https://api.bitbucket.org/2.0/users/stephenc\"\n"
                + "        },\n"
                + "        \"html\": {\n"
                + "          \"href\": \"https://bitbucket.org/stephenc/\"\n"
                + "        },\n"
                + "        \"avatar\": {\n"
                + "          \"href\": \"https://bitbucket.org/account/stephenc/avatar/32/\"\n"
                + "        }\n"
                + "      }\n"
                + "    },\n"
                + "    \"type\": \"pullrequest\",\n"
                + "    \"id\": 2\n"
                + "  },\n"
                + "  \"actor\": {\n"
                + "    \"username\": \"stephenc\",\n"
                + "    \"display_name\": \"Stephen Connolly\",\n"
                + "    \"type\": \"user\",\n"
                + "    \"uuid\": \"{f70f195e-ade1-4961-9f89-547541377b80}\",\n"
                + "    \"links\": {\n"
                + "      \"self\": {\n"
                + "        \"href\": \"https://api.bitbucket.org/2.0/users/stephenc\"\n"
                + "      },\n"
                + "      \"html\": {\n"
                + "        \"href\": \"https://bitbucket.org/stephenc/\"\n"
                + "      },\n"
                + "      \"avatar\": {\n"
                + "        \"href\": \"https://bitbucket.org/account/stephenc/avatar/32/\"\n"
                + "      }\n"
                + "    }\n"
                + "  },\n"
                + "  \"repository\": {\n"
                + "    \"website\": \"\",\n"
                + "    \"scm\": \"git\",\n"
                + "    \"name\": \"temp\",\n"
                + "    \"links\": {\n"
                + "      \"self\": {\n"
                + "        \"href\": \"https://api.bitbucket.org/2.0/repositories/cloudbeers/temp\"\n"
                + "      },\n"
                + "      \"html\": {\n"
                + "        \"href\": \"https://bitbucket.org/cloudbeers/temp\"\n"
                + "      },\n"
                + "      \"avatar\": {\n"
                + "        \"href\": \"https://bitbucket.org/cloudbeers/temp/avatar/32/\"\n"
                + "      }\n"
                + "    },\n"
                + "    \"project\": {\n"
                + "      \"links\": {\n"
                + "        \"self\": {\n"
                + "          \"href\": \"https://api.bitbucket.org/2.0/teams/cloudbeers/projects/DUB\"\n"
                + "        },\n"
                + "        \"html\": {\n"
                + "          \"href\": \"https://bitbucket.org/account/user/cloudbeers/projects/DUB\"\n"
                + "        },\n"
                + "        \"avatar\": {\n"
                + "          \"href\": \"https://bitbucket.org/account/user/cloudbeers/projects/DUB/avatar/32\"\n"
                + "        }\n"
                + "      },\n"
                + "      \"type\": \"project\",\n"
                + "      \"name\": \"dublin\",\n"
                + "      \"key\": \"DUB\",\n"
                + "      \"uuid\": \"{7f08415d-57d0-4172-8978-059345fa0369}\"\n"
                + "    },\n"
                + "    \"full_name\": \"cloudbeers/temp\",\n"
                + "    \"owner\": {\n"
                + "      \"username\": \"cloudbeers\",\n"
                + "      \"display_name\": \"cloudbeers\",\n"
                + "      \"type\": \"team\",\n"
                + "      \"uuid\": \"{5e429024-1f85-425f-8955-8af0c35d1b41}\",\n"
                + "      \"links\": {\n"
                + "        \"self\": {\n"
                + "          \"href\": \"https://api.bitbucket.org/2.0/teams/cloudbeers\"\n"
                + "        },\n"
                + "        \"html\": {\n"
                + "          \"href\": \"https://bitbucket.org/cloudbeers/\"\n"
                + "        },\n"
                + "        \"avatar\": {\n"
                + "          \"href\": \"https://bitbucket.org/account/cloudbeers/avatar/32/\"\n"
                + "        }\n"
                + "      }\n"
                + "    },\n"
                + "    \"type\": \"repository\",\n"
                + "    \"is_private\": true,\n"
                + "    \"uuid\": \"{708c9715-0ecc-4a5d-87ed-63c4ba48ea06}\"\n"
                + "  }\n"
                + "}");
        assertThat(event.getRepository(), notNullValue());
        assertThat(event.getRepository().getScm(), is("git"));
        assertThat(event.getRepository().getFullName(), is("cloudbeers/temp"));
        assertThat(event.getRepository().getOwner().getDisplayName(), is("cloudbeers"));
        assertThat(event.getRepository().getOwner().getUsername(), is("cloudbeers"));
        assertThat(event.getRepository().getRepositoryName(), is("temp"));
        assertThat(event.getRepository().isPrivate(), is(true));
        assertThat(event.getRepository().getLinks(), notNullValue());
        assertThat(event.getRepository().getLinks().get("self"), notNullValue());
        assertThat(event.getRepository().getLinks().get("self").getHref(),
                is("https://api.bitbucket.org/2.0/repositories/cloudbeers/temp"));

        assertThat(event.getPullRequest(), notNullValue());
        assertThat(event.getPullRequest().getTitle(), is("README.md edited online with Bitbucket"));
        assertThat(event.getPullRequest().getAuthorLogin(), is("stephenc"));
        assertThat(event.getPullRequest().getLink(),
                is("https://bitbucket.org/cloudbeers/temp/pull-requests/2"));

        assertThat(event.getPullRequest().getDestination(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository().getScm(), is("git"));
        assertThat(event.getPullRequest().getDestination().getRepository().getFullName(), is("cloudbeers/temp"));
        assertThat(event.getPullRequest().getDestination().getRepository().getOwner().getDisplayName(),
                is("cloudbeers"));
        assertThat(event.getPullRequest().getDestination().getRepository().getOwner().getUsername(), is("cloudbeers"));
        assertThat(event.getPullRequest().getDestination().getRepository().getRepositoryName(), is("temp"));
        assertThat(event.getPullRequest().getDestination().getRepository().isPrivate(), is(true));
        assertThat(event.getPullRequest().getDestination().getRepository().getLinks(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository().getLinks().get("self"), notNullValue());
        assertThat(event.getPullRequest().getDestination().getRepository().getLinks().get("self").getHref(),
                is("https://api.bitbucket.org/2.0/repositories/cloudbeers/temp"));
        assertThat(event.getPullRequest().getDestination().getBranch(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getBranch().getName(), is("master"));
        assertThat(event.getPullRequest().getDestination().getBranch().getRawNode(),
                anyOf(is("f612156eff2c"), is("f612156eff2c958f52f8e6e20c71f396aeaeaff4")));
        assertThat(event.getPullRequest().getDestination().getCommit(), notNullValue());
        assertThat(event.getPullRequest().getDestination().getCommit().getHash(),
                anyOf(is("f612156eff2c"), is("f612156eff2c958f52f8e6e20c71f396aeaeaff4")));

        assertThat(event.getPullRequest().getSource(), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository(), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository().getScm(), is("git"));
        assertThat(event.getPullRequest().getSource().getRepository().getFullName(), is("cloudbeers/temp"));
        assertThat(event.getPullRequest().getSource().getRepository().getOwner().getDisplayName(), is("cloudbeers"));
        assertThat(event.getPullRequest().getSource().getRepository().getOwner().getUsername(), is("cloudbeers"));
        assertThat(event.getPullRequest().getSource().getRepository().getRepositoryName(), is("temp"));
        assertThat(event.getPullRequest().getSource().getRepository().isPrivate(), is(true));
        assertThat(event.getPullRequest().getSource().getRepository().getLinks(), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository().getLinks().get("self"), notNullValue());
        assertThat(event.getPullRequest().getSource().getRepository().getLinks().get("self").getHref(),
                is("https://api.bitbucket.org/2.0/repositories/cloudbeers/temp"));

        assertThat(event.getPullRequest().getSource().getBranch(), notNullValue());
        assertThat(event.getPullRequest().getSource().getBranch().getName(), is("foo"));
        assertThat(event.getPullRequest().getSource().getBranch().getRawNode(),
                anyOf(is("a72355f35fde"), is("a72355f35fde2ad4f5724a279b970ef7b6729131")));
        assertThat(event.getPullRequest().getSource().getCommit(), notNullValue());
        assertThat(event.getPullRequest().getSource().getCommit().getHash(),
                anyOf(is("a72355f35fde"), is("a72355f35fde2ad4f5724a279b970ef7b6729131")));
    }

}
