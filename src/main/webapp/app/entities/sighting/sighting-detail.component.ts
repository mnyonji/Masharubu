import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ISighting } from 'app/shared/model/sighting.model';

@Component({
  selector: 'jhi-sighting-detail',
  templateUrl: './sighting-detail.component.html'
})
export class SightingDetailComponent implements OnInit {
  sighting: ISighting;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sighting }) => {
      this.sighting = sighting;
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }
  previousState() {
    window.history.back();
  }
}
