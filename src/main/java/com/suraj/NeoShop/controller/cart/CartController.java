package com.suraj.NeoShop.controller.cart;

import com.suraj.NeoShop.model.Cart;
import com.suraj.NeoShop.response.SendResponse;
import com.suraj.NeoShop.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/neoshop/cart")
public class CartController {
    private final CartService service;

    @GetMapping("/{id}")
    public ResponseEntity<SendResponse<Cart>> getCart(@PathVariable Long id) {
        return ResponseEntity.ok(new SendResponse<>(HttpStatus.OK, "Cart Fetched Successfully!", service.getCart(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SendResponse<Void>> clearCart(@PathVariable Long id) {
        service.clearCart(id);
        return ResponseEntity.ok(new SendResponse<>(HttpStatus.NO_CONTENT, "Cart Cleared Successfully!", null));
    }

    @GetMapping("/total")
    public ResponseEntity<SendResponse<BigDecimal>> getTotalPrice(@PathVariable Long id) {
        return ResponseEntity.ok(new SendResponse<>(HttpStatus.OK, "Cart Cleared Successfully!", service.getTotalAmount(id)));
    }
}
