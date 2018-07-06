function UserServiceClient() {
    this.login = login();
    var self = this;

    function login(username, password) {
        return fetch(self.login(),{
            method: 'post',
            body: JSON.stringify({username: username, password: password}),
            headers: {
                'content-type': 'application/json'
            }
        });
    }
}