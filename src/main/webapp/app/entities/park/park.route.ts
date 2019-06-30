import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Park } from 'app/shared/model/park.model';
import { ParkService } from './park.service';
import { ParkComponent } from './park.component';
import { ParkDetailComponent } from './park-detail.component';
import { ParkUpdateComponent } from './park-update.component';
import { ParkDeletePopupComponent } from './park-delete-dialog.component';
import { IPark } from 'app/shared/model/park.model';

@Injectable({ providedIn: 'root' })
export class ParkResolve implements Resolve<IPark> {
  constructor(private service: ParkService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPark> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Park>) => response.ok),
        map((park: HttpResponse<Park>) => park.body)
      );
    }
    return of(new Park());
  }
}

export const parkRoute: Routes = [
  {
    path: '',
    component: ParkComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'Parks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ParkDetailComponent,
    resolve: {
      park: ParkResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Parks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ParkUpdateComponent,
    resolve: {
      park: ParkResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Parks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ParkUpdateComponent,
    resolve: {
      park: ParkResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Parks'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const parkPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ParkDeletePopupComponent,
    resolve: {
      park: ParkResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Parks'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
