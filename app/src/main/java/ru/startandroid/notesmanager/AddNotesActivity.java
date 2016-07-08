package ru.startandroid.notesmanager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddNotesActivity extends AppCompatActivity {
    EditText editText;
    String LOG_TAG = "tanya";
    AppDatabase db;
    Button save;
    Button update;
    String note_text;
    String note_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int flag = Integer.valueOf(getIntent().getStringExtra("flag_place"));
        setContentView(R.layout.activity_add_notes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editText = (EditText) findViewById(R.id.editText);

        save = (Button) findViewById(R.id.button);
        update = (Button) findViewById(R.id.button3);


        if (flag == 0) {

            getSupportActionBar().setTitle("Создать заметку");
            update.setEnabled(false);
        } else if (flag == 1) {
            getSupportActionBar().setTitle("Обновить заметку");
            save.setEnabled(false);
            note_text = getIntent().getStringExtra("note_text");
            note_date = getIntent().getStringExtra("note_data");

            editText.setText(note_text);
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onSaveClick(View view) {
        String s = editText.getText().toString();
        if (s.equals("")) {
            Toast t = Toast.makeText(AddNotesActivity.this, "Введите данные", Toast.LENGTH_SHORT);
            t.show();
        } else {
            Calendar c = Calendar.getInstance();//объявление календапря
            Log.d("WebTag", "Calendar c " + c.toString());

            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Log.d("WebTag", "Calendar c c.getTime()" + c.getTime().toString());
            String formatedDate = df.format(c.getTime());

            double lat = 0.0;//широта
            double longT = 0.0;//долгота


            GPSTracker gp = new GPSTracker(AddNotesActivity.this);

            if (gp.canGetLocation()) {
                lat = gp.getLatitude();
                longT = gp.getLongitude();

                Log.d("Широта", "Lat " + String.valueOf(lat));
                Log.d("Долгота", "Long " + String.valueOf(longT));
            } else {
                gp.showSettingsAlert();
            }
            if (lat == 0.0 && longT == 0.0) {
                Toast t = Toast.makeText(AddNotesActivity.this, "Координаты не определены, попробуйте еще раз", Toast.LENGTH_SHORT);
                t.show();

            } else {

                Log.d("formatedDate", formatedDate);
                Note note = new Note(s, formatedDate, String.valueOf(lat), String.valueOf(longT));
                db = new AppDatabase(getApplicationContext());
                db.addrecord(note);

                Toast t = Toast.makeText(AddNotesActivity.this, "Заметка создана", Toast.LENGTH_SHORT);
                t.show();
                //finish();
                //  db.getAllPages();
                //db.getAllPagesDate();
            }
        }
    }

    public void OnUpdateClick(View view) {
        String refreshedNoteText = editText.getText().toString();

        AppDatabase db = new AppDatabase(AddNotesActivity.this);

        db.updateNoteByDate(note_date, refreshedNoteText);

        Toast t = Toast.makeText(this, "Заметка обновлена", Toast.LENGTH_LONG);
        t.show();
        finish();


    }
}
