package ru.startandroid.notesmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 23.05.2016.
 */
public class AppDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "AppDatabase";
    final String LOG_TAG = "WebDatabase";
    private static final int DATABASE_VERSION = 2;

    public AppDatabase(Context context) /*, String name, SQLiteDatabase.CursorFactory factory, int version)*/ {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(LOG_TAG, "Создание БД");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sQuery = "CREATE TABLE NOTES (id integer primary key autoincrement, note text, date text, width text, length text);";
        db.execSQL(sQuery);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addrecord(Note n) {
        Log.d(LOG_TAG, "Переход в класс базы данных");

        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(LOG_TAG, "подключение к базе");
        Log.d(LOG_TAG, "Объект Note n " + n.toString());
        ContentValues noteT = new ContentValues();
        noteT.put("note", n.getNote_text());
        noteT.put("date", n.getNote_data());
        noteT.put("width",n.getNote_width());
        noteT.put("length", n.getNote_length());



        db.insert("NOTES", null, noteT);
      //  Log.d(LOG_TAG, " данные введены " + noteT);

        db.close();
        Log.d(LOG_TAG, "База закрыта ");
    }
    public void updateNoteByDate(String date, String new_note_text) {
Log.d(LOG_TAG, date);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("note", new_note_text);
        //"UPDATE notes set note_text = Новый_ТЕКСТ WHERE date = ДАТА_ЗАМЕТКИ"
        String where = "date=?";
        String[] whereArgs = new String[]{date};
        //whereArgs = "date= МОЯ_ДАТА_ПЕРЕДАВАЕМАЯ_В_ФУНКЦИЮ"
        db.update("NOTES", cv, where, whereArgs);
        Log.d(LOG_TAG, "Note: " + new_note_text.toString());
        db.close();
    }
    public List<Note> getAllNotes() {
        Log.d(LOG_TAG, "вход в функцию getAllPages");
        List<Note> noteList = new ArrayList<Note>();

        String selectQuery = "SELECT *  FROM NOTES ORDER BY id DESC";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        Log.d(LOG_TAG, "cursor " + cursor.toString());//121231dsad

        if (cursor.moveToFirst()) {

            do {

                //String s = cursor.getString(0);
                Note note = new Note();
                note.setId(Integer.valueOf(cursor.getString(0)));
                note.setNote_text(cursor.getString(1));
                note.setNote_data(cursor.getString(2));
                note.setNote_width(cursor.getString(3));
                note.setNote_length(cursor.getString(4));

                noteList.add(note);
            } while (cursor.moveToNext());

        }
        db.close();
        Log.d(LOG_TAG, " noteList " + noteList.toString());
        return noteList;

    }



}
