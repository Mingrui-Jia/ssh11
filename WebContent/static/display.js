/**
 * 
 */
var app = angular.module("DisplayApp", []);
app.controller("DisplayController",
    function ($scope, $http) {
        $scope.detailsBook=function(book)
        {
            //var id = $scope.book.ID;
            var down = "http://it-ebooks-api.info/v1/book/" + book.ID;
            $http.get(down).success(function (response) {
                $scope.details = response;
                console.log(book.ID);            })
        }
        $scope.searchBooks = function () {
            var title = $scope.searchByTitle;
            var url = "http://it-ebooks-api.info/v1/search/" + title;

            $http.get(url).success(function (response) {
                //console.log(url);
                $scope.books = response.Books;
            })
        }
        
    })