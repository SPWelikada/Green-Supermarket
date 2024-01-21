package serviceImpl;

import entity.ShoppingCart;
import repoImpl.ShoppingCartRepositoryImpl;

import java.util.List;


public class ShoppingCartSericeImpl  {

    private ShoppingCartRepositoryImpl shoppingCartRepository = new ShoppingCartRepositoryImpl();

    public void addShoppingCart(ShoppingCart shoppingCart) {
        shoppingCartRepository.addShoppingCart(shoppingCart);
    }


    public void updateShoppingCart(ShoppingCart shoppingCart) {
        shoppingCartRepository.updateShoppingCart(shoppingCart);
    }


    public void deleteShoppingCart(int ShoppingCartId) {
        shoppingCartRepository.deleteShoppingCart(ShoppingCartId);
    }


    public ShoppingCart getShoppingCartById(int ShoppingCartId) {
        return shoppingCartRepository.getShoppingCartById(ShoppingCartId);
    }


    public List<ShoppingCart> getAllShoppingCart() {
        return shoppingCartRepository.getAllShoppingCart();
    }
}
