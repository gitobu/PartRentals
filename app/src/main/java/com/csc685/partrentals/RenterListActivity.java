package com.csc685.partrentals;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RenterListActivity extends AppCompatActivity {
/*private View.OnClickListener onItemClickListener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag(); /*who tagged me*/
        /*use getTag to know the ViewHolder that sent the tag - who tagged me?*/
        //int position = viewHolder.getAdapterPosition();/*Where was the tag*/
        /*With the position known, we go to the activity - here RenterListActivity*/
        /*At this time, we have the position but will do nothing with it*/
        //Intent intent = new Intent(RenterListActivity.this, OrderActivity.class); /*Where do we go*/
        //startActivity(intent);

   // }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_renter_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        buttonOrderAction();
        //orderSummaryAction();
        buttonLocationAction();
        buttonRenterAction();

        RentalDataSource ds = new RentalDataSource(this);

        /*Example of a manually populated array.*/
        ArrayList<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Steve");
        names.add("Tom");

        /*Using the database to populate an array*/
        ArrayList<String> renters;
        try {
            ds.open();
            renters = ds.getRenterNames();
            ds.close();
            RecyclerView renterList = findViewById(R.id.rvRenters);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            renterList.setLayoutManager(layoutManager);
            RentalAdapter rentalAdapter = new RentalAdapter(renters);
            renterList.setAdapter(rentalAdapter);
        } catch (SQLException e) {
            Toast.makeText(this, "Error retrieving renters", Toast.LENGTH_LONG).show();
        }
    }
    private void buttonOrderAction(){
        ImageButton address = findViewById(R.id.imageButtonOrder);
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RenterListActivity.this, OrderActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    /*private void orderSummaryAction(){
        ImageButton orders = findViewById(R.id.imageButtonSummary);
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RenterListActivity.this, OrderSummaryActivity.class);

                String referenced_activity = "You are on Orders Activity";
                intent.putExtra("destination_activity", referenced_activity);

                EditText editTextName = findViewById(R.id.editTextCustomerName);
                String cust_name = editTextName.getText().toString();
                intent.putExtra("cname", cust_name);

                EditText -7editTextItem = findViewById(R.id.editTextRentalItem);+6-
                String rental_item = editTextItem.getText().toString();
                intent.putExtra("item", rental_item);

                EditText editTextDescription = findViewById(R.id.editTextItemDescription);
                String item_desc = editTextDescription.getText().toString();
                intent.putExtra("desc", item_desc);

                EditText editTextQuantity = findViewById(R.id.editTextQuantity);
                String item_qty = editTextQuantity.getText().toString();
                intent.putExtra("qty", item_qty);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
*/
    private void buttonRenterAction(){
        ImageButton address = findViewById(R.id.imageButtonLocation);
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RenterListActivity.this, RenterListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    private void buttonLocationAction(){
        ImageButton address = findViewById(R.id.imageButtonLocation);
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RenterListActivity.this, LocationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}