package com.asif196.TaskTracker;

import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Date;

public class Operations {

    private static Integer nTasks = 0;
    private ObjectMapper objectMapper;

    public Operations(){
        objectMapper = new ObjectMapper();
    }

    public void add_task(String task_description){
        Task task = new Task(++nTasks, task_description);
        String taskJson = objectMapper.writeValueAsString(task);
        objectMapper.writeValue(
                new File(String.format("target/tasks/{}", nTasks.toString())),
                taskJson
        );
    }
}
