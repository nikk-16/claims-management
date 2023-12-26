import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.scss'
})
export class LoginPageComponent {

  form:any;
  constructor(private fb:FormBuilder,private router:Router){
    this.form=this.fb.group({
      "email":["",[Validators.required,Validators.email]],
      "password":["",Validators.required]
    })
  }
  login(){
    console.log(this.form.value);
    this.router.navigate([''])
  }
}
