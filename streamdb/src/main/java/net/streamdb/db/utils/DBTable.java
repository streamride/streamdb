package net.streamdb.db.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by andreyzakharov on 02.02.15.
 */
@Target(ElementType.TYPE)
@Retention(RUNTIME)
public @interface DBTable {

    String tableName() default "";
}
