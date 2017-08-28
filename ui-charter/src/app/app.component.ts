import {Component, HostListener, OnInit, NgModule } from '@angular/core';
import { LoginService } from './services/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
  title = 'app';
  constructor(private loginService:LoginService) { }
   
  ngOnInit() {
    console.log("do I see this?")
   }

   @HostListener('window:beforeunload', ['$event'])
 beforeunloadHandler($event) {
   console.log("window closed?")

 
 //  this.logout();
 //    return false;
}
    logout() {
    this.loginService.logout().subscribe(
      res => {
        location.reload();
      },
      error => {
        console.log(error);
      }
    );
  //  this.router.navigate(['/'])
  }
}


