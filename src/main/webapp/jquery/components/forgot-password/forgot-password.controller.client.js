(function () {
    var $emailFld;
    var $sendBtn;
    var userService = new UserServiceClient();
    $(init);

    function init() {
        $emailFld = $('#emailFld');
        $sendBtn = $('#sendBtn');

        $sendBtn.click(sendEmail);
    }

    function sendEmail() {
        $('.alert').show();
        userService.forgotPassword($emailFld.val())
            .then(function (value) {
                if(value){
                    $('.alert').show();
                } else{
                    alert('Please enter a valid email');
                }
            });
    }
})();