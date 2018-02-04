app.controller("ProductsController", function ($scope, Service, $timeout) {

    
$scope.init = function () {
      console.log('init func');
      $scope.isLoginPage=false;
  
      $scope.currentUrl= 'products.html';
      getBooks();
  
     
    }
    $scope.WSForgetPassword = function () {
      
     
    }
    $scope.linkTo = function (page) {
        switch(page)
        {
        case 'home':
        $scope.currentUrl= 'products.html?v=11';
        break;
        case 'about':
        $scope.currentUrl= 'about.html?v=11';
        break;
        case 'login':
        $scope.currentUrl= 'l.html';
        $scope.isLoginPage=true;
        break;
        case 'Users':
        $scope.currentUrl= 'users.html';
        $scope.isLoginPage=true;
        break;
        
        
        
        default:
        $scope.currentUrl= 'login.html';
        $scope.isLoginPage=true;
        break;
        }
        
    }
    $scope.getUsers = function () {
   
            return $http({
            url: "users",
            dataType: 'json',
            method: 'GET',
            data: {},
            headers: { "Content-Type": "application/json" }
        })

              .then(
               function (response) {
                   return {
                       users: response.data,
                   };
               },
               function (httpError) {
                   // translate the error
                   var error;
                   // LostConnection();
               });
    
    }
    
    
      function getBooks()
    {
    
     $scope.books=[
       {id:1,title:"book1" ,description:"description descri descri descri",imgUrl:'pictures/book1_1.PNG',Status:1},
       {id:2,title:"book2" ,description:"description descri descri descri",imgUrl:'pictures/book1_1.PNG',Status:0},
       {id:3,title:"book3" ,description:"description descri descri descri",imgUrl:'pictures/book1_1.PNG',Status:0},
       {id:4,title:"book4" ,description:"description description description description",imgUrl:'pictures/book1_1.PNG',Status:0},
       {id:5,title:"book5" ,description:"description description description description",imgUrl:'pictures/book1_1.PNG',Status:0},
       {id:6,title:"book6" ,description:"description description description description",imgUrl:'pictures/book1_1.PNG',Status:1}];         
        
       return ;
       
      
          Service.getBooks().then(function (data) {
             var d =data.items.replace(/'/g, '"');
            $scope.books =JSON.parse(d);
            });
    }
      
      
    
    
});