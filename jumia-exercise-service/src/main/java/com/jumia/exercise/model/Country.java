package com.jumia.exercise.model;

import java.util.regex.Pattern;

public class Country {
    private String code;
    private String name;
    private Pattern pattern;

    public Country(String code, String name, String pattern) {
        this.code = code;
        this.name = name;
        // compile regex pattern once and used it later better for performance
        this.pattern = Pattern.compile(pattern);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Pattern getPattern() {
        return pattern;
    }
}
