package android.example.mybooklistingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity  {

    CheckBox checkBox1;
    CheckBox checkBox2;
    String query;
    EditText et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_activity);

        et = (EditText) findViewById(R.id.et);

        checkBox1 = findViewById(R.id.authorCB);
        checkBox2 = (CheckBox)findViewById(R.id.titleCB);


    }

    public void onClick(View view){

        if (checkBox1.isChecked()& !checkBox2.isChecked()){
            query = et.getText().toString()+ " + inauthor";
        }
        else if (checkBox2.isChecked()& !checkBox1.isChecked()){
            query = et.getText().toString()+ " + intitle";
        }
        else query = et.getText().toString();

        //if (!query.equals("")){
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("search",query);
        startActivity(intent);}

       // else Toast.makeText(this,"Please input text", Toast.LENGTH_SHORT).show();
    }

