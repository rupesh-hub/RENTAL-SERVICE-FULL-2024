import {HttpInterceptorFn} from "@angular/common/http";
import {Auth2AuthService} from "./auth2-auth.service";
import {inject} from "@angular/core";

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  console.log("inside auth interceptor !!")
  const oauth2Service = inject(Auth2AuthService);
  const token = oauth2Service.accessToken;
  console.log("token: " + token)
  if(token) {
    req = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
  }
  console.log("inside auth interceptor => request => ", req)
  return next(req);
}
