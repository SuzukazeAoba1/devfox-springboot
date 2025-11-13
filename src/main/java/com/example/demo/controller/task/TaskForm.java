package com.example.demo.controller.task;

import com.example.demo.service.task.TaskEntity;
import com.example.demo.service.task.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

//front -> DB
public record TaskForm(
        @NotBlank //空チェック
        @Size(max = 256, message = "256文字以内で入力してください。")
        String summary,
        String description,
        @NotBlank
        @Pattern(regexp="TODO|DOING|DONE", message = "Todo, Doing, Done のいずれかを選択してください。")
        String status
) {

    // Entity -> form
    public static Object fromEntity(TaskEntity taskEntity) {
        return new TaskForm(
                taskEntity.summary(),
                taskEntity.description(),
                taskEntity.status().name()
        );
    }

    // form 登録 -> Entity
    public TaskEntity toEntity(String loginId, String nickname){
        return new TaskEntity(null, summary(), description(), TaskStatus.valueOf(status()), loginId, nickname);
    }

    // form 修正 -> Entity (UpdateにはIDが必要しない)
    public TaskEntity toEntity(Long id){
        return new TaskEntity(id, summary(), description(), TaskStatus.valueOf(status()), null, null);
    }
}
