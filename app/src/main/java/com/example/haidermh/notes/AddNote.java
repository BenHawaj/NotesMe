package com.example.haidermh.notes;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;

public class AddNote extends AppCompatActivity {


    Toolbar toolbar;
    EditText noteTitle , noteDetails ;
    Calendar c;
    String todaysDate;
    String currentTime;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New Note");



        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);


        noteTitle = findViewById(R.id.nTitle);
        noteDetails = findViewById(R.id.noteDetails);


        noteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length() != 0)
                {
                    getSupportActionBar().setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //get current date & time
        c = Calendar.getInstance();
        todaysDate = c.get(Calendar.YEAR)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.DAY_OF_MONTH);
        currentTime = pad(c.get(Calendar.HOUR))+"/"+ pad(c.get(Calendar.MINUTE));

        Log.d("Calender" ,"Date and Time"  +todaysDate+ "and"+ currentTime );
    }

    private String pad(int i) {
        if(i<10)
        {
            return "0"+i;
   
        }else
            {
                return String.valueOf(i);
            }



    }



    ///new
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu , menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if((item.getItemId() == R.id.delete))
        {

            Toast.makeText(this , "delete" , Toast.LENGTH_LONG).show();
            onBackPressed();
        }


        if((item.getItemId() == R.id.save))
        {

            Note note = new Note(noteTitle.getText().toString() , noteDetails.getText().toString(),todaysDate,currentTime);
            NoteDatabase db = new NoteDatabase(this);
            db.addNote(note);
            Toast.makeText(this , "Save" , Toast.LENGTH_LONG).show();
            goToMain();

        }

        return super.onOptionsItemSelected(item);
    }

    private void goToMain() {

        Intent i = new Intent(this , MainActivity.class);
        startActivity(i);

    }


}
