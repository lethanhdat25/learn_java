package com.example.helloT2008M.annotation;

import com.example.helloT2008M.ultil.Constants.Constants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Id {
    String name();
    String type();
    boolean isPrimaryKey() default false;
    boolean isAutoIncrement() default false;
}
