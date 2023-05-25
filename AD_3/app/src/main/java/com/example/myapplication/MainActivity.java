package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton,registerButton;
    private SQLiteDatabase mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize the UI components
        mUsernameEditText = findViewById(R.id.username);
        mPasswordEditText = findViewById(R.id.password);
        mLoginButton = findViewById(R.id.login);
        registerButton=findViewById(R.id.button2);
        // Open the database connection
        mDatabase = openOrCreateDatabase("mydatabase.db", MODE_PRIVATE, null);
        // Create the users table if it doesn't exist
        mDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)");
        // Set the click listener for the login button
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mUsernameEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                // Query the database for the user with the given username and password
                Cursor cursor = mDatabase.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", new String[]{username, password});
                // Check if the query returned any results
                if (cursor.getCount() > 0) {
                    // The user is authenticated, start the main activity
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // The user is not authenticated, display an error message
                    Toast.makeText(MainActivity.this, "Enter valid credentials or User don't exist", Toast.LENGTH_SHORT).show();
                }
            }
        });


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mUsernameEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();

                ContentValues contentValues = new ContentValues();
                contentValues.put("username",username);
                contentValues.put("password",password);
                Cursor cursor = mDatabase.rawQuery("SELECT * FROM users WHERE username = ? ", new String[]{username});
                // Check if the query returned any results
                if (cursor.getCount() > 0) {
                    Toast.makeText(MainActivity.this, "User already exist!!", Toast.LENGTH_SHORT).show();
                }else{
                    long result= mDatabase.insert("users",null,contentValues);
                    if (result == -1){
                        Toast.makeText(MainActivity.this, "SomeThing went wrong", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}
