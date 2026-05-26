package com.jma.orderapi.service;

import com.jma.orderapi.exceptions.BadResourceRequestException;
import com.jma.orderapi.exceptions.NoSuchResourceFoundException;
import com.jma.orderapi.model.Product;
import com.jma.orderapi.repository.OrderRepository;
import com.jma.orderapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchResourceFoundException("Product not found with id: " + id));
    }

    @Transactional
    public Product create(Product product) {
        // As validações principais (@NotBlank, @Positive) o Jakarta Validation já faz na entrada do Controller,
        // mas regras de negócio complexas entrariam aqui.
        return productRepository.save(product);
    }

    @Transactional
    public Product update(Long id, Product productDetails) {
        Product product = findById(id);

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());

        return productRepository.save(product);
    }

    @Transactional
    public void delete(Long id) {
        Product product = findById(id);

        // Regra: Bloquear a exclusão se o produto estiver em algum pedido
        if (orderRepository.countByProductsProductId(id) > 0) {
            throw new BadResourceRequestException("Cannot delete product. This product is included in existing orders.");
        }

        productRepository.delete(product);
    }
}