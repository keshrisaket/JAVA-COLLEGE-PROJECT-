package com.example.javacollegeproject.lab;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javacollegeproject.IsValidEntry;
import com.example.javacollegeproject.R;
import com.example.javacollegeproject.dahsboard.LabAdapter;
import com.example.javacollegeproject.studentdetails.StudentDetails;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import com.example.javacollegeproject.dahsboard.Dashboard;

public class lab extends AppCompatActivity {

    public static int seatno=0;


    int labno;
     static int totlanoofseat;

    public static  int vacentseat =-1;
    int posi;


    ArrayList<StudentDetails> arr_data=new ArrayList<>();

    FloatingActionButton floatingActionButton;

    TextView textView;
    RecyclerView recyclerView;

    public  lab(){
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab);
        textView=findViewById(R.id.texttitle);
        recyclerView=findViewById(R.id.recyclerView);  // recyckerView
        floatingActionButton=findViewById(R.id.flotingActionButton);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // BUNDLE PASSINNG
        Intent fromAct=getIntent();
        labno= fromAct.getIntExtra("labnumber",-1);
         totlanoofseat=fromAct.getIntExtra("totalnoofstudent",-1);
          posi=fromAct.getIntExtra("position",-1);
        textView.setText("LAB : "+labno);

            vacentseat = totlanoofseat;
            posi=posi;



        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vacentseat>0) {
                    Dialog dialog = new Dialog(lab.this);
                    dialog.setContentView(R.layout.add_update_studentdetails);

                    EditText name = dialog.findViewById(R.id.name);
                    EditText sex = dialog.findViewById(R.id.sex);
                    EditText college = dialog.findViewById(R.id.college);
                    EditText roll = dialog.findViewById(R.id.roll);
                    EditText course = dialog.findViewById(R.id.course);
                    AppCompatButton addstudent = dialog.findViewById(R.id.additem);
                    AppCompatButton canclestudent = dialog.findViewById(R.id.cancelitem);


                    canclestudent.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    addstudent.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            String stdname = name.getText().toString().trim();
                            String stdSex = sex.getText().toString().trim();
                            String stdcollege = college.getText().toString().trim();
                            String stdroll = roll.getText().toString().trim();
                            String stdcourse = course.getText().toString().trim();


                            if (stdroll.length()>8){
                                Toast.makeText(lab.this,"Enter the valid roll",Toast.LENGTH_SHORT).show();
                            }
                            System.out.println(IsValidEntry.isvalidName(stdname));
                            if (!IsValidEntry.isvalidName(stdname)){
                                Toast.makeText(lab.this, "Enter the valid name", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            System.out.println(IsValidEntry.isvalidSex(stdSex));

                            if (!IsValidEntry.isvalidSex(stdSex)) {
                                Toast.makeText(lab.this, "Enter the valid sex", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if (stdname.isEmpty() ) {
                                Toast.makeText(lab.this, "Enter the valid name", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if (stdSex.isEmpty()) {
                                Toast.makeText(lab.this, "Enter the valid sex", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if (stdcollege.isEmpty()) {
                                Toast.makeText(lab.this, "Enter the valid college", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if (stdroll.isEmpty()) {
                                Toast.makeText(lab.this, "Enter the valid roll", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            int rollNumber;
                            try {
                                rollNumber = Integer.parseInt(stdroll);
                            } catch (NumberFormatException e) {
                                Toast.makeText(lab.this, "Enter a valid roll number", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if (stdcourse.isEmpty()) {
                                Toast.makeText(lab.this, "Enter the valid course", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            arr_data.add(new StudentDetails(stdname, stdSex, stdcollege, rollNumber, stdcourse));
                            StudentAdapter adp = new StudentAdapter(lab.this, arr_data);
                            adp.notifyItemInserted(arr_data.size() - 1);
                            recyclerView.scrollToPosition(arr_data.size() - 1);
                            vacentseat--;
                            seatno++;

                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }else {
                    Toast.makeText(lab.this, "Seat's Are Not Available", Toast.LENGTH_SHORT).show();
                }
            }
        });

        StudentAdapter adpStudent=new StudentAdapter(lab.this,arr_data);
        recyclerView.setAdapter(adpStudent);


    }

    @Override
    public void onBackPressed() {
        Dashboard.vacSeat=vacentseat;
        Dashboard.pos=posi;
        System.out.println(vacentseat);
        System.out.println(posi);
        super.onBackPressed();
    }
}
