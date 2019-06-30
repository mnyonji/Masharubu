import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IPark, Park } from 'app/shared/model/park.model';
import { ParkService } from './park.service';
import { ICountry } from 'app/shared/model/country.model';
import { CountryService } from 'app/entities/country';

@Component({
  selector: 'jhi-park-update',
  templateUrl: './park-update.component.html'
})
export class ParkUpdateComponent implements OnInit {
  isSaving: boolean;

  countries: ICountry[];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.maxLength(100)]],
    location: [null, [Validators.required, Validators.maxLength(100)]],
    countryId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected parkService: ParkService,
    protected countryService: CountryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ park }) => {
      this.updateForm(park);
    });
    this.countryService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICountry[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICountry[]>) => response.body)
      )
      .subscribe((res: ICountry[]) => (this.countries = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(park: IPark) {
    this.editForm.patchValue({
      id: park.id,
      name: park.name,
      location: park.location,
      countryId: park.countryId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const park = this.createFromForm();
    if (park.id !== undefined) {
      this.subscribeToSaveResponse(this.parkService.update(park));
    } else {
      this.subscribeToSaveResponse(this.parkService.create(park));
    }
  }

  private createFromForm(): IPark {
    return {
      ...new Park(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      location: this.editForm.get(['location']).value,
      countryId: this.editForm.get(['countryId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPark>>) {
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

  trackCountryById(index: number, item: ICountry) {
    return item.id;
  }
}
