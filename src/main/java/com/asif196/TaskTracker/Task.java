package com.asif196.TaskTracker;

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

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

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


    public Task(Integer id, String description){
       this.id = id;
       this.description = description;
       this.status = "todo";
       Date currentDate = new Date();
       this.createdAt = currentDate;
       this.updatedAt = currentDate;
    }


    public void display(){
        System.out.println(id.toString());
        System.out.println(description);
        System.out.println(status);
        System.out.println(createdAt.toString());
        System.out.println(updatedAt.toString());
    }
}
