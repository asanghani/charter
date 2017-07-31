import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { routing } from './app.routing';
import { MaterialModule } from '@angular/material';
import 'hammerjs';

import { LoginService } from './services/login.service';
import {CharteruserService } from './services/charteruser.service';
import { GetUserListService} from './services/get-user-list.service';
import { GetUserService} from './services/get-user.service';
import { EditUserService} from './services/edit-user.service';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {NoopAnimationsModule} from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { LoginComponent } from './components/login/login.component';
import { AddNewCharteruserComponent } from './components/add-new-charteruser/add-new-charteruser.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { VeiwUserComponent } from './components/veiw-user/veiw-user.component';
import { EditUserComponent } from './components/edit-user/edit-user.component';

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    LoginComponent,
    AddNewCharteruserComponent,
    UserListComponent,
    VeiwUserComponent,
    EditUserComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing,
   BrowserAnimationsModule,
    NoopAnimationsModule,
    MaterialModule
  ],
  providers: [
    LoginService,
    CharteruserService,
    GetUserListService,
    GetUserService,
    EditUserService

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
