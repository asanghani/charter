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
  private selectdUser : Charteruser;
  private checked:boolean;
  private userList1: Charteruser[];
  private allChecked: boolean;
  private removeUserList: Charteruser[] = new Array();

  constructor(

    private GetUserListService: GetUserListService,
    private router:Router
  ) { }

  ngOnInit() {

    this.GetUserListService.getUserList().subscribe(
      res => {
        console.log(res.json());
        this.userList1=res.json();
      },
      error =>{
        console.log(error);
      }

    )
  }

}
