var express = require('express');
var app = express();
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var bodyParser = require('body-parser');
var device = require('express-device');
var cookieParser = require('cookie-parser');
var fs = require('fs');
var i18nMod = require('./modules/il8n/loader');
var LoggerMod = require('./modules/logger/init');

var myForks = function(req, res, next) {
    
    next();
};

app.use(myForks);
app.use(i18nMod);

app.set('view engine', 'ejs');
app.set('view options', { layout: true });
app.set('views', __dirname + '/views');
app.set("layout extractScripts", true);

/*app.use(logger('dev'));*/
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(express.static(path.join(__dirname, 'public')));
app.use(express.static(path.join(__dirname, 'node_modules')));

app.use(device.capture());

device.enableDeviceHelpers(app);
device.enableViewRouting(app);

var homeRoutes = require('./routes/home');

app.use('/', homeRoutes);

app.use(function(req, res, next) {
  /*var err = new Error('Page Not Found');
  err.status = 404;
  next(err);*/
  res.status(404);
  res.send('404: File Not Found');
});

if (app.get('env') === 'development') {
  app.use(function(err, req, res, next) {
    res.status(err.status || 500);
    res.render('error', {
      message: err.message,
      error: err
    });
  });
}

app.use(function(err, req, res, next) {
  res.status(err.status || 500);
  res.render('error', {
    message: err.message,
    error: {}
  });
});

module.exports = app;