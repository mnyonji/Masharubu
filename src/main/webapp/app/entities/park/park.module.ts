import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MasharubuSharedModule } from 'app/shared';
import {
  ParkComponent,
  ParkDetailComponent,
  ParkUpdateComponent,
  ParkDeletePopupComponent,
  ParkDeleteDialogComponent,
  parkRoute,
  parkPopupRoute
} from './';

const ENTITY_STATES = [...parkRoute, ...parkPopupRoute];

@NgModule({
  imports: [MasharubuSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [ParkComponent, ParkDetailComponent, ParkUpdateComponent, ParkDeleteDialogComponent, ParkDeletePopupComponent],
  entryComponents: [ParkComponent, ParkUpdateComponent, ParkDeleteDialogComponent, ParkDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MasharubuParkModule {}
