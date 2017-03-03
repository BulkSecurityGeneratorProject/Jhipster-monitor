/**
 * Created by jaime on 28/02/17.
 */
'use strict';

angular.module('armadilloMonitorApp')
    .directive('armadilloHealthTable', function() {
        return {
            restrict: 'E',
            scope: {
                idApplication: '=idApplication'
            },
            templateUrl: 'app/armadillo-health-table/armadillo-health-table.html',
            controller: ['$scope','Instance','Application', function ($scope, Instance, Application) {

                $scope.instances = [];

                $scope.loadInstanceItems = function(){
                    if($scope.idApplication===undefined){
                        $scope.instances=Instance.query();
                    }else{
                        $scope.application = Application.get({id: $scope.idApplication});
                        $scope.instances = $scope.application.instances;
                        console.dir($scope.application);
                    }
                }
                $scope.loadInstanceItems();
            }]
        };
    });
