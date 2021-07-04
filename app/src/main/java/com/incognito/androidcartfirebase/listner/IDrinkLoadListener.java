package com.incognito.androidcartfirebase.listner;

import com.incognito.androidcartfirebase.model.DrinkModel;

import java.util.List;

public interface IDrinkLoadListener {
    void onDrinkLoadSuccess(List<DrinkModel> drinkModelList);
    void onDrinkLoadFailed(String message);



}
