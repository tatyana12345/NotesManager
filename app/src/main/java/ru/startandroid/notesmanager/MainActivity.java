package ru.startandroid.notesmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String LOG_TAG = "tanya";
    NotesAdapter adapter;
    ListView listView;
//    private ArrayList<String> data;

    @Override
    protected void onStart() {
        super.onStart();

        adapter = new NotesAdapter(MainActivity.this, getData(), MainActivity.this.getApplicationContext());
        ListView lvNotes = (ListView) findViewById(R.id.listView);
        lvNotes.setAdapter(adapter);


        getSupportActionBar().setTitle("Заметки (" + String.valueOf(adapter.getCount()) + ")");
        getSupportActionBar().setSubtitle("крутое приложение");

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        adapter = new NotesAdapter(MainActivity.this, getData(), MainActivity.this.getApplicationContext());
        ListView lvNotes = (ListView) findViewById(R.id.listView);
        lvNotes.setAdapter(adapter);


        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    public ArrayList<Note> getData() {

        AppDatabase db = new AppDatabase(MainActivity.this.getApplicationContext());
        final ArrayList<Note> stringItems = new ArrayList<Note>();

        ArrayList<Note> pr = (ArrayList<Note>) db.getAllNotes();

        for (Note p : pr) {
            stringItems.add(p);
        }

        return stringItems;

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.addNotes) {
            Intent intent = new Intent(this, AddNotesActivity.class);
            int flag = 0;
            intent.putExtra("flag_place",String.valueOf(flag));

            startActivity(intent);
        }
        if (id == R.id.dev){
            Intent intent = new Intent(this, CallActivity.class);


            startActivity(intent);

        }
        if (id ==R.id.map){
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}
