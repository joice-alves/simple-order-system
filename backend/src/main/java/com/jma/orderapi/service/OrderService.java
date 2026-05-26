package com.jma.orderapi.service;

import com.jma.orderapi.exceptions.NoSuchResourceFoundException;
import com.jma.orderapi.model.Customer;
import com.jma.orderapi.model.Order;
import com.jma.orderapi.model.Product;
import com.jma.orderapi.repository.CustomerRepository;
import com.jma.orderapi.repository.OrderRepository;
import com.jma.orderapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchResourceFoundException("Order not found with id: " + id));
    }

    @Transactional
    public Order create(Order orderInput) {
        // 1. Validar e buscar o cliente no banco
        Long clientId = orderInput.getCustomer().getCustomerId();
        Customer customer = customerRepository.findById(clientId)
                .orElseThrow(() -> new NoSuchResourceFoundException("Cannot create order. Customer not found with id: " + clientId));

        // 2. Criar a entidade de pedido real que será salva
        Order newOrder = new Order();
        newOrder.setCustomer(customer);

        // 3. Buscar e vincular os produtos reais vindos da lista de IDs do input
        List<Product> productsToBind = new ArrayList<>();
        for (Product p : orderInput.getProducts()) {
            Product realProduct = productRepository.findById(p.getProductId())
                    .orElseThrow(() -> new NoSuchResourceFoundException("Product not found with id: " + p.getProductId()));
            productsToBind.add(realProduct);
        }

        newOrder.setProducts(productsToBind);

        // 4. Salvar no banco (Isso gera o order_id, o timestamp automático e popula a tb_order_product)
        return orderRepository.save(newOrder);
    }

    @Transactional
    public void delete(Long id) {
        Order order = findById(id);
        orderRepository.delete(order);
    }
}