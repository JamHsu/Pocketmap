(function() {
    angular.module('PocketModule')
        .controller('OsmMapController', OsmMapController);



    OsmMapController.$injector = ['$scope', '$http', '$window', 'leafletMarkerEvents'];

    function OsmMapController($scope, $http, $window, leafletMarkerEvents) {

        var me = this;

        $scope.layers = {
            baselayers: {
                osm: {
                    name: 'OpenStreetMap',
                    url: 'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
                    type: 'xyz'
                }
            }
        };

        $scope.center = {
            lat: 24,
            lng: 121,
            zoom: 7
        };

        $scope.map = {
            bounds: {},
            scrollwheel: false
        };

        $scope.events = {
            markers: {
                enable: leafletMarkerEvents.click
            }
        };

        $scope.mapControls = {
            scale: true,
            custom: [
                L.control.locate({
                    strings: {
                        title: 'Show current location.'
                    },
                    drawCircle: false,
                    keepCurrentZoomLevel: true
                })
            ]
        };


        $scope.$on('leafletDirectiveMarker.click', function(event, args){
            $scope.restaurant = {};
            $scope.restaurant.name = args.model.name;
            $scope.restaurant.imgUrl = args.model.imgUrl;
            $scope.restaurant.url = args.model.url;
        });


        $window.navigator.geolocation.getCurrentPosition(
            function (position) {
                console.log(position)
                // angular.extend($scope, {
                //     center: {
                //         lat: position.coords.latitude,
                //         lng: position.coords.longitude,
                //         zoom: 12
                //     }
                //
                // });

            },
            function (err) {

            });

        requestRestaurantData();
        function requestRestaurantData() {
            $http({
                method: 'GET',
                url: '/restaurant/tabelog'
            }).then(function successCallback(response) {
                $scope.markers = createMarkers(response.data);
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.error(response)
            });
        }

        function createMarkers(restaurantList) {
            var markerList = [],
                marker;
            angular.forEach(restaurantList, function(restaurant) {
                marker = {
                    lat: restaurant.lat,
                    lng: restaurant.lng,
                    message: restaurant.rating + ' '+ restaurant.name + '(' + restaurant.note + ')',
                    name: restaurant.name,
                    imgUrl: restaurant.imgUrl,
                    url: restaurant.url,
                    group: 'tabelog'
                };
                this.push(marker);
            }, markerList);
            return markerList;
        }
    }

})();