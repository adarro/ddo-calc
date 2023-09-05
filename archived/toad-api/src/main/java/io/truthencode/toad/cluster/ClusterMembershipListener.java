/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2021 Andre White.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.truthencode.toad.cluster;

import com.hazelcast.cluster.MembershipEvent;
import com.hazelcast.cluster.MembershipListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ClusterMembershipListener
        implements MembershipListener {
    private final Logger log = LoggerFactory.getLogger(ClusterMembershipListener.class);

    public void memberAdded(MembershipEvent membershipEvent) {
        log.info("Added: " + membershipEvent);
    }

    public void memberRemoved(MembershipEvent membershipEvent) {
        log.info("Removed: " + membershipEvent);
    }


}
