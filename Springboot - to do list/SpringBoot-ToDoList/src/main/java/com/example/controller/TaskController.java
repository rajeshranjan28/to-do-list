package com.example.controller;

import com.example.entity.Task;
import com.example.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable int id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody Task taskDetails) {
        Task updatedTask = taskService.updateTask(id, taskDetails);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/priority")
    public List<Task> getTasksOrderedByPriority() {
        return taskService.getTasksOrderedByPriority();
    }

    @GetMapping("/category/{category}")
    public List<Task> getTasksByCategory(@PathVariable String category) {
        return taskService.getTasksByCategory(category);
    }

    @PutMapping("/{id}/priority")
    public ResponseEntity<Task> updateTaskPriority(@PathVariable int id, @RequestParam int priority) {
        Task updatedTask = taskService.updateTaskPriority(id, priority);
        return ResponseEntity.ok(updatedTask);
    }

    @PutMapping("/{id}/category")
    public ResponseEntity<Task> updateTaskCategory(@PathVariable int id, @RequestParam String category) {
        Task updatedTask = taskService.updateTaskCategory(id, category);
        return ResponseEntity.ok(updatedTask);
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Task> markTaskAsComplete(@PathVariable int id) {
        Task updatedTask = taskService.markTaskAsComplete(id);
        return ResponseEntity.ok(updatedTask);
    }

    @GetMapping("/status")
    public List<Task> getTasksByCompletionStatus(@RequestParam boolean completed) {
        return taskService.getTasksByCompletionStatus(completed);
    }

    @DeleteMapping("/category/{category}")
    public ResponseEntity<Void> deleteTasksByCategory(@PathVariable String category) {
        taskService.deleteTasksByCategory(category);
        return ResponseEntity.noContent().build();
    }
}
