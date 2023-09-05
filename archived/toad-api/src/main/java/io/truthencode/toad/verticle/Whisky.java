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
package io.truthencode.toad.verticle;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.json.JsonObject;

/**
 * Sample data for Verticle test
 */
public class Whisky {

  private String id;

  private String name;

  private String origin;

  /**
   * Constructor Whisky creates a new Whisky instance.
   *
   * @param name Name / Brand of Whiskey
   * @param origin Region of Origin
   */
  public Whisky(String name, String origin) {
    this.name = name;
    this.origin = origin;
    this.id = "";
  }

  /**
   * Constructor Whisky creates a new Whisky instance using a Json Object
   *
   * @param json of type JsonObject that contains the name / origin and ID of the whiskey.
   */
  public Whisky(JsonObject json) {
    this.name = json.getString("name");
    this.origin = json.getString("origin");
    this.id = json.getString("_id");
  }

  /**
   * Constructor Whisky creates an empty Whisky instance with no id / name or region.
   */
  public Whisky() {
    this.id = "";
  }

  /**
   * Constructor Whisky creates a new Whisky instance with specified name / origin and id
   *
   * @param id UID of the wishkey
   * @param name Name of the whiskey
   * @param origin Region of Origin.
   */
  public Whisky(String id, String name, String origin) {
    this.id = id;
    this.name = name;
    this.origin = origin;
  }

  /**
   * Serializes open to Json
   * @return Serialized instance
   */
  public JsonObject toJson() {
    JsonObject json = new JsonObject()
        .put("name", name)
        .put("origin", origin);
    if (id != null && !id.isEmpty()) {
      json.put("_id", id);
    }
    return json;
  }

  /**
   * The name of the object
   *
   *
   *
   * @return the name (type String) of this Whisky object.
   */
  public String getName() {
    return name;
  }

  /**
   * Method getOrigin returns the Region of origin of this Whisky object.
   *
   *
   *
   * @return the origin
   */
  public String getOrigin() {
    return origin;
  }

  /**
   * Method getId returns the id of this Whisky object.
   *
   *
   *
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * Method setName sets the name of this Whisky object in a fluent manner.
   *
   *
   *
   * @param name the name of this Whisky object.
   *
   * @return Whisky
   */
  @Fluent
  public Whisky setName(String name) {
    this.name = name;
    return this;
  }

  /**
   * Method setOrigin sets the origin of this Whisky object in a fluent manner
   *
   *
   *
   * @param origin the origin of this Whisky object.
   *
   * @return Whisky
   */
  @Fluent
  public Whisky setOrigin(String origin) {
    this.origin = origin;
    return this;
  }

  /**
   * Method setId sets the id of this Whisky object in a fluent manner.
   *
   *
   *
   * @param id the id of this Whisky object.
   *
   * @return Whisky
   */
  @Fluent
  public Whisky setId(String id) {
    this.id = id;
    return this;
  }
}