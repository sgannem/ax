module.exports = function(grunt) {

grunt.loadNpmTasks('grunt-zip');
grunt.loadNpmTasks('grunt-mkdir');
grunt.loadNpmTasks('grunt-typescript');
grunt.loadNpmTasks('grunt-contrib-copy');
grunt.loadNpmTasks('grunt-contrib-clean');
grunt.loadNpmTasks('grunt-contrib-rename');
grunt.loadNpmTasks('grunt-contrib-uglify');
grunt.loadNpmTasks('grunt-contrib-cssmin');
grunt.loadNpmTasks('grunt-contrib-htmlmin');

grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),
    typescript: {
        common: {
          src: ['public/ts/**/*.ts'],
          dest: 'public/ts/'
        }
    },
    clean: {
        build: ['build/'],
        prod_temp: ['op/'],
        prod: ['prod/']
    },
    mkdir: {
        common: {
            options: {
                create: ['build/', 'build/selogs']
            },
        },
        prod: {
            options: {
                create: ['prod/']
            },
        }
    },
    copy: {
        common: {
            files: [
                {expand: true, src: ['public/**/*.ttf'], dest: 'build/', filter: 'isFile'},
                {expand: true, src: ['public/**/*.otf'], dest: 'build/', filter: 'isFile'},
                {expand: true, src: ['public/**/*.ico'], dest: 'build/', filter: 'isFile'},
                {expand: true, src: ['public/**/*.png'], dest: 'build/', filter: 'isFile'},
                {expand: true, src: ['public/**/*.jpg'], dest: 'build/', filter: 'isFile'},
                {expand: true, src: ['config/**/*.json'], dest: 'build/', filter: 'isFile'},
                {expand: true, src: ['modules/**/*.json'], dest: 'build/', filter: 'isFile'},
                {expand: true, src: ['mail_templates/**/*.vm'], dest: 'build/', filter: 'isFile'},
                {expand: true, src: ['bin/**/*'], dest: 'build/', filter: 'isFile'},
                {expand: true, src: ['package.json'], dest: 'build/', filter: 'isFile'},
                {expand: true, src: ['start_se.sh'], dest: 'build/', filter: 'isFile'},
                {expand: true, src: ['stop_se.sh'], dest: 'build/', filter: 'isFile'}
            ]
        },
        prod: {
            files: [
                {expand: true, src: ['op.zip'], dest: 'prod/', filter: 'isFile'}
            ]
        }
    },//copy Done
    uglify: {
        app_js_minify: {
            options: {
                report: 'none',
                mangle: {
                    except: ['jQuery']
                }
            },
            files: [{
                expand: true,
                cwd: '.',
                src: 'app.js',
                dest: 'build/'
            }]
        },
        ui_js_minify: {
            options: {
                report: 'none',
                mangle: {
                    except: ['jQuery']
                }
            },
            files: [{
                expand: true,
                cwd: '.',
                src: 'public/js/**/*.js',
                dest: 'build/'
            }]
        },
        routes_js_minify: {
            options: {
                report: 'none',
                mangle: {
                    except: ['jQuery']
                }
            },
            files: [{
                expand: true,
                cwd: '.',
                src: 'routes/**/*.js',
                dest: 'build/'
            }]
        },
        services_js_minify: {
            options: {
                report: 'none',
                mangle: {
                    except: ['jQuery']
                }
            },
            files: [{
                expand: true,
                cwd: '.',
                src: 'services/**/*.js',
                dest: 'build/'
            }]
        },
        modules_js_minify: {
            options: {
                report: 'none',
                mangle: {
                    except: ['jQuery']
                }
            },
            files: [{
                expand: true,
                cwd: '.',
                src: 'modules/**/*.js',
                dest: 'build/'
            }]
        }
    },//uglify Done
    cssmin: {
        dist: {
            files: [{
                expand: true,
                cwd: '.',
                src: ['public/theme/**/*.css', '!public/theme/**/*.min.css'],
                dest: 'build',
                ext: '.css'
            }]
        }
    },//cssmin Done
    htmlmin: {
        dist: {
            options: {
                minifyJS: true,
                minifyCSS: true,
                removeComments: true,
                collapseWhitespace: true
            },
            files: [
                {
                    expand: true,
                    cwd: '.',
                    src: ['views/**/*.ejs'],
                    dest: 'build/'},
            ]
        }
    },//htmlmin Done
    rename: {
        prod_analytics: {
            files: [
                {src: 'build/views/desktop/analytics/prod-general.ejs', dest: 'build/views/desktop/analytics/general.ejs'},
                {src: 'build/views/phone/analytics/prod-general.ejs', dest: 'build/views/phone/analytics/general.ejs'},
                {src: 'build/views/tablet/analytics/prod-general.ejs', dest: 'build/views/tablet/analytics/general.ejs'},
                {src: 'build/', dest: 'op/'}
            ]
        }
    },
    zip: {
        'prod_package': {
            src: ['op/**/*.*'],
            dest: 'op.zip'
        }
    }
});

    grunt.registerTask('default', ['typescript', 'clean:build', 'mkdir:common', 'copy:common', 'uglify', 'cssmin', 'htmlmin']);
    grunt.registerTask('dev', 'Development Build', function() {
        grunt.task.run(['default']);
    });aq
    grunt.registerTask('prod', 'Production Build', function() {
        grunt.task.run(['default', 'mkdir:prod', 'rename:prod_analytics', 'zip:prod_package', 'clean:prod_temp','copy:prod', 'clean:prod']);
    });
};