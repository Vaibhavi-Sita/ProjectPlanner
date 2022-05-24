import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AddPlanComponent } from './components/add-plan/add-plan.component';
import { PlanDetailsComponent } from './components/plan-details/plan-details.component';
import { PlansListComponent } from './components/plans-list/plans-list.component';
import { AppRoutingModule } from './app-routing.module';

@NgModule({
  declarations: [
    AppComponent,
    AddPlanComponent,
    PlanDetailsComponent,
    PlansListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
