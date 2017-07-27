import { Component, OnInit } from '@angular/core';
import { Charteruser} from '../../models/charteruser';
import { CharteruserService } from '../../services/charteruser.service';


@Component({
  selector: 'app-add-new-charteruser',
  templateUrl: './add-new-charteruser.component.html',
  styleUrls: ['./add-new-charteruser.component.css']
})
export class AddNewCharteruserComponent implements OnInit {

  private newcharteruser: Charteruser = new Charteruser();
  private charteruserAdded: boolean;

  constructor(private charteruserService:CharteruserService) { }

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
  }

}