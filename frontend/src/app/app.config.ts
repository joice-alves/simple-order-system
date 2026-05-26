import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';

import { routes } from './app.routes';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }), // <-- Garanta essa linha aqui!
    provideRouter(routes),
    provideHttpClient(),      // Ativa a comunicação com sua API Java
    provideAnimationsAsync()  // Ativa o visual do Angular Material
  ]
};
