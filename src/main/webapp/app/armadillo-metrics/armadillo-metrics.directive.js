/**
 * Created by jaime on 28/02/17.
 */
'use strict';

angular.module('armadilloMonitorApp')
    .directive('armadilloMetrics', function() {
        return {
            restrict: 'E',
            scope: {
                idArmadillo: '=idArmadillo'
            },
            templateUrl: 'app/armadillo-metrics/armadillo-metrics.html',
            controller: ['$scope','ArmadilloMetricsService', function ($scope, ArmadilloMetricsService) {
                $scope.metrics = {};
                $scope.loadMetricItems = function(){
                    // $scope.metrics= ArmadilloMetricsService.getMetrics($scope.idArmadillo);
                    ArmadilloMetricsService.getMetrics($scope.idArmadillo).then(function(metrics){
                        $scope.metrics = metrics;
                    }).catch(function(error){

                    });
                }

                $scope.loadMetricItems();
            }]
        };
    });
