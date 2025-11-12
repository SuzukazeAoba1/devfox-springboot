package com.example.demo.controller.task;

import com.example.demo.service.comment.CommentEntity;
import com.example.demo.service.comment.CommentService;
import com.example.demo.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor // コンストラクタの自動生成
@RequestMapping("/tasks") // Mapping Root (Base)
public class TaskController {

    private final TaskService taskService;
    private final CommentService commentService;

    //リスト照会機能
    @GetMapping
    public String list(Model model,
                       @RequestParam(value = "page", required = false, defaultValue = "1") int page){

        var taskList = taskService.findList(page)
                .stream()   // Iterator (List -> Stream)
                .map(TaskDTO::toDTO) // foreach + function pointer
                .toList(); // Stream -> List

        model.addAttribute("maxPageNum", taskService.getCountPageNum());
        model.addAttribute("taskList", taskList);
        model.addAttribute("page", page);
        return "tasks/list";
    }

    //読み取り機能
    @GetMapping("/{id}")
    public String showDetail(@PathVariable("id") long taskId, Model model){ // mapping 変数 -> @PathVariable 代入
        var taskDTO = taskService.findById(taskId)
                .map(TaskDTO::toDTO)
                .orElseThrow(TaskNotFoundException::new); //投稿が存在しない場合

        var commentList = commentService.findByTaskId(taskId)
                .stream()
                .map(CommentDTO::toDTO)
                .toList();

        model.addAttribute("task", taskDTO);
        model.addAttribute("comment", commentList);
        return "tasks/detail";
    }

    //書き込みフォーム
    @GetMapping("/creationForm")
    public String showCreationForm(TaskForm form, Model model){
        model.addAttribute("mode", "CREATE");
        return "tasks/form";
    }

    //書き込み機能
    @PostMapping
    public String create(@Validated TaskForm form,
                         BindingResult bindingResult,
                         @RequestParam(defaultValue = "1") int page,
                         Model model,
                         RedirectAttributes ra){ // @Validated -> Jakarta Validation check
        if(bindingResult.hasErrors()){
            return showCreationForm(form, model);
        }
        taskService.create(form.toEntity());
        ra.addAttribute("page", page);
        return "redirect:/tasks"; //PRGパタン、redirectにはRedirectAttributesが必要
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
                         @RequestParam(defaultValue = "1") int page,
                         @Validated @ModelAttribute TaskForm form,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes ra
    ){
        if(bindingResult.hasErrors()){
            model.addAttribute("taskForm", form);
            model.addAttribute("mode", "EDIT");
            return "tasks/form";
        }

        var entity = form.toEntity(id);
        taskService.update(entity);
        ra.addAttribute("page", page);
        return "redirect:/tasks/{id}"; //redirectにはRedirectAttributesが必要
    }

    //削除機能
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id){
        taskService.delete(id);
        return "redirect:/tasks";
    }

    @PostMapping("/{id}/comments")
    public String createComment(@PathVariable("id") long id,
                                @RequestParam String content,
                                @RequestParam(defaultValue = "1") int page,
                                RedirectAttributes ra){

        var commentCount = commentService.countByTaskId(id);
        commentService.create(new CommentEntity(null, id, commentCount+1L, content,"test"));
        ra.addAttribute("page", page);
        return "redirect:/tasks/{id}"; //PRGパタン、redirectにはRedirectAttributesが必要
    }

    @DeleteMapping("/{id}/comments/{commentOrder}")
    public String deleteComment(@PathVariable("id") long taskId,
                                @PathVariable("commentOrder") long commentOrder,
                                @RequestParam(defaultValue = "1") int page,
                                RedirectAttributes ra){
        commentService.delete(taskId, commentOrder);
        ra.addAttribute("page", page);
        return "redirect:/tasks/{id}";
    }

}
