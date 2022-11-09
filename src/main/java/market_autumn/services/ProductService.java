package market_autumn.services;

import market_autumn.data.Product;
import market_autumn.exceptions.ExistEntityException;
import market_autumn.exceptions.ResourceNotFoundException;
import market_autumn.repositories.ProductRepository;
import market_autumn.repositories.specification.ProductSpecification;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
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

    public Product addProduct(Product product){
        if (productRepository.existsProductByTitle(product.getTitle())) {
            throw new ExistEntityException("This product already exists");
        } else {
            productRepository.save(product);
        }
        return product;
    }

    public void updateProduct(Product product) {
        if (productRepository.existsProductById(product.getId())) {
            productRepository.save(product);
        } else {
            throw new ResourceNotFoundException("Product not found");
        }
    }

    public List<Product> find(Integer p, Integer minPrice, Integer maxPrice, String partTitle){
        Specification<Product> specification = Specification.where(null);
        if (minPrice != null) {
            specification = specification.and(ProductSpecification.priceGreaterOrEqualThan(minPrice));
        }
        if (maxPrice != null) {
            specification = specification.and(ProductSpecification.priceLessOrEqualThan(maxPrice));
        }
        if(partTitle != null) {
            specification = specification.and(ProductSpecification.titleLike(partTitle));
        }
        return productRepository.findAll(specification, PageRequest.of(p -1, 10)).stream().toList();
    }


}
