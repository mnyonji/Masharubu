import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITourOperator } from 'app/shared/model/tour-operator.model';

@Component({
  selector: 'jhi-tour-operator-detail',
  templateUrl: './tour-operator-detail.component.html'
})
export class TourOperatorDetailComponent implements OnInit {
  tourOperator: ITourOperator;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ tourOperator }) => {
      this.tourOperator = tourOperator;
    });
  }

  previousState() {
    window.history.back();
  }
}
