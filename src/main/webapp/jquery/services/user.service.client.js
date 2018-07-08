function UserServiceClient() {
    this.login = login;
    this.register = register;
    var self = this;

    function login(username, password) {
        return fetch(self.login(),{
            method: 'post',
            body: JSON.stringify({username: username, password: password}),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) {
            if(response.status === 200){
                return response.json();
            }
            else {
                alert("Invalid Username or Password")
            }
        });
    }

    function register(user){
        return fetch('http://localhost:8080/api/register',{
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) { return response.json() });
    }
}