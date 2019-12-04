var gulp = require('gulp');
var sass = require('gulp-sass');
var autoprefixer = require('gulp-autoprefixer');
var cssnano = require('gulp-cssnano');
var rename = require('gulp-rename');
var del = require('del');
var runSequence = require('run-sequence');
var browserSync = require('browser-sync');
var browserify = require('browserify');
var source = require('vinyl-source-stream');
var imagemin = require('gulp-imagemin');
var uglify = require('gulp-uglify');
var buffer = require('vinyl-buffer');

gulp.task('default', function(done){
    return runSequence(
        [
            'build'
        ],
        [
            'watch'
        ],
        done
    ); 
});

// SASS

gulp.task('sass', function(){
    return gulp.src('./src/main/resources/static/assets/styles/main.scss')
        .pipe(sass({
                "errLogToConsole": true
            }).on('error', sass.logError)
        )
        .pipe(autoprefixer())
        .pipe(cssnano({zindex: false}))
        .pipe(rename({
            extname: '.min.css'
        }))
        .pipe(gulp.dest('./src/main/resources/static/css'))
});

// JAVASCRIPT

gulp.task("js", function(){
    return browserify('./src/main/resources/static/assets/scripts/main.js')
    .transform('babelify',{
        presets: ['es2015'],
    })
    .bundle()
    .pipe(source('main.js', './src/main/resources/static/js'))
    .pipe(buffer())
    .pipe(uglify({mangle: false}))
    .pipe(gulp.dest('./src/main/resources/static/js'))
});


// WATCH

gulp.task('watch', ['browserSync'], function(){
    gulp.watch('./src/main/resources/static/assets/styles/**/*', ['sass', browserSync.reload]);
    gulp.watch('./src/main/resources/static/assets/scripts/main.js', ['js', browserSync.reload]);
    gulp.watch('./src/main/resources/templates/*.html', [browserSync.reload]);
});



// CLEAN

gulp.task('clean', function() {
    del(['./src/main/resources/static/css/main.min.css', './src/main/resources/static/js/main.js']);
});

// BROWSER SYNC

gulp.task('browserSync', function(){
    browserSync({
        server: {
            baseDir: "./"
        }
    });
});

// BUILD

gulp.task('build', function(done){
    return runSequence(
        [
            'clean'
        ],
        [
            'sass',
            'js'
        ],
        done
    );
});