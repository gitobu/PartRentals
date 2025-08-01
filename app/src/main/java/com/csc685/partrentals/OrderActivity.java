package com.csc685.partrentals;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OrderActivity extends AppCompatActivity {
private Rental currentRental;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //currentRental = new Rental();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        buttonOrderAction();
        //orderSummaryAction();
        initToggleButton();
        buttonLocationAction();
        buttonRenterAction();
        saveAction();
        initTextChangedEvents();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            initRenter(extras.getInt("renterId"));
        } else {
            currentRental = new Rental();
        }
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
    private void initTextChangedEvents(){
        /*A reference to the Rental object Customer Name EditText is assigned to the variable etCustomerName
         * This is declared as a final because it is used inside the event code*/
        final EditText etCustomerName = findViewById(R.id.editTextCustomerName);
        /*A text changed listener is added by creating a TextWatcher object
         * The Textwatcher requires three methods as below*/
        etCustomerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                /*The beforeTextChanged is executed when a user presses down on a key to enter
                 * the EditText, but before the value in the EditText is changed*/
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                /*The onTextChanged method is executed after every character change in
                 * the EditText*/
            }

            @Override
            public void afterTextChanged(Editable editable) {
                /*The afterTextChanged method is called after a user completes editing the data
                 * and leaves the EditText*/
                currentRental.setCustomer_name(etCustomerName.getText().toString());
            }
        });

        final EditText etPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        etPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentRental.setPhone_number(etPhoneNumber.getText().toString());
            }
        });
        final EditText etAddress = findViewById(R.id.editTextAddress);
        etAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentRental.setAddress(etAddress.getText().toString());
            }
        });
        final EditText etCity = findViewById(R.id.editTextCity);
        etCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentRental.setCity(etCity.getText().toString());
            }
        });
        final EditText etState = findViewById(R.id.editTextState);
        etState.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
            currentRental.setState(etState.getText().toString());
            }
        });

        final EditText etZipCode = findViewById(R.id.editTextZipCode);
        etZipCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
            currentRental.setZip_code(etZipCode.getText().toString());
            }
        });
        final EditText etItem = findViewById(R.id.editTextRentalItem);
        etItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentRental.setItem(etItem.getText().toString());
            }
        });

        final EditText etQuantity = findViewById(R.id.editTextQuantity);
        etQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
             currentRental.setQuantity(Integer.parseInt(etQuantity.getText().toString()));
            }
        });

        final EditText etDescription = findViewById(R.id.editTextItemDescription);
        etDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentRental.setDescription(etDescription.getText().toString());
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
        button.setOnClickListener(new View.OnClickListener() { // declare the widget to use the listener
            // and determines the operations that should be performed based on the success or failure of the methods
            @Override
            public void onClick(View view) {
            boolean wasSuccessful; //variable captures the return values of RentalDataSource methods
                // and determines the operations that should be performed based on the success or failure of the methods
            hideKeyboard();
            RentalDataSource ds = new RentalDataSource(OrderActivity.this);
            try {
                ds.open(); //open the database
                if (currentRental.getRentalID() == -1) {
                    //if the database opens and is a new Rental record, save it
                    //if -1 we will insert data
                    wasSuccessful = ds.insertRental(currentRental);
                    //successfully opened, get the ID we will use
                    if (wasSuccessful){
                        int newId = ds.getLastRentalId();
                        currentRental.setRentalID(newId);
                    }
                } else {
                    //if the database opens and there is an existing rental record, update it
                    wasSuccessful = ds.updateRental(currentRental);
                }
                ds.close(); //close the database
            } catch (Exception e) {
                wasSuccessful = false;
            }
            if (wasSuccessful) {
                ToggleButton editToggle = findViewById(R.id.toggleButtonEditSave);
                editToggle.toggle();
                setForEditing(false);
            }
            }
        });
    }
    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        EditText editTextName = findViewById(R.id.editTextCustomerName);
        imm.hideSoftInputFromWindow(editTextName.getWindowToken(),0);
        EditText editPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        imm.hideSoftInputFromWindow(editPhoneNumber.getWindowToken(), 0);
        EditText editAddress = findViewById(R.id.editTextAddress);
        imm.hideSoftInputFromWindow(editAddress.getWindowToken(),0);
        EditText editTextCity = findViewById(R.id.editTextCity);
        imm.hideSoftInputFromWindow(editTextCity.getWindowToken(),0);
        EditText editState = findViewById(R.id.editTextState);
        imm.hideSoftInputFromWindow(editState.getWindowToken(),0);
        EditText editTextZipCode = findViewById(R.id.editTextZipCode);
        imm.hideSoftInputFromWindow(editTextZipCode.getWindowToken(),0);
        EditText editItem = findViewById(R.id.editTextRentalItem);
        imm.hideSoftInputFromWindow(editItem.getWindowToken(),0);
        EditText editTextQuantity = findViewById(R.id.editTextQuantity);
        imm.hideSoftInputFromWindow(editTextQuantity.getWindowToken(),0);
        EditText editDescription = findViewById(R.id.editTextItemDescription);
        imm.hideSoftInputFromWindow(editDescription.getWindowToken(),0);
    }
    private void initRenter(int id){
        RentalDataSource ds = new RentalDataSource(OrderActivity.this);
        try {
            ds.open();
            currentRental = ds.getSpecificRental(id);
            ds.close();
        } catch (SQLException e) {
            Toast.makeText(this, "Load Rental Failed", Toast.LENGTH_SHORT).show();
        }
        EditText editName = findViewById(R.id.editTextCustomerName);
        EditText editPhone = findViewById(R.id.editTextPhoneNumber);
        EditText editAddress = findViewById(R.id.editTextAddress);
        EditText editCity = findViewById(R.id.editTextCity);
        EditText editState = findViewById(R.id.editTextState);
        EditText editZipCode = findViewById(R.id.editTextZipCode);
        EditText editItem = findViewById(R.id.editTextRentalItem);
        EditText editQuantity = findViewById(R.id.editTextQuantity);
        EditText editDescription = findViewById(R.id.editTextItemDescription);

        editName.setText(currentRental.getCustomer_name());
        editPhone.setText(currentRental.getPhone_number());
        editAddress.setText(currentRental.getAddress());
        editCity.setText(currentRental.getCity());
        editState.setText(currentRental.getState());
        editZipCode.setText(currentRental.getZip_code());
        editItem.setText(currentRental.getItem());
        editQuantity.setText(currentRental.getQuantity());
        editDescription.setText(currentRental.getDescription());

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
    /*
    private void orderSummaryAction(){
        ImageButton orders = findViewById(R.id.imageButtonSummary);
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this, OrderSummaryActivity.class);
                String referenced_activity = "You are on Orders Activity";
                intent.putExtra("destination_activity", referenced_activity);

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

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
*/
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
    private void buttonRenterAction(){
        ImageButton address = findViewById(R.id.imageButtonRenter);
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this, RenterListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

}