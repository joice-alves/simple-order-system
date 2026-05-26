import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  // Permite o acesso se estiver logado
  if (authService.isLoggedIn()) {
    return true;
  } else {
    // Se não estiver logado, redireciona para a tela de login
    router.navigate(['/login']);
    return false;
  }
};
