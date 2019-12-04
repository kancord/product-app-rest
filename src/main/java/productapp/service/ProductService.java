package productapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productapp.model.Product;
import productapp.model.ProductDao;

import java.util.List;

@Service
public class ProductService {
    private ProductDao productDao;

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getProductList() {
        return productDao.getProductList();
    }

    public boolean createProduct(String name, int price) {
        if (price < 1 || price > 5000) {
            return false;
        } else {
            return productDao.createProduct(name, price);
        }
    }

    public boolean updateProduct(int id, String name, int price) {
        if (price < 1 || price > 5000) {
            return false;
        } else {
            return productDao.updateProduct(id, name, price);
        }

    }

    public boolean deleteProduct(int id) {
        return productDao.deleteProduct(id);
    }

    public Product getProductById(int id) {
            if (productDao.hasProduct(id)) {
            return productDao.getProductByID(id);
        } else {
            productDao.createProductWithId(id);
            return productDao.getProductByID(id);
        }
    }
}
