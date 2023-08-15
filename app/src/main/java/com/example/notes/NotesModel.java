package com.example.notes;

public class NotesModel {
    String title;
    String noteShortcut;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNoteShortcut() {
        return noteShortcut;
    }

    public void setNoteShortcut(String noteShortcut) {
        this.noteShortcut = noteShortcut;
    }



    public NotesModel(String title, String noteShortcut) {
        this.title = title;
        this.noteShortcut = noteShortcut;
    }
}
