# Provider - Consumer Services

This module should contain a generic container api to allow separately managed Entities.

Each entity should be responsible for maintaining its current state and publishing changes.
These containers can then be injected into any required instance.
In this fashion, all logic (stacking / cascading effects) can be asynchronously.
