package com.ssg.intern.dev.domain.comment.presentation;

import com.ssg.intern.dev.domain.comment.presentation.model.CommentRegisterRequest;
import com.ssg.intern.dev.domain.comment.presentation.model.CommentSelectResponse;
import com.ssg.intern.dev.domain.comment.service.CommentCommandService;
import com.ssg.intern.dev.domain.comment.service.CommentQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentApi {

    private final CommentCommandService commentCommandService;
    private final CommentQueryService commentQueryService;

    @GetMapping("/feeds/{feed-id}/comments")
    public CommentSelectResponse getComments(@PathVariable("feed-id") Long id) {
        return commentQueryService.getComments(id);
    }

    @PostMapping("/feeds/{feed-id}/comments")
    public void createComment(@PathVariable("feed-id") Long feedId,
                              @RequestHeader(value="Authorization") String accountId,
                              @RequestBody CommentRegisterRequest request) {
        commentCommandService.createComment(feedId, accountId, request);
    }

    @PutMapping("/comments/{comment-id}")
    public void updateComment(@PathVariable("comment-id") Long id,
                              @RequestHeader(value = "Authorization") String accountId,
                              @RequestBody CommentRegisterRequest request) {
        commentCommandService.updateComment(id, accountId, request);
    }

    @DeleteMapping("/comments/{comment-id}")
    public void deleteComment(@PathVariable("comment-id") Long id,
                              @RequestHeader(value = "Authorization") String accountId) {
        commentCommandService.deleteComment(id, accountId);
    }

    @PostMapping("/comments/{comment-id}/report")
    public void reportComment(@PathVariable("comment-id") Long id) {
        commentCommandService.reportComment(id);
    }

}
