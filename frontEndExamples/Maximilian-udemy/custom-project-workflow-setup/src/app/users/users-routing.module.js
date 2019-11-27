import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import { UsersComponent } from "./users.component";
var UsersRoutingModule = /** @class */ (function () {
    function UsersRoutingModule() {
    }
    UsersRoutingModule.decorators = [
        { type: NgModule, args: [{
                    imports: [RouterModule.forChild([
                            {
                                path: '',
                                component: UsersComponent
                            }
                        ])],
                    exports: [RouterModule]
                },] },
    ];
    return UsersRoutingModule;
}());
export { UsersRoutingModule };
