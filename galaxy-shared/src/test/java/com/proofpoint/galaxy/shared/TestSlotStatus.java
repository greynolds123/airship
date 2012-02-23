/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.proofpoint.galaxy.shared;

import com.google.common.collect.ImmutableMap;
import org.testng.annotations.Test;

import java.net.URI;
import java.util.UUID;

import static com.proofpoint.galaxy.shared.AssignmentHelper.APPLE_ASSIGNMENT;
import static com.proofpoint.galaxy.shared.AssignmentHelper.BANANA_ASSIGNMENT;
import static com.proofpoint.galaxy.shared.SlotLifecycleState.RUNNING;
import static com.proofpoint.galaxy.shared.SlotLifecycleState.STOPPED;
import static com.proofpoint.galaxy.shared.SlotLifecycleState.TERMINATED;
import static com.proofpoint.galaxy.shared.SlotLifecycleState.UNKNOWN;
import static com.proofpoint.galaxy.shared.SlotStatus.createSlotStatus;
import static com.proofpoint.testing.EquivalenceTester.equivalenceTester;

public class TestSlotStatus
{
    @Test
    public void testEquivalence()
    {
        UUID appleId = UUID.randomUUID();
        String appleSlotName = "apple";
        URI appleSelf = URI.create("internal://apple");
        URI appleExternalUri = URI.create("external://apple");
        String applePath = "/apple";

        UUID bananaId = UUID.randomUUID();
        String bananaSlotName = "banana";
        URI bananaSelf = URI.create("internal://banana");
        URI bananaExternalUri = URI.create("external://banana");
        String bananaPath = "/banana";

        equivalenceTester()
                .addEquivalentGroup(
                        createSlotStatus(appleId, appleSlotName, appleSelf, appleExternalUri, "location", TERMINATED, null, applePath, ImmutableMap.<String, Integer>of()),
                        createSlotStatus(appleId, appleSlotName, appleSelf, appleExternalUri, "location", TERMINATED, null, applePath, ImmutableMap.<String, Integer>of()))
                .addEquivalentGroup(
                        createSlotStatus(bananaId, appleSlotName, appleSelf, appleExternalUri, "location", TERMINATED, null, applePath, ImmutableMap.<String, Integer>of()),
                        createSlotStatus(bananaId, appleSlotName, appleSelf, appleExternalUri, "location", TERMINATED, null, applePath, ImmutableMap.<String, Integer>of()))
                .addEquivalentGroup(
                        createSlotStatus(appleId, appleSlotName, appleSelf, appleExternalUri, "location", RUNNING, APPLE_ASSIGNMENT, applePath, ImmutableMap.<String, Integer>of()),
                        createSlotStatus(appleId, appleSlotName, appleSelf, appleExternalUri, "location", RUNNING, APPLE_ASSIGNMENT, applePath, ImmutableMap.<String, Integer>of()))
                .addEquivalentGroup(
                        createSlotStatus(bananaId, bananaSlotName, bananaSelf, bananaExternalUri, "location", RUNNING, APPLE_ASSIGNMENT, bananaPath, ImmutableMap.<String, Integer>of()),
                        createSlotStatus(bananaId, bananaSlotName, bananaSelf, bananaExternalUri, "location", RUNNING, APPLE_ASSIGNMENT, bananaPath, ImmutableMap.<String, Integer>of()))
                .addEquivalentGroup(
                        createSlotStatus(appleId, appleSlotName, appleSelf, appleExternalUri, "location", RUNNING, BANANA_ASSIGNMENT, bananaPath, ImmutableMap.<String, Integer>of()),
                        createSlotStatus(appleId, appleSlotName, appleSelf, appleExternalUri, "location", RUNNING, BANANA_ASSIGNMENT, bananaPath, ImmutableMap.<String, Integer>of()))
                .addEquivalentGroup(
                        createSlotStatus(appleId, appleSlotName, appleSelf, appleExternalUri, "location", STOPPED, APPLE_ASSIGNMENT, applePath, ImmutableMap.<String, Integer>of()),
                        createSlotStatus(appleId, appleSlotName, appleSelf, appleExternalUri, "location", STOPPED, APPLE_ASSIGNMENT, applePath, ImmutableMap.<String, Integer>of()))
                .addEquivalentGroup(
                        createSlotStatus(appleId, appleSlotName, appleSelf, appleExternalUri, "location", UNKNOWN, APPLE_ASSIGNMENT, applePath, ImmutableMap.<String, Integer>of()),
                        createSlotStatus(appleId, appleSlotName, appleSelf, appleExternalUri, "location", UNKNOWN, APPLE_ASSIGNMENT, applePath, ImmutableMap.<String, Integer>of()))
                .check();
    }
}
