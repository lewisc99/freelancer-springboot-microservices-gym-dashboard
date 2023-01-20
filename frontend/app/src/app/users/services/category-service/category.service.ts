import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map, catchError, throwError } from 'rxjs';
import { CategoryDTO } from '../../domain/dtos/categoryDTO';
import { environment } from '../../../../environments/environment.test';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private fullURL = environment.category_URL;

  constructor(private http: HttpClient) { }
  
  
  public getAll() : Observable<CategoryDTO[]>
  {
      return this.http.get<CategoryDTO[]>(this.fullURL).pipe(
        map(
           response => response
        ),
        catchError( error => throwError(() => error ))
      )
  }
}
