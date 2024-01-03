import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent implements OnInit {
  username: string | any;
  // sidebar:boolean = false;
 
 
  constructor(private router: Router) { }
 
  ngOnInit() {
    this.router.events.subscribe((val: any) => {
      if (localStorage.getItem('username') != null) {
        this.username = localStorage.getItem('username');
      }
    })
  }
 
  logOut() {
    localStorage.setItem('username', '');
    localStorage.setItem('token', '');
    this.router.navigate(['/']);
  }

  // toggleSidebar(){
  //   if(this.sidebar){
  //     this.sidebar = false;
  //   }
  //   else{
  //     this.sidebar = true;
  //   }
  // }
}
