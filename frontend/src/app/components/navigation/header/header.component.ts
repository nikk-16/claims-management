import { Component, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent implements OnInit {
  username: string | any;
 
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
