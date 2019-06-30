import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPark } from 'app/shared/model/park.model';

@Component({
  selector: 'jhi-park-detail',
  templateUrl: './park-detail.component.html'
})
export class ParkDetailComponent implements OnInit {
  park: IPark;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ park }) => {
      this.park = park;
    });
  }

  previousState() {
    window.history.back();
  }
}
