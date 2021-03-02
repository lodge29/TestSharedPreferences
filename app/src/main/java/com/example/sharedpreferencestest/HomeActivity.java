package com.example.sharedpreferencestest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    TextView textView_name, textView_email;
    Button button_logout;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textView_name = findViewById(R.id.textName);
        textView_email = findViewById(R.id.textEmail);
        button_logout = findViewById(R.id.button_logout);

        //References SHARED PREFERENCES by name "mypref"
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        //... then retrieves key-value NAME and EMAIL from SHARED PREFERENCES
        String name = sharedPreferences.getString(KEY_NAME, null);
        String email = sharedPreferences.getString(KEY_EMAIL, null);

        if (name != null || email != null) {
            //set data to TextView
            textView_name.setText("Full Name: " + name);
            textView_email.setText("Email: " + email);

            button_logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //ORDER: EDIT->CLEAR()->FINISH()
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.commit();
                    Toast.makeText(HomeActivity.this, "Logged out!", Toast.LENGTH_LONG).show();
                    finish();

                }
            });
        }
    }
}