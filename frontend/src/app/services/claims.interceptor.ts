import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpInterceptorFn, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';

export const claimsInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req);
};

@Injectable()
export class ClaimsInterceptor implements HttpInterceptor{
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // return claimsInterceptor(req, next);
    const token = localStorage.getItem('token');
    if (token) {
      const authReq = req.clone({
        headers: req.headers.set('Authorization', 'Bearer ' + token)
      });
      return next.handle(authReq).pipe(
        catchError((error:HttpErrorResponse)=>this.handleError(error))
      );
    }
    return next.handle(req).pipe(
      catchError((error:HttpErrorResponse)=>this.handleError(error))
    );
  }

  private handleError(error:HttpErrorResponse){
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Client-side error
      console.log('An error occurred:', error.error.message);
      errorMessage = `Client error: ${error.error.message}`;
    } else {
      // Server-side error
      console.log(`Server returned status code ${error.status}`);
      errorMessage = `Server error: ${error.status}`;
    }
    // Return the error message to the caller
    return throwError(()=>new Error(errorMessage));
  }
  
}
