package com.discern.api.exceptions;

public class NoteNotFoundException extends ResourceNotFound {
    public NoteNotFoundException() { super("Note Not Found!"); }

}
