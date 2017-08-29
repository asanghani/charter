import { ModuleWithProviders } from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import { AddNewCharteruserComponent } from './components/add-new-charteruser/add-new-charteruser.component';
import { UserListComponent} from './components/user-list/user-list.component';
import { VeiwUserComponent} from './components/veiw-user/veiw-user.component';
import { EditUserComponent} from './components/edit-user/edit-user.component';
import { CharterauthComponent } from './components/charterauth/charterauth.component';
const appRoutes: Routes = [
    {
        path : '',
        redirectTo: '/charterauth',
        pathMatch: 'full'
    },
    {
        path: 'charterauth',
        component: CharterauthComponent
    },
    {
        path: 'addUser',
        component: AddNewCharteruserComponent

    },
    {
        path: 'userList',
        component: UserListComponent
    },
    {
        path: 'viewUser/:user',
        component: VeiwUserComponent
    },
    {
        path: 'editUser/:user',
        component: EditUserComponent
    }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
