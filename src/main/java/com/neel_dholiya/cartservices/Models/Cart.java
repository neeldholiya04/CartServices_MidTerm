package com.neel_dholiya.cartservices.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cart {
    public long id;
    public long userId;
    public String date;
    public Product[] products;
}
