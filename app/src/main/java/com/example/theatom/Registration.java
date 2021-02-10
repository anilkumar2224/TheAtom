package com.example.theatom;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    private TextView name;
    private Button Button;
    private ProgressBar progressBar;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    SharedPreferences sharedpreferences;
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().setElevation(0);


        name=(TextView)findViewById(R.id.name);
        Button=(Button)findViewById(R.id.button);
          progressBar=findViewById(R.id.progress);
        progressBar.setVisibility(View.INVISIBLE);

// for storing user name locally
        sharedpreferences =getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);

        Button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                String n=name.getText().toString().trim();
                int nameValue=n.length();
                if(nameValue!=0){
                    progressBar.setVisibility(View.VISIBLE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(Name, n);
                    editor.apply();
                    Toast.makeText(Registration.this,"Welcome "+n,Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Registration.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //if user name exits, will be redirected to Main Page
        String nameValue=sharedpreferences.getString(Name, null);
        if(nameValue!=null){
            Intent intent=new Intent(Registration.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
