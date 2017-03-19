var WinstonLogger = require('winston');
var WinstonLoggerArchive = require('winston-archivefile');
var WinstonLoggerRotate = require('winston-logrotate');

var seLogger = new (WinstonLogger.Logger)({
    transports: [
        new (WinstonLogger.transports.Console)({
            name: 'app-console',
            level: 'debug',
            colorize: false
        }),
        new (WinstonLogger.transports.File)({
            name: 'app-info',
            filename: '../logs/appprovider-info.log',
            level: 'info',
            json: false,
            maxsize: 5242880, //5MB
            maxFiles: 5,
            colorize: false
        }),
        new (WinstonLogger.transports.File)({
            name: 'app-debug',
            filename: '../logs/appprovider-debug.log',
            level: 'debug',
            json: false,
            maxsize: 5242880, //5MB
            maxFiles: 5,
            colorize: false
        }),
        new (WinstonLogger.transports.File)({
            name: 'app-error',
            filename: '../logs/appprovider-error.log',
            level: 'error',
            json: false,
            maxsize: 5242880, //5MB
            maxFiles: 5,
            colorize: false
        })
    ],
    exitOnError: false
});

module.exports = seLogger;