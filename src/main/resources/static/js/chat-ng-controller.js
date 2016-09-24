angular.module('WebChatAngularApp', [])
   .controller('SampleController', function($scope, $http) {
        $scope.sendMessage = function(username, inputMessage) {
            console.log("In sendMessage function in ng controller!");

            $http.get("/sendMessage.json?username=" + $scope.username + "&messageText=" + inputMessage)
                .then(
                    function successCallback(response) {
                        console.log(response.data);
                        console.log("Adding data to scope");
                        $scope.messageList = response.data;
                    },
                    function errorCallback(response) {
                        console.log("Unable to get data!");
                    });
        };

        $scope.setUsername = function(usernameNG) {
            console.log("In setUsername function in ng controller!");

            $scope.username = usernameNG;
            console.log("Username is: " + $scope.username);
        };
    });