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

    @GetMapping("/carts/{id}")
    public Cart getCartById(@PathVariable("id") int id) {
        return cartServices.getCartById(id);
    }

    @GetMapping("/carts?startdate={startDate}&enddate={endDate}")
    public List<Cart> getInDateRange(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        return cartServices.getInDateRange(startDate, endDate);
    }

    @PostMapping("/carts")
    public Cart addNewCart(@RequestBody Cart cart) {
        return cartServices.addNewCart(cart);
    }

    @PutMapping("/carts/{id}")
    public Cart updateCart(@RequestBody Cart cart, @PathVariable("id") long id)
    {
        return cartServices.updateCart(cart);
    }

    @DeleteMapping("/carts/{id}")
    public void deleteCart(@PathVariable("id") int id) {
        cartServices.deleteCart(id);
    }

    @GetMapping("/carts/user/{userId}")
    public List<Cart> getCartByUserId(@PathVariable("userId") int userId) {
        return cartServices.getCartByUserId(userId);
    }
}
