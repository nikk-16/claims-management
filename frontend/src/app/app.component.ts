import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'claims-management';
  username:string|any;
  constructor(private router:Router){
    this.router.events.subscribe((value:any)=>{
      this.username=localStorage.getItem('username');
    })
  }
}
