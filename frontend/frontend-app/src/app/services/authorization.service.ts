import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {UserLoginData} from "../models/UserLoginData";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {

  private baseUrl = "api";

  constructor(private http: HttpClient) { }

  loginUser(data: UserLoginData): Observable<any>{
    const url = `${this.baseUrl}/login`;
    return this.http.post(url, data);
  }

  setAccessToken(tokenData: string): void{
    localStorage.setItem("accessToken", tokenData);
  }

  setRefreshToken(tokenData: string): void{
    localStorage.setItem("refreshToken", tokenData);
  }

  setUserToLocalStorage(userLogin: string): void{
    localStorage.setItem("userLogin", userLogin);
  }

  getUserFromLocalStorage(userLogin: string): void{
    localStorage.getItem("userLogin");
  }

  clearUserFromLocalStorage(): void{
    localStorage.removeItem("userLogin");
  }


}

