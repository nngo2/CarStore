var UserDetail = (function() {
    let callback = null;

    function addHandler () {
        if (typeof callback === 'function') {
            callback();
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
            <div><label>Login Name: <input id="login" name="login" type="text" required/></label></div>
            <div><label>Password: <input id="password" name="password" type="text" required/></label></div>
            <div><label>Repeat Password: <input id="rpassword" name="rpassword" type="text" required/></label></div>
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