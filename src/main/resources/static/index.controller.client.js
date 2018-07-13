(function () {
    var $loginBtn, $adminBtn;
    $(init);

    function init() {
        $loginBtn = $('#loginBtn');
        $adminBtn = $('#adminBtn');

        $loginBtn.click(redirectLogin);
        $adminBtn.click(redirectAdmin);
    }

    function redirectLogin() {
        window.location.href = '../../jquery/components/login/login.template.client.html';
    }

    function redirectAdmin() {
        window.location.href = '../../jquery/components/admin/user-admin.template.client.html';
    }

})();