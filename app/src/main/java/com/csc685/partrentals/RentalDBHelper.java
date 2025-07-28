package com.csc685.partrentals;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;

public class RentalDBHelper extends SQLiteOpenHelper { // a subclass of SQLiteOpenHelper
   private static final  String DATABASE_NAME = "rental.db"; //a static variable to name the db
   private static final int DATABASE_VERSION  = 1;//a variable to hold the version number
    //Database creation sql statement
   private static final String CREATE_TABLE_RENTAL = //a string variable for query
           "create table rental (_id integer primary key autoincrement, "
           + "customer_name text not null, phone_number text not null,"
           + "address text not null, city text not null, state text not null,"
           + "zip_code text not null, item text not null, quantity int not null, description text not null);";


    public RentalDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public RentalDBHelper(Context context) { // a constructor method to call the superclass constructor
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_RENTAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //method to upgrade version
        Log.w(RentalDBHelper.class.getName(),
                "Upgrading database from version "+ oldVersion + " to "
        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS rental");
        onCreate(db);
    }
}
