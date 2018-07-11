function UserServiceClient() {
    this.login = login;
    this.checkUsername = checkUsername;
    this.register = register;
    this.getProfile = getProfile;
    this.updateProfile = updateProfile;
    this.findUserById = findUserById;
    this.findAllUsers = findAllUsers;
    this.createUser = createUser;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.logout = logout;
    this.forgotPassword = forgotPassword;

    function login(username, password) {
        return fetch('http://localhost:8080/api/login',{
            method: 'post',
            'credentials': 'include',
            body: JSON.stringify({username: username, password: password}),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) {
            return response.text().then(function(text) {
                return text ? JSON.parse(text) : {}
            })
        });
    }

    function checkUsername(username){
        return fetch('http://localhost:8080/api/username/'+ username,{
            method: 'get'
        }).then(function (response) {
            return response.text();
        });
    }

    function register(user){
        return fetch('http://localhost:8080/api/register',{
            method: 'post',
            'credentials': 'include',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) {
            return response.text().then(function(text) {
                return text ? JSON.parse(text) : {}
            })
        });
    }

    function getProfile(){
        return fetch('http://localhost:8080/api/profile',{
            'credentials': 'include'
        }).then(function (response) { return response.json(); })
    }

    function updateProfile(user){
        return fetch('http://localhost:8080/api/profile',{
            method: 'put',
            body: JSON.stringify(user),
            'credentials':'include',
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) { return response.json(); });
    }

    function findUserById(id){
        return fetch('http://localhost:8080/api/user/'+id)
            .then(function (response) { return response.json(); })
    }

    function findAllUsers() {
        return fetch('http://localhost:8080/api/user')
            .then(function (response) { return response.json(); })
    }

    function createUser(user){
        return fetch('http://localhost:8080/api/register',{
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type' : 'application/json'
            }
        }).then( function (response) {return response.json();});
    }

    function deleteUser(id){
        return fetch('http://localhost:8080/api/user/'+id,{
            method: 'delete'
        }).then(function (response) { return response });
    }

    function updateUser(id, user){
        return fetch('http://localhost:8080/api/user/'+id,{
            method: 'put',
            body: JSON.stringify(user),
            headers:{
            'content-type' : 'application/json'
            }
        }).then(function (response) { return response.json(); });
    }

    function logout() {
        return fetch('http://localhost:8080/api/logout',{
            method: 'post'
        })
    }

    function forgotPassword(email){
        return fetch('http://localhost:8080/api/resetpassword',{
            method: 'post',
            body: JSON.stringify({email: email}),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) { return response.text(); })
    }
}