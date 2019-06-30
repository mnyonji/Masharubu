import { NgModule } from '@angular/core';

import { MasharubuSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
  imports: [MasharubuSharedLibsModule],
  declarations: [JhiAlertComponent, JhiAlertErrorComponent],
  exports: [MasharubuSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class MasharubuSharedCommonModule {}
