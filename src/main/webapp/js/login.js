$(function() {
    function showAddUserView() {
        UserDetail.buildAddUserView("popup", addCallback);
        $("#popup").dialog({ modal: true, minWidth: 750, maxWidth: 750, minHeight: 600, title: "Add New User" });
    }

    function addCallback(data) {
        $("#popup").dialog("close");
    }

    window.onload = function() {
        CarStoreCommon.resetHeaderButtons().done(function() {
            $("#create-user-action").off('click').on('click', function() {
                showAddUserView();
            });
        });
    }
});