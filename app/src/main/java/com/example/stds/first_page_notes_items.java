package com.example.stds;

public class first_page_notes_items {
String Title ;
String Note ;

    public first_page_notes_items(String title, String note) {
        Title = title;
        Note = note;
    }

    public String getTitle() {
        return Title;
    }

    public String getNote() {
        return Note;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setNote(String note) {
        Note = note;
    }
}
