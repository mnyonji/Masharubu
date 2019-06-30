import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPark } from 'app/shared/model/park.model';

type EntityResponseType = HttpResponse<IPark>;
type EntityArrayResponseType = HttpResponse<IPark[]>;

@Injectable({ providedIn: 'root' })
export class ParkService {
  public resourceUrl = SERVER_API_URL + 'api/parks';

  constructor(protected http: HttpClient) {}

  create(park: IPark): Observable<EntityResponseType> {
    return this.http.post<IPark>(this.resourceUrl, park, { observe: 'response' });
  }

  update(park: IPark): Observable<EntityResponseType> {
    return this.http.put<IPark>(this.resourceUrl, park, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPark>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPark[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
