package com.example.javacollegeproject.lab;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javacollegeproject.R;
import com.example.javacollegeproject.dahsboard.Dashboard;
import com.example.javacollegeproject.dahsboard.LabAdapter;
import com.example.javacollegeproject.studentdetails.StudentDetails;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class lab extends AppCompatActivity {

    FloatingActionButton floatingActionButton;

   private int n;

   TextView title;
   RecyclerView recyclerView;
   private String labnumber;
    ArrayList<StudentDetails> arr_data=new ArrayList<>(n);

    public lab(){

    }

    public lab(int numOfStudent){
        this.n=numOfStudent;
    }




    Vibrator vibrator;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab);
        floatingActionButton=findViewById(R.id.flotingActionButton);
        vibrator = (Vibrator) getSystemService(lab.this.VIBRATOR_SERVICE);


        title =findViewById(R.id.texttitle);
        recyclerView=findViewById(R.id.recyckerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        Intent fromAct=getIntent();
        labnumber= fromAct.getStringExtra("labnumber");
        String totalnoofseat= fromAct.getStringExtra("totalnoofstudent");

//        n= Integer.parseInt(totalnoofseat);

        title.setText("LAB : "+labnumber);

        arr_data.add(new StudentDetails("saket","male","amity",12,"maca"));


        StudentAdapter adpstdAdp=new StudentAdapter(lab.this,arr_data);
        recyclerView.setAdapter(adpstdAdp);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(70);

                Dialog dialog=new Dialog(lab.this);
                dialog.setContentView(R.layout.add_update_studentdetails);

                EditText stdname=dialog.findViewById(R.id.name);
                EditText stdgender=dialog.findViewById(R.id.sex);
                EditText stdcollege=dialog.findViewById(R.id.college);
                EditText stdcourse=dialog.findViewById(R.id.course);
                EditText stdroll=dialog.findViewById(R.id.roll);
                AppCompatButton btnadd =dialog.findViewById(R.id.additem);
                AppCompatButton btncancle =dialog.findViewById(R.id.cancelitem);

                btncancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final  String name=stdname.getText().toString().trim();
                        final String  gender=stdgender.getText().toString().trim();
                        final String  college=stdcollege.getText().toString().trim();
                        final String course=stdcourse.getText().toString().trim();
                        int roll= Integer.parseInt(stdroll.getText().toString().trim());


                        arr_data.add(new StudentDetails(name,gender,college,roll,course));
                        StudentAdapter labAdapter1=new StudentAdapter(getApplicationContext(),arr_data);
                        labAdapter1.notifyItemInserted(arr_data.size()-1);
                        recyclerView.scrollToPosition(arr_data.size()-1);
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });








    }
}
