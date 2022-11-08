'use strict';
app
    //angular.module('hotelApp.hotelList', ['ngRoute','ngCookies', 'ngResource', 'hotelApp.sessionCustomerService', /*'hotelApp.fileUploader',*/ 'hotelApp.activityService', 'hotelApp.activityDto', 'dnd'])

    .controller('MenuListController',
    ['$scope', '$rootScope', '$location', '$resource', '$stateParams' ,'SessionCustomer', 'HotelDto', 'HotelCheckin', '$window', '$state', '$translate', 'editableOptions', 'editableThemes', '$filter', '$mdDialog',
        function($scope, $rootScope, $location, $resource, $stateParams, SessionCustomer, HotelDto, HotelCheckin, $window, $state, $translate, editableOptions, editableThemes, $filter, $mdDialog) {

        editableThemes.bs3.inputClass = 'input-sm';
        editableThemes.bs3.buttonsClass = 'btn-sm';
        editableOptions.theme = 'bs3';

         var menuItemService = $resource('./menu/item/customer/:customerId/hotelId/:hotelId/cafeId/:cafeId/menuItemId/:itemId', 
             {customerId:'@customerId', hotelId:'@hotelId', cafeId:'@cafeId', itemId:'@itemId'}, 
             {
                 'getMenuItem': {method: 'GET', isArray: true},
                 'addUpdateMenuItem': {method: 'POST', isArray: false},
                 'deleteMenuItem': {method: 'DELETE', isArray: false}
             }
         );
            
       
            
        var menuItemReorderingService = $resource('./menu/item/customer/:customerId/hotelId/:hotelId/cafeId/:cafeId/reorder/:reorder', 
             {customerId:'@customerId', hotelId:'@hotelId', cafeId:'@cafeId', reorder:'@reorder'}, 
             {
                 'getReorder': {method: 'GET', isArray: true}
                 //,
                 //'addUpdate': {method: 'POST', isArray: false},
                 //'delete': {method: 'DELETE', isArray: false}
             }
         );

        if($state.current.name == "app.menuList" || $scope.editMenuOrderId)
        {
            $scope.localState = {

                ready: 0,

                selectedFilterHotel : undefined,
                selectedCityHotel : undefined,

                searchFilter : {},

                strictSearch: true,

                filterHotels : [],

                filterMenuItems : [],

                menuItemsLoaded : false,

                initFilterHotelId : 0,

                orderingChanged : false,

                dataLoaded : false,

                orderLoaded : false,

                menuDescrWidth : (document.documentElement.clientWidth - 25 - 65 - 50) + "px" ,

                menuAccepted : $scope.hotelState.currentMenuOrder && ($scope.hotelState.currentMenuOrder.orderStatus=='ACCEPTED'||$scope.hotelState.currentMenuOrder.orderStatus=='EXECUTED'),

                hotelMenuItems : []
            };

            $scope.accordionStatus = {
                editHotelOpen: false,
                infoHotelOpen: [],
                descriptionHotelOpen: [],
                newActivityOpen: false,
                isEditHotelDisabled: false,
                isInfoHotelDisabled: false,
                isDescriptionHotelDisabled: false,
                isNewActivityDisabled: false
            };

            $scope.updateLocalFilter = function(onlySetIfEmpty, subSetToSearch)
            {

                if(onlySetIfEmpty && $scope.localState.hotelMenuItems && $scope.localState.hotelMenuItems.length>0)
                {
                    return;
                }

                $scope.localState.searchFilter = {};

                if($scope.localState.selectedFilterHotel && $scope.localState.selectedFilterHotel.id>0)
                {
                    $scope.localState.searchFilter.hotelId = $scope.localState.selectedFilterHotel.id;
                }
                //else if($scope.mainState.selectedCity != "-1" && $scope.mainState.selectedCity != undefined && $scope.mainState.selectedCity.toUpperCase() != $filter('translate')('alert.info.allCities').toUpperCase())
                //{
                //    $scope.localState.searchFilter.city = $scope.mainState.selectedCity;
                //}

                if($scope.localState.selectedFilterHotel && !$scope.localState.hotelMenuItems[$scope.localState.selectedFilterHotel.id])
                {
                    $scope.localState.hotelMenuItems[$scope.localState.selectedFilterHotel.id] = [];
                }

                if(!subSetToSearch || subSetToSearch.length==0 || subSetToSearch[0].dtoType!="menuItem")
                {
                    subSetToSearch = $scope.localState.selectedFilterHotel? $scope.localState.hotelMenuItems[$scope.localState.selectedFilterHotel.id] : [];
                }

                $scope.localState.filterMenuItems = $filter('filter')(subSetToSearch, $scope.localState.searchFilter, true);//,  $scope.localState.strictSearch);

                //TODO Eugen: sortyBy orderIndex
                $scope.localState.filterMenuItems.sort(function(a, b) {
                    return parseFloat(a.orderIndex) - parseFloat(b.orderIndex);
                });
                //$scope.localState.noMenuFound = $scope.localState.hotelMenuItems.length==0 && $scope.mainState.hotelArray.length>0;

                //$scope.localState.copyHotels = angular.copy($scope.localState.filterHotels);


            };
        } 
            
        
        //################ filter Cities and Hotels #########################
        
        var oldNewItemsMap = [];    
        var oldNewItemsReorder = "";    
            
        $scope.detectOrderChange = function()
        {
            oldNewItemsMap = [];
            oldNewItemsReorder = "";
            
            var menuItemsUl = document.getElementById("menuItemsUl");
            
            var childElts = [];
            
            if(menuItemsUl)
            {
                for(var i=0; i<menuItemsUl.childNodes.length; i++)
                {
                    if(menuItemsUl.childNodes[i].innerHTML)
                    {
                        childElts.push(menuItemsUl.childNodes[i]);
                    }
                }
            }
            
            for(var i=0; i<childElts.length; i++)
            {
                var htmlElts = childElts[i].getElementsByClassName("menuItem_index");
                
                if (htmlElts.length>0)
                {
                    var htmlElt = htmlElts[0];
                        
                    var oldIndex = parseInt(htmlElt.innerHTML);

                    
                    var classSplit = htmlElt.classList[0].split("_");

                    var itemIndex = -1;

                    if(classSplit.length>1)
                    {
                        itemIndex =  parseInt(classSplit[1]);
                    }

                    if(i!=oldIndex)
                    {
                        oldNewItemsMap[itemIndex] = i;
                        oldNewItemsReorder += itemIndex + ">" + i + "#";
                    }
                    
                }
                
            }
            
            if(oldNewItemsReorder.length>0)
            {
                var requesterId = $scope.hotelState.getRequesterId();
                var hotelId = $scope.hotelState.profileData.hotelId;
                var cafeId = 0;
                
                $rootScope.showLoading(true);
                
                menuItemReorderingService.getReorder({customerId: requesterId, hotelId : hotelId, cafeId: cafeId, reorder:oldNewItemsReorder}, function(menuItemArray) {

                    //if(menuItemArray.length>0)
                    //{
                    //    $scope.localState.hotelMenuItems[menuItemArray[0].hotelId] = [];
                    //}

                    for(var m=0; m<menuItemArray.length; m++)
                    {
                        var nextMenuItem = menuItemArray[m];
                        $scope.addUpdateMenuItem(nextMenuItem)
                    }
                    
                    $scope.updateLocalFilter();
                    
                    $rootScope.showLoading(false);

                });
            }
            
        }

        function addUpdateItemInOrder(order, item, amountAction)
        {
            if(!amountAction)
            {
                amountAction = '+';
            }
            
            if(!order.menuItems || order.menuItems.length==0)
            {
                order.menuItems = [];
            }
            
            var itemUpdated = false;

            if(item.delimiter)
            {
                item.amount = 0;
            }
            else if(item.amount == 0)
            {
                item.amount = 1;
            }
            
            if(order.menuItems && order.menuItems.length>0)
            {
                for(var i=0; i < order.menuItems.length; i++)
                {
                    if(order.menuItems[i].id == item.id)
                    {
                        
                        if(amountAction=="+")
                        {
                            order.menuItems[i].amount = order.menuItems[i].amount + 1;
                        }
                        else if(amountAction=="-" && order.menuItems[i].amount>0)
                        {
                            order.menuItems[i].amount = order.menuItems[i].amount - 1;
                        }
                        
                        if(order.menuItems[i].amount<0)
                        {
                            order.menuItems[i].amount = 0;
                        }
                          
                        itemUpdated = true;
                        break;
                    }
                }
            }
                
            if(!itemUpdated)
            {
                order.menuItems.push(item);
            }

            var itemAmount = 0;
            var totalPrice = 0;
            
            for(var i=0; i < order.menuItems.length; i++)
            {
                itemAmount += order.menuItems[i].amount;
                totalPrice += order.menuItems[i].amount * order.menuItems[i].price;
            }
            
            order.itemAmount = itemAmount;
            order.totalPrice = totalPrice;
                
            return order;
        }
            
        function setBgColorSwitch(classId, switchOff)
        {
            if(!classId)
            {
                return;
            }
            
           var eltIds =  document.getElementsByClassName(classId);
            
            if(eltIds && eltIds.length>0)
            {
                //var previousColor = eltIds[0].style.backgroundColor;
                eltIds[0].style.backgroundColor = "#BCCFB2";
                
                if(switchOff)
                {
                    setTimeout(function(){
                        eltIds[0].style.backgroundColor = "";
                    }, 500);
                }
            }
        }
            
        $scope.changeOrderingAmount = function(menuItem, amountAction, classId)
        {
            setBgColorSwitch(classId, true);

            addUpdateItemInOrder($scope.hotelState.currentMenuOrder, menuItem, amountAction);
        }
            
        $scope.addOrderItemAction = function(showMenuItem, action)    
        {
            if(showMenuItem.delimiter){
               return; 
            }

            if($scope.hotelState.currentMenuOrder && ($scope.hotelState.currentMenuOrder.orderStatus=='ACCEPTED' || $scope.hotelState.currentMenuOrder.orderStatus=='EXECUTED'))
            {
                $scope.mainState.errorMsg = "Neue Menu-Bestellung wird freigestellt, sobald die bestehende bezahlt wird...";
                $rootScope.topPage();
                return;
            }
            //setBgColorSwitch("item_" + showMenuItem.id, false);
            
            if(showMenuItem && action=='addItemToOrder')
            {
                //$scope.localState.newOrder.price += showMenuItem.price;
                $scope.hotelState.currentMenuOrder = addUpdateItemInOrder($scope.hotelState.currentMenuOrder, showMenuItem);
            }
        }
            
        $scope.emptyPreviousSearch = function()
        {
            $scope.localState.searchFilter = {};
            $scope.localState.selectedFilterHotel=null;

            $scope.localState.filterHotels = $filter('filter')($scope.mainState.hotelArray, $scope.localState.searchFilter, true);//,  $scope.localState.strictSearch);

        };
            
        
            
            
        //$scope.isHotelStaffOrAdmin = function()
        //{
        //    return $scope.hotelState.profileData.id>0 && $scope.hotelState.profileData.logged && ($scope.hotelState.profileData.hotelStaff && $scope.hotelState.profileData.hotelId == $scope.localState.selectedFilterHotel.id || $scope.hotelState.profileData.admin);
        //    
        //}
            
        //##################### WATCHERS ####################

        $scope.$on('mainState.globalIntervalCheck', function(next, current) {
            //
            //if($state.current.name == "app.menuList")
            //{
            //    $scope.getUpdateModuleData();
            //}

        });
            
        $scope.$watch('localState.selectedFilterHotel', function(){
        //$scope.$on('localState.selectedFilterHotel', function(next, current) {
            //
            if($state.current.name == "app.menuList")
            {
                $scope.getUpdateModuleData();
            }

        });
            
        $rootScope.$on('menuOrderAccepted', function(event, mass) {

            if(!$scope.hotelState.hotelMenuOrders)
            {
                $scope.hotelState.hotelMenuOrders = [];
            }

            if($scope.hotelState.currentMenuOrder.orderStatus=="ACCEPTED" || $scope.hotelState.currentMenuOrder.orderStatus=="EXECUTED")
            {
                $scope.hotelState.addUpdateMenuOrder(angular.copy($scope.hotelState.currentMenuOrder));
                $scope.updateLocalFilter();

            }
            
        });
            
       
        ///############### INIT BLOCK #######################################

        $scope.getUpdateMenuList = function () {
            
            if($scope.localState.selectedFilterHotel && $scope.localState.selectedFilterHotel.id>0 || $scope.localState.initFilterHotelId>0)
            {
                var requesterId = $scope.hotelState.getRequesterId();
                var hotelId = $scope.localState.initFilterHotelId>0? $scope.localState.initFilterHotelId : $scope.localState.selectedFilterHotel.id;
                var cafeId = -1;
                //var orderId = -1;

                menuItemService.getMenuItem({customerId: requesterId, hotelId : hotelId, cafeId: cafeId, itemId:-1}, function(menuItemArray){

                    $scope.localState.initFilterHotelId = 0;

                    if(menuItemArray.length>0)
                    {
                        $scope.localState.hotelMenuItems[menuItemArray[0].hotelId] = [];
                    }

                    for(var m=0; m<menuItemArray.length; m++)
                    {
                        var nextMenuItem = menuItemArray[m];

                        $scope.localState.hotelMenuItems[nextMenuItem.hotelId].push(nextMenuItem);
                    }
                    
                    $scope.updateLocalFilter();
                    
                    $scope.localState.menuItemsLoaded = true;

                    $rootScope.showLoading(false);
                })
            }
            else {
                $rootScope.showLoading(false);
            }
            //$scope.getUpdateMainHotelList().then(function(responseList){
            //
            //    for (var h = 0; h < responseList.length; h++) {
            //        //$scope.accordionStatus.infoHotelOpen[responseList[h].id] = false;
            //        //$scope.accordionStatus.descriptionHotelOpen[responseList[h].id] = false;
            //    }
            //    
            //    $rootScope.showLoading(false);
            //    $scope.updateLocalFilter();
            //
            //});

        };
            
            
        $scope.switchMenuItems = function(switchMenuItem, direction)
        {
            if(!switchMenuItem)
            {
                return;
            }
            
            var nowIndex = switchMenuItem.orderIndex;
            
            if(direction)
            {
                nowIndex = direction=='top'? nowIndex-1 : nowIndex+1;

                switchMenuItem.orderIndex = nowIndex;
                $scope.saveMenuItem(switchMenuItem, switchMenuItem);
            }
            
        }
            
        $scope.addUpdateMenuItem = function(newMenuItem)
        {
            var menuItemUpdated = false;
            
            var currentHotelMenuItems = $scope.localState.hotelMenuItems[newMenuItem.hotelId];

            if(newMenuItem.initId>0 && newMenuItem.hotelId>0 && currentHotelMenuItems)
            {
                for(var m=0; m<currentHotelMenuItems.length; m++)
                {
                    if(currentHotelMenuItems[m].initId == newMenuItem.initId)
                    {
                        currentHotelMenuItems[m] = newMenuItem;
                        menuItemUpdated = true;
                        
                        if(!newMenuItem.active || newMenuItem.hidden)
                        {
                            currentHotelMenuItems.splice(m, 1);
                        }
                        
                        break;
                    }
                }
            }

            if(!$scope.localState.hotelMenuItems[newMenuItem.hotelId])
            {
                $scope.localState.hotelMenuItems[newMenuItem.hotelId] = [];
            }
            
            if(!menuItemUpdated)
            {
                $scope.localState.hotelMenuItems[newMenuItem.hotelId].push(newMenuItem);
            }

            $scope.updateLocalFilter();
        }
            
        

        $scope.checkName = function(editData, origMenuItemData) {
            //if (id === 2 && data !== 'awesome') {
            //    return "Username 2 should be `awesome`";
            //}
        }; 
            
        $scope.checkIndex = function(editData, origMenuItemData) {
            if(!Number.isInteger(parseInt(editData))){
                return "Wrong index"; 
            }
        };
            
        $scope.checkPrice = function(editData, origMenuItemData) {
            if(!(/^-?(\d{1,3})(\.\d{1,2})?$/.test(editData))){
                return "Wrong Price"; 
            }
        };

        $scope.saveMenuItem = function(menuItemData, origMenuItemData) {
            //$scope.user not updated yet
            
            angular.extend(menuItemData, {
                delimiter: origMenuItemData.delimiter, 
                amount: origMenuItemData.amount, 
                orderIndex: origMenuItemData.orderIndex, 
                price: menuItemData.price? menuItemData.price : origMenuItemData.price, 
                cafeId: origMenuItemData.cafeId, 
                hotelId: origMenuItemData.hotelId, 
                senderId: $scope.hotelState.getRequesterId(),
                initId: (origMenuItemData.initId? origMenuItemData.initId : 0)
            });
            //return $http.post('/saveUser', data);
            
            menuItemService.addUpdateMenuItem({customerId: $scope.hotelState.profileData.id, hotelId: menuItemData.hotelId, cafeId:menuItemData.cafeId, itemId: menuItemData.initId}, menuItemData, function(menuItem)
            {
                $scope.addUpdateMenuItem(menuItem);
                $rootScope.topPage();
            }, function(error){
                $rootScope.showErrorObj(error);
            });
            
        };

        // remove user
        $scope.removeMenuItem = function(delMenuItem) {
            
            //$scope.hotelMenuItems[$scope.localState.selectedFilterHotel.id].splice(index, 1);
            
            menuItemService.deleteMenuItem({customerId: $scope.hotelState.profileData.id, hotelId: delMenuItem.hotelId, cafeId:delMenuItem.cafeId, itemId: delMenuItem.initId}, delMenuItem, function(menuItem)
            {
                menuItem.active = false;
                
                $scope.addUpdateMenuItem(menuItem);
            });
        };

        // add user
        $scope.addMenuItem = function(itemType) {

            if($scope.localState.selectedFilterHotel && !$scope.localState.hotelMenuItems[$scope.localState.selectedFilterHotel.id])
            {
                $scope.localState.hotelMenuItems[$scope.localState.selectedFilterHotel.id] = [];
            }
            
            var currentHotelMenuItemIndex = $scope.localState.hotelMenuItems[$scope.localState.selectedFilterHotel.id].length;

            
            var newItem = $scope.hotelService.getInitMenuItem();

            newItem.hotelId = $scope.localState.selectedFilterHotel.id;
            newItem.senderId = $scope.hotelState.getRequesterId();
            newItem.orderIndex = currentHotelMenuItemIndex;
                
            if(itemType && itemType=="delimiter")
            {
                newItem.delimiter = true;
            }

            //newItem.amount = (newItem.isDelimiter? 0:1);

            menuItemService.addUpdateMenuItem({customerId: $scope.hotelState.profileData.id, hotelId: $scope.localState.selectedFilterHotel.id, cafeId:-1, itemId: -1}, newItem, function(menuItem)
            {
                $scope.inserted = menuItem;
                $scope.addUpdateMenuItem(menuItem);
                //$scope.updateLocalFilter();
            });

            $scope.updateLocalFilter();
        };

        $scope.orderMenuAction = function(menuOrder, action)
        {
            $rootScope.rootErrorMsg = false;
            $scope.mainState.errorMsg = false;

            var requesterId = $scope.hotelState.getRequesterId();
            var hotelId = $scope.localState.selectedFilterHotel? $scope.localState.selectedFilterHotel.id : menuOrder.menuItems[0].hotelId;
            var cafeId = 0;
            
            if(menuOrder)
            {
                var error = $scope.hotelService.validateMenuInfo(menuOrder);

                if(error.length>0)
                {
                    $scope.mainState.errorMsg = error;
                    $rootScope.topPage();
                    return;
                }

                if(action=="submitMenu")
                {
                    menuOrder.senderId = requesterId;
                    menuOrder.hotelId = hotelId;

                    var orderId = menuOrder.id? menuOrder.id  : -1;

                    var decodedMenuOrder = $scope.hotelService.decodeMenuOrder(menuOrder);

                    $rootScope.showLoading(true);

                    $scope.hotelService.getMenuOrderService().addUpdate({customerId: requesterId, hotelId: hotelId, cafeId: cafeId, orderId: orderId, showClosed:false}, decodedMenuOrder, function(resp)
                        {
                            $scope.hotelState.currentMenuOrder = resp;
                            $scope.localState.menuAccepted = true;
                            $scope.mainState.successMsg = "Menu order updated";
                            $rootScope.showLoading(false);
                        },
                        $rootScope.showErrorObj);
                }
                else if(action)
                {
                    var orderId = menuOrder.id;

                    var decodedMenuOrder = $scope.hotelService.decodeMenuOrder(menuOrder);
                    
                    switch(action)
                    {
                        case "executeMenu": decodedMenuOrder.orderStatus = "EXECUTED"; break;
                        case "closeMenu": decodedMenuOrder.orderStatus = "CLOSED"; break;
                        case "rejectMenu": decodedMenuOrder.orderStatus = "REJECTED"; break;
                    }
                    
                    $rootScope.showLoading(true);

                    $scope.hotelService.getMenuOrderService().addUpdate({customerId: requesterId, hotelId: hotelId, cafeId: cafeId, orderId: orderId, showClosed: false}, decodedMenuOrder, function(respObj)
                    {
                        if(menuOrder)
                        {
                            menuOrder = respObj;
                        }

                        $scope.hotelState.addUpdateMenuOrder(respObj);
                        $scope.updateLocalFilter();
                        $rootScope.showLoading(false);

                    });
                }
            }
            
        }
            
        $scope.onSortEnd = function (dropElt, initIndex) {
            console.log('sort end');
    
            setTimeout($scope.detectOrderChange, 500);
            
        }
            
            
        $scope.getUpdateModuleData = function() {

            if ($state.current.name == "app.menuList") {
                $scope.getUpdateMenuList();
                //$scope.localState.hotelCitySelectorUpdator++;
                //$rootScope.$broadcast('hotelCitySelectorChangeEvent', []);
            }

          

        }
            
        $scope.resetOrder = function()
        {
            $scope.hotelState.currentMenuOrder = $scope.hotelService.getInitMenuOrder();
        }
            
        function getExistingMenuOrder(requestOrderId)
        {
            var requesterId = $scope.hotelState.getRequesterId();
            var hotelId = $scope.localState.selectedFilterHotel? $scope.localState.selectedFilterHotel.id : $scope.hotelState.profileData.hotelId;
            var cafeId = 0;
            var orderId = requestOrderId? requestOrderId : -1;

            //var menuFound = false;
            
            for(var n=0; n<$scope.hotelState.hotelMenuOrders.length; n++)
            {
                if($scope.hotelState.hotelMenuOrders[n] && ($scope.hotelState.hotelMenuOrders[n].initId == orderId || $scope.hotelState.hotelMenuOrders[n].id == orderId))
                {
                    var foundMenu = $scope.hotelState.hotelMenuOrders[n];

                    //menuFound = true;

                    if(foundMenu.orderStatus=="ACCEPTED" || foundMenu.orderStatus=="EXECUTED" || foundMenu.orderStatus=="CLOSED" )
                    {
                        //if($scope.isHotelStaffOrAdmin())
                        //$scope.hotelState.hotelMenuOrders[n] = foundMenu;
                        $scope.hotelState.currentMenuOrder = foundMenu;

                        $scope.localState.selectedFilterHotel = {};
                        $scope.localState.selectedFilterHotel.id = $scope.hotelState.currentMenuOrder.hotelId;
                        //$rootScope.$broadcast('hotelCitySelectorChangeEvent', []);

                        return;
                    }
                }
            }
            
            $scope.hotelService.getMenuOrderService().getMenu({customerId:requesterId, hotelId:hotelId, cafeId:cafeId, orderId:orderId, closed:false}, function(orderList)
            {
                if(orderList.length>0)
                {
                    for(var i=0; i<orderList.length; i++)
                    {
                        if(orderList[i].orderStatus=="ACCEPTED" || orderList[i].orderStatus=="EXECUTED"|| orderList[i].orderStatus=="CLOSED" )
                        {
                            $scope.hotelState.addUpdateMenuOrder( orderList[i]);
                            $scope.hotelState.currentMenuOrder = orderList[i];

                            $scope.localState.selectedFilterHotel = {};
                            $scope.localState.selectedFilterHotel.id = $scope.hotelState.currentMenuOrder.hotelId;

                            $scope.updateLocalFilter();
                            
                            //$rootScope.$broadcast('hotelCitySelectorChangeEvent', []);

                            //for(var n=0; n<$scope.hotelState.hotelMenuOrders.length; n++)
                            //{
                            //    if($scope.hotelState.hotelMenuOrders[n] && ($scope.hotelState.hotelMenuOrders[n].initId == orderList[i].initId))
                            //    {
                            //        $scope.hotelState.hotelMenuOrders[n] = orderList[i];
                            //       
                            //    }
                            //}
                            

                        }
                    }
                }
                
                //if($scope.editMenuOrderId && orderList.length==0)
                //{
                //    $scope.hotelState.curentMenuOrder = orderList[0];
                //}
            });
        }

        function initMenuList(){
            
            $rootScope.showLoading(true);
           
            getExistingMenuOrder($scope.editMenuOrderId);
            
            if($scope.hotelState.profileData.checkedIn && $scope.hotelState.profileData.hotelId)
            {
                //$scope.hotelState.getCurrentHotel().then(function(hotel)
                {
                    $scope.localState.selectedFilterHotel = {
                        id: parseInt($scope.hotelState.profileData.hotelId)
                    };
                    $scope.hotelState.getCurrentHotel($scope.localState.selectedFilterHotel.id, $scope.hotelState.profileData).then(function(hotelResponse){

                        if(hotelResponse.name)
                        {
                            $scope.localState.selectedFilterHotel.name = hotelResponse.name;
                        }

                    });
                }
            //);
            }
            
            //if($scope.hotelState.profileData.hotelId>0)
            {
                //$stateParams.filterCity = $scope.hotelState.profileData.hotelCity;
                //$stateParams.filterHotelId = $scope.hotelState.profileData.hotelId;
                
                $scope.localState.initFilterCity = $stateParams.filterCity;
                $scope.localState.initFilterHotelId = $stateParams.filterHotelId;
            }
            
            $scope.getUpdateModuleData();
            //$rootScope.showLoading(false);
            $scope.updateLocalFilter();

            var isStafOrAdmin = $scope.localState.selectedFilterHotel? $scope.hotelState.detectHotelStaffOrAdmin($scope.localState.selectedFilterHotel.id) : false;
            
            $scope.localState.menuDescrWidth = (document.documentElement.clientWidth - 25 - 65 - (isStafOrAdmin?45:0)) + "px";

        }

        if($state.current.name == "app.menuList" || $scope.editMenuOrderId)
        {
            initMenuList();
        }
       
            
    }]);