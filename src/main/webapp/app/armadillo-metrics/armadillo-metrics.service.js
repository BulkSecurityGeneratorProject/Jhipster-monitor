(function() {
    'use strict';

    angular
        .module('armadilloMonitorApp')
        .factory('ArmadilloMetricsService', ArmadilloMetricsService);

    ArmadilloMetricsService.$inject = ['$http','Instance','$q'];

    function ArmadilloMetricsService ($http, Instance,$q) {
        var service = {
            getMetrics: getMetrics
        };

        return service;

        function getMetrics(idArmadillo) {
            var deferred = $q.defer();

            Instance.get({id: idArmadillo}).$promise.then(function(instance) {
                $http.get(instance.armadillo_url+ "auth/metrics").then(function (response) {
                    deferred.resolve(response);
                }).catch(function(response) {
                    deferred.reject(response);
                });
            });

            return deferred.promise;
            // return Instance.get({id: idArmadillo}).$promise.then(function(instance) {
            //     return $http.get(instance.armadillo_url+ "auth/metrics");
            // });
        }

/*        function getMetrics (idArmadillo) {
            return Instance.get({id:idArmadillo},function(response){
                /!*acceder al endpoints metrics de este armadillo*!/
                var metricsUrl;
                metricsUrl=response.armadillo_url+"auth/metrics";
                getMetricsArmadillo(metricsUrl);
                return response;
            },function (error) {
                console.log("error: "+error);
            });
            function getMetricsArmadillo (url) {
                return $http.get(url).then(function (response) {
                    return response.data;
                });
            }
            /!*return $http.get('management/jhipster/metrics').then(function (response) {
                return response.data;
            });*!/
        }*/

    }
})();
