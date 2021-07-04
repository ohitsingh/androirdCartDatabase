package com.incognito.androidcartfirebase.listner;

import com.incognito.androidcartfirebase.model.CartModel;
import com.incognito.androidcartfirebase.model.DrinkModel;

import java.util.List;

public interface ICartLoadListner {
    void onCartLoadSuccess(List<CartModel> cartModelList);
    void onCartLoadFailed(String message);
}
