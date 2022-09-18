package com.mhdarslan.safinaz2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner spinner_category,spinner_product;
    TextView price_text;
    Button btn_submit, btn_view;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get Spinner from the xml
        spinner_category = findViewById(R.id.spinner_category);
        spinner_product = findViewById(R.id.spinner_product);
        price_text = findViewById(R.id.price_text);



        btn_submit = findViewById(R.id.btn_submit);
        btn_view = findViewById(R.id.btn_view);



        //create a list of items for the spinner.
        String[] items_category = new String[]{"Her", "Him", "Kids"};
//        String[] items_sub_category = new String[]{"Pant", "Shirt", "Caps", "Jewellery", "Watch", "Toys"};
        String[] items_product = new String[]{"Diamond Ring", "Silver Ring", "White Gold", "Zale Diamond", "AirBud", "Headphone", "Rolex Watch", "Smart Watch", "Cap", "Umbrella"};
        String[] item_price = new String[]{"98000","12000","99000","89000","24000","2500","5800","6999","699","550"};

        //create an adapter to describe how the items are displayed
        ArrayAdapter<String> adapter_category = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,items_category);
        ArrayAdapter<String> adapter_product = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,items_product);

        // set Spinner adapter
        spinner_category.setAdapter(adapter_category);
        spinner_product.setAdapter(adapter_product);

        spinner_product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                price_text.setText(item_price[position]);

                Log.v("item", parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData(spinner_category.getSelectedItem().toString(),spinner_product.getSelectedItem()
                        .toString(),price_text.getText().toString());
                // Write a message to the database
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference myRef = database.getReference("message");
//
//                myRef.setValue("Hello, World!");
            }
        });

        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,OrderActivity.class);
                startActivity(intent);
            }
        });

    }




    private void sendData(String category, String product, String price){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("category",category);
        hashMap.put("product",product);
        hashMap.put("price",price);

        reference.child("ProductData").push().setValue(hashMap);
        Toast.makeText(this, "Order Submited", Toast.LENGTH_SHORT).show();
    }

}