/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MasharubuTestModule } from '../../../test.module';
import { TourOperatorDetailComponent } from 'app/entities/tour-operator/tour-operator-detail.component';
import { TourOperator } from 'app/shared/model/tour-operator.model';

describe('Component Tests', () => {
  describe('TourOperator Management Detail Component', () => {
    let comp: TourOperatorDetailComponent;
    let fixture: ComponentFixture<TourOperatorDetailComponent>;
    const route = ({ data: of({ tourOperator: new TourOperator(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MasharubuTestModule],
        declarations: [TourOperatorDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TourOperatorDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TourOperatorDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tourOperator).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
