package com.suraj.NeoShop.controller.cart;

import com.suraj.NeoShop.model.Cart;
import com.suraj.NeoShop.response.SendResponse;
import com.suraj.NeoShop.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/neoshop/cart")
public class CartController {
    private final CartService service;

    @GetMapping("/{id}")
    public ResponseEntity<SendResponse<Cart>> getCart(@PathVariable Long id) {
        return ResponseEntity.ok(new SendResponse<>(HttpStatus.OK, "Cart Fetched Successfully!", service.getCart(id)));
    }
}
