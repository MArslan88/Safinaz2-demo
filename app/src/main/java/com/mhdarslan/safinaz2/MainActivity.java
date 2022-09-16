package com.mhdarslan.safinaz2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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

    Spinner spinner_category;
    EditText et_price,et_product;
    Button btn_submit, btn_view;

    FirebaseUser fuser;
    private FirebaseAuth mAuth;
    private String currentUserID;

    DatabaseReference reference;

    List<Data> mData;
    DataAdapter dataAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get Spinner from the xml
        spinner_category = findViewById(R.id.spinner_category);

        et_price = findViewById(R.id.et_price);
        et_product = findViewById(R.id.et_product);

        btn_submit = findViewById(R.id.btn_submit);
        btn_view = findViewById(R.id.btn_view);

//        reference = FirebaseDatabase.getInstance().getReference();


        //create a list of items for the spinner.
        String[] items_category = new String[]{"Her", "Him", "Kids"};
//        String[] items_sub_category = new String[]{"Pant", "Shirt", "Caps", "Jewellery", "Watch", "Toys"};
//        String[] items_product = new String[]{"Diamond Ring", "Silver Ring", "PWhite Gold", "Zale Diamond", "AirBud", "Headphone", "Rolex Watch", "Smart Watch", "Cap", "Umbrella"};

        //create an adapter to describe how the items are displayed
        ArrayAdapter<String> adapter_category = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,items_category);

        // set Spinner adapter
        spinner_category.setAdapter(adapter_category);

        mAuth= FirebaseAuth.getInstance();
//        currentUserID=mAuth.getCurrentUser().getUid();
        fuser = FirebaseAuth.getInstance().getCurrentUser();

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData(spinner_category.toString(),et_product.toString(),Integer.parseInt(et_price.toString()));
            }
        });

    }


    private void sendData(String category, String product, int price){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("ProductData");

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("category",category);
        hashMap.put("product",product);
        hashMap.put("price",price);

        reference.child("ProductData").push().setValue(hashMap);
    }

    private void readData(){
        mData = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("ProductData");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mData.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Data data = snapshot.getValue(Data.class);

                    mData.add(data);

                    dataAdapter = new DataAdapter(MainActivity.this, mData);
                    recyclerView.setAdapter(dataAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });
    }
}