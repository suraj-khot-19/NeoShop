package com.suraj.NeoShop.controller.cartItem;

import com.suraj.NeoShop.response.SendResponse;
import com.suraj.NeoShop.service.cartItem.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/neoshop/cart-item")
public class CartItemController {
    private final CartItemService service;

    /// add cart item
    @PostMapping("/new")
    public ResponseEntity<SendResponse<Void>> addItemToCart(@RequestParam Long productId, @RequestParam Long cartId, @RequestParam int quantity) {
        service.addItemToCart(productId, quantity, cartId);
        return ResponseEntity.ok(new SendResponse<>(HttpStatus.OK, "Cart Item Added Successfully!", null));
    }
}
