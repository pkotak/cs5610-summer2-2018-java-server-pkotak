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

        id = getIdFromUrl();
        renderUI(id);
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
            dob: $dob.val()
        }

        userService.updateProfile(user)
            .then($('.alert').show());
    }

    function logout() {
        userService.logout();
    }
    
    function getIdFromUrl() {
        var url = window.location.href;
        var id = url.slice(url.indexOf('=')+1);
        return id;
    }

    function renderUI(id) {
        userService.findUserById(id)
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
        $dob.val(user.dob);
    }
})();