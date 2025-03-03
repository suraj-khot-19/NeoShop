package com.suraj.NeoShop.repository.cart;

import com.suraj.NeoShop.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
