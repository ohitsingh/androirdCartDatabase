package com.incognito.androidcartfirebase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.incognito.androidcartfirebase.R;
import com.incognito.androidcartfirebase.model.DrinkModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyDrinkAdapter extends RecyclerView.Adapter<MyDrinkAdapter.MyDrinkViewHolder> {

    private Context context;
    private List<DrinkModel> drinkModelList;

    public MyDrinkAdapter(Context context, List<DrinkModel> drinkModelList) {
        this.context = context;
        this.drinkModelList = drinkModelList;
    }

    @NonNull
    @NotNull
    @Override
    public MyDrinkViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new MyDrinkViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_drink_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyDrinkAdapter.MyDrinkViewHolder holder, int position) {
        Glide.with(context)
                .load(drinkModelList.get(position).getImage())
                .into(holder.imageView);
        holder.txtPrice.setText(new StringBuilder("₹ ").append(drinkModelList.get(position).getPrice()));
        holder.txtName.setText(new StringBuilder().append(drinkModelList.get(position).getName()));

    }

    @Override
    public int getItemCount() {
        return drinkModelList.size();
    }

    public class MyDrinkViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.txtName)
        TextView txtName;
        @BindView(R.id.txtPrice)
        TextView txtPrice;


        private Unbinder unbinder;
        public MyDrinkViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            unbinder= ButterKnife.bind(this,itemView);
        }
    }
}
