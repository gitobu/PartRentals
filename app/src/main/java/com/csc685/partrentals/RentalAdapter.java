package com.csc685.partrentals;

import android.content.Context;
import android.database.SQLException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RentalAdapter extends RecyclerView.Adapter {
    private ArrayList<Rental> renterData;
    /*updated code to have item respond to click*/
    private View.OnClickListener mOnItemClickListener;

    private boolean isDeleting;

    private Context parentContext;

    public class RentalViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewRenter;
        public TextView textPhone;
        public Button deleteButton;

        public RentalViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRenter = itemView.findViewById(R.id.textViewRenterName);
            textPhone = itemView.findViewById(R.id.textPhoneNumber);
            deleteButton = itemView.findViewById(R.id.buttonDeleteRenter);
            itemView.setTag(this); /*updated code to have item respond to click*/
            itemView.setOnClickListener(mOnItemClickListener); /*updated code to have item respond to click*/
        }

        public TextView getRenterTextView(){
            return textViewRenter;
        }
        public TextView getTextPhoneTextView(){
            return textPhone;
         }
         public Button getDeleteButton(){
            return deleteButton;
        }
    }
    public RentalAdapter(ArrayList<Rental> arrayList, Context context){
        renterData = arrayList;
        parentContext = context;
    }
    /*updated code to have item respond to click*/
    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new RentalViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RentalViewHolder rvh = (RentalViewHolder) holder;
        rvh.getRenterTextView().setText(renterData.get(position).getCustomer_name());
        rvh.getTextPhoneTextView().setText(renterData.get(position).getPhone_number());
        /*If the adapter is in delete mode, the delete button for each vaccine record is set to be
         * visible and a onClickListener is added to the adapters delete method.*/
        if (isDeleting) {
            rvh.getDeleteButton().setVisibility(View.VISIBLE);
            rvh.getDeleteButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteItem(position);
                }
            });
        } else {
            rvh.getDeleteButton().setVisibility(View.INVISIBLE);
        }
    }
    public void setDelete(boolean b) {
        isDeleting = b;
    }
    public void deleteItem(int position) {
        Rental rental = renterData.get(position);
        RentalDataSource dataSource = new RentalDataSource(parentContext);
        try {
            dataSource.open();
            boolean didDelete = dataSource.deleteRental(rental.getRentalID());
            dataSource.close();
            if (didDelete) {
                renterData.remove(position);
                notifyDataSetChanged();
            } else {
                Toast.makeText(parentContext, "Delete failed!", Toast.LENGTH_LONG).show();
            }
        } catch (SQLException e) {
            Toast.makeText(parentContext, "Delete Failed!", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public int getItemCount() {
        return renterData.size();
    }
}
