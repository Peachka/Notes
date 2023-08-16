package com.example.notes;

public class NotesModel {
    String title;
    String noteShortcut;
    String modifiedTime;
    String createdTime;

    public NotesModel(String title, String noteShortcut, String modifiedTime, String createdTime) {
        this.title = title;
        this.noteShortcut = noteShortcut;
        this.modifiedTime = modifiedTime;
        this.createdTime = createdTime;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }


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


}
