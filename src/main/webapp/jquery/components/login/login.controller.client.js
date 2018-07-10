(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $usernameFld = $('#usernameFld');
        $passwordFld = $('#passwordFld');
        $loginBtn = $('#loginBtn');

        $loginBtn.click(verifyLogin);
    }

    function verifyLogin() {
        if ($usernameFld.val() == "" || $passwordFld.val() == "") {
            alert("Username or password cannot be blank!");
        }
        else {
            login();
        }

    }

    function login() {
        var username = $usernameFld.val();
        var password = $passwordFld.val();

        userService.login(username,password).then(function (response) {
            if(response.username === username && response.password === password){
                window.location.href = "../profile/profile.template.client.html?userid="+response.id;
            }
        });
    }
})();
