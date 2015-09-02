package net.streamdb.db.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by andreyzakharov on 02.02.15.
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface DBField {

    String columnName() default "";
    boolean primaryKey() default false;


}
