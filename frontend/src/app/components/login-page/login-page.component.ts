import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UsersService } from 'src/app/services/users.service';

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
   
    this.userService.login(this.form.value.username,this.form.value.password).subscribe(response=>{
      if(response=="Login successful"){
        localStorage.setItem('username',this.form.value.username);
        this.userService.isLoggedin=true;
        this.router.navigate(['/home']);
      }
      else{
        alert("user details invalid");
      }
    });

  }
}
