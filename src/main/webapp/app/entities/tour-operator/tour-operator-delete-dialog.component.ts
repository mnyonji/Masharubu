import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITourOperator } from 'app/shared/model/tour-operator.model';
import { TourOperatorService } from './tour-operator.service';

@Component({
  selector: 'jhi-tour-operator-delete-dialog',
  templateUrl: './tour-operator-delete-dialog.component.html'
})
export class TourOperatorDeleteDialogComponent {
  tourOperator: ITourOperator;

  constructor(
    protected tourOperatorService: TourOperatorService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.tourOperatorService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'tourOperatorListModification',
        content: 'Deleted an tourOperator'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-tour-operator-delete-popup',
  template: ''
})
export class TourOperatorDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ tourOperator }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(TourOperatorDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.tourOperator = tourOperator;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/tour-operator', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/tour-operator', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
