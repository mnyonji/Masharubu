import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'country',
        loadChildren: './country/country.module#MasharubuCountryModule'
      },
      {
        path: 'park',
        loadChildren: './park/park.module#MasharubuParkModule'
      },
      {
        path: 'tour-operator',
        loadChildren: './tour-operator/tour-operator.module#MasharubuTourOperatorModule'
      },
      {
        path: 'driver',
        loadChildren: './driver/driver.module#MasharubuDriverModule'
      },
      {
        path: 'sighting',
        loadChildren: './sighting/sighting.module#MasharubuSightingModule'
      },
      {
        path: 'animal',
        loadChildren: './animal/animal.module#MasharubuAnimalModule'
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MasharubuEntityModule {}
