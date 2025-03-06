package com.suraj.NeoShop.service.cartItem;

import com.suraj.NeoShop.model.Cart;

public interface CartItemService {
    void addItemToCart(Long productId,int quantity, Long cartId);
}
