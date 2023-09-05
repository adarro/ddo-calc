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
package io.truthencode.toad.verticle

import io.vertx.ext.bridge.PermittedOptions

/**
 * Created by adarr on 7/25/2016.
 */
trait DefaultBridgeOptions extends StackableOption {
  abstract override def mergeBridge = super.mergeBridge
    // Allow inbound events going to the terminal session
    .addInboundPermitted(new PermittedOptions()
      .setAddressRegex("^terminal\\.to\\.server\\..*$"))
    .addInboundPermitted(new PermittedOptions()
      .setAddressRegex("^delegate\\.control\\..*$")
      .setRequiredAuthority("session"))
    // Allow inbound events revoking control from invited collaborator
    .addInboundPermitted(new PermittedOptions()
      .setAddressRegex("^revoke\\.control\\..*$")
      .setRequiredAuthority("session"))
    // Allow inbound events for chat messages coming from the client for a given session
    .addInboundPermitted(new PermittedOptions()
      .setAddressRegex("^chat\\.to\\.server\\..*$")
      .setRequiredAuthority("session"))
    // Allow inbound events requesting control of a shared terminal
    .addInboundPermitted(new PermittedOptions()
      .setAddressRegex("^request\\.control\\..*$")
      .setRequiredAuthority("session"))
    // Allow inbound events releasing control of a shared terminal
    .addInboundPermitted(new PermittedOptions()
      .setAddressRegex("^release\\.control\\..*$")
      .setRequiredAuthority("session"))
    // Allow outbound events from the terminal session to the UI
    .addOutboundPermitted(new PermittedOptions()
      .setAddressRegex("^server\\.to\\.terminal\\..*$")
      .setRequiredAuthority("session"))
    // Allow outbound events from the server to participants in a session's chat
    .addOutboundPermitted(new PermittedOptions()
      .setAddressRegex("^server\\.to\\.chat\\..*$")
      .setRequiredAuthority("session"))
    // Allow outbound events to the UI indicating that control was granted
    .addOutboundPermitted(new PermittedOptions()
      .setAddressRegex("^control\\.granted\\..*$")
      .setRequiredAuthority("session"))
    // Allow outbound events to the UI indicating that control was revoked
    .addOutboundPermitted(new PermittedOptions()
      .setAddressRegex("^control\\.revoked\\..*$")
      .setRequiredAuthority("session"))
}
