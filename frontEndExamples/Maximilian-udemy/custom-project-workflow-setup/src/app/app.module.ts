import { NgModule } from "@angular/core";
import { AppComponent } from "./app.component";
import { AppRoutingModule } from "./app-routing.module";
import { HomeComponent } from "./home.component";
import { BrowserModule } from '@angular/platform-browser';
@NgModule({
    declarations: [
        AppComponent,
        HomeComponent
    ],
    imports: [  
        BrowserModule,
        AppRoutingModule],
    bootstrap: [AppComponent]
})
export class AppModule {

}