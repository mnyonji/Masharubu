/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { MasharubuTestModule } from '../../../test.module';
import { TourOperatorUpdateComponent } from 'app/entities/tour-operator/tour-operator-update.component';
import { TourOperatorService } from 'app/entities/tour-operator/tour-operator.service';
import { TourOperator } from 'app/shared/model/tour-operator.model';

describe('Component Tests', () => {
  describe('TourOperator Management Update Component', () => {
    let comp: TourOperatorUpdateComponent;
    let fixture: ComponentFixture<TourOperatorUpdateComponent>;
    let service: TourOperatorService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MasharubuTestModule],
        declarations: [TourOperatorUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(TourOperatorUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TourOperatorUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TourOperatorService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TourOperator(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new TourOperator();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
