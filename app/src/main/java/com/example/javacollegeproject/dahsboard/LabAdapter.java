package com.example.javacollegeproject.dahsboard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javacollegeproject.R;
import com.example.javacollegeproject.lab.ModalLab;

import java.util.ArrayList;

public class LabAdapter extends RecyclerView.Adapter<LabAdapter.ViewHolder> {

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
        holder.lab_vacentseat.setText(String.valueOf("TOTAL NO OF VACENT SEAT = "+lab_list.get(position).getVacant_seat()));

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


            }
        });


}

    @Override
    public int getItemCount() {
        return lab_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView lab_no,lab_id,lab_name,lab_totalseat,lab_vacentseat;
       CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lab_no=itemView.findViewById(R.id.lab_no);
            lab_id=itemView.findViewById(R.id.lab_id);
            lab_name=itemView.findViewById(R.id.lab_name);
            lab_totalseat=itemView.findViewById(R.id.lab_totalseat);
            lab_vacentseat=itemView.findViewById(R.id.lab_vacantseat);
            cardView=itemView.findViewById(R.id.cardView);
        }
    }
}
