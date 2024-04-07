package com.example.javacollegeproject.dahsboard;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.javacollegeproject.R;
import com.example.javacollegeproject.lab.ModalLab;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    RecyclerView recyclerView;


    ArrayList<ModalLab> lab_List=new ArrayList<>();
    FloatingActionButton floatingActionButton;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        vibrator = (Vibrator) getSystemService(Dashboard.this.VIBRATOR_SERVICE);
        floatingActionButton = findViewById(R.id.flotingActionButton);
        recyclerView=findViewById(R.id.recyckerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        lab_List.add(new ModalLab(1,"LAB_1","8252",100,20));
        lab_List.add(new ModalLab(1,"LAB_1","8252",100,20));
        lab_List.add(new ModalLab(1,"LAB_1","8252",100,20));


        LabAdapter labAdapter=new LabAdapter(Dashboard.this,lab_List);
        recyclerView.setAdapter(labAdapter);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(70);

                Dialog dialog=new Dialog(Dashboard.this);
                dialog.setContentView(R.layout.add_updatelayout);

                EditText editlabname=dialog.findViewById(R.id.labname);
                EditText editlabnumber=dialog.findViewById(R.id.labnumber);
                EditText editlabid=dialog.findViewById(R.id.labid);
                EditText editlabtotlaseat=dialog.findViewById(R.id.labtotlaseat);
                AppCompatButton btnadd =dialog.findViewById(R.id.additem);

                btnadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final  String name=editlabname.getText().toString().trim();
                        final String  id=editlabid.getText().toString().trim();
                        int number= Integer.parseInt(editlabnumber.getText().toString().trim());
                        int totalnumberofseat= Integer.parseInt(editlabtotlaseat.getText().toString().trim());

                        lab_List.add(new ModalLab(number,name,id,totalnumberofseat,totalnumberofseat));
                        LabAdapter labAdapter1=new LabAdapter(getApplicationContext(),lab_List);
                        labAdapter1.notifyItemInserted(lab_List.size()-1);
                        recyclerView.scrollToPosition(lab_List.size()-1);
                        dialog.dismiss();
                    }
                });

               dialog.show();
            }
        });











    }
}