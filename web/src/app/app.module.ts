import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GreenhouseManagementComponent } from './greenhouse-management/greenhouse-management.component';
import { SetupGreenhouseComponent } from './setup-greenhouse/setup-greenhouse.component';
import { GreenhouseListComponent } from './greenhouse-list/greenhouse-list.component';
import { GreenhouseCommandComponent } from './greenhouse-command/greenhouse-command.component';
import { ViewDataComponent } from './view-data/view-data.component';
import { ViewGreenhouseDataComponent } from './view-greenhouse-data/view-greenhouse-data.component';
import { SetpointsComponent } from './setpoints/setpoints.component';
import { HumiditySetpointComponent } from './humidity-setpoint/humidity-setpoint.component';
import { TemperatureSetpointComponent } from './temperature-setpoint/temperature-setpoint.component';
import { LightSetpointComponent } from './light-setpoint/light-setpoint.component';
import { RegulatorComponent } from './regulator/regulator.component';

@NgModule({
  declarations: [
    AppComponent,
    GreenhouseManagementComponent,
    SetupGreenhouseComponent,
    GreenhouseListComponent,
    GreenhouseCommandComponent,
    ViewDataComponent,
    ViewGreenhouseDataComponent,
    SetpointsComponent,
    HumiditySetpointComponent,
    TemperatureSetpointComponent,
    LightSetpointComponent,
    RegulatorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
