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
package io.truthencode.toad.config

/**
 * Main (Http) Server port information used to bind the public facing web interface.
 * @param ip
 *   external IP address
 * @param port
 *   external Port (may be further mapped by production environment. (Defaults to 8080)
 * @param hostName
 *   convenience string for host:port or ip:port.
 */
case class ServerInfo(ip: String, port: String, hostName: String)
