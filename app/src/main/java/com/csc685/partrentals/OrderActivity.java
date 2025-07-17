package com.csc685.partrentals;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        buttonOrderAction();
        orderSummaryAction();
        initToggleButton();
        buttonLocationAction();
        saveAction();

    }
    private void initToggleButton() {
        final ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButtonEditSave);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setForEditing(toggleButton.isChecked());
                setForEditing(true);
            }
        });
    }
    private void setForEditing(boolean enabled) {
        EditText editMake = findViewById(R.id.editTextCustomerName);
        editMake.setEnabled(enabled);
        EditText editModel = findViewById(R.id.editTextPhoneNumber);
        editModel.setEnabled(enabled);
        EditText editCategory = findViewById(R.id.editTextAddress);
        editCategory.setEnabled(enabled);
        EditText editCity = findViewById(R.id.editTextCity);
        editCity.setEnabled(enabled);
        EditText editState = findViewById(R.id.editTextState);
        editState.setEnabled(enabled);
        EditText editZipCode = findViewById(R.id.editTextZipCode);
        editZipCode.setEnabled(enabled);
        EditText editItem = findViewById(R.id.editTextRentalItem);
        editItem.setEnabled(enabled);
        EditText editDescription = findViewById(R.id.editTextItemDescription);
        editDescription.setEnabled(enabled);
        EditText editQuantity = findViewById(R.id.editTextQuantity);
        editQuantity.setEnabled(enabled);
        if (enabled) {
            editMake.requestFocus();
        } else {
            ScrollView s = findViewById(R.id.scrollViewControls);
            s.fullScroll(ScrollView.FOCUS_UP);
        }
    }
    private void saveAction(){
        Button button = findViewById(R.id.buttonSave);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this, OrderSummaryActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                EditText editTextName = findViewById(R.id.editTextCustomerName);
                String cust_name = editTextName.getText().toString();
                intent.putExtra("cname", cust_name);

                EditText editTextItem = findViewById(R.id.editTextRentalItem);
                String rental_item = editTextItem.getText().toString();
                intent.putExtra("item", rental_item);

                EditText editTextDescription = findViewById(R.id.editTextItemDescription);
                String item_desc = editTextDescription.getText().toString();
                intent.putExtra("desc", item_desc);

                EditText editTextQuantity = findViewById(R.id.editTextQuantity);
                String item_qty = editTextQuantity.getText().toString();
                intent.putExtra("qty", item_qty);

                startActivity(intent);
            }
        });
    }
    private void buttonOrderAction(){
        ImageButton address = findViewById(R.id.imageButtonOrder);
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this, OrderActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    private void orderSummaryAction(){
        ImageButton orders = findViewById(R.id.imageButtonSummary);
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this, OrderSummaryActivity.class);
                String referenced_activity = "You are on Orders Activity";
                intent.putExtra("destination_activity", referenced_activity);
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
                Intent intent = new Intent(OrderActivity.this, LocationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }


}