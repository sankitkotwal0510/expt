package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private TextView fileContentsTextView;
    public File myFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileContentsTextView = findViewById(R.id.fileContentsTextView);

        Button viewButton = findViewById(R.id.viewButton);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFile();
            }
        });

        Button editButton = findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editFile();
            }
        });

        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteFile();
            }
        });
    }

    private void viewFile() {
        try {
            // Read the contents of the file and display them in the text view
            FileInputStream fis = new FileInputStream(myFile);
            byte[] buffer = new byte[(int) myFile.length()];
            fis.read(buffer);
            fis.close();
            String fileText = new String(buffer);
            fileContentsTextView.setText(fileText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createFile(View view) {
        String fileName = "newFile.txt";
        String fileContents = "This is the contents of the new file.";
        try {
            File docsFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            if (!docsFolder.exists()) {
                docsFolder.mkdir();
            }
            File file = new File(docsFolder, fileName);
            myFile = file;
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(fileContents.getBytes());
            fos.close();
            fileContentsTextView.setText("File created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            fileContentsTextView.setText("File creation failed.");
        }
    }


    private void editFile() {
        String fileName = "newFile.txt";
        String fileContents = "This is the contents of the new file after being edited.";
        try {
            File docsFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            if (!docsFolder.exists()) {
                docsFolder.mkdir();
            }
            File file = new File(docsFolder, fileName);
            myFile = file;
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(fileContents.getBytes());
            fos.close();
            FileInputStream fis = new FileInputStream(myFile);
            byte[] buffer = new byte[(int) myFile.length()];
            fis.read(buffer);
            fis.close();
            String fileText = new String(buffer);
            fileContentsTextView.setText(fileText);
        } catch (IOException e) {
            e.printStackTrace();
            fileContentsTextView.setText("File creation failed.");
        }
    }

    private void deleteFile() {
        String fileName = "newFile.txt";
        File docsFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(docsFolder, fileName);
        boolean deleted = file.delete();
        if (deleted) {
            fileContentsTextView.setText("File deleted successfully.");
        } else {
            fileContentsTextView.setText("File deletion failed.");
        }
    }
}