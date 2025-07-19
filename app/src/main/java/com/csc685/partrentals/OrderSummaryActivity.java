package com.csc685.partrentals;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OrderSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_summary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        buttonOrderAction();
        orderSummaryAction();
        buttonLocationAction();
        getValues();
    }
private void getValues(){
        Intent intent = getIntent();
        String customer_name = intent.getStringExtra("cname");
        TextView textViewCname = findViewById(R.id.textViewCustomer);
        textViewCname.setText("Customer name: " + customer_name);

    String order_item = intent.getStringExtra("item");
    TextView rentalItem = findViewById(R.id.textViewOrderItem);
    rentalItem.setText("Item Type:" + order_item);

    String item_description = intent.getStringExtra("desc");
    TextView itemDescription = findViewById(R.id.textViewDescription);
    itemDescription.setText("Item Description:" + item_description);

    String item_qty = intent.getStringExtra("qty");
    TextView cost = findViewById(R.id.textViewQuantity);
    cost.setText("Item Quantity:" + item_qty);

    int multiplier;

    switch (order_item){
        case "Chairs":
            multiplier = 50;
        break;
        case "Tables":
            multiplier = 100;
        break;
        case "Plates":
            multiplier = 10;
        break;
        default:
            multiplier = 0;
    }
    int total = Integer.parseInt(item_qty) * multiplier;
    TextView totalCost = findViewById(R.id.textViewTotalCost);
    totalCost.setText("Total Cost: " + total);
}
    private void buttonOrderAction(){
        ImageButton imageButton = findViewById(R.id.imageButtonOrder);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderSummaryActivity.this, OrderActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }
    private void orderSummaryAction(){
        ImageButton imageButton = findViewById(R.id.imageButtonSummary);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderSummaryActivity.this, OrderSummaryActivity.class);
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
                Intent intent = new Intent(OrderSummaryActivity.this, LocationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }


}