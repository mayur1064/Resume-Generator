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

public class WorkExperience extends AppCompatActivity {
    EditText etOrganisation,etDescription;
    Button btnAddWork,btnSaveWork;
    LinearLayout containerWork;
    Map<Integer,EditText> hmOrg = new HashMap<Integer,EditText>();

    Map<Integer,EditText> hmDes = new HashMap<Integer,EditText>();
    List<String> orgList = new ArrayList<String>();
    List<String> desList = new ArrayList<String>();
    List<String> skillList1 = new ArrayList<String>();
    int i = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_experience);

        etOrganisation = findViewById(R.id.etOrganisation);
        etDescription = findViewById(R.id.etDescription);

        hmOrg.clear();
        hmDes.clear();


        hmOrg.put(i,etOrganisation);
        hmDes.put(i,etDescription);

        i++;
        btnAddWork = findViewById(R.id.btnAddExperience);
        btnSaveWork = findViewById(R.id.btnSaveWork);


        containerWork = (LinearLayout)findViewById(R.id.containerWork);

        btnAddWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater1 = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView1 = layoutInflater1.inflate(R.layout.rowworkorganisation, null);
                EditText workExperience1 = (EditText) addView1.findViewById(R.id.etOrganisation1);
                workExperience1.setHint("Organisation "+i);
                hmOrg.put(i,workExperience1);

//                LayoutInflater layoutInflater2 = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView2 = layoutInflater1.inflate(R.layout.rowworkdescription, null);
                EditText workDescription1 = (EditText) addView2.findViewById(R.id.etDescription1);
                workDescription1.setHint("Description "+i);
                hmDes.put(i,workDescription1);

                i++;
                containerWork.addView(addView1);
                containerWork.addView(addView2);

            }
        });

        btnSaveWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();


                for(int t=1;t<=hmOrg.size();t++)
                {
                    String s1 = hmOrg.get(t).getText().toString();
                    String s2 = hmDes.get(t).getText().toString();
                    editor.putString("organisation"+t, s1);
                    editor.putString("description"+t, s2);
                    orgList.add(s1);
                    desList.add(s2);
                }

                String length = String.valueOf(hmOrg.size());

                editor.putString("workNum",length);

                editor.apply();

                Intent intent = new Intent(WorkExperience.this, Achievements.class);
                startActivity(intent);




            }
        });

    }


}
