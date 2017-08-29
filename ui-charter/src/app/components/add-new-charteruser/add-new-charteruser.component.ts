import { Component, OnInit } from '@angular/core';
import { Charteruser} from '../../models/charteruser';
import { CharteruserService } from '../../services/charteruser.service';
import { LoginService} from '../../services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-new-charteruser',
  templateUrl: './add-new-charteruser.component.html',
  styleUrls: ['./add-new-charteruser.component.css']
})
export class AddNewCharteruserComponent implements OnInit {
  private loggedIn = false;
  private newcharteruser: Charteruser = new Charteruser();
  private charteruserAdded: boolean;

  constructor(
    private charteruserService:CharteruserService,
    private loginService:LoginService,
    private router:Router
  ) { }

  onSubmit(){

    this.charteruserService.sendUser(this.newcharteruser).subscribe(
        res => {
          this.charteruserAdded=true;
          this.newcharteruser = new Charteruser();
        },
        error => {
          console.log(error);
        }
    )
  }
   ngOnInit() {
    this.loginService.checkSession().subscribe(
      res => {
        this.loggedIn=true;
      },
      error => {
        this.loggedIn=false;
        this.router.navigate(['/'])
        location.reload();
      }
    );
   }

}