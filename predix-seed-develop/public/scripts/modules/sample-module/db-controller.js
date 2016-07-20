define(['angular', './sample-module'], function (angular, controllers) {
    'use strict';

controllers.controller('DBController',['$scope', 'DbService', '$state', function($scope, DbService, $state){
    dbString = undefined;
    $scope.newconn = {
        'username' : null,
        'password' : null,
        'host' : null,
        'port' : null
    };
    $scope.reset = function() {
        $scope.newconn = {};
    };

    $scope.createQuery = function(){
        $state.go('querymodular');
    };
    $scope.connect = function(){
        console.log("in controller connect");
        DbService.makeDbConnection($scope.newconn)
        .then(function(dbconn){
            $scope.databases = dbconn.MetaData;
            console.log('coming here');
            dbString = $scope.databases;
            console.log("here");
            console.log(dbString);
        }, function(error){

        });/*
        $scope.databases = {
            "public": {
                "sb_user": {
                    "name": "character varying",
                    "alias": "character varying",
                    "id": "integer"
                },
                "testtable": {
                    "Name": "ARRAY"
                },
                "tablink": {
                    "created_dt": "text",
                    "user_id": "integer",
                    "link_url": "text",
                    "link_name": "text",
                    "link_id": "integer"
                }
            },
            "poc": {
                "csa_customer_dtls": {
                    "year": "integer",
                    "true_cost": "numeric",
                    "customer_name": "character varying",
                    "customer_region": "character varying",
                    "engine_model": "character varying",
                    "last_shop_visit": "date",
                    "cust_id": "integer"
                }
            } 
        };*/
    }
}]);
});

