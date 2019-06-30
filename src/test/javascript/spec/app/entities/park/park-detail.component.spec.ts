/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MasharubuTestModule } from '../../../test.module';
import { ParkDetailComponent } from 'app/entities/park/park-detail.component';
import { Park } from 'app/shared/model/park.model';

describe('Component Tests', () => {
  describe('Park Management Detail Component', () => {
    let comp: ParkDetailComponent;
    let fixture: ComponentFixture<ParkDetailComponent>;
    const route = ({ data: of({ park: new Park(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MasharubuTestModule],
        declarations: [ParkDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ParkDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ParkDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.park).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
