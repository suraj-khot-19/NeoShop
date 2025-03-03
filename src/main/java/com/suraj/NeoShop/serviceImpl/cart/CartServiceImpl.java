package com.suraj.NeoShop.serviceImpl.cart;

import com.suraj.NeoShop.exception.ResourceNotFoundException;
import com.suraj.NeoShop.model.Cart;
import com.suraj.NeoShop.model.CartItem;
import com.suraj.NeoShop.repository.cart.CartRepository;
import com.suraj.NeoShop.repository.cartItem.CartItemRepository;
import com.suraj.NeoShop.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public Cart getCart(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart Not Exists!"));
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        return cartRepository.save(cart);
    }
}
