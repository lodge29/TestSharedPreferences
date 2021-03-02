package com.example.sharedpreferencestest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText_name, editText_email;
    Button button;
    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_name = findViewById(R.id.editTextName);
        editText_email = findViewById(R.id.editTextEmail);
        button = findViewById(R.id.button_save);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        //when open activity first check shared preferences data available or not

        String name = sharedPreferences.getString(KEY_NAME, null);

        // if data exists after OnPause(), intent to HomeActivity to Resume() session
        if (name != null) {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        }

        //if data is NULL click button to save data and intent to HomeActivity
        button .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(KEY_NAME, editText_name.getText().toString());
                editor.putString(KEY_EMAIL, editText_email.getText().toString());
                editor.apply();

                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);

                Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_LONG).show();
            }
        });

    }
}