import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UsersService } from '../../services/users.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.scss'
})
export class LoginPageComponent {

  form:any;
  constructor(private fb:FormBuilder,private router:Router,private userService:UsersService){
    this.form=this.fb.group({
      "username":["",[Validators.required]],
      "password":["",Validators.required]
    })
  }
  login(){
    this.userService.login(this.form.value.username,this.form.value.password).subscribe((response: any)=>{
        //console.log(response);
        localStorage.setItem('username',this.form.value.username);
        localStorage.setItem('token', response.token);
        this.router.navigate(['/home']);
    });
  }
}
