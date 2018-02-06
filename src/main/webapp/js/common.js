var CarStoreCommon = (function() {
    var targetUrl = "http://localhost:8080/carstore/";
    var isCartEmpty = true;
    var isLoggedin = false;
    var isAdmin = false;
    var appStatus = {};

    function toasterInfo(msg) {
        toastr.options.closeButton = true;
        toastr.options.positionClass = "toast-bottom-right";
        toastr.info(msg);
    }

    function resetHeaderButtons() {
        let d = $.Deferred();

        getAppStatus().done(function() {
            if (CarStoreCommon.isCartEmpty) {
                CarStoreCommon.setCartButtonStatus(false);
            } else {
                CarStoreCommon.setCartButtonStatus(true);
            }

            if (CarStoreCommon.isLoggedin) {
                CarStoreCommon.setLogoutButtonStatus(true);
                CarStoreCommon.setLoginButtonStatus(false);
            } else {
                CarStoreCommon.setLogoutButtonStatus(false);
                CarStoreCommon.setLoginButtonStatus(true);
            }

            d.resolve();
        });

        return d.promise();
    }

    function getAppStatus() {
        return $.get(targetUrl + "appstatus").done(function(data) {
            appStatus = JSON.parse(data);
            CarStoreCommon.isCartEmpty = appStatus.cartEmpty;
            CarStoreCommon.isLoggedin = appStatus.loggedIn;
            CarStoreCommon.isAdmin = appStatus.adminUser;
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

    function showSpinner () {
        $("#spinner").show();
    }

    function hideSpinner () {
        $("#spinner").hide();
    }

    return {
        targetUrl: targetUrl,
        isCartEmpty: isCartEmpty,
        isLoggedin: isLoggedin,
        isAdmin: isAdmin,
        showSpinner: showSpinner,
        hideSpinner: hideSpinner,
        toasterInfo: toasterInfo,
        setLogoutButtonStatus: setLogoutButtonStatus,
        setCartButtonStatus: setCartButtonStatus,
        setLoginButtonStatus: setLoginButtonStatus,
        getAppStatus: getAppStatus,
        resetHeaderButtons: resetHeaderButtons
    }

})();