import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITourOperator } from 'app/shared/model/tour-operator.model';

type EntityResponseType = HttpResponse<ITourOperator>;
type EntityArrayResponseType = HttpResponse<ITourOperator[]>;

@Injectable({ providedIn: 'root' })
export class TourOperatorService {
  public resourceUrl = SERVER_API_URL + 'api/tour-operators';

  constructor(protected http: HttpClient) {}

  create(tourOperator: ITourOperator): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tourOperator);
    return this.http
      .post<ITourOperator>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tourOperator: ITourOperator): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tourOperator);
    return this.http
      .put<ITourOperator>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITourOperator>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITourOperator[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tourOperator: ITourOperator): ITourOperator {
    const copy: ITourOperator = Object.assign({}, tourOperator, {
      dateCreated: tourOperator.dateCreated != null && tourOperator.dateCreated.isValid() ? tourOperator.dateCreated.toJSON() : null,
      dateValidated: tourOperator.dateValidated != null && tourOperator.dateValidated.isValid() ? tourOperator.dateValidated.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateCreated = res.body.dateCreated != null ? moment(res.body.dateCreated) : null;
      res.body.dateValidated = res.body.dateValidated != null ? moment(res.body.dateValidated) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tourOperator: ITourOperator) => {
        tourOperator.dateCreated = tourOperator.dateCreated != null ? moment(tourOperator.dateCreated) : null;
        tourOperator.dateValidated = tourOperator.dateValidated != null ? moment(tourOperator.dateValidated) : null;
      });
    }
    return res;
  }
}
