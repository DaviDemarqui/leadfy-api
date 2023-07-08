package com.discern.api.exceptions;

public class TaskNotFoundException extends ResourceNotFound {
    public TaskNotFoundException() { super("Task Not Found!"); }

}
