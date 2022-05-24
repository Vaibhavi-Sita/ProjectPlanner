import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PlansListComponent } from './components/plans-list/plans-list.component';
import { PlanDetailsComponent } from './components/plan-details/plan-details.component';
import { AddPlanComponent } from './components/add-plan/add-plan.component';

const routes: Routes = [
  { path: '', redirectTo: 'plans', pathMatch: 'full' },
  { path: 'plans', component: PlansListComponent },
  { path: 'plans/:id', component: PlanDetailsComponent },
  { path: 'add', component: AddPlanComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }