package com.example.service;

import com.example.entity.Task;
import com.example.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(int id) {
        return taskRepository.findById(id);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(int id, Task taskDetails) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTaskName(taskDetails.getTaskName());
        task.setPriority(taskDetails.getPriority());
        task.setCategory(taskDetails.getCategory());
        task.setCompleted(taskDetails.isCompleted());
        return taskRepository.save(task);
    }

    public void deleteTask(int id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        taskRepository.delete(task);
    }

    public List<Task> getTasksOrderedByPriority() {
        return taskRepository.findAll().stream()
                .sorted((t1, t2) -> Integer.compare(t2.getPriority(), t1.getPriority()))
                .collect(Collectors.toList());
    }

    public List<Task> getTasksByCategory(String category) {
        return taskRepository.findAll().stream()
                .filter(task -> category.equals(task.getCategory()))
                .collect(Collectors.toList());
    }

    public Task updateTaskPriority(int id, int priority) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setPriority(priority);
        return taskRepository.save(task);
    }

    public Task updateTaskCategory(int id, String category) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setCategory(category);
        return taskRepository.save(task);
    }

    public Task markTaskAsComplete(int id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setCompleted(true);
        return taskRepository.save(task);
    }

    public List<Task> getTasksByCompletionStatus(boolean completed) {
        return taskRepository.findAll().stream()
                .filter(task -> task.isCompleted() == completed)
                .collect(Collectors.toList());
    }

    public void deleteTasksByCategory(String category) {
        List<Task> tasksToDelete = taskRepository.findAll().stream()
                .filter(task -> category.equals(task.getCategory()))
                .collect(Collectors.toList());
        taskRepository.deleteAll(tasksToDelete);
    }
}
