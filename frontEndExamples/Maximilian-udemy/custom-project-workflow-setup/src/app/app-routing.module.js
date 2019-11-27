import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import { HomeComponent } from './home.component';
var appRoutes = [
    {
        path: '',
        component: HomeComponent
    },
    {
        path: 'users',
        loadChildren: './users/users.module#UsersModule'
    }
];
var AppRoutingModule = /** @class */ (function () {
    function AppRoutingModule() {
    }
    AppRoutingModule.decorators = [
        { type: NgModule, args: [{
                    imports: [RouterModule.forRoot(appRoutes)],
                    exports: [RouterModule]
                },] },
    ];
    return AppRoutingModule;
}());
export { AppRoutingModule };
