package com.example.haidermh.notes;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class MainActivity extends AppCompatActivity {


    Toolbar toolbar;
    RecyclerView recyclerView;
    Adapter adapter;
    List<Note> notes ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NoteDatabase db = new NoteDatabase(this);
        notes = db.getNotes();

        recyclerView = findViewById(R.id.listOfNotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this , notes);
        recyclerView.setAdapter(adapter);


    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu , menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if((item.getItemId() == R.id.add))
        {
            Intent i = new Intent(this , AddNote.class);
            startActivity(i);
            //finish();
            Toast.makeText(this , "clicekd" , Toast.LENGTH_LONG).show();
        }else
            {
                Toast.makeText(this , "NOT" , Toast.LENGTH_LONG).show();
            }
        return super.onOptionsItemSelected(item);
    }


}





