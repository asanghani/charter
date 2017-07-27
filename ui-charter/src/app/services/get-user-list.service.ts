import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {Charteruser} from '../models/charteruser';

@Injectable()
export class GetUserListService {

  constructor(private http:Http) { }

    getUserList(){

    let url = "http://localhost:8080/charteruser/userList";
    
    let headers = new Headers ({
      'Content-Type' : 'application/Json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    return this.http.post(url, {headers: headers});
  }

}
