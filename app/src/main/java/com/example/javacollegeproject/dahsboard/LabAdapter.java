package com.example.javacollegeproject.dahsboard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javacollegeproject.IsValidEntry;
import com.example.javacollegeproject.R;
import com.example.javacollegeproject.lab.lab;


import java.util.ArrayList;

public class LabAdapter extends RecyclerView.Adapter<LabAdapter.ViewHolder> implements IsValidEntry {

    Context context;
    ArrayList<ModalLab> lab_list;

    public LabAdapter(Context context,ArrayList<ModalLab> lab_list){
        this.context=context;
        this.lab_list=lab_list;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.lab_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.lab_no.setText(String.valueOf("LAB : "+lab_list.get(position).getLab_num()));
        holder.lab_id.setText("LAB ID = "+lab_list.get(position).getLab_id());
        holder.lab_name.setText("NAME  = "+lab_list.get(position).getLab_name());
        holder.lab_totalseat.setText(String.valueOf("TOTAL NO OF SEATS = "+lab_list.get(position).getTotal_seat()));
        holder.lab_vacentseat.setText(String.valueOf("TOTAL NO OF VACANT SEAT = "+lab_list.get(position).getVacant_seat()));
        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the context from the holder's itemView
                Context context = holder.itemView.getContext();

                // Start the lab activity with intent
                Intent intent = new Intent(context,lab.class);

                int labno=lab_list.get(position).getLab_num();
                int labtotalnoofseat=lab_list.get(position).getTotal_seat();
                intent.putExtra("labnumber",labno);
                intent.putExtra("totalnoofstudent",labtotalnoofseat);
                intent.putExtra("position",position);
                System.out.println(position);

                context.startActivity(intent);
            }
        });


        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context)
                        .setTitle("DELETE COMPUTER LABORATORY")
                        .setMessage("Are you sure want to delete ? ")
                        .setIcon(R.drawable.baseline_delete_24)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                lab_list.remove(position);
                                notifyItemRemoved(position);
                            }
                        })
                        .setNegativeButton("NO ", new DialogInterface.OnClickListener() {
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
                dialog.setContentView(R.layout.add_updatelayout);


                TextView title=dialog.findViewById(R.id.title);
                EditText editlabname=dialog.findViewById(R.id.labname);
                EditText editlabnumber=dialog.findViewById(R.id.labnumber);
                EditText editlabid=dialog.findViewById(R.id.labid);
                EditText editlabtotlaseat=dialog.findViewById(R.id.labtotlaseat);
                AppCompatButton btnadd =dialog.findViewById(R.id.additem);
                AppCompatButton btncancle=dialog.findViewById(R.id.cancelitem);

                btncancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                title.setText("UPDATE COMPUTER LABORATORY");
                editlabid.setText(lab_list.get(position).getLab_id());
                editlabname.setText(lab_list.get(position).getLab_name());
                editlabnumber.setText(String.valueOf(lab_list.get(position).getLab_num()));
                editlabtotlaseat.setText(String.valueOf(lab_list.get(position).getTotal_seat()));

                btnadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String name = editlabname.getText().toString().trim();
                        final String id = editlabid.getText().toString().trim();
                        String numberString = editlabnumber.getText().toString().trim();
                        String totalnumberofseatString = editlabtotlaseat.getText().toString().trim();

                        if (numberString.isEmpty() || totalnumberofseatString.isEmpty()) {
                            Toast.makeText(context, "Enter valid LAB NUMBER and TOTAL NUMBER OF SEATS", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        int number;
                        int totalnumberofseat;

                        try {
                            number = Integer.parseInt(numberString);
                            totalnumberofseat = Integer.parseInt(totalnumberofseatString);
                        } catch (NumberFormatException e) {
                            Toast.makeText(context, "Enter valid LAB NUMBER and TOTAL NUMBER OF SEATS", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        String numref = String.valueOf(number);
                        String totalnoref = String.valueOf(totalnumberofseat);

                        if (numref.length() > 8) {
                            Toast.makeText(context, "Enter a LAB NUMBER with a maximum of 8 digits", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (id.isEmpty()) {
                            Toast.makeText(context, "Enter the valid LAB ID", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (name.isEmpty()) {
                            Toast.makeText(context, "Enter the valid LAB NAME", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (totalnoref.length() > 8) {
                            Toast.makeText(context, "Enter a valid TOTAL NUMBER OF SEATS with a maximum of 8 digits", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        lab_list.set(position, new ModalLab(number, name, id, totalnumberofseat, totalnumberofseat));
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
        return lab_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView lab_no,lab_id,lab_name,lab_totalseat,lab_vacentseat;

        AppCompatButton details;
       CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lab_no=itemView.findViewById(R.id.lab_no);
            lab_id=itemView.findViewById(R.id.lab_id);
            lab_name=itemView.findViewById(R.id.lab_name);
            lab_totalseat=itemView.findViewById(R.id.lab_totalseat);
            lab_vacentseat=itemView.findViewById(R.id.lab_vacantseat);
            cardView=itemView.findViewById(R.id.cardView);
            details=itemView.findViewById(R.id.details);
        }
    }
}
