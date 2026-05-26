import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
  // Se o usuário entrar na raiz (http://localhost:4200), ele é redirecionado para o login
  { path: '', redirectTo: 'login', pathMatch: 'full' },

  // Rota pública da Tela de Login
  { path: 'login', component: LoginComponent },

  // Rota protegida da Listagem de Pedidos
  // O 'canActivate: [authGuard]' impede a entrada se não estiver logado
  {
    path: 'orders',
    loadComponent: () => import('./components/order-list/order-list.component').then(m => m.OrderListComponent),
    canActivate: [authGuard]
  },

  // Se o usuário digitar qualquer rota que não existe, joga para o login
  { path: '**', redirectTo: 'login' }
];
