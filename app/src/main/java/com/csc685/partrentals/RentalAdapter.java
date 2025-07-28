package com.csc685.partrentals;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RentalAdapter extends RecyclerView.Adapter {
    private ArrayList<String> renterData;
    /*updated code to have item respond to click*/
    //private View.OnClickListener mOnItemClickListener;
    public class RentalViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewRenter;
       // public TextView textViewPhone;
       // public Button deleteButton;



        public RentalViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRenter = itemView.findViewById(R.id.textViewRenter);
           // textViewPhone = itemView.findViewById(R.id.textPhoneNumber);
           // deleteButton = itemView.findViewById(R.id.buttonDeleteRenter);
            itemView.setTag(this); /*updated code to have item respond to click*/
          //  itemView.setOnClickListener(mOnItemClickListener); /*updated code to have item respond to click*/
        }

        public TextView getRenterTextView(){
            return textViewRenter;
        }
       // public TextView getTextPhoneTextView(){
       //     return textViewPhone;
       // }
        //public Button getDeleteButton(){
        //    return deleteButton;
        //}
    }
    public RentalAdapter(ArrayList<String> arrayList){
        renterData = arrayList;
    }
    /*updated code to have item respond to click*/
    //public void setmOnItemClickListener(View.OnClickListener itemClickListener) {
     //   mOnItemClickListener = itemClickListener;
    //}
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_item_view,parent,false);
        return new RentalViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RentalViewHolder rvh = (RentalViewHolder) holder;
        rvh.getRenterTextView().setText(renterData.get(position));
        //rvh.getTextPhoneTextView().setText(renterData.get(position));
    }

    @Override
    public int getItemCount() {
        return renterData.size();
    }
}
