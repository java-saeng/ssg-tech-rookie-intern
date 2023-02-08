package com.ssg.intern.dev.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class HashTagProfile {

    private List<String> hashtags;

    public HashTagProfile(final List<String> hashtags) {
        this.hashtags = hashtags;
    }
}
