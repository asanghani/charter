import { Component, OnInit } from '@angular/core';
import { Params, ActivatedRoute, Router} from '@angular/router';
import { GetUserService } from '../../services/get-user.service';
import { Charteruser} from '../../models/charteruser';
import { EditUserService } from '../../services/edit-user.service';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  private username: string;
  private user: Charteruser = new Charteruser();
  private userUpdated: boolean;

  constructor(
    private editUserService: EditUserService,
    private getUserService: GetUserService,
    private route: ActivatedRoute,
    private router: Router 

  ) { }

  onSubmit() {
  	this.editUserService.sendUser(this.user).subscribe(
  		data => {
  			this.userUpdated=true;
  		},
  		error => console.log(error)
  	);
  }

  ngOnInit() {
  this.route.params.forEach((params: Params) => {
        console.log(params);
        this.username = params['user'];
    });
      
  	this.getUserService.getUser(this.username).subscribe(
  		res => {
  			this.user = res.json();
  		}, 
  		error => console.log(error)
  	)
  }

}