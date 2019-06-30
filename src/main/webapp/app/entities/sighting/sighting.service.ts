import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISighting } from 'app/shared/model/sighting.model';

type EntityResponseType = HttpResponse<ISighting>;
type EntityArrayResponseType = HttpResponse<ISighting[]>;

@Injectable({ providedIn: 'root' })
export class SightingService {
  public resourceUrl = SERVER_API_URL + 'api/sightings';

  constructor(protected http: HttpClient) {}

  create(sighting: ISighting): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sighting);
    return this.http
      .post<ISighting>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(sighting: ISighting): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sighting);
    return this.http
      .put<ISighting>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ISighting>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISighting[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(sighting: ISighting): ISighting {
    const copy: ISighting = Object.assign({}, sighting, {
      dateSighted: sighting.dateSighted != null && sighting.dateSighted.isValid() ? sighting.dateSighted.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateSighted = res.body.dateSighted != null ? moment(res.body.dateSighted) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((sighting: ISighting) => {
        sighting.dateSighted = sighting.dateSighted != null ? moment(sighting.dateSighted) : null;
      });
    }
    return res;
  }
}
