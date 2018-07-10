(function () {
    $(main);
    var $usernameFld, $passwordFld, $firstNameFld, $lastNameFld, $roleFld, $emailFld;
    var $removeBtn, $editBtn, $createBtn;
    var $userRowTemplate, $tbody;
    var userService = new UserServiceClient();

    function main(){
        $usernameFld = $('#userameFld');
        $passwordFld = $('#passwordFld');
        $firstNameFld = $('#firstNameFld');
        $lastNameFld = $("#lastNameFld");
        $roleFld = $('#roleFld');
        $emailFld = $('#emailFld');

        $tbody = $('.wbdv-tbody');
        $userRowTemplate = $('.wbdv-template');

        $('#create').click(createUser);
        //$('#update').click(updateUser);

        findAllUsers();
    }

    function createUser() {

        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var role = $('#roleFld').val();
        var email = $('#emailFld').val();

        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            role: role,
            email: email
        };

        userService
            .createUser(user)
            .then(findAllUsers);
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
            console.log(currentUser.username);
            row.attr('id', currentUser.id);

            row.find('.wbdv-username')
                .html(currentUser.username);

            row.find('.wbdv-first-name')
                .html(currentUser.firstName);

            row.find('.wbdv-last-name')
                .html(currentUser.lastName);

            row.find('.wbdv-role')
                .html(currentUser.role);

            row.find('.wbdv-email')
                .html(currentUser.email);

            row.find('.delete').click(deleteUser);
            row.find('.edit').click(deleteUser);
            $tbody.append(row);
        }
    }

    function deleteUser(event){
        var deleteBtn = $(event.currentTarget);
        var userId = deleteBtn.parent().parent().parent().attr('id');
        userService.deleteUser(userId)
            .then(findAllUsers);
    }

    function editUser(event){
        console.log(event);
    }
})();