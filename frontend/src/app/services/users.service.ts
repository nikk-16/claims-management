import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';
import { usersUri } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class UsersService implements OnInit{
  token:String = "ClaimsInterceptor";
  constructor(private http: HttpClient,private router:Router) { }
  ngOnInit(){
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
    return this.http.get<any>(`${usersUri}/getAll`);
  }

  getUserById(userId:string){
    return this.http.get<any>(`${usersUri}/${userId}`);
  }

  signUp(userData:any){
    return this.http.post(`${usersUri}/signup`,userData,{responseType:"json"});
  }

  updateUser(userData:any){
    return this.http.put(`${usersUri}/update`,userData,{responseType:"json"});
  }

  login(username: string, password: string) {
    return this.http.get(`${usersUri}/login`, {
    responseType: 'text',
    params: {
      username: username,
      password: password
    }
  }).pipe(tap(token => {
    localStorage.setItem('token', token);
    localStorage.setItem("username", username);
  }));
  }
}
