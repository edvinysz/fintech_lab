package com.example.fintech_lab;

public class Note {

    String title;
    String noteText;

    public Note(String title, String noteText) {
        this.title = title;
        this.noteText = noteText;
    }

    public String getTitle() {
        return title;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }
}
