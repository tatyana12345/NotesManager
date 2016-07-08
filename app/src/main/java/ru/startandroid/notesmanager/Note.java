package ru.startandroid.notesmanager;

/**
 * Created by admin on 23.05.2016.
 */
public class Note {
    int id;
    String note_text;
    String note_date;
    String note_width;
    String note_length;

    public Note() {

    }

    public Note(String s, String d) {
        this.note_text = s;
        this.note_date = d;
    }

    public Note(int id, String note_text, String note_date, String note_width, String note_length) {
        this.id = id;
        this.note_text = note_text;
        this.note_date = note_date;
        this.note_width = note_width;
        this.note_length = note_length;
    }

    public Note(String note_text, String note_date, String note_width, String note_length) {
        this.note_text = note_text;
        this.note_date = note_date;
        this.note_width = note_width;
        this.note_length = note_length;
    }

    public Note(int i, String s, String d) {
        this.id = i;
        this.note_text = s;
        this.note_date = d;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote_text() {
        return note_text;
    }

    public void setNote_text(String note_text) {
        this.note_text = note_text;
    }

    public String getNote_data() {
        return note_date;
    }

    public void setNote_date(String note_date) {
        this.note_date = note_date;
    }

    public void setNote_width(String note_width) {
        this.note_width = note_width;
    }

    public void setNote_length(String note_length) {
        this.note_length = note_length;
    }

    public String getNote_width(){return note_width;}

    public String getNote_length() {return note_length;}


    public void setNote_data(String note_data) {
        this.note_date = note_data;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", note_text='" + note_text + '\'' +
                ", note_date='" + note_date + '\'' +
                ", note_width='" + note_width + '\'' +
                ", note_length='" + note_length + '\'' +
                '}';
    }
}
