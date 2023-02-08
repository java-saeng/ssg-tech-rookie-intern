package com.ssg.intern.dev.external.adapter.out;

import com.ssg.intern.dev.common.ExternalMockDataProfile;
import com.ssg.intern.dev.common.FeedSearchingConditionRequest;
import com.ssg.intern.dev.common.HashTagProfile;
import com.ssg.intern.dev.common.PersistenceAdapter;
import com.ssg.intern.dev.external.hashtag.adapter.out.HashTagMapper;
import com.ssg.intern.dev.external.hashtag.adapter.out.HashTagRepository;
import com.ssg.intern.dev.external.hashtag.domain.HashTag;
import com.ssg.intern.dev.external.hashtag.port.out.LoadTop10HashTagPort;
import com.ssg.intern.dev.external.product.adapter.out.ProductMapper;
import com.ssg.intern.dev.external.speical_review.adapter.out.persistence.SpecialReviewMapper;
import com.ssg.intern.dev.external.speical_review.adapter.out.persistence.SpecialReviewRepository;
import com.ssg.intern.dev.external.speical_review.domain.SpecialReview;
import com.ssg.intern.dev.external.speical_review.port.out.LoadSearchingSpecialReviewPort;
import com.ssg.intern.dev.external.speical_review.port.out.LoadSingleSpecialReviewPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class MockDataPersistenceAdapter implements LoadSearchingSpecialReviewPort, LoadSingleSpecialReviewPort,
        LoadTop10HashTagPort {

    private final SpecialReviewRepository specialReviewRepository;
    private final HashTagRepository hashTagRepository;
    private final SpecialReviewMapper specialReviewMapper;
    private final HashTagMapper hashTagMapper;
    private final ProductMapper productMapper;

    @Override
    public Page<ExternalMockDataProfile> findBySearchingCondition(final Pageable pageable,
                                                                  final FeedSearchingConditionRequest request) {

        return specialReviewRepository.findBySearchingCondition(pageable, request)
                                      .map((specialReview) -> {
                                          return ExternalMockDataProfile.builder()
                                                                        .hashTagProfile(
                                                                                hashTagMapper.mapToHashTagProfile(
                                                                                        specialReview.getHashTags()))
                                                                        .productProfile(
                                                                                productMapper.mapToProductProfile(
                                                                                        specialReview.getProduct()))
                                                                        .specialReviewProfile(
                                                                                specialReviewMapper.mapToSpecialReviewProfile(
                                                                                        specialReview))
                                                                        .build();
                                      });
    }

    @Override
    public ExternalMockDataProfile findBySpecialReviewId(final long specialReviewId) {
        final SpecialReview specialReview = specialReviewRepository.findBySpecialReviewId(specialReviewId);

        return ExternalMockDataProfile.builder()
                                      .hashTagProfile(
                                              hashTagMapper.mapToHashTagProfile(
                                                      specialReview.getHashTags()))
                                      .productProfile(
                                              productMapper.mapToProductProfile(
                                                      specialReview.getProduct()))
                                      .specialReviewProfile(
                                              specialReviewMapper.mapToSpecialReviewProfile(
                                                      specialReview))
                                      .build();
    }

    @Override
    public List<ExternalMockDataProfile> findSpecialReviewByAccountId(final long accountId) {
        return specialReviewRepository.findSpecialReviewByAccountId(accountId)
                                      .stream()
                                      .map((specialReview) -> {
                                          return ExternalMockDataProfile.builder()
                                                                        .hashTagProfile(
                                                                                hashTagMapper.mapToHashTagProfile(
                                                                                        specialReview.getHashTags()))
                                                                        .productProfile(
                                                                                productMapper.mapToProductProfile(
                                                                                        specialReview.getProduct()))
                                                                        .specialReviewProfile(
                                                                                specialReviewMapper.mapToSpecialReviewProfile(
                                                                                        specialReview))
                                                                        .build();
                                      })
                                      .collect(Collectors.toList());
    }

    @Override
    public HashTagProfile loadTop10HashTag() {
        final List<String> result = hashTagRepository.findDistinctTop10ByOrderByIdAsc()
                                                     .stream()
                                                     .map(HashTag::getName)
                                                     .collect(Collectors.toList());

        return new HashTagProfile(result);
    }
}
