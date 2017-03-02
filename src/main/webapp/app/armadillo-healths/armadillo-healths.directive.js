/**
 * Created by jaime on 28/02/17.
 */
'use strict';

angular.module('armadilloMonitorApp')
    .directive('armadilloHealths', function() {
        return {
            restrict: 'E',
            scope: {
                idArmadillo: '=idArmadillo'
            },
            templateUrl: 'app/armadillo-healths/armadillo-healths.html',
            controller: ['$scope','ArmadilloHealthsService','Instance', function ($scope, ArmadilloHealthsService) {
                $scope.health = {};
                $scope.loadHealthItems = function(){
                    ArmadilloHealthsService.getHealths($scope.idArmadillo).then(function(metrics){
                        $scope.health = metrics;
                    }).catch(function(error){

                    });
                }

                $scope.loadHealthItems();
                $scope.getLabelClass = function getLabelClass (status) {
                    if (status === 200) {
                        return 'label-success';
                    } else {
                        return 'label-danger';
                    }
                }
            }]
        };
    });
