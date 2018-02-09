package com.brotherhui.cucumber.common.annotation;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CsvColumn {

    String title();

    int order() default 9999;
}

