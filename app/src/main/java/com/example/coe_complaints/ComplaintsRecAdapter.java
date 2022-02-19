package com.example.coe_complaints;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.io.Serializable;
import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmResults;

public class ComplaintsRecAdapter extends RecyclerView.Adapter<ComplaintsRecAdapter.ViewHolder> {
    private RealmResults<Complaint> complaints;

    private Context context;

    public ComplaintsRecAdapter(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.complaints_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int pos=position;
        holder.txtIssueName.setText(complaints.get(pos).getIssueName());
        holder.txtRaisedOnDate.setText(complaints.get(pos).getRaisedOnDate());
        holder.txtStatusValue.setText(complaints.get(pos).getStatus());

        holder.complaintCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ComplaintDetailsActivity.class);
                intent.putExtra("complaint_id",complaints.get(pos).get_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return complaints.size();
    }

    public void setComplaints(RealmResults<Complaint> complaints) {
        this.complaints = complaints;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtIssueName,txtRaisedOnDate,txtStatusValue;
        private MaterialCardView complaintCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIssueName = itemView.findViewById(R.id.txtIssueName);
            txtRaisedOnDate = itemView.findViewById(R.id.txtRaisedOnDate);
            txtStatusValue = itemView.findViewById(R.id.txtStatusValue);
            complaintCard = itemView.findViewById(R.id.complaintCard);
        }
    }

}
