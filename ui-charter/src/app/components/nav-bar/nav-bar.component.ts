import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { Router} from '@angular/router';
@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  private local_loggedIn = false;

  constructor(private loginService:LoginService, private router:Router) { }

  // toggleDisplay() {
  // 	this.loggedIn = !this.loggedIn;
  // }

  logout() {
    this.loginService.logout().subscribe(
      res => {
       localStorage.setItem('xAuthToken', '');
      localStorage.setItem('randid', '');
      localStorage.setItem("glob_loggedIn",'false');
      this.local_loggedIn=false;
       location.reload();
      },
      error => {
        console.log(error);
      }
    );
    this.router.navigate(['/'])
  }
  ngOnInit() {
   this.loginService.checkSession().subscribe(
      res => {
        if (localStorage.getItem("glob_loggedIn") == 'true'){
          this.local_loggedIn = true;
        } else {
          this.local_loggedIn = false;
        }
      },
      error => {
        this.local_loggedIn=false;
        this.router.navigate(['/'])
      }
    );
  }

}
