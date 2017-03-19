var express = require('express');
var router = express.Router();
var app = express();

var SignInService = require('../services/signin');

var LoggerMod = require('../modules/logger/init');

/* GET Home page */
router.get('/', function(req, res) {
    res.render('app/index');
});

/* GET Home page */
router.get('/dashboardAP', function(req, res) {
    res.render('app/dashboard-ap', {layout: 'dashboard-ap-layout.ejs'});
});

router.get('/dashboardCI', function(req, res) {
    res.render('app/dashboard-ci', {layout: 'dashboard-ci-layout.ejs'});
});

/* GET Home page */
router.get('/home', function(req, res) {
    res.render('app/index');
});



module.exports = router;