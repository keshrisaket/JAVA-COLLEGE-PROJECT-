package com.example.javacollegeproject.dahsboard;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.javacollegeproject.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    RecyclerView recyclerView;

    int check=-2;

    public static int vacSeat =1;
    public static int pos =0;

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



        lab_List.add(new ModalLab(1,"LAB_1","8252",1,1));


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
                        final String name = editlabname.getText().toString().trim();
                        final String id = editlabid.getText().toString().trim();
                        String numberString = editlabnumber.getText().toString().trim();
                        String totalnumberofseatString = editlabtotlaseat.getText().toString().trim();

                        if (numberString.isEmpty() || totalnumberofseatString.isEmpty()) {
                            Toast.makeText(Dashboard.this, "Enter valid LAB NUMBER and TOTAL NUMBER OF SEATS", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        int number;
                        int totalnumberofseat;

                        try {
                            number = Integer.parseInt(numberString);
                            totalnumberofseat = Integer.parseInt(totalnumberofseatString);
                        } catch (NumberFormatException e) {
                            Toast.makeText(Dashboard.this, "Enter valid LAB NUMBER and TOTAL NUMBER OF SEATS", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        String numref = String.valueOf(number);
                        String totalnoref = String.valueOf(totalnumberofseat);

                        if (numref.length() > 8) {
                            Toast.makeText(Dashboard.this, "Enter a LAB NUMBER with a maximum of 8 digits", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (id.isEmpty()) {
                            Toast.makeText(Dashboard.this, "Enter the valid LAB ID", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (name.isEmpty()) {
                            Toast.makeText(Dashboard.this, "Enter the valid LAB NAME", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (totalnoref.length() > 8) {
                            Toast.makeText(Dashboard.this, "Enter a valid TOTAL NUMBER OF SEATS with a maximum of 8 digits", Toast.LENGTH_SHORT).show();
                            return;
                        }

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


        onResume();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (check > -2) {
            // Update the vacant seat count for the specific lab
            lab_List.get(pos).setVacant_seat(vacSeat);

            // Notify the adapter about the change in the specific position
            LabAdapter labAdapter = (LabAdapter) recyclerView.getAdapter();
            if (labAdapter != null) {
                labAdapter.notifyItemChanged(pos);
            }

            // Scroll to the updated position if needed
            recyclerView.scrollToPosition(pos);

            // Debugging prints
            System.out.println("Vacant Seats: " + vacSeat);
            System.out.println("Lab Position: " + pos);
            System.out.println("Check: " + check);
        }

        check++;
    }


}