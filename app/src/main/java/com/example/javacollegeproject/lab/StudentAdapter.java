package com.example.javacollegeproject.lab;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javacollegeproject.R;
import com.example.javacollegeproject.studentdetails.StudentDetails;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    Context context;
    ArrayList<StudentDetails>  arr_data;

    public StudentAdapter(Context context,ArrayList<StudentDetails> arr_data){
        this.context=context;
        this.arr_data=arr_data;
    }
    @NonNull
    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.student_list,parent,false);
        ViewHolder view=new ViewHolder(v);
        return view;

    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.ViewHolder holder, int position) {
          holder.name.setText("NAME : "+arr_data.get(position).getName());
          holder.gender.setText("GENDER : "+arr_data.get(position).getGender());
          holder.college.setText("COLLEGE : "+arr_data.get(position).getCollege());
          holder.course.setText("COURSE : "+arr_data.get(position).getCourse());
          holder.roll.setText(String.valueOf("ROLL : "+arr_data.get(position).getCourse()));

          // for deletion in arrdata

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context)
                        .setTitle("DELETE STUDENT ")
                        .setMessage("Are you want to delete ? ")
                        .setIcon(R.drawable.baseline_delete_24)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
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


        // for updation in recycler view

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.add_update_studentdetails);


                EditText stdname=dialog.findViewById(R.id.name);
                EditText stdgender=dialog.findViewById(R.id.sex);
                EditText stdcollege=dialog.findViewById(R.id.college);
                EditText stdcourse=dialog.findViewById(R.id.course);
                EditText stdroll=dialog.findViewById(R.id.roll);
                AppCompatButton btnadd =dialog.findViewById(R.id.additem);
                AppCompatButton btncancle =dialog.findViewById(R.id.cancelitem);
                TextView title=dialog.findViewById(R.id.title);

                title.setText("UPDATE CONTACT");

                stdname.setText(arr_data.get(position).getName());
                stdcollege.setText(arr_data.get(position).getCollege());
                stdcourse.setText(arr_data.get(position).getCourse());
                stdroll.setText(String.valueOf(arr_data.get(position).getRoll()));
                stdgender.setText(arr_data.get(position).getGender());


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
        TextView name,gender,college,roll,course;
        CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            gender=itemView.findViewById(R.id.gender);
            college=itemView.findViewById(R.id.college);
            roll=itemView.findViewById(R.id.roll);
            course=itemView.findViewById(R.id.course);
            cardView=itemView.findViewById(R.id.cardView);
        }
    }
}
