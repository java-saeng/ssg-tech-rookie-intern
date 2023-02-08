package com.ssg.intern.dev.external.image.domain;

import lombok.Getter;

@Getter
public enum CookStep {

    INGREDIENT("재료"),
    PROCESS("과정"),
    COMPLETE("완성");

    private final String course;

    CookStep(final String level) {
        this.course = level;
    }
}
