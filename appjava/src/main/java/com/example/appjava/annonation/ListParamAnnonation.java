package com.example.appjava.annonation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ListParamAnnonation {
    boolean canBeNull() default true;

    int minSize() default -1;

    int maxSize() default -1;

    String message() default "";
}
