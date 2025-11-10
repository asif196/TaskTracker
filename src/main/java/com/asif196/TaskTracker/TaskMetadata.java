package com.asif196.TaskTracker;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class TaskMetadata {

    @JsonGetter("nTasks")
    public Integer getnTasks() {
        return nTasks;
    }

    @JsonSetter("nTasks")
    public void setnTasks(Integer nTasks) {
        this.nTasks = nTasks;
    }

    private Integer nTasks;

    public TaskMetadata(){
    }
}
