package com.neel_dholiya.cartservices.Services;

import com.neel_dholiya.cartservices.Models.Cart;

import java.util.List;

public interface CartServices {
    List<Cart> getAllProducts();
    Cart getCartById(int id);

    List<Cart> getInDateRange(String startDate, String endDate);

    Cart addNewCart(Cart cart);

    Cart updateCart(Cart cart);

    void deleteCart(int id);
    List<Cart> getCartByUserId(int userId);
}
