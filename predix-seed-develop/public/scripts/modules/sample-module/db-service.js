define(['angular', './sample-module'], function(angular, module) {
    'use strict';

    /**
     * Cube home service is used for all services required for home page
     */
    module.factory('DbService', ['$q', '$http', function($q, $http) {
        var protocol = 'https';
        var hostUrl = 'predix-tool-metadetadataloader-001.run.aws-usw02-pr.ice.predix.io';

        var _makeDbConnection = function(requestData){
            requestData = angular.toJson(requestData);
            console.log(requestData);
            var deferred = $q.defer();
            var req = {
                method: 'POST',
                url: 'https://predix-tool-metadetadataloader-001.run.aws-usw02-pr.ice.predix.io/LoadMetaData',
                data : requestData,
                headers: {
                    'Content-Type': 'application/json'
                }
            };
            $http(req)
            .then(function(response){
                console.log("in success");
                console.log(response);
                deferred.resolve(response.data);
            }, function(error){
                deferred.reject('Error fetching Options' + error);
            });
            return deferred.promise;
        };

        var _getChartData = function(xaxis, yaxis){
            var deferred = $q.defer();
            var req = {
                method: 'GET',
                url: 'https://predix-tool-dataconvertor-001.run.aws-usw02-pr.ice.predix.io/dataConvetor?xaxis='+xaxis+'&yaxis='+yaxis+'&drilldown=customer_region',
                headers: {
                    'Content-Type': 'application/json'
                }
            };
            $http(req)
            .then(function(response){
                console.log("in success");
                console.log(response);
                deferred.resolve(response.data);
            }, function(error){
                deferred.reject('Error fetching Options' + error);
            });
            return deferred.promise;
        };

        return {
            makeDbConnection : _makeDbConnection,
            getChartData : _getChartData
        };
    }]);
});