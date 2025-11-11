package com.example.demo.service.comment;

import com.example.demo.repository.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public int countByTaskId(long taskId) {
        return commentRepository.countByTaskId(taskId);
    }

    public List<CommentEntity> findByTaskId(long taskId) {
        return commentRepository.selectByTaskId(taskId);
    }

    @Transactional //問題発生時にロールバック
    public void create(CommentEntity commentEntity) {
        commentRepository.insert(commentEntity);
    }

    @Transactional
    public void delete(long taskId, long commentOrder) {
        commentRepository.delete(taskId, commentOrder);
    }

}
