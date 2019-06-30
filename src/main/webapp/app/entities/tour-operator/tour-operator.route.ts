import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TourOperator } from 'app/shared/model/tour-operator.model';
import { TourOperatorService } from './tour-operator.service';
import { TourOperatorComponent } from './tour-operator.component';
import { TourOperatorDetailComponent } from './tour-operator-detail.component';
import { TourOperatorUpdateComponent } from './tour-operator-update.component';
import { TourOperatorDeletePopupComponent } from './tour-operator-delete-dialog.component';
import { ITourOperator } from 'app/shared/model/tour-operator.model';

@Injectable({ providedIn: 'root' })
export class TourOperatorResolve implements Resolve<ITourOperator> {
  constructor(private service: TourOperatorService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITourOperator> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TourOperator>) => response.ok),
        map((tourOperator: HttpResponse<TourOperator>) => tourOperator.body)
      );
    }
    return of(new TourOperator());
  }
}

export const tourOperatorRoute: Routes = [
  {
    path: '',
    component: TourOperatorComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'TourOperators'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TourOperatorDetailComponent,
    resolve: {
      tourOperator: TourOperatorResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'TourOperators'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TourOperatorUpdateComponent,
    resolve: {
      tourOperator: TourOperatorResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'TourOperators'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TourOperatorUpdateComponent,
    resolve: {
      tourOperator: TourOperatorResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'TourOperators'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const tourOperatorPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: TourOperatorDeletePopupComponent,
    resolve: {
      tourOperator: TourOperatorResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'TourOperators'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
