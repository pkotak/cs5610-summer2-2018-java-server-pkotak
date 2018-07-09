function UserServiceClient() {
    this.login = login;
    this.register = register;
    this.updateProfile = updateProfile;
    this.findUserById = findUserById;

    function login(username, password) {
        return fetch('http://localhost:8080/api/login',{
            method: 'post',
            body: JSON.stringify({username: username, password: password}),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (value) {
            if (value.ok){
                return value.json();
            }else{
                alert('Error');
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
        }).then(function (response) { return response.json(); });
    }

    function updateProfile(user){
        return fetch('http://localhost:8080/api/profile',{
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type' : 'application/json'
            }
        }).then(function (response) { return response.json(); });
    }

    function findUserById(id){
        return fetch('http://localhost:8080/api/user/'+id)
            .then(function (response) { return response.json(); });
    }
}