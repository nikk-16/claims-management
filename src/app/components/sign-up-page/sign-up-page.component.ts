import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';

@Component({
  selector: 'app-sign-up-page',
  templateUrl: './sign-up-page.component.html',
  styleUrl: './sign-up-page.component.scss'
})
export class SignUpPageComponent {
  form:any
  flag:boolean=true;
  constructor(private fb:FormBuilder){
    this.form=this.fb.group({
      username:['',Validators.required],
      mobile:['',Validators.required],
      email:['',[Validators.required,Validators.email]],
      password:['',Validators.required],
      confirmPassword: ['', [Validators.required]]
    }, { validators: this.passwordMatchValidator })
  }
  get username(): AbstractControl { return this.form.get('username'); }
  get mobile(): AbstractControl { return this.form.get('mobile'); }
  get email(): AbstractControl { return this.form.get('email'); }
  get password(): AbstractControl { return this.form.get("password"); }
  get confirmPassword(): AbstractControl { return this.form.get('confirmPassword'); }
   
  passwordMatchValidator:ValidatorFn=(control:any): {[key:string]:boolean} | null=>{
    const password=control.get('password');
    const confirmPassword=control.get('confirmPassword');
    if( password!.value!==confirmPassword!.value){
      return {passwordMisMatch:true};
    }
   else{
    return null;
   }
  }

  signUp(){
    console.log("user added successfully");
    console.log(this.form.value);
  }

}
