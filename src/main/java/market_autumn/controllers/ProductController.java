package market_autumn.controllers;

import market_autumn.data.Product;
import market_autumn.exceptions.AppError;
import market_autumn.exceptions.ResourceNotFoundException;
import market_autumn.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> getAllProducts(@RequestParam(name = "p", defaultValue = "1") Integer page,
                                        @RequestParam(name = "min_price", required = false) Integer minPrice,
                                        @RequestParam(name = "max_price", required = false) Integer maxPrice,
                                        @RequestParam(name = "title_part", required = false) String partTitle

    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.find(page, minPrice, maxPrice, partTitle);
    }

    @PostMapping("/products")
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @GetMapping("/products/find/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " isn't found"));
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
