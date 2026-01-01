package com.asif196.TaskTracker;

import java.util.*;

public class Utils {
    private static final Map<String, Integer[]> operationArgumentCount;
    private static final Set<String> availableOperations;
    private static final Operations operations;
    static {
        operationArgumentCount = new HashMap<>();
        operationArgumentCount.put("update", new Integer[]{2, 2});
        operationArgumentCount.put("add", new Integer[]{1, 1});
        operationArgumentCount.put("delete", new Integer[]{1, 1});
        operationArgumentCount.put("mark-in-progress", new Integer[]{1, 1});
        operationArgumentCount.put("mark-done", new Integer[]{1, 1});
        operationArgumentCount.put("list", new Integer[]{0, 1});

        availableOperations = new HashSet<>(
                Set.of("update", "add", "delete", "mark-in-progress", "mark-done", "list")
        );

        operations = new Operations();
    }

    public static void validateArguments(String[] args){
        if(args.length == 0){
            throw new RuntimeException("No operation specified");
        }
        String operation = args[0];
        if(! availableOperations.contains(operation)){
           throw new RuntimeException(
                   "No such operation available: " + operation
           );
        }

        Integer[] argumentCount = operationArgumentCount.get(operation);
        if(args.length - 1 < argumentCount[0] || args.length - 1 > argumentCount[1]){
            throw new RuntimeException(
                    "Not enough arguments for operation"
            );
        }

    }

    public static void callOperations(String[] args){
        switch(args[0]){
            case "add":
                operations.addTask(args[1]);
                break;

            case "list":
                validateList(args);
                break;

            case "update":
                validateUpdate(args);
                break;

            case "delete":
                validateDelete(args);
                break;

            case "mark-in-progress":

            case "mark-done":
                validateMark(args);
                break;

        }
    }

    public static void validateList(String[] args){
        if(args.length == 2){
            operations.listTasks(args[1]);
        }
        else{
            operations.listTasks();
        }
    }

    public static void validateUpdate(String [] args){
        int taskId;
        try{
            taskId = Integer.parseInt(args[1]);
        }
        catch(NumberFormatException e){
            throw new RuntimeException("Task id provided in argument should be valid");
        }
        operations.updateTask(taskId, args[2]);
    }

    public static void validateDelete(String [] args){
        int taskId;
        try{
            taskId = Integer.parseInt(args[1]);
        }
        catch(NumberFormatException e){
            throw new RuntimeException("Task id provided in argument should be valid");
        }
        operations.deleteTask(taskId);
    }

    public static void validateMark(String[] args){
        String status = args[0].substring(args[0].indexOf("-")+1);
        int taskId;
        try{
            taskId = Integer.parseInt(args[1]);
        }
        catch(NumberFormatException e){
            throw new RuntimeException("Task id provided in argument should be valid");
        }
        operations.markTask(taskId, status);
    }
}
