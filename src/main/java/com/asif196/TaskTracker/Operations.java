package com.asif196.TaskTracker;

import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Operations {

    private TaskMetadata metadata;
    private ObjectMapper mapper;
    private final String dataDir = "/home/asif/IdeaProjects/TaskTracker/data";

    public Operations(){
        mapper = new ObjectMapper();
        File metadataFile = new File(dataDir + "/metadata.json");
        if(metadataFile.exists()) {
            metadata = mapper.readValue(metadataFile, TaskMetadata.class);
        }
        else {
            metadata = new TaskMetadata();
            metadata.setnTasks(0);
            mapper.writeValue(metadataFile, metadata);
        }

        Path taskDirectory = Paths.get(dataDir);
        try{
            if(!Files.exists(taskDirectory)){
                Files.createDirectories(taskDirectory);
            }
        } catch (IOException e) {
            throw new RuntimeException("Unexpected error when creating task folder " + e.getMessage());
        }
    }

    public void addTask(String taskDescription){
        Integer taskId = metadata.getnTasks() + 1;
        Task task = new Task(taskId, taskDescription);
        String fileName = String.format("%s/%s.json", dataDir, taskId);

        mapper.writeValue(new File(fileName), task);

        metadata.setnTasks(taskId);
        mapper.writeValue(new File(dataDir + "/metadata.json"), metadata);

        System.out.printf("Task added successfully: (ID: %d)", taskId);
    }

    public void deleteTask(Integer taskId){
        String fileName = String.format("%s/tasks/%s.json", dataDir, taskId.toString());
        File file = new File(fileName);
        if(!file.exists()){
            throw new RuntimeException("Task doesn't exist");
        }
        boolean isFileDeleted = file.delete();
        if(isFileDeleted){
            System.out.println("Task has been deleted");
        }
        else{
            System.out.println("Task has not been deleted");
        }
    }

    public void listTasks(){
        File tasksDir = new File("target/tasks/");
        File[] taskFiles = tasksDir.listFiles();
        for(File taskFile: taskFiles){
            Task task = mapper.readValue(taskFile, Task.class);
            task.display();
        }
    }

    public void listTasks(String status){
        File tasksDir = new File(dataDir);
        File[] taskFiles = tasksDir.listFiles();
        for(File taskFile: taskFiles){
            Task task = mapper.readValue(taskFile, Task.class);
            if(task.getStatus().equals(status)){
                task.display();
            }
        }
    }

    public void markTask(Integer taskId, String status){
        String fileName = String.format("%s/%s.json", dataDir, taskId.toString());

        File file = new File(fileName);
        if(!file.exists()){
            throw new RuntimeException("Task doesn't exist");
        }
        Task task = mapper.readValue(file, Task.class);
        task.setStatus(status);

        mapper.writeValue(file, task);
        System.out.printf("Task %s is set to %s%n", taskId, status);
    }

    public void updateTask(Integer taskId, String description){

        String fileName = String.format("%s/tasks/%s.json", dataDir, taskId.toString());
        File file = new File(fileName);
        if(!file.exists()){
            throw new RuntimeException("Task doesn't exist");
        }
        Task task = mapper.readValue(file, Task.class);
        task.setDescription(description);

        mapper.writeValue(file, task);
        System.out.printf("Updated task description of %s%n", taskId);

    }

}
