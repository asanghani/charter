import { Component, OnInit } from '@angular/core';
import { Charteruser} from '../../models/charteruser';
import { Router } from '@angular/router';
import { LoginService} from '../../services/login.service';
import { GetUserListService} from '../../services/get-user-list.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  private loggedIn = false;
  private selectdUser : Charteruser;
  private checked:boolean;
  private userList: Charteruser[];
  private allChecked: boolean;
  private removeUserList: Charteruser[] = new Array();

  constructor(
    private getUserListService: GetUserListService,
    private loginService:LoginService,
    private router:Router
  ) { }

  onSelect(cuser:Charteruser) {
    this.selectdUser=cuser;
    console.log(this.selectdUser.username);
    this.router.navigate(['/viewUser', this.selectdUser.username]);
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

    this.getUserListService.getUserList().subscribe(
      res => {
        console.log(res.json());
        this.userList=res.json();
      },
      error =>{
        console.log(error);
      }

    )
  }

}
