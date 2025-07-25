package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository repo;

    // Get all tasks
    @GetMapping
    public List<Task> getAll() {
        return repo.findAll();
    }

    // Add single task
    @PostMapping
    public Task add(@RequestBody Task task) {
        return repo.save(task);
    }

    // **NEW** Bulk insert endpoint
    @PostMapping("/bulk")
    public List<Task> addTasks(@RequestBody List<Task> tasks) {
        return repo.saveAll(tasks);
    }

    // Update task
    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task updated) {
        Task task = repo.findById(id).orElseThrow();
        task.setCompleted(updated.isCompleted());
        task.setTitle(updated.getTitle()); // Added so title can also update
        return repo.save(task);
    }

    // Delete task
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
