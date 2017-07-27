import { ModuleWithProviders } from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import { AddNewCharteruserComponent } from './components/add-new-charteruser/add-new-charteruser.component';
import { UserListComponent} from './components/user-list/user-list.component';
//test commit change**2
const appRoutes: Routes = [
    {
        path : '',
        redirectTo: '/login',
        pathMatch: 'full'
    },
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'addUser',
        component: AddNewCharteruserComponent

    },
    {
        path: 'userList',
        component: UserListComponent
    }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
