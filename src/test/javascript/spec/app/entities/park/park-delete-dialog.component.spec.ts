/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { MasharubuTestModule } from '../../../test.module';
import { ParkDeleteDialogComponent } from 'app/entities/park/park-delete-dialog.component';
import { ParkService } from 'app/entities/park/park.service';

describe('Component Tests', () => {
  describe('Park Management Delete Component', () => {
    let comp: ParkDeleteDialogComponent;
    let fixture: ComponentFixture<ParkDeleteDialogComponent>;
    let service: ParkService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MasharubuTestModule],
        declarations: [ParkDeleteDialogComponent]
      })
        .overrideTemplate(ParkDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ParkDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ParkService);
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
