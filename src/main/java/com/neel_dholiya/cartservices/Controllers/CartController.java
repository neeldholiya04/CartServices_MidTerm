package com.neel_dholiya.cartservices.Controllers;

import com.neel_dholiya.cartservices.Models.Cart;
import com.neel_dholiya.cartservices.Services.CartServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class CartController {
    CartServices cartServices;

    public CartController(CartServices cartServices) {
        this.cartServices = cartServices;
    }

    // to get all products
    @GetMapping("/carts")
    public List<Cart> getAllProducts() {
        return cartServices.getAllProducts();
    }


    // to get a product by id
    @GetMapping("/carts/{id}")
    public Cart getCartById(@PathVariable("id") int id) {
        return cartServices.getCartById(id);
    }


    // to get all products in a date range
    @GetMapping("/carts?startdate={startDate}&enddate={endDate}")
    public List<Cart> getInDateRange(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        return cartServices.getInDateRange(startDate, endDate);
    }


    // to add a new product
    @PostMapping("/carts")
    public Cart addNewCart(@RequestBody Cart cart) {
        return cartServices.addNewCart(cart);
    }


    // to update a product
    @PutMapping("/carts/{id}")
    public Cart updateCart(@RequestBody Cart cart, @PathVariable("id") long id)
    {
        return cartServices.updateCart(cart);
    }


    // to delete a product
    @DeleteMapping("/carts/{id}")
    public void deleteCart(@PathVariable("id") int id) {
        cartServices.deleteCart(id);
    }


    // to get all products by user id
    @GetMapping("/carts/user/{userId}")
    public List<Cart> getCartByUserId(@PathVariable("userId") int userId) {
        return cartServices.getCartByUserId(userId);
    }
}
