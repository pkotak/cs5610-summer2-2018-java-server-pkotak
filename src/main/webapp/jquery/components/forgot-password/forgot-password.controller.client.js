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
        //userService.sendEmail($emailFld.val());
    }
})();