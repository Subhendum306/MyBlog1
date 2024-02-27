package com.myblog.myblog1.service;

import com.myblog.myblog1.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, long postId);
    public void deleteComment(long id);
    CommentDto updateComment(long id, CommentDto commentDto, long postId);
}
