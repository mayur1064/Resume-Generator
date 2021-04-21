package com.example.resumemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class PersonalDetails extends AppCompatActivity {

    EditText etFullName, etEmail, etNumber, etTagLine;
    Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etNumber = findViewById(R.id.etNumber);
        etTagLine = findViewById(R.id.etTagLine);
        btnSave = findViewById(R.id.btnSave);



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullName = etFullName.getText().toString();
                String email = etEmail.getText().toString();
                String phoneNumber = etNumber.getText().toString();
                String tagLine = etTagLine.getText().toString();


                SharedPreferences sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                editor.clear();

                editor.putString("fullName", fullName);
                editor.putString("email", email);
                editor.putString("phoneNumber", phoneNumber);
                editor.putString("tagLine", tagLine);
                editor.apply();

                Intent intent = new Intent(PersonalDetails.this, EducationDetails.class);
                startActivity(intent);

            }
        });


    }

}

