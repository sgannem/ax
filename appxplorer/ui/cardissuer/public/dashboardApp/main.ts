import { enableProdMode } from '@angular/core';
import { Router } from '@angular/router';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './modules/app.module';

enableProdMode();
platformBrowserDynamic().bootstrapModule(AppModule, Router);