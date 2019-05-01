import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule} from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegulatorComponent } from './regulator/regulator.component';
import { LightSetpointComponent } from './light-setpoint/light-setpoint.component';
import { TemperatureSetpointComponent } from './temperature-setpoint/temperature-setpoint.component';
import { HumiditySetpointComponent } from './humidity-setpoint/humidity-setpoint.component';
import { SetpointsComponent } from './setpoints/setpoints.component';
import { ViewGreenhouseDataComponent } from './view-greenhouse-data/view-greenhouse-data.component';
import { ViewDataComponent } from './view-data/view-data.component';
import { GreenhouseCommandComponent } from './greenhouse-command/greenhouse-command.component';
import { GreenhouseListComponent } from './greenhouse-list/greenhouse-list.component';
import { SetupGreenhouseComponent } from './setup-greenhouse/setup-greenhouse.component';
import { GreenhouseManagementComponent } from './greenhouse-management/greenhouse-management.component';
import { HttpClientModule} from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    RegulatorComponent,
    LightSetpointComponent,
    TemperatureSetpointComponent,
    HumiditySetpointComponent,
    SetpointsComponent,
    ViewGreenhouseDataComponent,
    ViewDataComponent,
    GreenhouseCommandComponent,
    GreenhouseListComponent,
    SetupGreenhouseComponent,
    GreenhouseManagementComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
