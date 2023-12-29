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
  constructor(private router: Router, private userService: UsersService) { }
  ngOnInit() {
    this.router.events.subscribe((val: any) => {
      if (localStorage.getItem('username') != null) {
        this.username = localStorage.getItem('username');
      }
    })
  }

  logOut() {
    localStorage.setItem('username', '');
    this.userService.isLoggedin = false;
    this.router.navigate(['/']);
  }
}
