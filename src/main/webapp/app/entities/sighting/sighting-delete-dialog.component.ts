import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISighting } from 'app/shared/model/sighting.model';
import { SightingService } from './sighting.service';

@Component({
  selector: 'jhi-sighting-delete-dialog',
  templateUrl: './sighting-delete-dialog.component.html'
})
export class SightingDeleteDialogComponent {
  sighting: ISighting;

  constructor(protected sightingService: SightingService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.sightingService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'sightingListModification',
        content: 'Deleted an sighting'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-sighting-delete-popup',
  template: ''
})
export class SightingDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sighting }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(SightingDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.sighting = sighting;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/sighting', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/sighting', { outlets: { popup: null } }]);
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
