/**
 * System configuration for Angular samples
 * Adjust as necessary for your application needs.
 */
(function (global) {
  System.config({
    paths: {
      // paths serve as alias
      'npm:': '/'
    },
    // map tells the System loader where to look for things
    map: {
      // our app is within the app folder
      app: 'app',

      // angular bundles
      '@angular/core': 'npm:@angular/core/bundles/core.umd.js',
      '@angular/common': 'npm:@angular/common/bundles/common.umd.js',
      '@angular/compiler': 'npm:@angular/compiler/bundles/compiler.umd.js',
      '@angular/platform-browser': 'npm:@angular/platform-browser/bundles/platform-browser.umd.js',
      '@angular/platform-browser-dynamic': 'npm:@angular/platform-browser-dynamic/bundles/platform-browser-dynamic.umd.js',
      '@angular/http': 'npm:@angular/http/bundles/http.umd.js',
      '@angular/router': 'npm:@angular/router/bundles/router.umd.js',
      '@angular/forms': 'npm:@angular/forms/bundles/forms.umd.js',
      'angular2flashmessages': 'npm:angular2-flash-messages',
      'ng2bs3modal': 'npm:ng2-bs3-modal',
      'ng2_select2': 'npm:ng2-select2',
      'ng2_pdf_viewer':'npm:ng2-pdf-viewer',
      'pdfjs_dist':'npm:pdfjs-dist',

      // other libraries
      'rxjs': 'npm:rxjs',
      'angular-in-memory-web-api': 'npm:angular-in-memory-web-api/bundles/in-memory-web-api.umd.js'
    },
    // packages tells the System loader how to load when no filename and/or no extension
    packages: {
      homeApp: {
        main: './main.js',
        defaultExtension: 'js'
      },

      dashboardAPApp: {
        main: './main.js',
        defaultExtension: 'js'
      },

      dashboardCIApp: {
        main: './main.js',
        defaultExtension: 'js'
      },

      angular2flashmessages: { main: 'index.js', defaultExtension: 'js' },
      ng2bs3modal: { main: 'ng2-bs3-modal.js', defaultExtension: 'js' },
      ng2_select2: { main: 'index.js', defaultExtension: 'js' },
      ng2_pdf_viewer: { main: 'dist/index.js', defaultExtension: 'js' },
      pdfjs_dist: { main: 'dist/pdf.js', defaultExtension: 'js'},
      rxjs: {
        defaultExtension: 'js'
      }
    }
  });
})(this);