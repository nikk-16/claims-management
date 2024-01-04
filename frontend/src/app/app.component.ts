import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'claims-management';
  // @Input() side:any = true;
  username:string|any="";
  side:boolean=true;
  constructor(private router:Router){
    this.username=localStorage.getItem('username');  
    
  }
  ngOnInit(){
    this.router.events.subscribe((value:any)=>{
      this.username=localStorage.getItem('username');  
    })
  }
  put(side:string){
    console.error(side)
    this.side = !this.side;
  }
}
