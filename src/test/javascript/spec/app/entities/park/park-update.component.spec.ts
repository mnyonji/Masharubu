/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { MasharubuTestModule } from '../../../test.module';
import { ParkUpdateComponent } from 'app/entities/park/park-update.component';
import { ParkService } from 'app/entities/park/park.service';
import { Park } from 'app/shared/model/park.model';

describe('Component Tests', () => {
  describe('Park Management Update Component', () => {
    let comp: ParkUpdateComponent;
    let fixture: ComponentFixture<ParkUpdateComponent>;
    let service: ParkService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MasharubuTestModule],
        declarations: [ParkUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ParkUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ParkUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ParkService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Park(123);
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
        const entity = new Park();
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
