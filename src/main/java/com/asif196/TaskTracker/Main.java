package com.asif196.TaskTracker;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Operations operations = new Operations();
        operations.markTask(4, "in-progress");
        operations.listTasks();
    }
}