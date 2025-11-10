package com.asif196.TaskTracker;

import tools.jackson.databind.ObjectMapper;

import java.io.File;

public class Operations {

    private TaskMetadata metadata;
    private ObjectMapper mapper;

    public Operations(){
        mapper = new ObjectMapper();
        metadata = mapper.readValue( new File("target/metadata.json"), TaskMetadata.class);
    }

    public void addTask(String task_description){
        Integer taskId = metadata.getnTasks() + 1;
        Task task = new Task(taskId, task_description);
        String fileName = String.format("target/tasks/%s.json", taskId.toString());

        mapper.writeValue(new File(fileName), task);

        metadata.setnTasks(taskId);
        mapper.writeValue(new File("target/metadata.json"), metadata);

        System.out.printf("Task added successfully: (ID: %d)", taskId);
    }

    public void deleteTask(Integer taskId){
        String fileName = String.format("target/tasks/%s.json", taskId.toString());
        File file = new File(fileName);
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
        File tasksDir = new File("target/tasks/");
        File[] taskFiles = tasksDir.listFiles();
        for(File taskFile: taskFiles){
            Task task = mapper.readValue(taskFile, Task.class);
            if(task.getStatus().equals(status)){
                task.display();
            }
        }
    }

    public void markTask(Integer taskId, String status){
        String fileName = String.format("target/tasks/%s.json", taskId.toString());
        Task task = mapper.readValue(new File(fileName), Task.class);
        task.setStatus(status);

        mapper.writeValue(new File(fileName), task);
        System.out.println(
                String.format("Task %s is set to %s", taskId.toString(), status)
        );
    }

    public void updateTask(Integer taskId, String description){

        String fileName = String.format("target/tasks/%s.json", taskId.toString());
        Task task = mapper.readValue(new File(fileName), Task.class);
        task.setDescription(description);

        mapper.writeValue(new File(fileName), task);
        System.out.println(
                String.format("Updated task description of %s", taskId.toString())
        );

    }

}
