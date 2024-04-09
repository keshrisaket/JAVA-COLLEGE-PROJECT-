package com.example.javacollegeproject.lab;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javacollegeproject.IsValidEntry;
import com.example.javacollegeproject.R;
import com.example.javacollegeproject.studentdetails.StudentDetails;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    Context context;
    ArrayList<StudentDetails> arr_data;

    public StudentAdapter(Context context,ArrayList<StudentDetails> arr_data){
        this.context=context;
        this.arr_data=arr_data;
    }

    @NonNull
    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.student_list,parent,false);
        ViewHolder view =new ViewHolder(v);

        return view;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.ViewHolder holder, int position) {

        holder.name.setText("NAME : "+arr_data.get(position).getName());
        holder.gender.setText("SEX : "+arr_data.get(position).getGender());
        holder.roll.setText(String.valueOf("ROLL : "+arr_data.get(position).getRoll()));
        holder.course.setText("COURSE : "+arr_data.get(position).getCourse());
        holder.college.setText("COLLEGE : "+arr_data.get(position).getCollege());
        holder.seatno.setText("SEAT : "+lab.seatno);

        // deletion in recycler view
        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(context)
                        .setTitle("DELETE STUDENT")
                        .setMessage("Are you sure want to delete ? ")
                        .setIcon(R.drawable.baseline_delete_24)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                lab.vacentseat--;
                                lab.seatno--;
                                arr_data.remove(position);
                                notifyItemRemoved(position);
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                builder.show();
                return true;
            }
        });

        // updation in recycler view

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.add_update_studentdetails);
                TextView title=dialog.findViewById(R.id.title);
                EditText name=dialog.findViewById(R.id.name);
                EditText sex=dialog.findViewById(R.id.sex);
                EditText college=dialog.findViewById(R.id.college);
                EditText roll=dialog.findViewById(R.id.roll);
                EditText course=dialog.findViewById(R.id.course);
                AppCompatButton  addstudent=dialog.findViewById(R.id.additem);
                AppCompatButton canclestudent=dialog.findViewById(R.id.cancelitem);

                title.setText("UPDATE STUDENT DETAILS");
                name.setText(arr_data.get(position).getName());
                sex.setText(arr_data.get(position).getGender());
                college.setText(arr_data.get(position).getCollege());
                roll.setText(String.valueOf(arr_data.get(position).getRoll()));
                course.setText(arr_data.get(position).getCourse());

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
                            Toast.makeText(context,"Enter the valid roll",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (!IsValidEntry.isvalidName(stdname)){
                            Toast.makeText(context, "Enter the valid name", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (stdname.isEmpty()) {
                            Toast.makeText(context, "Enter the valid name", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (stdSex.isEmpty()) {
                            Toast.makeText(context, "Enter the valid sex", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (stdcollege.isEmpty()) {
                            Toast.makeText(context, "Enter the valid college", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (stdroll.isEmpty()) {
                            Toast.makeText(context, "Enter the valid roll", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        int rollNumber;
                        try {
                            rollNumber = Integer.parseInt(stdroll);
                        } catch (NumberFormatException e) {
                            Toast.makeText(context, "Enter a valid roll number", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (stdcourse.isEmpty()) {
                            Toast.makeText(context, "Enter the valid course", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        arr_data.set(position, new StudentDetails(stdname, stdSex, stdcollege, rollNumber, stdcourse));
                        notifyItemChanged(position);
                        dialog.dismiss();

                    }
                });
                dialog.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return arr_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView seat_no,name,gender,roll,course,college,seatno;

        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            seat_no=itemView.findViewById(R.id.seatno);
            name=itemView.findViewById(R.id.name);
            gender=itemView.findViewById(R.id.gender);
            roll=itemView.findViewById(R.id.roll);
            course=itemView.findViewById(R.id.course);
            college=itemView.findViewById(R.id.college);
            linearLayout=itemView.findViewById(R.id.linearlayout);
            seatno=itemView.findViewById(R.id.seatno);

        }
    }
}
