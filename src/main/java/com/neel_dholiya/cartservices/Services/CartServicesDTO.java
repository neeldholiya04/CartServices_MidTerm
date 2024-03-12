package com.neel_dholiya.cartservices.Services;

import com.neel_dholiya.cartservices.DTOs.CartDTO;
import com.neel_dholiya.cartservices.DTOs.ProductDTO;
import com.neel_dholiya.cartservices.Models.Cart;
import com.neel_dholiya.cartservices.Models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServicesDTO implements CartServices {
    RestTemplate restTemplate = new RestTemplate();

    @Override
public Cart getCartById(int id) {
    CartDTO cartDTO = restTemplate.getForObject(
            "https://fakestoreapi.com/carts/" + id,
            CartDTO.class
    );
    Cart cart = new Cart();
    cart.setId(cartDTO.getId());
    cart.setUserId(cartDTO.getUserId());
    cart.setDate(cartDTO.getDate());

    // map products from CartDTO to Product and add them to Cart
    Product[] products = Arrays.stream(cartDTO.getProducts())
            .map(productDTO -> {
                Product product = new Product();
                product.setId(productDTO.getProductId());
                product.setQuantity(productDTO.getQuantity());
                return product;
            })
            .toArray(Product[]::new);

    cart.setProducts(products);
    return cart;
}

    @Override
    public List<Cart> getAllProducts() {
        CartDTO[] cartDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/carts",
                CartDTO[].class
        );
        if (cartDTO != null) {
            List<Cart> carts = new ArrayList<>();
            for (CartDTO dto : cartDTO) {
                Cart cart = new Cart();
                cart.setId(dto.getId());
                cart.setUserId(dto.getUserId());
                cart.setDate(dto.getDate());

                List<Product> products = new ArrayList<>();
                for (ProductDTO productDTO : dto.getProducts()) {
                    Product product = new Product();
                    product.setId(productDTO.getProductId());
                    product.setQuantity(productDTO.getQuantity());
                    products.add(product);
                }
                Product[] productsArray = new Product[products.size()];
                productsArray = products.toArray(productsArray);
                cart.setProducts(productsArray);
                carts.add(cart);
            }
            return carts;
        }
        return null;
    }

    @Override
    public List<Cart> getInDateRange(String startDate, String endDate) {
        CartDTO[] cartDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/carts?startdate=" + startDate + "&enddate=" + endDate,
                CartDTO[].class
        );
        if (cartDTO != null) {
            List<Cart> carts = new ArrayList<>();
            for (CartDTO dto : cartDTO) {
                Cart cart = new Cart();
                cart.setId(dto.getId());
                cart.setUserId(dto.getUserId());
                cart.setDate(dto.getDate());

                List<Product> products = new ArrayList<>();
                for (ProductDTO productDTO : dto.getProducts()) {
                    Product product = new Product();
                    product.setId(productDTO.getProductId());
                    product.setQuantity(productDTO.getQuantity());
                    products.add(product);
                }
                Product[] productsArray = new Product[products.size()];
                productsArray = products.toArray(productsArray);
                cart.setProducts(productsArray);
                carts.add(cart);
            }
            return carts;
        }
        return null;
    }

    @Override
    public Cart addNewCart(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setUserId(cart.getUserId());
        cartDTO.setDate(cart.getDate());
        cartDTO.setProducts(Arrays.stream(cart.getProducts())
                .map(product -> {
                    ProductDTO productDTO = new ProductDTO();
                    productDTO.setProductId(product.getId());
                    productDTO.setQuantity(product.getQuantity());
                    return productDTO;
                })
                .collect(Collectors.toList())
                .toArray(new ProductDTO[0])
        );
        restTemplate.postForObject(
                "https://fakestoreapi.com/carts",
                cartDTO,
                CartDTO.class
        );


        return null;
    }

    @Override
    public Cart updateCart(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setUserId(cart.getUserId());
        cartDTO.setDate(cart.getDate());
        cartDTO.setProducts(Arrays.stream(cart.getProducts())
                .map(product -> {
                    ProductDTO productDTO = new ProductDTO();
                    productDTO.setProductId(product.getId());
                    productDTO.setQuantity(product.getQuantity());
                    return productDTO;
                })
                .collect(Collectors.toList())
                .toArray(new ProductDTO[0])
        );
        restTemplate.put(
                "https://fakestoreapi.com/carts/" + cart.getId(),
                cartDTO
        );
        return null;
    }

    @Override
    public void deleteCart(int id) {
        restTemplate.delete("https://fakestoreapi.com/carts/" + id);
    }

    @Override
    public List<Cart> getCartByUserId(int userId) {
        CartDTO[] cartDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/carts/user/" + userId,
                CartDTO[].class
        );
        if (cartDTO != null) {
            List<Cart> carts = new ArrayList<>();
            for (CartDTO dto : cartDTO) {
                Cart cart = new Cart();
                cart.setId(dto.getId());
                cart.setUserId(dto.getUserId());
                cart.setDate(dto.getDate());

                List<Product> products = new ArrayList<>();
                for (ProductDTO productDTO : dto.getProducts()) {
                    Product product = new Product();
                    product.setId(productDTO.getProductId());
                    product.setQuantity(productDTO.getQuantity());
                    products.add(product);
                }
                Product[] productsArray = new Product[products.size()];
                productsArray = products.toArray(productsArray);
                cart.setProducts(productsArray);
                carts.add(cart);
            }
            return carts;
        }
        return null;
    }


}
