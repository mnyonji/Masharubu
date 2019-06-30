import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IDriver, Driver } from 'app/shared/model/driver.model';
import { DriverService } from './driver.service';
import { IUser, UserService } from 'app/core';
import { ITourOperator } from 'app/shared/model/tour-operator.model';
import { TourOperatorService } from 'app/entities/tour-operator';

@Component({
  selector: 'jhi-driver-update',
  templateUrl: './driver-update.component.html'
})
export class DriverUpdateComponent implements OnInit {
  isSaving: boolean;

  users: IUser[];

  touroperators: ITourOperator[];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.maxLength(100)]],
    phoneNbr: [null, [Validators.required, Validators.maxLength(20)]],
    emailAddr: [null, [Validators.required, Validators.maxLength(100)]],
    gender: [null, [Validators.required]],
    status: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    dateCreated: [],
    validatedBy: [null, [Validators.required]],
    dateValidated: [],
    userId: [],
    tourOperatorId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected driverService: DriverService,
    protected userService: UserService,
    protected tourOperatorService: TourOperatorService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ driver }) => {
      this.updateForm(driver);
    });
    this.userService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
        map((response: HttpResponse<IUser[]>) => response.body)
      )
      .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.tourOperatorService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ITourOperator[]>) => mayBeOk.ok),
        map((response: HttpResponse<ITourOperator[]>) => response.body)
      )
      .subscribe((res: ITourOperator[]) => (this.touroperators = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(driver: IDriver) {
    this.editForm.patchValue({
      id: driver.id,
      name: driver.name,
      phoneNbr: driver.phoneNbr,
      emailAddr: driver.emailAddr,
      gender: driver.gender,
      status: driver.status,
      createdBy: driver.createdBy,
      dateCreated: driver.dateCreated != null ? driver.dateCreated.format(DATE_TIME_FORMAT) : null,
      validatedBy: driver.validatedBy,
      dateValidated: driver.dateValidated != null ? driver.dateValidated.format(DATE_TIME_FORMAT) : null,
      userId: driver.userId,
      tourOperatorId: driver.tourOperatorId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const driver = this.createFromForm();
    if (driver.id !== undefined) {
      this.subscribeToSaveResponse(this.driverService.update(driver));
    } else {
      this.subscribeToSaveResponse(this.driverService.create(driver));
    }
  }

  private createFromForm(): IDriver {
    return {
      ...new Driver(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      phoneNbr: this.editForm.get(['phoneNbr']).value,
      emailAddr: this.editForm.get(['emailAddr']).value,
      gender: this.editForm.get(['gender']).value,
      status: this.editForm.get(['status']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      dateCreated:
        this.editForm.get(['dateCreated']).value != null ? moment(this.editForm.get(['dateCreated']).value, DATE_TIME_FORMAT) : undefined,
      validatedBy: this.editForm.get(['validatedBy']).value,
      dateValidated:
        this.editForm.get(['dateValidated']).value != null
          ? moment(this.editForm.get(['dateValidated']).value, DATE_TIME_FORMAT)
          : undefined,
      userId: this.editForm.get(['userId']).value,
      tourOperatorId: this.editForm.get(['tourOperatorId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDriver>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackUserById(index: number, item: IUser) {
    return item.id;
  }

  trackTourOperatorById(index: number, item: ITourOperator) {
    return item.id;
  }
}
