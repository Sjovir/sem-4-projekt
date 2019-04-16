import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GreenhouseManagementComponent } from './greenhouse-management/greenhouse-management.component';
import { ViewDataComponent } from './view-data/view-data.component';
import { SetpointsComponent } from './setpoints/setpoints.component';

const routes: Routes = [{path:"greenhouse-management",component:GreenhouseManagementComponent},
{path:"view-data",component:ViewDataComponent},
{path:"setpoints",component:SetpointsComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
