package com.neel_dholiya.cartservices.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDTO {
    public long id;
    public long userId;
    public String date;
    public ProductDTO[] products;
}
