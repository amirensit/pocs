import {HttpEvent, HttpEventType, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
/*
  this is a request interceptor. It is called just before the request leave the frontend part and after all services calls.
  (Something like the final proxy to pass from the frontend).
  To work with the response returned from backend, you need to use tipe() operator after the handle() call:
  return next.handle(req).pipe(...). Remember we always get events with next.handle()
 */
export class AuthInterceptorService implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const modifiedRequest = req.clone({
      params: req.params.set('var2', 'xyz'), // this will ovveride the old value var2 query param.
      headers: req.headers.append('newHeaderFromInterceptor', 'hello')
    });
    return next.handle(modifiedRequest); // (a)
    // (a): it means: let the request continue. You must return this otherwise any request will not be forwarded.
  }

}
