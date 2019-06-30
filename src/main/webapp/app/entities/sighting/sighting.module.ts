import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MasharubuSharedModule } from 'app/shared';
import {
  SightingComponent,
  SightingDetailComponent,
  SightingUpdateComponent,
  SightingDeletePopupComponent,
  SightingDeleteDialogComponent,
  sightingRoute,
  sightingPopupRoute
} from './';

const ENTITY_STATES = [...sightingRoute, ...sightingPopupRoute];

@NgModule({
  imports: [MasharubuSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    SightingComponent,
    SightingDetailComponent,
    SightingUpdateComponent,
    SightingDeleteDialogComponent,
    SightingDeletePopupComponent
  ],
  entryComponents: [SightingComponent, SightingUpdateComponent, SightingDeleteDialogComponent, SightingDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MasharubuSightingModule {}
