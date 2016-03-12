require.config({
    baseUrl: 'js/lib',
    paths: {
        index: '../app/index',
        bootstrap: 'modules/bootstrap'
    }
});

requirejs(['jquery', 'bootstrap', 'angular'], function() {
    requirejs(['index']);
});