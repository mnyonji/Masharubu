import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPark } from 'app/shared/model/park.model';
import { ParkService } from './park.service';

@Component({
  selector: 'jhi-park-delete-dialog',
  templateUrl: './park-delete-dialog.component.html'
})
export class ParkDeleteDialogComponent {
  park: IPark;

  constructor(protected parkService: ParkService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.parkService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'parkListModification',
        content: 'Deleted an park'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-park-delete-popup',
  template: ''
})
export class ParkDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ park }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ParkDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.park = park;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/park', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/park', { outlets: { popup: null } }]);
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
