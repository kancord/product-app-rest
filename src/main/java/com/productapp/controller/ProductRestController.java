package com.productapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.productapp.model.Product;
import com.productapp.service.ProductService;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ProductRestController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<Product> productList() {
        return productService.getProductList();
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Product getProduct(@PathVariable int id) {
//        if need validate id > 0
//        if (id<1) {throw new IllegalArgumentException("id must be > 0");}
        return productService.getProductById(id);
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public boolean addProduct(@RequestBody Product product) {
        if (product.getProductName() == null) {
            throw new IllegalArgumentException("Name must be NOT NULL");
        } else {
            if (product.getPrice() <= 0) {
                throw new IllegalArgumentException("Price must be > 0");
            } else {
                return productService.createProduct(product.getProductName(), product.getPrice());
            }
        }
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public boolean updateProduct(@PathVariable int id, @RequestBody Product product) {
        if (product.getProductName() == null) {
            throw new IllegalArgumentException("Name must be NOT NULL");
        } else {
            if (product.getPrice() <= 0) {
                throw new IllegalArgumentException("Price must be > 0");
            } else {
                return productService.updateProduct(id, product.getProductName(), product.getPrice());
            }
        }
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public boolean deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }
}
