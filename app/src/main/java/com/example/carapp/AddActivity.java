package com.example.carapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {
    EditText model,brand,fueltype,curl;
    Button btnAdd,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        model=(EditText)findViewById(R.id.txtModel);
        brand=(EditText)findViewById(R.id.txtBrand);
        fueltype=(EditText)findViewById(R.id.txtFuelType);
        curl=(EditText)findViewById(R.id.txtImageUrl);

        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnBack=(Button)findViewById(R.id.btnBack);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                clearAll();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finish();
            }
        });
    }
    private void insertData()
    {
        Map<String,Object>map=new HashMap<>();
        map.put("model",model.getText().toString());
        map.put("brand",brand.getText().toString());
        map.put("fueltype",fueltype.getText().toString());
        map.put("curl",curl.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("cars").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this,"Data Inserted Successfully!",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddActivity.this, "Error While Inserting", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void clearAll()
    {
        model.setText("");
        brand.setText("");
        fueltype.setText("");
        curl.setText("");
    }

    }
