import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MasharubuSharedModule } from 'app/shared';
import {
  TourOperatorComponent,
  TourOperatorDetailComponent,
  TourOperatorUpdateComponent,
  TourOperatorDeletePopupComponent,
  TourOperatorDeleteDialogComponent,
  tourOperatorRoute,
  tourOperatorPopupRoute
} from './';

const ENTITY_STATES = [...tourOperatorRoute, ...tourOperatorPopupRoute];

@NgModule({
  imports: [MasharubuSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    TourOperatorComponent,
    TourOperatorDetailComponent,
    TourOperatorUpdateComponent,
    TourOperatorDeleteDialogComponent,
    TourOperatorDeletePopupComponent
  ],
  entryComponents: [
    TourOperatorComponent,
    TourOperatorUpdateComponent,
    TourOperatorDeleteDialogComponent,
    TourOperatorDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MasharubuTourOperatorModule {}
