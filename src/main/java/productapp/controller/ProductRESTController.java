package productapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import productapp.model.Product;
import productapp.service.ProductService;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ProductRESTController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<Product> productList() {
        return productService.getProductList();
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Product getProduct(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public boolean addProduct(@RequestBody Product prod) {
        return productService.createProduct(prod.getProductName(), prod.getPrice());
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public boolean updateProduct(@PathVariable int id, @RequestBody Product product) {
        return productService.updateProduct(id, product.getProductName(), product.getPrice());
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public boolean deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }

}
