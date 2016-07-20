define(['angular', './sample-module'], function (angular, controllers) {
    'use strict';

controllers.controller('queryModellerCtrl',['$scope', 'DbService', '$state', function($scope, DbService, $state){
    document.querySelector('px-app-nav').markSelected('/querymodular');

    $scope.executeQuery = function(){
        alert("Execute Query");
    };
}]);
});

