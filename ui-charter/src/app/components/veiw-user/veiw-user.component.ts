import { Component, OnInit } from '@angular/core';
import { Params, ActivatedRoute, Router} from '@angular/router';
import { GetUserService } from '../../services/get-user.service';
import { Charteruser} from '../../models/charteruser';

@Component({
  selector: 'app-veiw-user',
  templateUrl: './veiw-user.component.html',
  styleUrls: ['./veiw-user.component.css']
})
export class VeiwUserComponent implements OnInit {

  private user:Charteruser = new Charteruser();
  private username:string;

  constructor(private getUserService:GetUserService,
   private route:ActivatedRoute, private router:Router) { }


   onSelect(user:Charteruser) {
    this.router.navigate(['/editUser', this.user.username])
    //.then(s => location.reload());

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
        error => {
            console.log(error);
        }
    );
  }

}
