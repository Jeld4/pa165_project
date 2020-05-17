'use strict';

/* Defines application and its dependencies */

var pneuApp = angular.module('pneuApp', ['ngRoute', 'eshopControllers']);
var eshopControllers = angular.module('eshopControllers', []);

/* Configures URL fragment routing, e.g. #/user/1  */
pneuApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
        when('/', {templateUrl: 'partials/main_page.html', controller: 'MainPageCtrl'}).
        when('/allUsers', {templateUrl: 'partials/all_users.html', controller: 'AllUsersCtrl'}).
        when('/user/:userId', {templateUrl: 'partials/user_info.html', controller: 'UserInfoCtrl'}).
        when('/userRegister', {templateUrl: 'partials/user_register.html', controller: 'UserRegisterCtrl'}).
        when('/allOrders', {templateUrl: 'partials/all_orders.html', controller: 'AllOrdersCtrl'}).
        when('/order/:orderId', {templateUrl: 'partials/order_info.html', controller: 'OrderInfoCtrl'}).
        when('/allServices', {templateUrl: 'partials/all_services.html', controller: 'AllServicesCtrl'}).
        when('/service/:serviceId', {templateUrl: 'partials/service_info.html', controller: 'ServiceInfoCtrl'}).
        when('/allTires', {templateUrl: 'partials/all_tires.html', controller: 'AllTiresCtrl'}).
        when('/user/profile/:userId', {templateUrl: 'partials/user_profile.html', controller: 'UserProfileCtrl'}).
        when('/login', {templateUrl: 'partials/login.html', controller: 'LoginCtrl'}).
        when('/tire/:tireId', {templateUrl: 'partials/tire_info.html', controller: 'TireInfoCtrl'}).
        //when('/category/:categoryId', {templateUrl: 'partials/category_detail.html', controller: 'CategoryDetailCtrl'}).
        //when('/admin/users', {templateUrl: './partials/tire_detail.html', controller: 'TireDetailCtrl'}).
        //when('/admin/newuser', {templateUrl: 'partials/admin_new_user.html', controller: 'AdminNewProductCtrl'}).
        //when('/admin/categories', {templateUrl: 'partials/admin_categories.html', controller: 'AdminCategoriesCtrl'}).
        //when('/admin/newcategory', {
        //    templateUrl: 'partials/admin_new_category.html',
        //    controller: 'AdminNewCategoryCtrl'
        //}).
        otherwise({redirectTo: '/'});

    }]);

eshopControllers.controller('AllUsersCtrl',
	    function ($scope, $rootScope, $routeParams, $http) {
	$http.get('/pa165/api/v1/users').then(function (response) {
        $scope.users = response.data['_embedded']['userDTOList'];
        console.log('AJAX loaded all users ');
    });
	        })

eshopControllers.controller('AllOrdersCtrl',
    function ($scope, $rootScope, $routeParams, $http) {
        $http.get('/pa165/api/v1/orders').then(function (response) {
            $scope.orders = response.data['_embedded']['orderDTOList'];
            console.log('AJAX loaded all orders ');
        });
    })


eshopControllers.controller('AllServicesCtrl',
    function ($scope, $rootScope, $routeParams, $http) {
        $http.get('/pa165/api/v1/services').then(function (response) {
            $scope.services = response.data['_embedded']['serviceDTOList'];
            console.log('AJAX loaded all services ');
        });
    })


eshopControllers.controller('AllTiresCtrl',
    function ($scope, $rootScope, $routeParams, $http) {
        $http.get('/pa165/api/v1/tires').then(function (response) {
            $scope.tires = response.data['_embedded']['tireDTOList'];
            console.log('AJAX loaded all tires ');
        });
    })

    eshopControllers.controller('MenuCtrl',
	    function ($scope, $routeParams, $http, $location, $rootScope) {
    	$scope.logout = () => {
            $rootScope.successAlert = 'success';
            $rootScope.logedUser = undefined; 
            $location.path("/");
    	}
    });
    
    
    eshopControllers.controller('LoginCtrl',
	    function ($scope, $routeParams, $http, $location, $rootScope) {
    $scope.user = {
            'name': 'BLANK',
            'login': '',
            'password': '',
            'isAdmin': false,
        };
    
    $scope.login = function (user) {
    	console.log(user);
        $http({
            method: 'POST',
            url: 'api/v1/login',
            headers: { 'Content-Type': 'application/hal+json' },
            data: user
        }).then(function success(response) {
            $rootScope.successAlert = 'success';
            $http({
                method: 'GET',
                url: 'api/v1/users/login/' + user.login,
                headers: { 'Content-Type': 'application/hal+json' },
                data: user
            }).then(function success(res) {
                $rootScope.logedUser = res.data; 
                $location.path("/");
            })
            
        }, function error(response) {
            console.log("error when creating user");
            console.log(response);
            switch (response.data.code) {
                case 'InvalidRequestException':
                    $rootScope.errorAlert = 'wrong login or password';
                    break;
                default:
                    $rootScope.errorAlert = 'Cannot login user ! Reason given by the server: '+response.data.message;
                    break;
            }
        });
    };
});
    
pneuApp.run(function ($rootScope,$http) {
    // alert closing functions defined in root scope to be available in every template
    $rootScope.hideSuccessAlert = function () {
        $rootScope.successAlert = undefined;
    };
    $rootScope.hideWarningAlert = function () {
        $rootScope.warningAlert = undefined;
    };
    $rootScope.hideErrorAlert = function () {
        $rootScope.errorAlert = undefined;
    };
    //change the HTTP Accept header globally to signal accepting the HAL format
    $http.defaults.headers.common.Accept = 'application/hal+json, */*';
});

/* Controllers */



/*
 * Public eshop interface
 */




/*
 * Product detail page
 */
eshopControllers.controller('TireDetailCtrl',
    function ($scope, $rootScope, $routeParams, $http) {
        // get user id from URL fragment #/user/:userId
        $http.get('/pa165/api/v1/tires/1').then(
            function (response) {
            	console.log(response)
                $scope.user = response.data;
                console.log('AJAX loaded detail of user ' + $scope.user.name);
            },
            function error(response) {
                //console.log("failed to load user "+userId);
                console.log(response);
                $rootScope.warningAlert = 'Cannot load user: '+response.data.message;
            }
        );
    });

eshopControllers.controller('OrderInfoCtrl',
    function ($scope, $rootScope, $routeParams, $http) {
        var orderId = $routeParams.orderId;
        $http.get('/pa165/api/v1/orders/' + orderId).then(
            function (response) {
                console.log(response)
                $scope.order = response.data;
                console.log('AJAX loaded detail of order ' + $scope.order.id);
            },
            function error(response) {
                console.log(response);
                $rootScope.warningAlert = 'Cannot load order: '+response.data.message;
            }
        );
    });

eshopControllers.controller('ServiceInfoCtrl',
    function ($scope, $rootScope, $routeParams, $http) {
        var serviceId = $routeParams.serviceId;
        $http.get('/pa165/api/v1/services/' + serviceId).then(
            function (response) {
                console.log(response)
                $scope.service = response.data;
                console.log('AJAX loaded detail of service ' + $scope.service.id);
            },
            function error(response) {
                console.log(response);
                $rootScope.warningAlert = 'Cannot load service: '+response.data.message;
            }
        );
    });

eshopControllers.controller('TireInfoCtrl',
    function ($scope, $rootScope, $routeParams, $http) {
        var tireId = $routeParams.tireId;
        $http.get('/pa165/api/v1/tires/' + tireId).then(
            function (response) {
                console.log(response)
                $scope.tire = response.data;
                console.log('AJAX loaded detail of tire ' + $scope.tire.id);
            },
            function error(response) {
                console.log(response);
                $rootScope.warningAlert = 'Cannot load service: '+response.data.message;
            }
        );
    });


eshopControllers.controller('MainPageCtrl',
	    function ($scope, $rootScope, $routeParams, $http) {

	    });

/*
 * Product detail page
 */
eshopControllers.controller('UserInfoCtrl',
    function ($scope, $routeParams, $http, $location, $rootScope) {
        // get user id from URL fragment #/user/:userId
		var userId = $routeParams.userId;
        $http.get('/pa165/api/v1/users/' + userId).then(

            function (response) {

                $scope.user = response.data;
                console.log(response.data)
                console.log('AJAX loaded detail of user ' + $scope.user.name);
            },

            function error(response) {
                console.log("failed to load user "+userId);
                console.log(response);
                $rootScope.warningAlert = 'Cannot load user: '+response.data.message;
            },

    		$scope.deleteUser = (user) => {
                console.log("deleting user with id=" + user.id + ' (' + user.name + ')');
                $http.delete(user._links.delete.href).then(

                    function success(response) {
                        console.log('deleted user ' + user.id + ' on server');
                        //display confirmation alert
                        $rootScope.successAlert = 'Deleted user "' + user.name + '"';
                        //load new list of all users
                        $location.path("/allUsers");
                    },
                    function error(response) {
                        console.log("error when deleting user");
                        console.log(response);
                        switch (response.data.code) {
                            case 'ResourceNotFoundException':
                                $rootScope.errorAlert = 'Cannot delete non-existent user ! ';
                                break;
                            default:
                                $rootScope.errorAlert = 'Cannot delete user ! Reason given by the server: '+response.data.message;
                                break;
                        }
                    }
                );
    		}
        );
    });

eshopControllers.controller('UserRegisterCtrl',
	    function ($scope, $routeParams, $http, $location, $rootScope) {
	        //set object bound to form fields
	        $scope.user = {
	            'name': '',
	            'login': '',
	            'password': '',
	            'isAdmin': false,

	        };

	        // function called when submit button is clicked, creates product on server
	        $scope.create = function (user) {
	        	console.log(user)
	            $http({
	                method: 'POST',
	                url: 'api/v1/users/create',
	                data: user
	            }).then(function success(response) {
	                console.log('created user');
	                var createdUser = response.data;
	                //display confirmation alert
	                $rootScope.successAlert = 'A new user "' + createdUser.name + '" was created';
	                $rootScope.logedUser = response.data
	                //change view to list of products
	                $location.path("/");
	            }, function error(response) {
	                //display error
	                console.log("error when creating user");
	                console.log(response);
	                switch (response.data.code) {
	                    case 'InvalidRequestException':
	                        $rootScope.errorAlert = 'Sent data were found to be invalid by server ! ';
	                        break;
	                    default:
	                        $rootScope.errorAlert = 'Cannot create user ! Reason given by the server: '+response.data.message;
	                        break;
	                }
	            });
	        };
	    });

eshopControllers.controller('UserProfileCtrl',
    function ($scope, $routeParams, $http, $location, $rootScope) {
        // get user id from URL fragment #/user/:userId
        var userId = $routeParams.userId;
        $http.get('/pa165/api/v1/users/' + userId).then(
            function (response) {
                $scope.user = response.data;
                console.log(response.data)
                console.log('AJAX loaded detail of user ' + $scope.user.name);
            },
            function error(response) {
                console.log("failed to load user "+userId);
                console.log(response);
                $rootScope.warningAlert = 'Cannot load user: '+response.data.message;
            }, $http.get('/pa165/api/v1/orders/getByUser/' + userId).then(
                function (response) {
                    $scope.orders = response.data;
                    console.log(response.data)
                    console.log('AJAX loaded user orders');
                }
            )
        );
    });

