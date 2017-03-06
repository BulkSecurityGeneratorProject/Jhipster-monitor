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
                     if(error.status ==-1){
                         error.statusText="ERROR. Probably caused by net::ERR_INSECURE_RESPONSE. "
                     }
                     deferred.reject(error);
                 });
            });

            return deferred.promise;
        }
    }
})();
