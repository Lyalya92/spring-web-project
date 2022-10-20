package market_autumn.controllers;

import market_autumn.data.Product;
import market_autumn.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/find/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping("/products/change_amount")
    public void changeAmount(@RequestParam Long productId, @RequestParam Integer delta) {
        productService.changeAmount(productId, delta);
    }

    @GetMapping("/products/not_null")
    public List<Product> findByAmountMoreThanNull() {
        return productService.findAllByAmountNotNull();
    }

    @GetMapping("/products/between")
    public List<Product> findAllByPriceBetween(@RequestParam Integer min, @RequestParam Integer max) {
        return productService.findAllByPriceBetween(min, max);
    }

    @GetMapping("/products/more_than")
    public List<Product> findAllByPriceMoreThan(@RequestParam Integer min) {
        return productService.findAllByPriceMoreThan(min);
    }

    @GetMapping("/products/less_than")
    public List<Product> findAllByPriceLessThan(@RequestParam Integer max) {
        return productService.findAllByPriceLessThan(max);
    }

    @GetMapping("/products/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }


}
