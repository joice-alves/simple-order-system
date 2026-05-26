package com.jma.orderapi.service;

import com.jma.orderapi.exceptions.BadResourceRequestException;
import com.jma.orderapi.exceptions.NoSuchResourceFoundException;
import com.jma.orderapi.model.Customer;
import com.jma.orderapi.repository.CustomerRepository;
import com.jma.orderapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchResourceFoundException("Customer not found with id: " + id));
    }

    @Transactional
    public Customer create(Customer customer) {
        // Se você adicionou a restrição de UNIQUE no CPF do banco,
        // o banco já barra duplicidade, mas validações customizadas de negócio entrariam aqui.
        return customerRepository.save(customer);
    }

    @Transactional
    public Customer update(Long id, Customer customerDetails) {
        Customer customer = findById(id);

        customer.setName(customerDetails.getName());
        customer.setCpf(customerDetails.getCpf());
        customer.setPhone(customerDetails.getPhone());

        return customerRepository.save(customer);
    }

    @Transactional
    public void delete(Long id) {
        Customer customer = findById(id);

        // Regra: Bloquear a exclusão se o cliente já tiver pedidos no sistema
        if (orderRepository.countByCustomerCustomerId(id) > 0) {
            throw new BadResourceRequestException("Cannot delete customer. This customer already has orders associated.");
        }

        customerRepository.delete(customer);
    }
}