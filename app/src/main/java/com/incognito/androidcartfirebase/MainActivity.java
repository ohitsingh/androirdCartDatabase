package com.incognito.androidcartfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.incognito.androidcartfirebase.adapter.MyDrinkAdapter;
import com.incognito.androidcartfirebase.listner.ICartLoadListner;
import com.incognito.androidcartfirebase.listner.IDrinkLoadListener;
import com.incognito.androidcartfirebase.model.CartModel;
import com.incognito.androidcartfirebase.model.DrinkModel;
import com.incognito.androidcartfirebase.utils.SpaceItemDecoration;
import com.nex3z.notificationbadge.NotificationBadge;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IDrinkLoadListener, ICartLoadListner {

    @BindView(R.id.recycler_drink)
    RecyclerView recyclerDrink;
    @BindView(R.id.mainLayout)
    RelativeLayout mainLayout;
    @BindView(R.id.badge)
    NotificationBadge badge;
    @BindView(R.id.btnCart)
    FrameLayout btnCart;

    IDrinkLoadListener drinkLoadListener;
    ICartLoadListner cartLoadListner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        loadDrinkFromFirebase();


    }

    private void loadDrinkFromFirebase() {
        List<DrinkModel> drinkModels=new ArrayList<>();
        FirebaseDatabase.getInstance()
                .getReference("Drink")
                .addListenerForSingleValueEvent(new ValueEventListener() {                           //<-
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        if (snapshot.exists())
                        {
                            for (DataSnapshot drinkSnapshot:snapshot.getChildren())
                            {
                                DrinkModel drinkModel=drinkSnapshot.getValue(DrinkModel.class);
                                drinkModel.setKey(drinkSnapshot.getKey());
                                drinkModels.add(drinkModel);
                            }
                            drinkLoadListener.onDrinkLoadSuccess(drinkModels);
                        }
                        else drinkLoadListener.onDrinkLoadFailed("Can't find Drink");

                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {
                        drinkLoadListener.onDrinkLoadFailed(error.getMessage());

                    }
                });
    }

    private void init() {

        ButterKnife.bind(this);

        drinkLoadListener=this;
        cartLoadListner=this;

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerDrink.setLayoutManager(gridLayoutManager);
        recyclerDrink.addItemDecoration(new SpaceItemDecoration());
    }

    @Override
    public void onDrinkLoadSuccess(List<DrinkModel> drinkModelList) {
        MyDrinkAdapter adapter=new MyDrinkAdapter(this,drinkModelList);
        recyclerDrink.setAdapter(adapter);

    }

    @Override
    public void onDrinkLoadFailed(String message) {
        Snackbar.make(mainLayout,message,Snackbar.LENGTH_LONG).show();

    }

    @Override
    public void onCartLoadSuccess(List<CartModel> cartModelList) {

    }

    @Override
    public void onCartLoadFailed(String message) {

    }
}