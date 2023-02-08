package com.ssg.intern.dev.external.hashtag.adapter.out;

import com.ssg.intern.dev.common.HashTagProfile;
import com.ssg.intern.dev.external.hashtag.domain.HashTag;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HashTagMapper {

    public HashTagProfile mapToHashTagProfile(List<HashTag> hashtags) {

        return new HashTagProfile(
                hashtags.stream()
                        .map(HashTag::getName)
                        .collect(Collectors.toList()));
    }
}
