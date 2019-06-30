/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { MasharubuTestModule } from '../../../test.module';
import { SightingDeleteDialogComponent } from 'app/entities/sighting/sighting-delete-dialog.component';
import { SightingService } from 'app/entities/sighting/sighting.service';

describe('Component Tests', () => {
  describe('Sighting Management Delete Component', () => {
    let comp: SightingDeleteDialogComponent;
    let fixture: ComponentFixture<SightingDeleteDialogComponent>;
    let service: SightingService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MasharubuTestModule],
        declarations: [SightingDeleteDialogComponent]
      })
        .overrideTemplate(SightingDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SightingDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SightingService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
