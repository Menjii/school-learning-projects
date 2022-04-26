package pl.edu.pwsztar;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ShoppingCart implements ShoppingCartOperation {

    private static final ArrayList<Product> cart = new ArrayList<>();

    public boolean addProducts(String productName, int price, int quantity) {
        if(price <=0 || quantity <=0) return false;
        if(cart.size() == ShoppingCart.PRODUCTS_LIMIT ) return false;
        Product product = new Product(productName, price, quantity);

        for (Product value : cart) {
            if (value.getName().equals(productName)) {
                if (value.getPrice() == price) {
                    value.setQuantity(value.getQuantity() + quantity);
                    return true;
                } else {
                    return false;
                }
            }
        }
        cart.add(product);
        return true;
    }

    public boolean deleteProducts(String productName, int quantity) {
        if(quantity <=0) return false;
        for (Product product : cart) {
            if (product.getName().equals(productName)) {
                if (product.getQuantity() >= quantity) {
                    product.setQuantity(product.getQuantity() - quantity);
                    return true;
                }
            }
        }
        return false;
    }

    public int getQuantityOfProduct(String productName) {
        for (Product product : cart) {
            if (product.getName().equals(productName)) {
                return product.getQuantity();
            }
        }
        return 0;
    }

    public int getSumProductsPrices() {
        int sum = 0;
        for (Product product : cart) {
            sum += product.getPrice() * product.getQuantity();
        }
        return sum;
    }

    public int getProductPrice(String productName) {
        for (Product product : cart) {
            if (product.getName().equals(productName)) {
                return product.getPrice();
            }
        }
        return 0;
    }

    public List<String> getProductsNames() {
        List<String> productsNames = new ArrayList<>();
        for (Product product : cart) {
            productsNames.add(product.getName());
        }
        return productsNames;
    }
}
