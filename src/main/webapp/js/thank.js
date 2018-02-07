$(function() {
    $(".buttonDetails").click(function() {
        viewDetail($(this).attr("data-productid"));
    });

    function viewDetail(itemId) {
        ProuctDetail.buildProductView("popup", itemId, viewDetailCallback);
        $("#popup").dialog({ minWidth: 750, maxWidth: 750, minHeight: 600, title: "Car Details" });
    }

    function viewDetailCallback() {
        $("#popup").dialog("close");
    }

    // this is to set up header buttons based on app states
    window.onload = function() {
        CarStoreCommon.resetHeaderButtons().done(function() {
        });
    }
});