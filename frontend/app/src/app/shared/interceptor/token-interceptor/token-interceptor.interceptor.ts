import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenStorageService } from '../../../auth/services/token-storage/token-storage.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private tokenStorage:TokenStorageService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    const token:string  = this.tokenStorage.getToken();
    
    request = request.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`,
        'Content-Type': 'application/json',
      },
    });

    return next.handle(request);
  }
}
