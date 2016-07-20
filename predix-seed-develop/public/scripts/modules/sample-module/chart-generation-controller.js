define(['angular', './sample-module'], function (angular, controllers) {
  'use strict';
  controllers.controller('chartgenerationctrl', ['$scope', 'DbService', function($scope, DbService) {
    /*var dataS= [{'name':'Microsoft Internet Explorer', 'y': 56.33, 'drilldown':'Microsoft Internet Explorer'},{'name':'Chrome', 'y': 24.03, 'drilldown':'Chrome'}, {'name':'Firefox', 'y': 10.38, 'drilldown':'Firefox'},{'name':'Safari', 'y': 4.77, 'drilldown':'Safari'},{'name':'Opera', 'y': 0.91, 'drilldown':'Opera'},{'name':'Proprietary or Undetectable', 'y': 0.2, 'drilldown':'Proprietary or Undetectable'}];
    var dataD=[
      {'names':'Microsoft Internet Explorer', 'id':'Microsoft Internet Explorer', 'data':[
      ['v11.0', 24.13],[ 'v8.0',17.2],[ 'v9.0',8.11],['v10.0',5.33],['v6.0',1.06],['v7.0',0.5] ] } ,
      {'names':'Chrome', 'id':'Chrome', 'data':[
      ['v40.0',5],['v41.0',4.32],['v42.0', 3.68],['v39.0',2.96],['v36.0',2.53],['v43.0',1.45],['v31.0',1.24],
      ['v35.0',0.85],['v38.0',0.6],['v32.0',0.55],['v37.0',0.38],['v33.0',0.19],['v34.0',0.14],['v30.0',0.14] ] } ,
      {'names':'Firefox', 'id':'Firefox', 'data':[
      ['v35',2.76],['v36',2.32],['v37',2.31],['v34',1.27],['v38',1.02],['v31',0.33],['v33',0.22],['v32',0.15] ] } ,
      {'names':'Safari', 'id':'Safari', 'data':[
      ['v8.0',2.56],['v7.1',0.77],['v5.1',0.42],['v5.0',0.3],['v6.1',0.29],['v7.0',0.26],['v6.2',0.17] ] } ,
      {'names':'Opera', 'id':'Opera', 'data':[
      ['v12.x',0.34],['v28',0.24],['v27',0.17],['v29',0.16] ] }
    ];*/

 /*   var dataS= [
    {
      "drilldown": "Luftansa",
      "name": "Luftansa",
      "y": 310
    },
    {
      "drilldown": "British Airways",
      "name": "British Airways",
      "y": 110
    },
    {
      "drilldown": "Air China",
      "name": "Air China",
      "y": 80
    }
  ];
    var dataD=[
    {
      "names": "Luftansa",
      "id": "Luftansa",
      "data": [
        [
          "US",
          90
        ],
        [
          "APAC",
          70
        ]
      ]
    },
    {
      "names": "British Airways",
      "id": "British Airways",
      "data": [
        [
          "EUR",
          20
        ]
      ]
    },
    {
      "names": "Air China",
      "id": "Air China",
      "data": [
        [
          "ASIA",
          40
        ]
      ]
    }
  ];*/
    $scope.charttitle = "";
    $scope.chartdata = "";
    $scope.chartdrilldata = "";
    var index=0;
    $("#ok_button").click(function () {
        var xaxis = $("#sel1").val();
        var yaxis = $("#sel2").val();
        DbService.getChartData(xaxis, yaxis)
        .then(function(chartData){
            console.log("after service call");
            console.log(chartData);
            $scope.chartdata = JSON.stringify(chartData.mainJSON);
            $scope.chartdrilldata = JSON.stringify(chartData.drillDownModel);
            index++;
        var node = document.createElement("div");
        node.setAttribute('id','chart-rend-space-'+index);
        node.setAttribute('class','span6 chart');
        document.getElementById("main-chart-div").appendChild(node);
        $("#chart-rend-space-"+index).append("<bar-chart id='Brands' class='span12' type='column' title='' divid='container"+index+"' subtitle='' data='"+$scope.chartdata+"' drilldowndata='"+$scope.chartdrilldata+"'>");
        }, function(e){

        });
        
    /*var draggie = new Draggabilly( '#chart-rend-space-'+index, {
    //handle: '.chart-drag-handle',
    //containment: 'body',
    //axis: 'y'
    });*/

  });
}]);
});