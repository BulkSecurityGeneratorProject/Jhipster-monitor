/**
 * Created by jaime on 28/02/17.
 */
'use strict';

angular.module('armadilloMonitorApp')
    .directive('armadilloMetricsTable', function() {
        return {
            restrict: 'E',
            scope: {
                idArmadillo: '=idArmadillo'
            },
            templateUrl: 'app/armadillo-metrics-table/armadillo-metrics-table.html',
            controller: ['$scope','Instance','Application', function ($scope, Instance, Application) {

                $scope.instance = [];

                $scope.loadInstanceItems = function(){

                        $scope.instance = Instance.get({id: $scope.idArmadillo});
                        console.dir($scope.instance);
                }
                $scope.loadInstanceItems();
            }]
        };
    });
