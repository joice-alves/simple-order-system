import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Order, Customer, Product, User } from '../models/types';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private http = inject(HttpClient);

  private baseUrl = 'http://localhost:8080';

  // ==========================================
  // ENDPOINTS DE PEDIDOS (ORDERS)
  // ==========================================
  getOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.baseUrl}/api/pedidos`);
  }
  createOrder(order: Order): Observable<Order> {
    return this.http.post<Order>(`${`${this.baseUrl}`}/api/pedidos`, order);
  }

  // ==========================================
  // ENDPOINTS DE CLIENTES (CUSTOMERS)
  // ==========================================
  getCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(`${this.baseUrl}/api/clientes`);
  }
  createCustomer(customer: Customer): Observable<Customer> {
    return this.http.post<Customer>(`${this.baseUrl}/api/clientes`, customer);
  }

  // ==========================================
  // ENDPOINTS DE PRODUTOS (PRODUCTS)
  // ==========================================
  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.baseUrl}/api/produtos`);
  }
  createProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(`${this.baseUrl}/api/produtos`, product);
  }

  // ==========================================
  // ENDPOINTS DE USUÁRIOS (USERS)
  // ==========================================
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl}/api/usuarios`);
  }
  createUser(user: User): Observable<User> {
    return this.http.post<User>(`${this.baseUrl}/api/usuarios`, user);
  }
}
