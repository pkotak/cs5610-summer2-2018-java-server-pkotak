(function () {
    var $usernameFld, $passwordFld, $verifyPasswordFld;
    var userService = new UserServiceClient();

    $(main);
    
    function main() {
        $usernameFld = $('#usernameFld');
        $passwordFld = $('#passwordFld');
        $verifyPasswordFld = $('#verifyPasswordFld');

        $('#registerBtn').click(register);
    }
    
    function register() {
        var user = {
            username: $usernameFld.val(),
            password: $passwordFld.val()
        };

        userService.register(user).then(function (response) {
            console.log('Response'+JSON.stringify(response));
        })
    }
})();