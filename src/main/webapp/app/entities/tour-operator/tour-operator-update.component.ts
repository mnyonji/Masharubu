import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { ITourOperator, TourOperator } from 'app/shared/model/tour-operator.model';
import { TourOperatorService } from './tour-operator.service';
import { IUser, UserService } from 'app/core';

@Component({
  selector: 'jhi-tour-operator-update',
  templateUrl: './tour-operator-update.component.html'
})
export class TourOperatorUpdateComponent implements OnInit {
  isSaving: boolean;

  users: IUser[];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.maxLength(100)]],
    phoneNbr: [null, [Validators.required, Validators.maxLength(20)]],
    emailAddr: [null, [Validators.required, Validators.maxLength(100)]],
    status: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    dateCreated: [],
    validatedBy: [null, [Validators.required]],
    dateValidated: [],
    physicalAddress: [null, [Validators.required]],
    userId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected tourOperatorService: TourOperatorService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ tourOperator }) => {
      this.updateForm(tourOperator);
    });
    this.userService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
        map((response: HttpResponse<IUser[]>) => response.body)
      )
      .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(tourOperator: ITourOperator) {
    this.editForm.patchValue({
      id: tourOperator.id,
      name: tourOperator.name,
      phoneNbr: tourOperator.phoneNbr,
      emailAddr: tourOperator.emailAddr,
      status: tourOperator.status,
      createdBy: tourOperator.createdBy,
      dateCreated: tourOperator.dateCreated != null ? tourOperator.dateCreated.format(DATE_TIME_FORMAT) : null,
      validatedBy: tourOperator.validatedBy,
      dateValidated: tourOperator.dateValidated != null ? tourOperator.dateValidated.format(DATE_TIME_FORMAT) : null,
      physicalAddress: tourOperator.physicalAddress,
      userId: tourOperator.userId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const tourOperator = this.createFromForm();
    if (tourOperator.id !== undefined) {
      this.subscribeToSaveResponse(this.tourOperatorService.update(tourOperator));
    } else {
      this.subscribeToSaveResponse(this.tourOperatorService.create(tourOperator));
    }
  }

  private createFromForm(): ITourOperator {
    return {
      ...new TourOperator(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      phoneNbr: this.editForm.get(['phoneNbr']).value,
      emailAddr: this.editForm.get(['emailAddr']).value,
      status: this.editForm.get(['status']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      dateCreated:
        this.editForm.get(['dateCreated']).value != null ? moment(this.editForm.get(['dateCreated']).value, DATE_TIME_FORMAT) : undefined,
      validatedBy: this.editForm.get(['validatedBy']).value,
      dateValidated:
        this.editForm.get(['dateValidated']).value != null
          ? moment(this.editForm.get(['dateValidated']).value, DATE_TIME_FORMAT)
          : undefined,
      physicalAddress: this.editForm.get(['physicalAddress']).value,
      userId: this.editForm.get(['userId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITourOperator>>) {
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
}
