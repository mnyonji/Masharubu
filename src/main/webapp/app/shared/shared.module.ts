import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { MasharubuSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [MasharubuSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [MasharubuSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MasharubuSharedModule {
  static forRoot() {
    return {
      ngModule: MasharubuSharedModule
    };
  }
}
