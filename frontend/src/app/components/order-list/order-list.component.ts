import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { OrderService } from '../../services/order.service';
import { AuthService } from '../../services/auth.service';
import { Order, Customer, Product, User } from '../../models/types';

// Módulos do Angular Material (Adicionamos o MatTabsModule)
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTabsModule } from '@angular/material/tabs';

@Component({
  selector: 'app-order-list',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatToolbarModule,
    MatTabsModule
  ],
  templateUrl: './order-list.component.html',
  styleUrl: './order-list.component.css'
})
export class OrderListComponent implements OnInit {
  private orderService = inject(OrderService);
  private authService = inject(AuthService);
  private router = inject(Router);

  // Listas que vieram do Spring Boot
  orders: Order[] = [];
  customers: Customer[] = [];
  products: Product[] = [];
  users: User[] = [];

  // Definição das colunas de cada uma das 4 tabelas
  orderColumns: string[] = ['orderId', 'customerId', 'itemsCount', 'totalValue', 'createdAt'];
  customerColumns: string[] = ['customerId', 'name', 'cpf', 'phone'];
  productColumns: string[] = ['productId', 'name', 'description', 'price'];
  userColumns: string[] = ['userId', 'name', 'username', 'active'];

  ngOnInit() {
    this.carregarTodosOsDados();
  }

  carregarTodosOsDados() {
    // Dispara as 4 consultas em paralelo para alimentar as abas
    this.orderService.getOrders().subscribe({
      next: (res) => this.orders = res,
      error: (err) => console.error('Erro ao buscar pedidos:', err)
    });

    this.orderService.getCustomers().subscribe({
      next: (res) => this.customers = res,
      error: (err) => console.error('Erro ao buscar clientes:', err)
    });

    this.orderService.getProducts().subscribe({
      next: (res) => this.products = res,
      error: (err) => console.error('Erro ao buscar produtos:', err)
    });

    this.orderService.getUsers().subscribe({
      next: (res) => this.users = res,
      error: (err) => console.error('Erro ao buscar usuários:', err)
    });
  }

  // Funções de navegação para as telas de cadastro (vamos criar a rota dinâmica depois)
  navegarParaCadastro(tipo: string) {
    this.router.navigate([`/orders/new`, { type: tipo }]);
  }

  fazerLogout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
