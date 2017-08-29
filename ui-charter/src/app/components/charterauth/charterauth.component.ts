import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../services/login.service';
@Component({
  selector: 'app-charterauth',
  templateUrl: './charterauth.component.html',
  styleUrls: ['./charterauth.component.css']
})
export class CharterauthComponent implements OnInit {

  private credential = {'username':'', 'password' :''};
  private local_loggedIn = false;

  constructor(private LoginService: LoginService) { }

  onSubmit(){
    this.LoginService.sendCredential(this.credential.username, this.credential.password).subscribe(
        res=>{
          console.log(res);
          localStorage.setItem("xAuthToken", res.json().token);
          localStorage.setItem("glob_loggedIn",'true');
       // localStorage.removeItem('glob_loggedIn');
        if (localStorage.getItem("glob_loggedIn") == 'true'){
          this.local_loggedIn = true;
        } else {
          this.local_loggedIn = false;
        }
          location.reload();
        },
        error => {
          console.log(error);
        }
    );
  }

   ngOnInit() { //This method I dont think needed
  	this.LoginService.checkSession().subscribe(
  		res => {
  			if (localStorage.getItem("glob_loggedIn") == 'true'){
          this.local_loggedIn = true;
        } else {
          this.local_loggedIn = false;
        }
  		},
  		error => {
  			this.local_loggedIn =false;
  		}
  	);
   }

}
