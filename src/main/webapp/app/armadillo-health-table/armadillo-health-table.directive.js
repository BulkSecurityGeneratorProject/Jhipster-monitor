/**
 * Created by jaime on 28/02/17.
 */
'use strict';

angular.module('armadilloMonitorApp')
    .directive('armadilloHealthTable', function() {
        return {
            restrict: 'E',
            scope: {
                idArmadillo: '=idArmadillo'
            },
            templateUrl: 'app/armadillo-health-table/armadillo-health-table.html',
            controller: ['$scope','Instance', function ($scope, Instance) {
                $scope.instances = [];

                $scope.loadInstanceItems = function(){
                    $scope.instances=Instance.query();
                }
                $scope.loadInstanceItems();
            }]
        };
    });
