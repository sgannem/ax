var express = require('express');
var router = express.Router();
var app = express();

var SignInService = require('../services/signin');

var LoggerMod = require('../modules/logger/init');

/* Redirect to Dashboard page */
router.get('/', function(req, res) {
    res.redirect('/dashboard');
});

/* GET Dashboard page */
router.get('/dashboard', function(req, res) {
    res.render('app/dashboard');
});

module.exports = router;