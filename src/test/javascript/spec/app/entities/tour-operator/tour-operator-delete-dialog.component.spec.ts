/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { MasharubuTestModule } from '../../../test.module';
import { TourOperatorDeleteDialogComponent } from 'app/entities/tour-operator/tour-operator-delete-dialog.component';
import { TourOperatorService } from 'app/entities/tour-operator/tour-operator.service';

describe('Component Tests', () => {
  describe('TourOperator Management Delete Component', () => {
    let comp: TourOperatorDeleteDialogComponent;
    let fixture: ComponentFixture<TourOperatorDeleteDialogComponent>;
    let service: TourOperatorService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MasharubuTestModule],
        declarations: [TourOperatorDeleteDialogComponent]
      })
        .overrideTemplate(TourOperatorDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TourOperatorDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TourOperatorService);
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
