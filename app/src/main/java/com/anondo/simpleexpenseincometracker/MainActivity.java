package com.anondo.simpleexpenseincometracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView totalBalance,ExpenseBalance,tvAddExpense,tvEXShowData,incomeBalance,tvAddIncome,tvInShowData;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalBalance=findViewById(R.id.totalBalance);
        ExpenseBalance=findViewById(R.id.ExpenseBalance);
        tvAddExpense=findViewById(R.id.tvAddExpense);
        tvEXShowData=findViewById(R.id.tvEXShowData);
        incomeBalance=findViewById(R.id.incomeBalance);
        tvAddIncome=findViewById(R.id.tvAddIncome);
        tvInShowData=findViewById(R.id.tvInShowData);

        databaseHelper=new DatabaseHelper(MainActivity.this);


        tvAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addData.Expense=true;
                startActivity(new Intent(MainActivity.this,addData.class));

            }
        });

        tvAddIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addData.Expense=false;
                startActivity(new Intent(MainActivity.this,addData.class));

            }
        });

        tvEXShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                show_Data.Expense=true;
                startActivity(new Intent(MainActivity.this,show_Data.class));

            }
        });

        tvInShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                show_Data.Expense=false;
                startActivity(new Intent(MainActivity.this,show_Data.class));

            }
        });

        updateUI();


    }

    public  void updateUI(){

        incomeBalance.setText("BDT "+databaseHelper.calculateAllIncome());
        ExpenseBalance.setText("BTD "+databaseHelper.calculateAllExpense());

        if (databaseHelper.calculateAllIncome()>databaseHelper.calculateAllExpense()){

            Double balance = (databaseHelper.calculateAllIncome()-databaseHelper.calculateAllExpense());

            totalBalance.setText("BDT "+balance);

        } else if (databaseHelper.calculateAllIncome()<databaseHelper.calculateAllExpense()) {

            Double balance = (databaseHelper.calculateAllExpense()-databaseHelper.calculateAllIncome());

            totalBalance.setText("BDT -"+balance);

        } else {

            totalBalance.setText("BDT 0.00");

        }



    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        updateUI();
    }
}