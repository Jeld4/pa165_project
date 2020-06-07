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

        when('/createOrder', {templateUrl: 'partials/create_order.html', controller: 'CreateOrderCtrl'}).

        when('/tire/edit/:tireId', {templateUrl: 'partials/tire_edit.html', controller: 'TireEditCtrl'}).
        when('/service/edit/:serviceId', {templateUrl: 'partials/service_edit.html', controller: 'ServiceEditCtrl'}).
        when('/user/edit/:userId', {templateUrl: 'partials/user_edit.html', controller: 'UserEditCtrl'}).
        when('/allCars', {templateUrl: 'partials/all_cars.html', controller: 'AllCarsCtrl'}).
        when('/createCar', {templateUrl: 'partials/car_create.html', controller: 'CarRegisterCtrl'}).
        when('/createTire', {templateUrl: 'partials/tire_create.html', controller: 'CreateTireCtrl'}).
        when('/createService', {templateUrl: 'partials/service_create.html', controller: 'CreateServiceCtrl'}).
        when('/createUser', {templateUrl: 'partials/user_create.html', controller: 'CreateUserCtrl'}).
        when('/tires_all', {templateUrl: 'partials/tires.html', controller: 'TiresCtrl'}).
        when('/services_all', {templateUrl: 'partials/services.html', controller: 'ServicesCtrl'}).
        //when('/category/:categoryId', {templateUrl: 'partials/category_detail.html', controller: 'CategoryDetailCtrl'}).
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

        $scope.deleteUser = (user) => {
            if ($rootScope.logedUser.id === user.id) {
                $rootScope.errorAlert = 'It is not possible to delete currently logged user!';
            } else {
                console.log("deleting user with id = " + user.id);
                $http.delete('/pa165/api/v1/users/' + user.id).then(
                    function success(response) {
                        console.log('deleted user ' + user.id + ' on server');
                        $rootScope.successAlert = 'User was successfully deleted.';
                    },
                    function error(response) {
                        console.log("error when deleting user");
                        console.log(response);
                        switch (response.data.code) {
                            case 'ResourceNotFoundException':
                                $rootScope.errorAlert = 'Cannot delete non-existent user! ';
                                break;
                            default:
                                $rootScope.errorAlert = 'Cannot delete user! There are some orders assigned to this user.';
                                break;
                        }
                    }
                );
            }
        }
    });

eshopControllers.controller('AllOrdersCtrl',
    function ($scope, $rootScope, $routeParams, $http) {
        $http.get('/pa165/api/v1/orders').then(function (response) {
            $scope.orders = response.data['_embedded']['orderDTOList'];
            console.log($scope.orders )
            console.log('AJAX loaded all orders ');
        });

        $scope.deleteOrder = (order) => {
            console.log("deleting order with id = " + order.id);
            $http.delete('/pa165/api/v1/orders/' + order.id).then(
                function success(response) {
                    console.log('deleted order ' + order.id + ' on server');
                    $rootScope.successAlert = 'Order was successfully deleted.';
                },
                function error(response) {
                    console.log("error when deleting order");
                    console.log(response);
                    switch (response.data.code) {
                        case 'ResourceNotFoundException':
                            $rootScope.errorAlert = 'Cannot delete non-existent order ! ';
                            break;
                        default:
                            $rootScope.errorAlert = 'Cannot delete order! Reason given by the server: '+response.data.message;
                            break;
                    }
                }
            );
        }
    })

eshopControllers.controller('AllCarsCtrl',
    function ($scope, $rootScope, $routeParams, $http) {
        $http.get('/pa165/api/v1/cars').then(function (response) {
            $scope.cars = response.data['_embedded']['carDTOList'];
            console.log('AJAX loaded all cars ');
        });

        $scope.deleteCar = (car) => {
            console.log("deleting car with id = " + car.id);
            $http.delete('/pa165/api/v1/cars/' + car.id).then(
                function success(response) {
                    console.log('deleted car ' + car.id + ' on server');
                    $rootScope.successAlert = 'Car was successfully deleted.';
                },
                function error(response) {
                    console.log("error when deleting car");
                    console.log(response);
                    switch (response.data.code) {
                        case 'ResourceNotFoundException':
                            $rootScope.errorAlert = 'Cannot delete non-existent car! ';
                            break;
                        default:
                            $rootScope.errorAlert = 'Cannot delete car! This car is assigned to some user.';
                            break;
                    }
                }
            );
        }
    })


eshopControllers.controller('AllServicesCtrl',
    function ($scope, $rootScope, $routeParams, $http) {
        $http.get('/pa165/api/v1/services').then(function (response) {
            $scope.services = response.data['_embedded']['serviceDTOList'];
            console.log('AJAX loaded all services ');
        });

        $scope.deleteService = (service) => {
            console.log("deleting service with id = " + service.id);
            $http.delete('/pa165/api/v1/services/' + service.id).then(
                function success(response) {
                    console.log('deleted service ' + service.id + ' on server');
                    $rootScope.successAlert = 'Service was successfully deleted.';
                },
                function error(response) {
                    console.log("error when deleting service");
                    console.log(response);
                    switch (response.data.code) {
                        case 'ResourceNotFoundException':
                            $rootScope.errorAlert = 'Cannot delete non-existent service! ';
                            break;
                        default:
                            $rootScope.errorAlert = 'Cannot delete service! It is part of some order.';
                            break;
                    }
                }
            );
        }
    })


eshopControllers.controller('AllTiresCtrl',
    function ($scope, $rootScope, $routeParams, $http) {
        $http.get('/pa165/api/v1/tires').then(function (response) {
            $scope.tires = response.data['_embedded']['tireDTOList'];
            console.log('AJAX loaded all tires ');

            $scope.deleteTire = (tire) => {
                console.log("deleting tire with id = " + tire.id);
                $http.delete('/pa165/api/v1/tires/' + tire.id).then(
                    function success(response) {
                        console.log('deleted tire ' + tire.id + ' on server');
                        $rootScope.successAlert = 'Tire was successfully deleted.';
                    },
                    function error(response) {
                        console.log("error when deleting tire");
                        console.log(response);
                        switch (response.data.code) {
                            case 'ResourceNotFoundException':
                                $rootScope.errorAlert = 'Cannot delete non-existent tire ! ';
                                break;
                            default:
                                $rootScope.errorAlert = 'Cannot delete tire! It is part of some order.';
                                break;
                        }
                    }
                );

            }
            
        });
    })

    eshopControllers.controller('MenuCtrl',
	    function ($scope, $routeParams, $http, $location, $rootScope) {
    	$scope.logout = () => {
            $rootScope.successAlert = 'You are now logged out.';
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
            $rootScope.successAlert = 'You are now logged in.';
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
                    $rootScope.errorAlert = 'Wrong login or password.';
                    break;
                default:
                    $rootScope.errorAlert = 'Wrong login or password. Try again.';
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
eshopControllers.controller('OrderInfoCtrl',
    function ($scope, $rootScope, $routeParams, $location, $http) {
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
            },
            
            $scope.removeOrder = (order) => {
                console.log("deleting order with id = " + order.id);
                $http.delete('/pa165/api/v1/orders/' + order.id).then(
                    function success(response) {
                    	
                        console.log('deleted order ' + order.id + ' on server');
                        //display confirmation alert
                        $rootScope.successAlert = 'Order was successfully deleted.';
                        $location.path("/allOrders");
                    },
                    function error(response) {
                        console.log("error when deleting user");
                        console.log(response);
                        switch (response.data.code) {
                            case 'ResourceNotFoundException':
                                $rootScope.errorAlert = 'Cannot delete non-existent order ! ';
                                break;
                            default:
                                $rootScope.errorAlert = 'Cannot delete order ! Reason given by the server: '+response.data.message;
                                break;
                        }
                    }
                );
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
                $rootScope.warningAlert = 'Cannot load tire: '+response.data.message;
            }
        );
    });

eshopControllers.controller('TireEditCtrl',
    function ($scope, $rootScope, $routeParams, $http) {
        var tireId = $routeParams.tireId;
        $http.get('/pa165/api/v1/tires/' + tireId).then(
            function (response) {
                console.log(response)
                $scope.tire = response.data;
                console.log('AJAX loaded edit of tire ' + $scope.tire.id);
            },
            function error(response) {
                console.log(response);
                $rootScope.warningAlert = 'Cannot load tire edit: '+response.data.message;
            }
        );
    });

eshopControllers.controller('ServiceEditCtrl',
    function ($scope, $rootScope, $routeParams, $http) {
        var serviceId = $routeParams.serviceId;
        $http.get('/pa165/api/v1/services/' + serviceId).then(
            function (response) {
                console.log(response)
                $scope.service = response.data;
                console.log('AJAX loaded edit of service ' + $scope.service.id);
            },
            function error(response) {
                console.log(response);
                $rootScope.warningAlert = 'Cannot load service edit: '+response.data.message;
            }
        );
    });

eshopControllers.controller('UserEditCtrl',
    function ($scope, $rootScope, $routeParams, $http) {
        var userId = $routeParams.userId;
        $http.get('/pa165/api/v1/users/' + userId).then(
            function (response) {
                console.log(response)
                $scope.user = response.data;
                console.log('AJAX loaded edit of user ' + $scope.user.id);
            },
            function error(response) {
                console.log(response);
                $rootScope.warningAlert = 'Cannot load user edit: '+response.data.message;
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
                        $rootScope.successAlert = 'User "' + user.name + '" was successfully deleted.';
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
    		},
            $http.get('/pa165/api/v1/orders/getByUser/' + userId).then(
                function (response) {
                    $scope.orders = response.data['_embedded']['orderDTOList'];
                    console.log($scope.orders);
                    console.log('AJAX loaded user orders');
                }
            )
        );
    });

eshopControllers.controller('UserRegisterCtrl',
	    function ($scope, $routeParams, $http, $location, $rootScope) {
	        //set object bound to form fields
	        $scope.user = {
	            'name': '',
	            'login': '',
	            'password': '',
                'userAddress': '',
                'telephone': '',
	            'isAdmin': false,

	        };

	        // function called when submit button is clicked, creates product on server
	        $scope.create = function (user) {
	        	console.log(user)
	            $http({
	                method: 'POST',
                    url: 'api/v1/users/create/',
	                data: user
	            }).then(function success(response) {
	                console.log('User was created.');
	                var createdUser = response.data;
	                //display confirmation alert
	                $rootScope.successAlert = 'A new user "' + createdUser.name + '" was created.';
	                $rootScope.logedUser = response.data
	                //change view to list of products
	                $location.path("/");
	            }, function error(response) {
	                //display error
	                console.log("error when creating user");
	                console.log(response);
	                switch (response.data.code) {
	                    case 'InvalidRequestException':
	                        $rootScope.errorAlert = 'Sent data were found to be invalid by server! ';
	                        break;
	                    default:
	                        $rootScope.errorAlert = 'Cannot create user due to wrong values.';
	                        break;
	                }
	            });
	        };
	    });

eshopControllers.controller('CreateOrderCtrl',
	    function ($scope, $routeParams, $http, $location, $rootScope) {
	        //set object bound to form fields
	        $scope.order = {
	            'user': $rootScope.logedUser,
	            'dateOfOrder': new Date(),
	            'state': "PENDING",
	            'tires': [],
	            'services': [],
	        };
	     
	        
	        $http.get('/pa165/api/v1/tires').then(function (response) {
	            $scope.tires = response.data['_embedded']['tireDTOList'];
	            console.log('AJAX loaded all tires ');
	        });
	        
	        $http.get('/pa165/api/v1/services').then(function (response) {
	            $scope.services = response.data['_embedded']['serviceDTOList'];
	            console.log('AJAX loaded all services ');
	        });
	        
	        $scope.create = (order) => {
	        	console.log(order)
	        	
	        	if (!order.tires && !order.services) {
	        		$rootScope.errorAlert = 'Cannot create empty order';
	        	}
	        	
	        	$http({
		                method: 'POST',
		                url: 'api/v1/orders/create/' + $rootScope.logedUser.login,
		                data: order,
		                headers: { 'Content-Type': 'application/hal+json' }
		            }).then(function success(response) {
		                console.log('created order');
		                var createdUser = response.data;
		                //display confirmation alert
		                $rootScope.successAlert = 'A new order was created.';
		                $location.path("/");
		                
		            }, function error(response) {
		                //display error
		                console.log("error when creating order");
		                console.log(response);
		                switch (response.data.code) {
		                    case 'InvalidRequestException':
		                        $rootScope.errorAlert = 'Sent data were found to be invalid by server! ';
		                        break;
		                    default:
		                        $rootScope.errorAlert = 'It is not possible to create an empty order.';
		                        break;
		                }
		            });
	        	}
	        
	        
	        $scope.addService = (service) => {
	        	$scope.order.services.push(service)
	        }

	        $scope.addTire = (tire) => {
	        	$scope.order.tires.push(tire)
	        }
	        
	        $scope.removeService = (service) => {
	        	$scope.order.services = $scope.order.services.filter(ser => {return ser.id != service.id})
	        }   
	        $scope.removeTire = (tire) => {
	        	$scope.order.tires = $scope.order.tires.filter(tir => {return tir.id != tire.id})
	        }
	   }
	)

eshopControllers.controller('CreateTireCtrl',
    function ($scope, $routeParams, $http, $location, $rootScope) {
        //set object bound to form fields
        $scope.tire = {
            'manufacturer': '',
            'type': '',
            'size': 0,
            'season': '',
            'price': 0,
        };

        $scope.create = function (tire) {
            console.log(tire)
            $http({
                method: 'POST',
                url: 'api/v1/tires/create',
                data: tire
            }).then(function success(response) {
                console.log('created tire');
                var createdTire = response.data;
                //display confirmation alert
                $rootScope.successAlert = 'A new tire "' + createdTire.manufacturer + '" was created.';
                //change view to list of products
                $location.path("/");
            }, function error(response) {
                //display error
                console.log("error when creating tire");
                console.log(response);
                switch (response.data.code) {
                    case 'InvalidRequestException':
                        $rootScope.errorAlert = 'Sent data were found to be invalid by server! ';
                        break;
                    default:
                        $rootScope.errorAlert = 'Cannot create tire due to wrong or missing values!';
                        break;
                }
            });
        };
    });

eshopControllers.controller('CreateUserCtrl',
    function ($scope, $routeParams, $http, $location, $rootScope) {
        //set object bound to form fields
        $scope.user = {
            'name': '',
            'login': '',
            'password': '',
            'userAddress': '',
            'telephone': '',
        };

        // function called when submit button is clicked, creates product on server
        $scope.create = function (user) {
            console.log(user)
            $http({
                method: 'POST',
                url: 'api/v1/users/create/',
                data: user
            }).then(function success(response) {
                console.log('created user');
                var createdUser = response.data;
                //display confirmation alert
                $rootScope.successAlert = 'A new user "' + createdUser.login + '" was created.';
                //change view to list of products
                $location.path("/");
            }, function error(response) {
                //display error
                console.log("error when creating user");
                console.log(response);
                switch (response.data.code) {
                    case 'InvalidRequestException':
                        $rootScope.errorAlert = 'Sent data were found to be invalid by server!';
                        break;
                    default:
                        $rootScope.errorAlert = 'Cannot create user due to wrong or missing values!';
                        break;
                }
            });
        };
    });

eshopControllers.controller('CreateServiceCtrl',
    function ($scope, $routeParams, $http, $location, $rootScope) {
        //set object bound to form fields
        $scope.service = {
            'name': '',
            'description': '',
            'price': 0,
        };

        // function called when submit button is clicked, creates product on server
        $scope.create = function (service) {
            console.log(service)
            $http({
                method: 'POST',
                url: 'api/v1/services/create/',
                data: service
            }).then(function success(response) {
                console.log('created service');
                var createdService = response.data;
                //display confirmation alert
                $rootScope.successAlert = 'A new service "' + createdService.name + '" was created.';
                //change view to list of products
                $location.path("/");
            }, function error(response) {
                //display error
                console.log("error when creating service");
                console.log(response);
                switch (response.data.code) {
                    case 'InvalidRequestException':
                        $rootScope.errorAlert = 'Sent data were found to be invalid by server!';
                        break;
                    default:
                        $rootScope.errorAlert = 'Cannot create service due to wrong or missing values!';
                        break;
                }
            });
        };
    });


eshopControllers.controller('CarRegisterCtrl',
    function ($scope, $routeParams, $http, $location, $rootScope) {
        //set object bound to form fields
        $scope.car = {
            'licencePlate': '',
            'model': '',
            'tireType': '',
        };

        // function called when submit button is clicked, creates product on server
        $scope.create = function (car) {
            console.log(car)
            $http({
                method: 'POST',
                url: 'api/v1/cars/create/' + $rootScope.logedUser.id,
                data: car
            }).then(function success(response) {
                console.log('created car');
                var createdCar = response.data;
                //display confirmation alert
                $rootScope.successAlert = 'A new car "' + createdCar.licencePlate + '" was created.';
                //change view to list of products
                $location.path("/");
            }, function error(response) {
                //display error
                console.log("error when creating car");
                console.log(response);
                switch (response.data.code) {
                    case 'InvalidRequestException':
                        $rootScope.errorAlert = 'Sent data were found to be invalid by server!';
                        break;
                    default:
                        $rootScope.errorAlert = 'Cannot create car due to wrong or missing values!';
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
            },

            $scope.deleteOrder = (order) => {
                console.log("deleting order with id=" + order.id);
                $http.delete('/pa165/api/v1/orders/' + order.id).then(

                    function success(response) {
                        console.log('Order with ID: ' + order.id + ' was successfully deleted');
                        //display confirmation alert
                        $rootScope.successAlert = 'Order was successfully canceled.';

                        $scope.user.orders = $scope.user.orders.filter( ord => {return ord.id != order.id})

                    },
                    function error(response) {
                        console.log("error when deleting user");
                        console.log(response);
                        switch (response.data.code) {
                            case 'ResourceNotFoundException':
                                $rootScope.errorAlert = 'Cannot delete non-existent order ! ';
                                break;
                            default:
                                $rootScope.errorAlert = 'Cannot delete order ! Reason given by the server: '+response.data.message;
                                break;
                        }
                    }
                );
            },

            $scope.deleteCar = (car) => {
                console.log("deleting car with id=" + car.id);
                $http.delete('/pa165/api/v1/cars/' + car.id).then(

                    function success(response) {
                        console.log('Car with ID: ' + car.id + ' was successfully deleted.');
                        //display confirmation alert
                        $rootScope.successAlert = 'Deleted car';
                    },
                    function error(response) {
                        console.log("error when deleting car");
                        console.log(response);
                        switch (response.data.code) {
                            case 'ResourceNotFoundException':
                                $rootScope.errorAlert = 'Cannot delete non-existent car ! ';
                                break;
                            default:
                                $rootScope.errorAlert = 'Cannot delete car! Reason given by the server: '+response.data.message;
                                break;
                        }
                    }
                );
            },

            $http.get('/pa165/api/v1/orders/getByUser/' + userId).then(
                function (response) {
                    $scope.orders = response.data['_embedded']['orderDTOList'];
                    console.log($scope.orders);
                    console.log('AJAX loaded user orders');
                }
            ), $http.get('/pa165/api/v1/cars/getByUser/' + userId).then(
                function (response) {
                    $scope.cars = response.data['_embedded']['carDTOList'];
                    console.log($scope.cars);
                    console.log('AJAX loaded user cars');
                }
            )
        );
    });

eshopControllers.controller('TiresCtrl',
    function ($scope, $rootScope, $routeParams, $http) {
        $http.get('/pa165/api/v1/tires').then(function (response) {
            $scope.tires = response.data['_embedded']['tireDTOList'];
            console.log('AJAX loaded all tires ');
        });
    })

eshopControllers.controller('ServicesCtrl',
    function ($scope, $rootScope, $routeParams, $http) {
        $http.get('/pa165/api/v1/services').then(function (response) {
            $scope.services = response.data['_embedded']['serviceDTOList'];
            console.log('AJAX loaded all services ');
        });
    })

