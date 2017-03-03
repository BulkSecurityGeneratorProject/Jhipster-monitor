(function() {
    'use strict';

    angular
        .module('armadilloMonitorApp')
        .factory('ArmadilloHealthsService', ArmadilloHealthsService);

    ArmadilloHealthsService.$inject = ['$http','Instance','$q'];

    function ArmadilloHealthsService ($http, Instance,$q) {
        var service = {
            getHealths: getHealths,
        };

        return service;
        function getHealths (idArmadillo){
            var deferred = $q.defer();

            Instance.get({id: idArmadillo}).$promise.then(function(instance) {
                 $http.get(instance.armadillo_url+ "/healthBasic").then(function (response) {
                     deferred.resolve(response);
                 }).catch(function(error) {
                     deferred.resolve(error);
                 });
            });

            return deferred.promise;
        }
    }
})();
