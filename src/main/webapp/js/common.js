var CarStoreCommon = (function() {
    var targetUrl = "http://localhost:8080/carstore/";

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

    return {
        targetUrl: targetUrl,
        setLogoutButtonStatus: setLogoutButtonStatus,
        setCartButtonStatus: setCartButtonStatus

    }

})();


