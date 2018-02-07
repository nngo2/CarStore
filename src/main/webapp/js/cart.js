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
        var confirmRemove = confirm("Are you sure you want to remove this item?");
        if(confirmRemove) {
            $('#item_' + $(this).attr("data-productid")).hide();
            removeProductFromCart($(this).attr("data-productid"));

            let currentTotalPrice = parseFloat($('#cartPrice').text());
            let priceOfRemovedItem = parseFloat($(this).attr("data-price"));
            currentTotalPrice = currentTotalPrice - priceOfRemovedItem;
            $('#cartPrice').text(currentTotalPrice.toLocaleString());
            if(currentTotalPrice > 0) {
                $('#cartPrice').text(currentTotalPrice.toLocaleString());
            } else{
                $('#cartPrice').text('Cart is empty!');
            }
        }
    });

    function removeProductFromCart(id) {
        let postData = {command: "remove-product-cart",  productIdRemoveFromCart: id};

        return  $.post(CarStoreCommon.targetUrl + "removeitem", {command: JSON.stringify(postData)});
    }

    // this is to set up header buttons based on app states
    window.onload = function() {
        CarStoreCommon.resetHeaderButtons().done(function() {
        });
    }
});