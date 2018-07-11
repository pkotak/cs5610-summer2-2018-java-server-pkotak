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

        userService.checkUsername($usernameFld.val())
            .then(function (value) {
                if(value === 'true'){
                    alert('Username is already taken');
                }
                else if($passwordFld.val() !== $verifyPasswordFld.val()){
                    alert('Password not matching');
                }else{
                    userService.register(user).then(function (response) {
                        window.location.href = '../profile/profile.template.client.html';
                    })
                }
            });

    }
})();