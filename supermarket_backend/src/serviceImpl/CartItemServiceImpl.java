package serviceImpl;

import entity.CartItem;

import repoImpl.CartItemRepositoryImpl;


import java.util.List;


public class CartItemServiceImpl {

    private final CartItemRepositoryImpl cartItemRepository = new CartItemRepositoryImpl();

    public void addCartItem(CartItem cartItem) {
      cartItemRepository.addCartItem(cartItem);
    }


    public void updateCartItem(CartItem cartItem) {
        cartItemRepository.updateCartItem(cartItem);
    }


    public void deleteCartItem(int CartItemId) {
        cartItemRepository.deleteCartItem(CartItemId);
    }


    public CartItem getCartItemById(int CartItemId) {
        return cartItemRepository.getCartItemById(CartItemId);
    }


    public List<CartItem> getAllCartItem() {
        return cartItemRepository.getAllCartItem();
    }
}
