(function() {
    'use strict';

    angular
        .module('armadilloMonitorApp')
        .controller('InstanceDialogController', InstanceDialogController);

    InstanceDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Instance'];

    function InstanceDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Instance) {
        var vm = this;

        vm.instance = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.instance.id !== null) {
                Instance.update(vm.instance, onSaveSuccess, onSaveError);
            } else {
                Instance.save(vm.instance, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('armadilloMonitorApp:instanceUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
