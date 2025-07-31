package com.anondo.simpleexpenseincometracker;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class show_Data extends AppCompatActivity {

    TextView tvTitle;
    ListView listView;
    HashMap<String,String> hashMap;
    ArrayList<HashMap<String,String>> arrayList;

    public static boolean Expense = true;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        tvTitle=findViewById(R.id.tvTitle);
        listView=findViewById(R.id.listView);

        dbHelper=new DatabaseHelper(show_Data.this);

        if (Expense==true){
            tvTitle.setText("All expense");
        }
        else {
            tvTitle.setText("All income");
        }

        loadData();

    }

    public class MyAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            LayoutInflater inflater = getLayoutInflater();
            View myview = inflater.inflate(R.layout.item, viewGroup, false);

            TextView tvresaon =myview.findViewById(R.id.tvreason);
            TextView tvamount =myview.findViewById(R.id.tvamount);
            TextView tvdelete =myview.findViewById(R.id.tvdelete);

            hashMap=arrayList.get(position);

            String reason =hashMap.get("reason");
            String time =hashMap.get("time");
            String amount =hashMap.get("amount");
            String id =hashMap.get("id");

            tvresaon.setText(""+reason);
            tvamount.setText("BDT "+amount);

            tvdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (Expense==true){
                        dbHelper.deleteExpense(Integer.parseInt(id));
                    }
                    else {
                        dbHelper.deleteIncome(Integer.parseInt(id));
                    }

                    loadData();

                }
            });


            return myview;
        }
    }

    public void loadData(){

        Cursor cursor=null;

        if (Expense==true) {
            cursor=dbHelper.getAllExpense();
        }
        else {
            cursor=dbHelper.getAllIncome();
        }

        if (cursor!=null && cursor.getCount()>0){

            arrayList=new ArrayList<>();

            while (cursor.moveToNext()){

                int id =cursor.getInt(0);
                Double amount=cursor.getDouble(1);
                String reason=cursor.getString(2);
                Double time = cursor.getDouble(3);

                hashMap = new HashMap<>();
                hashMap.put("id",""+id);
                hashMap.put("amount",""+amount);
                hashMap.put("reason",""+reason);
                hashMap.put("time",""+time);
                arrayList.add(hashMap);

                MyAdapter myAdapter = new MyAdapter();
                listView.setAdapter(myAdapter);

            }

        }
        else {
            tvTitle.setText("No data found");
        }

    }

}