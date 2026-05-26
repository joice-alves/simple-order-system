package com.jma.orderapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "tb_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @NotBlank(message = "Name is a required field")
    private String name;

    @NotBlank(message = "CPF number is a required field")
    @CPF(message = "Invalid CPF number") // Valida os dígitos verificadores do padrão brasileiro
    @Column(unique = true)
    private String cpf;

    @NotBlank(message = "Phone number is a required field")
    // Opcional: Garante um padrão numérico básico para o telefone (ex: apenas números entre 10 e 11 dígitos)
    @Pattern(regexp = "\\d{10,11}", message = "Phone number must contain only numbers (10 or 11 digits)")
    private String phone;

    // Construtor Padrão
    public Customer() {
    }

    // Construtor Completo
    public Customer(Long customerId, String name, String cpf, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
    }

    // Getters e Setters
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}