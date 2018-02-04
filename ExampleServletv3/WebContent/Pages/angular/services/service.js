

app.service('Service', function ($http, $q) {
    this.GetDataSearch = function (param) {
        return $http.post("service/WebServiceUser.asmx/GetSearchData", param)
              .then(
               function (response) {
                   return {
                       Meals: response.data.d,
                   };
               },
               function (httpError) {
                   // translate the error
                   var error;
                   // LostConnection();
               });
    }



    this.GETProdcutASIN = function (param, WithSellerScore, WithProductInfo) {
        //  var  = 10;
        var data = { ASIN: param, WithSellerScore: WithSellerScore, WithProductInfo: WithProductInfo }
        return $http({
            url: "service/webEbay.asmx/GETProdcutASIN",
            dataType: 'json',
            method: 'POST',
            data: data,
            headers: { "Content-Type": "application/json" }
        })

              .then(
               function (response) {
                   return {
                       items: response.data.d,
                   };
               },
               function (httpError) {
                   // translate the error
                   var error;
                   // LostConnection();
               });
    }
    
     this.getBooks = function () {    	
            return $http({
            url: "getJson/show",
            dataType: 'json',
            method: 'GET',
            data: {},
            headers: { "Content-Type": "application/json" }
        })

              .then(
               function (response) {
                   return {
                       items: response.data,
                   };
               },
               function (httpError) {
                   // translate the error
                   var error;
                   // LostConnection();
               });
    }
     

     
     
     this.getBooks2 = function () {
      	 $.get("getJson/show", function(responseText) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
              $("#somediv").text(responseText);           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
          });
     	 
      
     }


});