/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { SightingService } from 'app/entities/sighting/sighting.service';
import { ISighting, Sighting } from 'app/shared/model/sighting.model';

describe('Service Tests', () => {
  describe('Sighting Service', () => {
    let injector: TestBed;
    let service: SightingService;
    let httpMock: HttpTestingController;
    let elemDefault: ISighting;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(SightingService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Sighting(0, 'image/png', 'AAAAAAA', 0, 0, 0, currentDate, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            dateSighted: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a Sighting', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateSighted: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dateSighted: currentDate
          },
          returnedFromService
        );
        service
          .create(new Sighting(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Sighting', async () => {
        const returnedFromService = Object.assign(
          {
            picture: 'BBBBBB',
            latitude: 1,
            longitude: 1,
            altitude: 1,
            dateSighted: currentDate.format(DATE_TIME_FORMAT),
            description: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateSighted: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of Sighting', async () => {
        const returnedFromService = Object.assign(
          {
            picture: 'BBBBBB',
            latitude: 1,
            longitude: 1,
            altitude: 1,
            dateSighted: currentDate.format(DATE_TIME_FORMAT),
            description: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dateSighted: currentDate
          },
          returnedFromService
        );
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Sighting', async () => {
        const rxPromise = service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
