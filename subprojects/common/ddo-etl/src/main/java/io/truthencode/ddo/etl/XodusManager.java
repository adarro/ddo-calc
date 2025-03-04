package io.truthencode.ddo.etl;

import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import jetbrains.exodus.entitystore.PersistentEntityStore;
import jetbrains.exodus.entitystore.PersistentEntityStores;


@Singleton
public class XodusManager {
    @Produces
    PersistentEntityStore getStore() {
        return PersistentEntityStores.newInstance("./data/ddo.xdb");
    }
}
