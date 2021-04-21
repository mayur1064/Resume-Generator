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

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public class Achievements extends AppCompatActivity {
    EditText etAchievement;
    Button btnAddAchievement,btnFinal;
    LinearLayout containerArch;
    Map<Integer,EditText> hmArch = new HashMap<Integer,EditText>();
    ArrayList<String> AchList = new ArrayList<String>();
    ArrayList<String> skillList2 = new ArrayList<String>();
    ArrayList<String> orgList1 = new ArrayList<String>();
    ArrayList<String> desList1 = new ArrayList<String>();


    int j = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        etAchievement = findViewById(R.id.etAchievement);
        hmArch.put(j,etAchievement);

        j++;
        btnAddAchievement = findViewById(R.id.btnAddAchievement);
        btnFinal = findViewById(R.id.btnFinal);

        containerArch = (LinearLayout)findViewById(R.id.containerAch);

        btnAddAchievement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.rowachievement, null);
                EditText achievement1 = (EditText) addView.findViewById(R.id.etAchievement1);
                achievement1.setHint("Achievement "+j);

                hmArch.put(j,achievement1);
                j++;
                containerArch.addView(addView);
            }
        });

        btnFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String length = String.valueOf(hmArch.size());

                Toast.makeText(Achievements.this,length, Toast.LENGTH_LONG).show();

                try {
                    createPdf();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(Achievements.this,EndScreen.class);
                startActivity(intent);


            }



        });

    }

    private void createPdf() throws FileNotFoundException {
        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(pdfPath, "Resume.pdf");
        OutputStream outputStream = new FileOutputStream(file);
        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
        String fullName = sharedPreferences.getString("fullName","");
        String email = sharedPreferences.getString("email","" );
        String phoneNumber = sharedPreferences.getString("phoneNumber", "");
        String tagLine = sharedPreferences.getString("tagLine", "");


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


        String skillNumString = sharedPreferences.getString("skillNum", "");
        int skillNum = Integer.parseInt(skillNumString);


        for(int t=0;t<=skillNum;t++)
        {
            String s1 = sharedPreferences.getString("skill"+t, "");
            skillList2.add(s1);
        }
        Toast.makeText(this,"test5", Toast.LENGTH_LONG).show();

        String workNumString = sharedPreferences.getString("skillNum", "");
        int workNum = Integer.parseInt(workNumString);
        Toast.makeText(this,"test6", Toast.LENGTH_LONG).show();

        for(int t=0;t<=workNum;t++)
        {
            String s1 = sharedPreferences.getString("organisation"+t, "");
            String s2 = sharedPreferences.getString("description"+t, "");
            orgList1.add(s1);
            desList1.add(s2);

        }




        float columnWidth[] = {260,260};
        Table table = new Table(columnWidth);
//        table.setBorder(Border.NO_BORDER);

        Paragraph pName = new Paragraph();
        Text tName = new Text(fullName+"\n\n");
        tName.setFontSize(22f).setBold().setFontColor(new DeviceRgb(0, 102, 255));
        Text tTagLine = new Text(tagLine+"\n");
        tTagLine.setFontSize(16f);
        pName.add(tName);
        pName.add(tTagLine);

        Paragraph pEmail = new Paragraph();
        Text tEmail = new Text("Email - "+email+"\n"+"Contact No. -"+phoneNumber+"\n");
        tEmail.setFontSize(15f);
        pEmail.add(tEmail);

        Paragraph pEducation = new Paragraph();
        pEducation.add(new Text("\nEducation \n\n").setFontSize(18f).setBold());
        pEducation.add(new Text("\u2022  "+school+" \n").setFontSize(14f).setBold());
        pEducation.add(new Text(board+"  \n\n").setFontSize(11f));

        pEducation.add(new Text("\u2022  "+highSchool+" \n").setFontSize(14f).setBold());
        pEducation.add(new Text("High School, "+schoolStartYear+" -- "+schoolEndYear+"  \n\n").setFontSize(11f));

        pEducation.add(new Text("\u2022  "+college+" \n").setFontSize(14f).setBold());
        pEducation.add(new Text(degree+", "+field+"  \n").setFontSize(11f));
        pEducation.add(new Text(collegeStartYear+" -- "+collegeEndYear+"  \n\n").setFontSize(11f));


        Paragraph pSkills = new Paragraph();
        pSkills.add(new Text("\nSkills \n\n").setFontSize(18f).setBold());

        List sklList = new List();
        sklList.setListSymbol("\u2022  ");
        sklList.setFontSize(14f);

        skillList2.remove(0);


        for(String s:skillList2)
        {
            sklList.add(s);
        }
        pSkills.add(sklList);

        Paragraph pWorkExp = new Paragraph();
        pWorkExp.add(new Text("\nWork Experience \n\n").setFontSize(18f).setBold());

        orgList1.remove(0);
        desList1.remove(0);

        for(int p=0;p<orgList1.size();p++)
        {
            pWorkExp.add(new Text("\u2022  "+orgList1.get(p)+" \n").setFontSize(14f).setBold());
            pWorkExp.add(new Text(desList1.get(p)+" \n\n").setFontSize(12f));
        }

        Paragraph pAchievement = new Paragraph();
        pAchievement.add(new Text("\nAchievements \n\n").setFontSize(18f).setBold());
        List achList = new List();
        achList.setListSymbol("\u2022  ");
        achList.setFontSize(14f);


        for(int p=1;p<=hmArch.size();p++)
        {
            String s1 = hmArch.get(p).getText().toString();
            achList.add(s1);

        }
        pAchievement.add(achList);

        table.addCell(new Cell().add(pName).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(pEmail).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(pEducation).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(pSkills).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(pWorkExp).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(pAchievement).setBorder(Border.NO_BORDER));


        document.add(table);
        document.close();
        Toast.makeText(this,"Resume Created SuccessFully \n Please Check Downloads", Toast.LENGTH_LONG).show();

    }


}