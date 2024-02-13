import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RestApiInterceptor implements HttpInterceptor {

  private readonly token: string;

  constructor() {
    this.token = localStorage.getItem('accessToken');
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (this.token) {
      const modReq = req.clone({
        setHeaders: {
          'accessToken': this.token
        }
      });
      return next.handle(modReq);
    }
    return next.handle(req);
  }
}
