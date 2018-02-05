var CarStoreCommon = (function() {
    var targetUrl = "http://localhost:8080/carstore/";
    var isCartEmpty = true;
    var isLoggedin = false;
    var appStatus = {};

    function getAppStatus() {
        let self = this;
        return $.get(targetUrl + "appstatus").done(function(data) {
            appStatus = JSON.parse(data);
            this.isCartEmpty = appStatus.cartEmpty;
            this.isLoggedin = appStatus.loggedIn;
        });
    }

    function setLogoutButtonStatus(visible) {
        if (!visible) {
            $("#logoutButton").css("display", "none");
        } else {
            $("#logoutButton").css("display", "block");
        }
    }

    function setCartButtonStatus(visible) {
        if (!visible) {
            $("#cartButton").css("display", "none");
        } else {
            $("#cartButton").css("display", "block");
        }
    }


    function setLoginButtonStatus(visible) {
        if (!visible) {
            $("#loginButton").css("display", "none");
        } else {
            $("#loginButton").css("display", "block");
        }
    }


    return {
        targetUrl: targetUrl,
        isCartEmpty: isCartEmpty,
        isLoggedin: isLoggedin,
        setLogoutButtonStatus: setLogoutButtonStatus,
        setCartButtonStatus: setCartButtonStatus,
        setLoginButtonStatus: setLoginButtonStatus,
        getAppStatus: getAppStatus

    }

})();


