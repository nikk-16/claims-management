import { AfterViewInit, Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent implements OnInit, AfterViewInit {
  username: string | any;
  constructor(private router: Router) { }
  ngOnInit() {
    this.router.events.subscribe((val: any) => {
      if (localStorage.getItem('username') != null) {
        this.username = localStorage.getItem('username');
      }
    })
  }
  ngAfterViewInit() {
    //window.location.reload();
  }
  logOut() {
    localStorage.setItem('username', '');
    window.location.reload();
    this.router.navigate(['/']);
  }
}
