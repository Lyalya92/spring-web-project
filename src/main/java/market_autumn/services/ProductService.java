package market_autumn.services;

import market_autumn.data.Product;
import market_autumn.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public List<Product> findAllByAmountNotNull() {
        return productRepository.findAllByAmountNotNull();
    }

    public List<Product> findAllByPriceBetween(Integer minPrice, Integer maxPrice) {
        return productRepository.findAllByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> findAllByPriceMoreThan(Integer minPrice) {
        return productRepository.findAllByPriceMoreThan(minPrice);
    }

    public List<Product> findAllByPriceLessThan(Integer maxPrice) {
        return productRepository.findAllByPriceLessThan(maxPrice);
    }

    public void changeAmount(Long productId, Integer delta) {
        Product product = productRepository.findById(productId).orElseThrow();
        Integer newAmount = product.getAmount() + delta;
        if (newAmount >= 0) {
            product.setAmount(newAmount);
            productRepository.save(product);
        }
    }

}
