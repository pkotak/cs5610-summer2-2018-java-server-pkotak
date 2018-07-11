(function () {
    $(main);
    var $usernameFld, $passwordFld, $firstNameFld, $lastNameFld, $roleFld, $emailFld,
        $dobFld,$phoneFld;
    var $userRowTemplate, $tbody;
    var $id;
    var userService = new UserServiceClient();

    function main(){
        $usernameFld = $('#usernameFld');
        $passwordFld = $('#passwordFld');
        $firstNameFld = $('#firstNameFld');
        $lastNameFld = $("#lastNameFld");
        $roleFld = $('#roleFld');
        $emailFld = $('#emailFld');
        $dobFld = $('#dobFld');
        $phoneFld = $('#phoneFld');

        $tbody = $('.wbdv-tbody');
        $userRowTemplate = $('.wbdv-template');

        $('#create').click(createUser);
        $('#update').click(updateUser);

        findAllUsers();
    }

    function createUser() {

        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var role = $('#roleFld').val();
        var email = $('#emailFld').val();
        var dob = $('#dobFld').val();
        var phone = $('#phoneFld').val();

        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            role: role,
            email: email,
            dateOfBirth: dob,
            phone: phone
        };

        userService
            .createUser(user)
            .then(findAllUsers)
            .then(resetFields);
    }

    function findAllUsers(){
        userService.findAllUsers()
            .then(renderUI);
    }

    function renderUI(users){
        console.log('Inside render UI'+users);
        $tbody.empty();
        for(var i = 0; i < users.length; i++){
            var currentUser = users[i];
            var row = $userRowTemplate.clone();

            row.attr('id', currentUser.id);

            row.find('.wbdv-username')
                .html(currentUser.username);

            row.find('.wbdv-password')
                .html('*********');

            row.find('.wbdv-first-name')
                .html(currentUser.firstName);

            row.find('.wbdv-last-name')
                .html(currentUser.lastName);

            row.find('.wbdv-role')
                .html(currentUser.role);

            row.find('.wbdv-email')
                .html(currentUser.email);

            row.find('.wbdv-dob')
                .html(currentUser.dateOfBirth);

            row.find('.wbdv-phone')
                .html(currentUser.phone);

            row.find('.delete').click(deleteUser);
            row.find('.edit').click(editUser);
            $tbody.append(row);
        }
    }

    function deleteUser(event){
        var deleteBtn = $(event.currentTarget);
        var userId = deleteBtn.parent().parent().parent().attr('id');
        userService.deleteUser(userId)
            .then(findAllUsers);
    }

    function updateUser(){
        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var role = $('#roleFld').val();
        var email = $('#emailFld').val();
        var dob = $('#dobFld').val();
        var phone = $('#phoneFld').val();

        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            role: role,
            email: email,
            dateOfBirth: dob,
            phone: phone
        };

        userService
            .updateUser($id,user)
            .then(findAllUsers)
            .then(resetFields);
    }

    function editUser(event){
        var editBtn = $(event.currentTarget);
        $id = editBtn.parent().parent().parent().attr('id');
        userService.findUserById($id)
            .then(populateEditFields);
    }

    function populateEditFields(user){
        $usernameFld.val(user.username)
        $passwordFld.val(user.password);
        $firstNameFld.val(user.firstName);
        $lastNameFld.val(user.lastName);
        $roleFld.val(user.role);
        $emailFld.val(user.email);
        $dobFld.val(user.dateOfBirth);
        $phoneFld.val(user.phone);
    }

    function resetFields(){
        $usernameFld.val('');
        $passwordFld.val('');
        $firstNameFld.val('');
        $lastNameFld.val('');
        $roleFld.val('');
        $emailFld.val('');
        $dobFld.val('');
        $phoneFld.val('');
    }
})();