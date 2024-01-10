import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';
import { AppConstant } from '../util/constants';

@Injectable({
  providedIn: 'root'
})
export class UsersService implements OnInit {
  token = localStorage.getItem("token");
  constructor(private http: HttpClient, private router: Router) { }
  ngOnInit() {
    // this.router.events.subscribe((val: any) => {
    //   if (localStorage.getItem('username') != null) {
    //     this.isLoggedin=true;
    //   }
    //   else{
    //     this.isLoggedin=false;
    //   }
    // })
  }

  getAllUsers() {
    return this.http.get<any>(`${AppConstant.USERS_URI}/getAll`);
  }

  getUserById(userId: string) {
    return this.http.get<any>(`${AppConstant.USERS_URI}/${userId}`);
  }

  // signUp(userData: any) {
  //   return this.http.post(`${AppConstant.USERS_URI}/signup`, userData, { responseType: "json" });
  // }
  signUp(userData: any) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      responseType: 'json' as const
    };
  
    return this.http.post(`${AppConstant.USERS_URI}/signup`, userData, httpOptions);
  }

  updateUser(userData: any) {
    return this.http.put(`${AppConstant.USERS_URI}/update`, userData, { responseType: "json" });
  }

  login(username: string, password: string) {
    const body = {
      username: username,
      password: password
    };
    console.log(body)
    return this.http.post<any>(`http://localhost:8080/users/login`, body);
  }
  getUserByUsername(username: any) {
    return this.http.get<any>(`${AppConstant.USERS_URI}/user/${username}`);
  }
}
