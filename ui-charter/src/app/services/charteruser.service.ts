import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {Charteruser} from '../models/charteruser';


@Injectable()
export class CharteruserService {

  constructor(private http:Http) { }

  sendUser(user:Charteruser){

    let url = "http://localhost:8080/charteruser/addUser";
    
    let headers = new Headers ({
      'Content-Type' : 'application/Json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    return this.http.post(url, JSON.stringify(user), {headers: headers});
  }

}
