package com.example.boxingtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public String roundsNum;
    public String roundLen;
    public String restLen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner roundsSpinner = findViewById(R.id.roundsSpinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.rounds, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roundsSpinner.setAdapter(adapter1);
        roundsSpinner.setOnItemSelectedListener(this);

        Spinner roundLenSpinner = findViewById(R.id.roundLenSpinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.roundsLen, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roundLenSpinner.setAdapter(adapter2);
        roundLenSpinner.setOnItemSelectedListener(this);

        Spinner restLenSpinner = findViewById(R.id.restLenSpinner);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.restLen, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        restLenSpinner.setAdapter(adapter3);
        restLenSpinner.setOnItemSelectedListener(this);
    }

    public void NextPage(View v) {

        Intent myIntent = new Intent(this, timerPage.class);
        myIntent.putExtra("key1", roundsNum);
        myIntent.putExtra("key2", roundLen);
        myIntent.putExtra("key3", restLen);
        startActivity(myIntent);
    /*
        Intent i = new Intent(MainActivity.this, timerPage.class);
        i.putExtra("roundsNum",roundsNum);
        startActivity(i);

        Intent j = new Intent(MainActivity.this, timerPage.class);
        j.putExtra("roundLen",roundLen);
        startActivity(j);

        Intent k = new Intent(MainActivity.this, timerPage.class);
        k.putExtra("restLen",restLen);
        startActivity(k);

     */


    }




    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.roundsSpinner:
                roundsNum = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(),roundsNum, Toast.LENGTH_SHORT).show();
                break;
            case R.id.roundLenSpinner:
                roundLen = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(),roundLen, Toast.LENGTH_SHORT).show();
                break;
            case R.id.restLenSpinner:
                restLen = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(),restLen, Toast.LENGTH_SHORT).show();
                break;

        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}