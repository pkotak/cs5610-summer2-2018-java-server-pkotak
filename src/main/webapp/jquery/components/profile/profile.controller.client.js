(function () {
    var $fixedUsername, $password, $firstName, $lastName, $role, $dob, $phone, $email;
    var $updateBtn, $logoutBtn;
    var userService = new UserServiceClient();
    $(init);
    
    function init() {
        $fixedUsername = $('#staticUsername');
        $password = $('#passwordFld');
        $firstName = $('#firstNameFld');
        $lastName = $('#lastNameFld');
        $role = $('#role');
        $phone = $('#phoneFld');
        $email = $('#emailFld');
        $dob = $('#dob');

        $updateBtn = $('#updateBtn');
        $logoutBtn = $('#logoutBtn');

        $updateBtn.click(updateProfile);
        $logoutBtn.click(logout);
    }

    function updateProfile() {
        var user = {
            username: $fixedUsername.val(),
            password: $password.val(),
            firstName: $firstName.val(),
            lastName: $lastName.val(),
            role: $role.val(),
            email: $email.val(),
            phone: $phone.val(),
            dob: $dob.val()
        }

        userService.updateProfile(user);
    }

    function logout() {
        userService.logout();
    }
})();