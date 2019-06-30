import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Sighting } from 'app/shared/model/sighting.model';
import { SightingService } from './sighting.service';
import { SightingComponent } from './sighting.component';
import { SightingDetailComponent } from './sighting-detail.component';
import { SightingUpdateComponent } from './sighting-update.component';
import { SightingDeletePopupComponent } from './sighting-delete-dialog.component';
import { ISighting } from 'app/shared/model/sighting.model';

@Injectable({ providedIn: 'root' })
export class SightingResolve implements Resolve<ISighting> {
  constructor(private service: SightingService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISighting> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Sighting>) => response.ok),
        map((sighting: HttpResponse<Sighting>) => sighting.body)
      );
    }
    return of(new Sighting());
  }
}

export const sightingRoute: Routes = [
  {
    path: '',
    component: SightingComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'Sightings'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SightingDetailComponent,
    resolve: {
      sighting: SightingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Sightings'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SightingUpdateComponent,
    resolve: {
      sighting: SightingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Sightings'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SightingUpdateComponent,
    resolve: {
      sighting: SightingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Sightings'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const sightingPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: SightingDeletePopupComponent,
    resolve: {
      sighting: SightingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Sightings'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
