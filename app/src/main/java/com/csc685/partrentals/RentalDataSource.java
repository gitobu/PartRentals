package com.csc685.partrentals;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class RentalDataSource {
    private SQLiteDatabase database;
    private RentalDBHelper dbHelper;
    public RentalDataSource(Context context){
        dbHelper = new RentalDBHelper(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        if (database != null && database.isOpen()) {
            database.close(); // this explicitly closes the database connection
        }
    }

    public boolean insertRental(Rental r) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();
            initialValues.put("customer_name", r.getCustomer_name());
            initialValues.put("phone_number", r.getPhone_number());
            initialValues.put("address",r.getAddress());
            initialValues.put("city", r.getCity());
            initialValues.put("state", r.getCity());
            initialValues.put("state", r.getState());
            initialValues.put("zip_code", r.getZip_code());
            initialValues.put("item", r.getItem());
            initialValues.put("quantity", r.getQuantity());
            initialValues.put("description",r.getDescription());
            didSucceed = database.insert("rental", null, initialValues) > 0;
        } catch (Exception e) {
           // throw new RuntimeException(e);
        }
        return didSucceed;
    }

    public boolean updateRental(Rental r) {
        boolean didSucceed = false;
        try {
            Long rowId = (long)  r.getRentalID();
            ContentValues updateValues = new ContentValues();

            updateValues.put("customer_name", r.getCustomer_name());
            updateValues.put("phone_number", r.getPhone_number());
            updateValues.put("address", r.getAddress());
            updateValues.put("city", r.getCity());
            updateValues.put("state", r.getCity());
            updateValues.put("state", r.getState());
            updateValues.put("zip_code", r.getZip_code());
            updateValues.put("item", r.getItem());
            updateValues.put("quantity", r.getQuantity());
            updateValues.put("description",r.getDescription());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return didSucceed;
    }
    public int getLastRentalId(){ //This method gets the id to support creating a new one
        int lastId; // variable to hold last id
        try {
            String query = "Select max(_id) from rental";
            Cursor cursor = database.rawQuery(query, null); //cursor is an object that used to hold and move through the results

            cursor.moveToFirst();
            lastId = cursor.getInt(0);
            cursor.close();
        } catch (Exception e) {
            lastId = -1;
        }
        return lastId;

    }
    public ArrayList<String> getRenterNames() {

        ArrayList<String> renterNames = new ArrayList<>();
        try {
            String query = "select customer_name from rental";
            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) { //loop through all the records in the cursor
                renterNames.add(cursor.getString(0));
                cursor.moveToNext();
            }
            cursor.close();

        } catch (Exception e) {
            renterNames = new ArrayList<String>();
        }
        return renterNames;
    }

    public ArrayList<Rental> getRenterInfo() {
        ArrayList<Rental> renters = new ArrayList<Rental>();
        try {
            String query = "SELECT * FROM Rental";
            Cursor cursor = database.rawQuery(query, null);
            Rental newRental;
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                newRental = new Rental();
                newRental.setRentalID(cursor.getInt(0));
                newRental.setCustomer_name(cursor.getString(1));
                newRental.setPhone_number(cursor.getString(2));
                newRental.setAddress(cursor.getString(3));
                newRental.setCity(cursor.getString(4));
                newRental.setState(cursor.getString(5));
                newRental.setState(cursor.getString(6));
                newRental.setZip_code(cursor.getString(7));
                newRental.setItem(cursor.getString(8));
                newRental.setQuantity(cursor.getInt(9));
                newRental.setDescription(cursor.getString(10));
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception e) {
            renters = new ArrayList<Rental>();
        }
        return renters;
    }
}
