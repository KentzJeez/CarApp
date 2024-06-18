package com.example.carapp;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel,MainAdapter.myViewHolder> {
    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder,final int position, @NonNull MainModel m) {
        holder.model.setText(m.getModel());
        holder.brand.setText(m.getBrand());
        holder.fueltype.setText(m.getFueltype());

        Glide.with(holder.img.getContext()).load(m.getCurl()).placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark).circleCrop().error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal).into(holder.img);


        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.img.getContext()).setContentHolder(new ViewHolder(R.layout.update_popup)).
                        setExpanded(true,1200).create();
               // dialogPlus.show();


                View view =dialogPlus.getHolderView();
                EditText model=view.findViewById(R.id.txtModel);
                EditText brand=view.findViewById(R.id.txtBrand);
                EditText fueltype=view.findViewById(R.id.txtFuelType);
                EditText curl=view.findViewById(R.id.txtImageUrl);

                Button btnUpdate=view.findViewById(R.id.btnUpdate);
                model.setText(m.getModel());
                brand.setText(m.getBrand());
                fueltype.setText(m.getFueltype());
                curl.setText(m.getCurl());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("model",model.getText().toString());
                        map.put("brand",brand.getText().toString());
                        map.put("fueltype",fueltype.getText().toString());
                        map.put("curl",curl.getText().toString());


                        FirebaseDatabase.getInstance().getReference().child("cars").child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.model.getContext(),"Data Updated Succesfully",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.model.getContext(),"Error While Updating",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.model.getContext());
                builder.setTitle("Are you sure you want to Delete?");
                builder.setMessage("Deleted data can't be regained");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("cars")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.model.getContext(),"Cancelled",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView model,brand,fueltype;
        Button btnEdit,btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img=(CircleImageView)itemView.findViewById(R.id.img1);
            model=(TextView)itemView.findViewById(R.id.modeltext);
            brand=(TextView)itemView.findViewById(R.id.brandtext);
            fueltype=(TextView)itemView.findViewById(R.id.fueltext);

            btnEdit=(Button) itemView.findViewById((R.id.btnEdit));
            btnDelete=(Button) itemView.findViewById((R.id.btnDelete));
        }
    }
}
