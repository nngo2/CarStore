var ProuctDetail = (function() {
    var callback = null;

    function getProductData (id) {
        let postData = {command: "get-product-detail",  productId: id };
        return  $.post(CarStoreCommon.targetUrl + "productdata", {command: JSON.stringify(postData)});
    }

    function buildProductView (containerId, prodId, func) {
        getProductData(prodId).done(function(data) {
            buildDisplayView(containerId, JSON.parse(data), func);
        });
    }

    function okHandler (id) {
        if (typeof callback === 'function') {
            callback();
        }
    }

    function buildDisplayView(containerId, product, func) {
        callback = func;

        let containerElement = $("#" + containerId);

        containerElement.empty();

        let prodElement = `  
            <div>
                <img src="/carstore/image/${product.image}" alt="product image"/>
                <p><strong>Title: </strong> ${product.name}</p>
                <p><strong>Make: </strong> ${product.make}</p>
                <p><strong>Model: </strong> ${product.model}</p>
                <p><strong>Year: </strong> ${product.year}</p>
                <p><strong>Description: </strong> ${product.description}</p>
                <p><strong>Price: </strong><span class="price">$${product.unitPrice.toLocaleString()}</span></p>
                <p class="center-50">
                    <input id="ok_${product.id}" class="button" type="button" value=" OK " data-productid = "${product.id}">
                </p>
            </div>
        <div class="clear"></div>
        `;

        containerElement.append(prodElement);

        $("#ok_" + product.id).click(function() {
            okHandler($(this).attr("data-productid"));
        });
    }

    return {
        buildProductView: buildProductView
    };

})();