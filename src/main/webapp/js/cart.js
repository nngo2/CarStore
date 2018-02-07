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

    $(".removeButton").click(function() {
        $('#item_' + $(this).attr("data-productid")).hide();
        removeProductFromCart($(this).attr("data-productid"));

    });

    function removeProductFromCart(id) {
        let postData = {command: "remove-product-cart",  productIdRemoveFromCart: id};

        return  $.post(CarStoreCommon.targetUrl + "removeitem", {command: JSON.stringify(postData)});
    }

    window.onload = function() {
        CarStoreCommon.resetHeaderButtons();
    }
});