import { Injectable } from '@angular/core';
import { Http, Headers} from '@angular/http';

@Injectable()
export class GetUserService {

  constructor(private http:Http) { }

  getUser(username:string){

   let url = "http://localhost:8080/charteruser/"+username;
    
    let headers = new Headers ({
      'Content-Type' : 'application/Json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    return this.http.post(url, {headers: headers});
  }


}
