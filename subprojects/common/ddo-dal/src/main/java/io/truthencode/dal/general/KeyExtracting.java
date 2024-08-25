package io.truthencode.dal.general;

import jakarta.ws.rs.NameBinding;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation is used to mark a method that requires the X-UPDATE-KEYS header to be set.
 */
@NameBinding
@Retention(RetentionPolicy.RUNTIME)
public @interface KeyExtracting {
}
