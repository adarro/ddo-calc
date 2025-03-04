package io.truthencode.ddo.etl;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jetbrains.exodus.core.crypto.MessageDigestUtil;
import jetbrains.exodus.entitystore.Entity;
import jetbrains.exodus.entitystore.PersistentEntityStore;
import jetbrains.exodus.entitystore.StoreTransaction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
class XodusJavaTest {


    @Inject
    XodusManager xodusManager;

    @Test
    @DisplayName(
        "Xodus Entity Store basic functionality (Java)"
    )
    void XodusWithJavaTest() {
        try (PersistentEntityStore store = xodusManager.getStore()) {
            StoreTransaction txn = store.beginTransaction();
            try {
                do {
                    String loginName = "johny", fullName = "Johnny Fiver", email = "nova@disassembled.com", password = "reee";
                    final Entity user = txn.newEntity("User");
                    user.setProperty("login", loginName);
                    user.setProperty("fullName", fullName);
                    user.setProperty("email", email);
                    final String salt = MessageDigestUtil.sha256(Double.valueOf(Math.random()).toString());
                    user.setProperty("salt", salt);
                    user.setProperty("password", MessageDigestUtil.sha256(salt + password));
                    // if txn has already been aborted in user code
                    if (txn != store.getCurrentTransaction()) {
                        txn = null;
                        break;
                    }
                } while (!txn.flush());
            } finally {
                // if txn has not already been aborted in execute()
                if (txn != null) {
                    txn.abort();
                }
            }
        }
    }
}