package serviceImpl;

import entity.Product;

import repoImpl.ProductRepositoryImpl;

import util.Constant;

import java.util.List;


public class ProductServiceImpl  {
    private final ProductRepositoryImpl productRepository = new ProductRepositoryImpl();


      public void addProduct(Product product) {
        productRepository.addProduct(product);
    }


    public void updateProduct(Product product) {
        productRepository.updateProduct(product);
    }


    public void deleteProduct(int productId) {
        productRepository.deleteProduct(productId);
    }


    public Product getProductById(int productId) {

        Product product = productRepository.getProductById(productId);
        String filename = product.getImagePath();
        String fileResourcePath = "http://"+ Constant.HOST +":"+ Constant.SERVER_PORT +"/"+ Constant.WAR_FILE_NAME + Constant.RESOURCE_PATH +"/"+ filename;
        product.setImagePath(fileResourcePath);
        return product;
        
    }

    public Product getProductByName(String productName) {
        return productRepository.getProductByName(productName);
    }


    public List<Product> getAllProducts() throws Exception {
        return productRepository.getAllProducts();
    }
}