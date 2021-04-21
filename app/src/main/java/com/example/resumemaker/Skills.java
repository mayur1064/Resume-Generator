package com.example.resumemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Skills extends AppCompatActivity {
    EditText skill;
    Button btnAddSkill,btnSaveSkills;
    LinearLayout container;
    Map<Integer,EditText> hm = new HashMap<Integer,EditText>();
    List<String> skillList = new ArrayList<String>();
    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);

        skill = findViewById(R.id.etSkill);
        hm.put(i,skill);
        i++;
        btnAddSkill = findViewById(R.id.btnAddSkill);
        btnSaveSkills = findViewById(R.id.btnSaveSkill);

        container = (LinearLayout)findViewById(R.id.container);

        btnAddSkill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.row, null);
                EditText skill1 = (EditText) addView.findViewById(R.id.etSkill1);
                skill1.setHint("Skill "+i);
                hm.put(i,skill1);
                i++;
                container.addView(addView);
            }
        });

        btnSaveSkills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();


                for(int t=1;t<=hm.size();t++)
                {
                    String s1 = hm.get(t).getText().toString();
                    editor.putString("skill"+t, s1);
                    skillList.add(s1);
                }

                String skillNum = String.valueOf(hm.size());

                editor.putString("skillNum",skillNum);

                editor.apply();

                Intent intent = new Intent(Skills.this, WorkExperience.class);
                startActivity(intent);

//                String length = String.valueOf(hm.size());

//                Toast.makeText(Skills.this,length, Toast.LENGTH_LONG).show();




//                try {
//                    createPdf();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }


            }
        });


    }

    private void createPdf() throws  FileNotFoundException {
        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(pdfPath, "Resume.pdf");
        OutputStream outputStream = new FileOutputStream(file);
        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        Toast.makeText(this,"test1", Toast.LENGTH_LONG).show();

        SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
        String fullName = sharedPreferences.getString("fullName","");
        String email = sharedPreferences.getString("email","" );
        String phoneNumber = sharedPreferences.getString("phoneNumber", "");
        String tagLine = sharedPreferences.getString("tagLine", "");

        Toast.makeText(Skills.this,fullName+email+phoneNumber+tagLine, Toast.LENGTH_LONG).show();


        String school = sharedPreferences.getString("school", "");
        String board = sharedPreferences.getString("board", "");
        String highSchool = sharedPreferences.getString("highSchool", "");
        String schoolStartYear = sharedPreferences.getString("schoolStartYear","" );
        String schoolEndYear = sharedPreferences.getString("schoolEndYear","" );
        String degree = sharedPreferences.getString("degree","" );
        String field = sharedPreferences.getString("field", "");
        String college = sharedPreferences.getString("college","" );
        String collegeStartYear = sharedPreferences.getString("collegeStartYear","" );
        String collegeEndYear = sharedPreferences.getString("collegeEndYear", "");




        document.add(new Paragraph(fullName).setFontSize(12f).setItalic());
        document.add(new Paragraph(email).setFontSize(12f).setItalic());
        document.add(new Paragraph(phoneNumber).setFontSize(12f).setItalic());
        document.add(new Paragraph(tagLine).setFontSize(12f).setItalic());
        document.add(new Paragraph(school).setFontSize(12f).setItalic());
        document.add(new Paragraph(board).setFontSize(12f).setItalic());
        document.add(new Paragraph(highSchool).setFontSize(12f).setItalic());
        document.add(new Paragraph(schoolStartYear).setFontSize(12f).setItalic());
        document.add(new Paragraph(schoolEndYear).setFontSize(12f).setItalic());
        document.add(new Paragraph(degree).setFontSize(12f).setItalic());
        document.add(new Paragraph(field).setFontSize(12f).setItalic());
        document.add(new Paragraph(college).setFontSize(12f).setItalic());
        document.add(new Paragraph(collegeStartYear).setFontSize(12f).setItalic());
        document.add(new Paragraph(collegeEndYear).setFontSize(12f).setItalic());

        for(String s:skillList)
        {
            document.add(new Paragraph(s).setFontSize(12f).setItalic());
        }




        document.close();
        Toast.makeText(this,"Resume Created SuccessFully \n Please Check Downloads", Toast.LENGTH_LONG).show();

    }
}
