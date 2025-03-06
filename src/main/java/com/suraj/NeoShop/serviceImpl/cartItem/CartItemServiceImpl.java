package com.suraj.NeoShop.serviceImpl.cartItem;

import com.suraj.NeoShop.model.Cart;
import com.suraj.NeoShop.model.CartItem;
import com.suraj.NeoShop.model.Product;
import com.suraj.NeoShop.repository.cart.CartRepository;
import com.suraj.NeoShop.repository.cartItem.CartItemRepository;
import com.suraj.NeoShop.service.cart.CartService;
import com.suraj.NeoShop.service.cartItem.CartItemService;
import com.suraj.NeoShop.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductService productService;
    private final CartService cartService;

    /// add item to cart
    @Override
    public void addItemToCart(Long productId, int quantity, Long cartId) {
        Product product = productService.getProductById(productId);
        Cart cart = cartService.getCart(cartId);

        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst().orElse(new CartItem());

        if (cartItem.getId() != null) {
            cartItem.setCart(cart);
            cartItem.setQuantity(quantity);
            cartItem.setProduct(product);
            cartItem.setUnitPrice(product.getPrice());
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        cartItem.setTotalAmount();
        cart.addItem(cartItem);
        cartRepository.save(cart);
        cartItemRepository.save(cartItem);
    }
}
