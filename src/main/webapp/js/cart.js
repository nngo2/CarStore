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

            let currentTotalPrice = parseFloat($('#price').text());
            let priceOfRemovedItem = parseFloat($(this).attr("data-price"));
            currentTotalPrice = currentTotalPrice - priceOfRemovedItem;
            if(currentTotalPrice) {
                $('#price').text('$' + currentTotalPrice.toLocaleString());
            } else{
                $('#price').text('Cart is empty!');
            }
            console.log($(this).attr("data-price"))
        }
    });

    function removeProductFromCart(id) {
        let postData = {command: "remove-product-cart",  productIdRemoveFromCart: id};

        return  $.post(CarStoreCommon.targetUrl + "removeitem", {command: JSON.stringify(postData)});
    }
});