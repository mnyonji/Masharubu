/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { DriverService } from 'app/entities/driver/driver.service';
import { IDriver, Driver, Gender, Status } from 'app/shared/model/driver.model';

describe('Service Tests', () => {
  describe('Driver Service', () => {
    let injector: TestBed;
    let service: DriverService;
    let httpMock: HttpTestingController;
    let elemDefault: IDriver;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(DriverService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Driver(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', Gender.MALE, Status.NEW, 0, currentDate, 0, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            dateCreated: currentDate.format(DATE_TIME_FORMAT),
            dateValidated: currentDate.format(DATE_TIME_FORMAT)
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

      it('should create a Driver', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateCreated: currentDate.format(DATE_TIME_FORMAT),
            dateValidated: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dateCreated: currentDate,
            dateValidated: currentDate
          },
          returnedFromService
        );
        service
          .create(new Driver(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Driver', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            phoneNbr: 'BBBBBB',
            emailAddr: 'BBBBBB',
            gender: 'BBBBBB',
            status: 'BBBBBB',
            createdBy: 1,
            dateCreated: currentDate.format(DATE_TIME_FORMAT),
            validatedBy: 1,
            dateValidated: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateCreated: currentDate,
            dateValidated: currentDate
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

      it('should return a list of Driver', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            phoneNbr: 'BBBBBB',
            emailAddr: 'BBBBBB',
            gender: 'BBBBBB',
            status: 'BBBBBB',
            createdBy: 1,
            dateCreated: currentDate.format(DATE_TIME_FORMAT),
            validatedBy: 1,
            dateValidated: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dateCreated: currentDate,
            dateValidated: currentDate
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

      it('should delete a Driver', async () => {
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
