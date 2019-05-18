package com.example.itsmine.adventure.Home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.itsmine.adventure.R;

public class LogActivity extends AppCompatActivity {

    Button button_enter;
    EditText editText_nameinput;
    SharedPreferences sharedPreferences;
    private String sharedPrefFile = "com.example.android.mainsharedprefs";
    public static final String LOGGED_KEY = "Logged_Key";
    public static final String NAME_KEY = "Name_Key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        button_enter = (Button) findViewById(R.id.Log_buttonenter);
        editText_nameinput = (EditText) findViewById(R.id.Log_editText_NameInput);
        sharedPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        if(sharedPreferences.getBoolean(LOGGED_KEY,false)){
            goToMainActivity();
        }

        button_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity();
                // save: 1) user is logged in; 2) user's name
                sharedPreferences.edit().putBoolean(LOGGED_KEY, true).apply();
                sharedPreferences.edit().putString(NAME_KEY,editText_nameinput.getText().toString()).apply();
            }
        });
    }

    public void goToMainActivity(){
        Intent intent = new Intent(LogActivity.this,HomeActivity.class);
        startActivity(intent);
    }
}
