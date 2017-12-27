package org.aos.ddo.etl.tags;


import java.lang.annotation.*;

import org.scalatest.TagAnnotation;

/**
 * Database Testing
 *
 * This flag generally denotes actual database connections are used during a particular test sequence.
 */
@TagAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Db {
}
