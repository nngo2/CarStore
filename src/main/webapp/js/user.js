var UserDetail = (function() {
    let callback = null;

    function addUser (user) {
        let postData = {command: "add-user",  user: user };
        return  $.post(CarStoreCommon.targetUrl + "user", {command: JSON.stringify(postData)});
    }

    function addHandler () {
        if (typeof callback === 'function') {
            let firstName = $("#fname").val();
            let lastName = $("#lname").val();
            let username = $("#username").val();
            let password = $("#password").val();
            let rPassword = $("#rpassword").val();

            if (firstName === "" || lastName === "" || username === "") {
                CarStoreCommon.toasterError("Names must be filled out");
                return;
            }

            if (password === "") {
                CarStoreCommon.toasterError("Passowrd must be filled out");
                return;
            }

            if (password !== rPassword) {
                CarStoreCommon.toasterError("Passowrd does not match");
                return;
            }

            let user = {
                firstName: firstName,
                lastName: lastName,
                username: username,
                password: password
            };

            addUser(user).done(function(data) {
                let result = JSON.parse(data);
                if (result.error) {
                    CarStoreCommon.toasterError(result.error);
                    return;
                } else {
                    CarStoreCommon.toasterInfo(result.success);
                    callback();
                }
            });
        }
    }

    function cancelHandler () {
        if (typeof callback === 'function') {
            callback();
        }
    }

    function buildAddUserView(containerId, func) {
        callback = func;

        let containerElement = $("#" + containerId);

        containerElement.empty();

        let prodElement = `      
        <div class="user">
            <div><label>First Name: <input id="fname" name="fname" type="text" required/></label></div>
            <div><label>Last Name: <input id="lname" name="lname" type="text" required/></label></div>
            <div><label>Login Name: <input id="username" name="username" type="text" required/></label></div>
            <div><label>Password: <input id="password" name="password" type="password" required/></label></div>
            <div><label>Repeat Password: <input id="rpassword" name="rpassword" type="password" required/></label></div>
              <div class="center">
                <input id="add_user" class="button" type="button" value=" OK " />
                <input id="cancel_add" class="button" type="button" value=" Cancel " />
            </div>
        </div>
        <div class="clear"></div>
        `;

        containerElement.append(prodElement);

        $("#add_user").click(function() {
            addHandler();
        });

        $("#cancel_add").click(function() {
            cancelHandler();
        });

    }
    return {
        buildAddUserView: buildAddUserView
    };
})();