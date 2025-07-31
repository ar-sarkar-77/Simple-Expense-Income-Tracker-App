package com.anondo.simpleexpenseincometracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class addData extends AppCompatActivity {

    EditText amount,reason;
    TextView title;
    Button btnAdd;

    DatabaseHelper databaseHelper;

    public static Boolean Expense = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        reason=findViewById(R.id.reason);
        amount=findViewById(R.id.amount);
        title=findViewById(R.id.title);
        btnAdd=findViewById(R.id.btnAdd);

        databaseHelper =new DatabaseHelper(addData.this);

        if (Expense==true) {
            title.setText("Add Expense");
        }
        else {
            title.setText("Add Income");
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sAmount = amount.getText().toString();
                String sReason = reason.getText().toString();
                Double amount =Double.parseDouble(sAmount);

                if (Expense==true){

                    databaseHelper.addExpense(amount,sReason);

                }
                else {

                    databaseHelper.addIncome(amount,sReason);

                }

                Toast.makeText(addData.this,"Successful", Toast.LENGTH_LONG).show();

            }
        });




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}