package com.example.coe_complaints;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.io.Serializable;
import java.util.ArrayList;

public class PendingComplaintsRecAdapter extends RecyclerView.Adapter<PendingComplaintsRecAdapter.ViewHolder>{

    ArrayList<Complaint> complaints = new ArrayList<>();

    private Context context;

    public PendingComplaintsRecAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_complaints_item,parent,false);
        PendingComplaintsRecAdapter.ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Complaint complaint = complaints.get(position);


        if(complaint.isPending()) {
            holder.txtIssueName.setText(complaint.getIssueName());
            holder.txtRaisedOn.setText(complaint.getRaisedOnDate());
            holder.txtRaisedBy.setText(complaint.getRaisedBy());

            holder.pendingComplaintCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PendingComplaintDetailsActivity.class);
                    intent.putExtra("complaint", (Serializable) complaint);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return complaints.size();
    }

    public void setComplaints(ArrayList<Complaint> complaints) {
        for (Complaint complaint:complaints) {
            if(complaint.isPending())
            {
                this.complaints.add(complaint);
            }
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView txtIssueName,txtRaisedBy, txtRaisedOn;
        private MaterialCardView pendingComplaintCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIssueName = itemView.findViewById(R.id.txtIssueName);
            txtRaisedBy = itemView.findViewById(R.id.txtRaisedByValue);
            txtRaisedOn = itemView.findViewById(R.id.txtRaisedOnDate);
            pendingComplaintCard = itemView.findViewById(R.id.pendingComplaintsCard);
        }
    }

}
