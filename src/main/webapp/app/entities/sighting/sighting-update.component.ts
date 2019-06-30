import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { ISighting, Sighting } from 'app/shared/model/sighting.model';
import { SightingService } from './sighting.service';
import { IDriver } from 'app/shared/model/driver.model';
import { DriverService } from 'app/entities/driver';
import { IAnimal } from 'app/shared/model/animal.model';
import { AnimalService } from 'app/entities/animal';
import { IPark } from 'app/shared/model/park.model';
import { ParkService } from 'app/entities/park';

@Component({
  selector: 'jhi-sighting-update',
  templateUrl: './sighting-update.component.html'
})
export class SightingUpdateComponent implements OnInit {
  isSaving: boolean;

  drivers: IDriver[];

  animals: IAnimal[];

  parks: IPark[];

  editForm = this.fb.group({
    id: [],
    picture: [null, [Validators.required]],
    pictureContentType: [],
    latitude: [null, [Validators.required]],
    longitude: [null, [Validators.required]],
    altitude: [null, [Validators.required]],
    dateSighted: [],
    description: [],
    driverId: [],
    animalId: [],
    parkId: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected sightingService: SightingService,
    protected driverService: DriverService,
    protected animalService: AnimalService,
    protected parkService: ParkService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ sighting }) => {
      this.updateForm(sighting);
    });
    this.driverService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IDriver[]>) => mayBeOk.ok),
        map((response: HttpResponse<IDriver[]>) => response.body)
      )
      .subscribe((res: IDriver[]) => (this.drivers = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.animalService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IAnimal[]>) => mayBeOk.ok),
        map((response: HttpResponse<IAnimal[]>) => response.body)
      )
      .subscribe((res: IAnimal[]) => (this.animals = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.parkService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IPark[]>) => mayBeOk.ok),
        map((response: HttpResponse<IPark[]>) => response.body)
      )
      .subscribe((res: IPark[]) => (this.parks = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(sighting: ISighting) {
    this.editForm.patchValue({
      id: sighting.id,
      picture: sighting.picture,
      pictureContentType: sighting.pictureContentType,
      latitude: sighting.latitude,
      longitude: sighting.longitude,
      altitude: sighting.altitude,
      dateSighted: sighting.dateSighted != null ? sighting.dateSighted.format(DATE_TIME_FORMAT) : null,
      description: sighting.description,
      driverId: sighting.driverId,
      animalId: sighting.animalId,
      parkId: sighting.parkId
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  setFileData(event, field: string, isImage) {
    return new Promise((resolve, reject) => {
      if (event && event.target && event.target.files && event.target.files[0]) {
        const file = event.target.files[0];
        if (isImage && !/^image\//.test(file.type)) {
          reject(`File was expected to be an image but was found to be ${file.type}`);
        } else {
          const filedContentType: string = field + 'ContentType';
          this.dataUtils.toBase64(file, base64Data => {
            this.editForm.patchValue({
              [field]: base64Data,
              [filedContentType]: file.type
            });
          });
        }
      } else {
        reject(`Base64 data was not set as file could not be extracted from passed parameter: ${event}`);
      }
    }).then(
      () => console.log('blob added'), // sucess
      this.onError
    );
  }

  clearInputImage(field: string, fieldContentType: string, idInput: string) {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null
    });
    if (this.elementRef && idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const sighting = this.createFromForm();
    if (sighting.id !== undefined) {
      this.subscribeToSaveResponse(this.sightingService.update(sighting));
    } else {
      this.subscribeToSaveResponse(this.sightingService.create(sighting));
    }
  }

  private createFromForm(): ISighting {
    return {
      ...new Sighting(),
      id: this.editForm.get(['id']).value,
      pictureContentType: this.editForm.get(['pictureContentType']).value,
      picture: this.editForm.get(['picture']).value,
      latitude: this.editForm.get(['latitude']).value,
      longitude: this.editForm.get(['longitude']).value,
      altitude: this.editForm.get(['altitude']).value,
      dateSighted:
        this.editForm.get(['dateSighted']).value != null ? moment(this.editForm.get(['dateSighted']).value, DATE_TIME_FORMAT) : undefined,
      description: this.editForm.get(['description']).value,
      driverId: this.editForm.get(['driverId']).value,
      animalId: this.editForm.get(['animalId']).value,
      parkId: this.editForm.get(['parkId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISighting>>) {
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

  trackDriverById(index: number, item: IDriver) {
    return item.id;
  }

  trackAnimalById(index: number, item: IAnimal) {
    return item.id;
  }

  trackParkById(index: number, item: IPark) {
    return item.id;
  }
}
