/**
 * Created by jaime on 28/02/17.
 */
'use strict';

angular.module('armadilloMonitorApp')
    .directive('armadilloGlobalHealthTable', function() {
        return {
            restrict: 'E',
            scope: {
                idArmadillo: '=idArmadillo'
            },
            templateUrl: 'app/armadillo-global-health-table/armadillo-global-health-table.html',
            controller: ['$scope','Application', function ($scope, Application) {

                $scope.applications = [];

                $scope.loadApplicationItems = function(){
                    $scope.applications=Application.query();
                }
                $scope.loadApplicationItems();
            }]
        };
    });
