package com.example.demo.controller.task;

import com.example.demo.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor // コンストラクタの自動生成
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    //リスト照会機能
    @GetMapping
    public String list(Model model){
        var taskList = taskService.find()
                .stream()   // Iterator (List -> Stream)
                .map(TaskDTO::toDTO) // foreach + function pointer
                .toList(); // Stream -> List

        model.addAttribute("taskList", taskList);
        return "tasks/list";
    }

    //読み取り機能
    @GetMapping("/{id}")
    public String showDetail(@PathVariable("id") long taskId, Model model){ // mapping 変数 -> @PathVariable 代入
        var taskDTO = taskService.findById(taskId)
                .map(TaskDTO::toDTO)
                .orElseThrow(TaskNotFoundException::new); //投稿が存在しない場合
        model.addAttribute("task", taskDTO);
        return "tasks/detail";
    }

    //書き込みフォーム
    @GetMapping("/creationForm")
    public String showCreationForm(@ModelAttribute TaskForm form, Model model){ // @ModelAttribute -> model.addAttribute("form", form)
        model.addAttribute("mode", "CREATE");
        return "tasks/form";
    }

    //書き込み機能
    @PostMapping
    public String create(@Validated TaskForm form, BindingResult bindingResult, Model model){ // @Validated -> Jakarta Validation check
        if(bindingResult.hasErrors()){
            return showCreationForm(form, model);
        }
        taskService.create(form.toEntity());
        return "redirect:/tasks"; //PRGパタン
    }

    //修正フォーム
    @GetMapping("/{id}/editForm")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        var form = taskService.findById(id)
                .map(TaskForm::fromEntity)
                .orElseThrow(TaskNotFoundException::new);
        model.addAttribute("taskForm", form);
        model.addAttribute("mode", "EDIT");
        return "tasks/form";
    }

    //修正機能
    @PutMapping("{id}")
    public String update(@PathVariable("id") long id,
                         @Validated @ModelAttribute TaskForm form,
                         BindingResult bindingResult,
                         Model model
    ){
        if(bindingResult.hasErrors()){
            model.addAttribute("taskForm", form);
            model.addAttribute("mode", "EDIT");
            return "tasks/form";
        }

        var entity = form.toEntity(id);
        taskService.update(entity);
        return "redirect:/tasks/{id}";
    }

    //削除機能
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id){
        taskService.delete(id);
        return "redirect:/tasks";
    }

}
