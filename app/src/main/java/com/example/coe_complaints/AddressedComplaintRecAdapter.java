package com.example.coe_complaints;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class AddressedComplaintRecAdapter extends RecyclerView.Adapter<AddressedComplaintRecAdapter.AddressedComplaintViewHolder>{

    private ArrayList<Complaint> complaints = new ArrayList<>();

    private Context context;

    public AddressedComplaintRecAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public AddressedComplaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_addressed_complaint,parent,false);
        AddressedComplaintViewHolder holder = new AddressedComplaintViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddressedComplaintViewHolder holder, int position) {
        Complaint complaint = complaints.get(position);

        if(complaint.isPending()) {
            holder.txtIssueName.setText(complaint.getIssueName());
            holder.txtAddressedBy.setText(complaint.getRaisedOnDate());
            holder.txtAddressedOnDate.setText(complaint.getRaisedBy());

            holder.addressedComplaintCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class AddressedComplaintViewHolder extends RecyclerView.ViewHolder{

        private TextView txtIssueName,txtAddressedBy,txtAddressedOnDate;
        private MaterialCardView addressedComplaintCard;


        public AddressedComplaintViewHolder(@NonNull View itemView) {
            super(itemView);

            txtIssueName = itemView.findViewById(R.id.txtIssueName);
            txtAddressedBy = itemView.findViewById(R.id.txtAddressedBy);
            txtAddressedOnDate = itemView.findViewById(R.id.txtAddressedOnDate);

            addressedComplaintCard = itemView.findViewById(R.id.addressedComplaintsCard);
        }
    }
}
