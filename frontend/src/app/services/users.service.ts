import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  private url = "http://localhost:8080";
  constructor(private http: HttpClient) { }
  getAllUsers() {
    return this.http.get<any>(`${this.url}/getAll`);
  }

  getUserById(userId:string){
    return this.http.get<any>(`${this.url}/${userId}`);
  }

  signUp(userData:any){
    return this.http.post(`${this.url}/signup`,userData,{responseType:"json"});
  }

  updateUser(userData:any){
    return this.http.put(`${this.url}/update`,userData,{responseType:"json"});
  }

  login(username: string, password: string) {
    return this.http.get(`http://localhost:8080/login`, {
      responseType: 'text',
      params: {
        username: username,
        password: password
      }
    })
  }
}
