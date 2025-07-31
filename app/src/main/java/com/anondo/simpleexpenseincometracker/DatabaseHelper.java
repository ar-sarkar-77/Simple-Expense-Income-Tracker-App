package com.anondo.simpleexpenseincometracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "digitalMoney", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table expense (id INTEGER primary key autoincrement, amount DOUBLE,reason TEXT,time DOUBLE)");
        db.execSQL("create table income (id INTEGER primary key autoincrement, amount DOUBLE,reason TEXT,time DOUBLE)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists expense");
        db.execSQL("drop table if exists income");

    }

    public void addExpense(Double amount, String reason) {

        SQLiteDatabase dba = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("amount", amount);
        contentValues.put("reason", reason);
        contentValues.put("time", System.currentTimeMillis());
        dba.insert("expense", null, contentValues);

    }

    public Double calculateAllExpense() {

        Double totalExpense = (double) 0;

        SQLiteDatabase dbaa= this.getReadableDatabase();

        Cursor cursor = dbaa.rawQuery("select * from expense ",null);

        if (cursor!=null && cursor.getCount()>0){

            while (cursor.moveToNext()){

                Double amount = cursor.getDouble(1);
                totalExpense = totalExpense+amount;
                //   String reason = cursor.getString(2);

            }
        }
        return totalExpense;
    }

    public void addIncome(Double amount, String reason) {

        SQLiteDatabase dba = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("amount", amount);
        contentValues.put("reason", reason);
        contentValues.put("time", System.currentTimeMillis());
        dba.insert("income", null, contentValues);

    }

    public Double calculateAllIncome() {

        Double totalIncome = (double) 0;

        SQLiteDatabase dbaa= this.getReadableDatabase();

        Cursor cursor = dbaa.rawQuery("select * from income ",null);

        if (cursor!=null && cursor.getCount()>0){

            while (cursor.moveToNext()){

                Double amount = cursor.getDouble(1);
                totalIncome = totalIncome+amount;
                //   String reason = cursor.getString(2);

            }
        }
        return totalIncome;
    }

    public Cursor getAllExpense(){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor =db.rawQuery("select * from expense", null);
        return cursor;
    }

    public Cursor getAllIncome(){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor =db.rawQuery("select * from income", null);
        return cursor;
    }

    public void deleteExpense(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from expense where id like "+id);
    }

    public void deleteIncome(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from income where id like "+id);
    }


}


