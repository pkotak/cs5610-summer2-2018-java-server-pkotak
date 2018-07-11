(function () {
    var $fixedUsername, $password, $firstName, $lastName, $role, $dob, $phone, $email;
    var $updateBtn, $logoutBtn;
    var id;
    var userService = new UserServiceClient();
    $(init);
    
    function init() {
        $fixedUsername = $('#fixedUsername');
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

        renderUI();
    }

    function updateProfile() {
        var user = {
            id: id,
            username: $fixedUsername.val(),
            password: $password.val(),
            firstName: $firstName.val(),
            lastName: $lastName.val(),
            role: $role.val(),
            email: $email.val(),
            phone: $phone.val(),
            dateOfBirth: $dob.val()
        };

        userService.updateProfile(user)
            .then(function (response) {
                console.log(JSON.stringify(response));
                $('.alert').show();
            })
    }

    function logout() {
        userService.logout();
    }

    function renderUI() {
        userService.getProfile()
            .then(updateFields);
    }

    function updateFields(user){
        $fixedUsername.val(user.username);
        $password.val(user.password);
        $firstName.val(user.firstName);
        $lastName.val(user.lastName);
        $role.val(user.role);
        $phone.val(user.phone);
        $email.val(user.email);
        $dob.val(user.dateOfBirth);
    }
})();