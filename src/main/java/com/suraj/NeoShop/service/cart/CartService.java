package com.suraj.NeoShop.service.cart;

import com.suraj.NeoShop.model.Cart;

import java.math.BigDecimal;

public interface CartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalAmount(Long id);
}
