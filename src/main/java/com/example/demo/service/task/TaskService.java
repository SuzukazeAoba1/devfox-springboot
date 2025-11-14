package com.example.demo.service.task;

import com.example.demo.repository.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<TaskEntity> findList(int pageNum){

        int maxPage = 5;
        int postcnt = taskRepository.selectAllCounter(); // 8
        int offset = (pageNum-1) * maxPage;
        int lastPage = ((postcnt-1) % maxPage) + 1; // 8%5 = 3, 9%5 = 4, 10%5 = 5, 11%5 = 1
        int pageSize;

        if(pageNum * maxPage > postcnt)
        {
            pageSize = lastPage;
        }
        else
        {
            pageSize = maxPage;
        }

        return taskRepository.selectList(offset, pageSize);
    }

    public int getCountPageNum() {
        return (taskRepository.selectAllCounter() - 1) / 5 + 1;
    }

    public Optional<TaskEntity> findById(long taskId) {
        return taskRepository.selectById(taskId);
    }

    @Transactional //問題発生時にロールバック
    public void create(TaskEntity newEntity) {
        taskRepository.insert(newEntity);
    }

    @Transactional
    public void update(TaskEntity entity) {
        taskRepository.update(entity);
    }

    @Transactional
    public void delete(long id) {
        taskRepository.delete(id);
    }
}
