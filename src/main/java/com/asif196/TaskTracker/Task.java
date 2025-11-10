package com.asif196.TaskTracker;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Task {
    private static final Set<String> allowedStatuses;
    private Integer id;
    private String description;
    private String status;
    private Date updatedAt;
    private Date createdAt;

    static {
        allowedStatuses = new HashSet<>(Set.of("todo", "in-progress", "done"));
    }

    @JsonGetter("description")
    public String getDescription() {
        return description;
    }


    @JsonGetter("status")
    public String getStatus() {
        return status;
    }


    @JsonGetter("updatedAt")
    public Date getUpdatedAt() {
        return updatedAt;
    }


    @JsonGetter("createdAt")
    public Date getCreatedAt() {
        return createdAt;
    }


    @JsonGetter("id")
    public Integer getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        if(! allowedStatuses.contains(status)){
            throw new IllegalArgumentException(
                    status + "is not an allowed status. Only todo, in-progress and done ar allowed"
            );
        }
        this.status = status;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Task(){
    }

    public Task(Integer id, String description){
       this.id = id;
       this.description = description;
       this.status = "todo";
       Date currentDate = new Date();
       this.createdAt = currentDate;
       this.updatedAt = currentDate;
    }


    public void display(){
        System.out.println("---------TASK DETAILS--------");
        System.out.println("id: " + id.toString());
        System.out.println("description: " + description);
        System.out.println("status: " + status);
        System.out.println("createdAt: " + createdAt.toString());
        System.out.println("updatedAt: " + updatedAt.toString());
    }
}
