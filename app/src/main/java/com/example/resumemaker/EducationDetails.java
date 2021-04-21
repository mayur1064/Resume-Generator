package com.example.resumemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class EducationDetails extends AppCompatActivity {

    EditText etSchool, etBoard, etHighSchool, etSchoolStartYear, etSchoolEndYear, etDegree, etField, etCollege, etCollegeStartYear, etCollegeEndYear;
    Button btnSaveEducation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_details);

        etSchool = findViewById(R.id.etSchool);
        etBoard = findViewById(R.id.etBoard);
        etHighSchool = findViewById(R.id.etHighSchool);
        etSchoolStartYear = findViewById(R.id.etSchoolStartYear);
        etSchoolEndYear = findViewById(R.id.etSchoolEndYear);
        etDegree = findViewById(R.id.etDegree);
        etField = findViewById(R.id.etField);
        etCollege = findViewById(R.id.etCollege);
        etCollegeStartYear = findViewById(R.id.etCollegeStartYear);
        etCollegeEndYear = findViewById(R.id.etCollegeEndYear);
        btnSaveEducation = findViewById(R.id.btnSaveEducation);

        btnSaveEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String school = etSchool.getText().toString();
                String board = etBoard.getText().toString();
                String highSchool = etHighSchool.getText().toString();
                String schoolStartYear = etSchoolStartYear.getText().toString();
                String schoolEndYear = etSchoolEndYear.getText().toString();
                String degree = etDegree.getText().toString();
                String field = etField.getText().toString();
                String college = etCollege.getText().toString();
                String collegeStartYear = etCollegeStartYear.getText().toString();
                String collegeEndYear = etCollegeEndYear.getText().toString();

                SharedPreferences sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                editor.putString("school", school);
                editor.putString("board", board);
                editor.putString("highSchool", highSchool);
                editor.putString("schoolStartYear", schoolStartYear);
                editor.putString("schoolEndYear", schoolEndYear);
                editor.putString("degree", degree);
                editor.putString("field", field);
                editor.putString("college", college);
                editor.putString("collegeStartYear", collegeStartYear);
                editor.putString("collegeEndYear", collegeEndYear);
                editor.apply();


                Intent intent = new Intent(EducationDetails.this, Skills.class);
                startActivity(intent);



            }
        });

    }

}










