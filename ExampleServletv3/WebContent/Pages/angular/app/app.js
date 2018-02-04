/*var app = angular.module("app", ["ngRoute"]);*/

var app = angular.module("app", []);
/*app.config(function($routeProvider) {
    $routeProvider


});
*/

app.filter('FilterTypeAccomation', function () {
    return function (TypeAccomation) {
        switch (TypeAccomation.toString()) {
            case "1":
                return "שישי";
                break;
            case "2":
                return "שבת";
                break;
            case "3":
                return "שישי ושבת";
                break;


            default:
                return "שישי ושבת";
                break;
        }
    };
});
 