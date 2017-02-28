(function() {
    'use strict';

    angular
        .module('armadilloMonitorApp')
        .controller('InstanceDetailController', InstanceDetailController);

    InstanceDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Instance'];

    function InstanceDetailController($scope, $rootScope, $stateParams, previousState, entity, Instance) {
        var vm = this;

        vm.instance = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('armadilloMonitorApp:instanceUpdate', function(event, result) {
            vm.instance = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
