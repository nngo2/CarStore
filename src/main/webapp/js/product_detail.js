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

    function buildProductEdit (containerId, prodId, func) {
        getProductData(prodId).done(function(data) {
            buildEditView(containerId, JSON.parse(data), func);
        });
    }

    function okHandler (id) {
        if (typeof callback === 'function') {
            callback();
        }
    }

    function saveHandler (id) {
        if (typeof callback === 'function') {
            callback();
        }
    }

    function cancelHandler (id) {
        if (typeof callback === 'function') {
            callback();
        }
    }

    function buildDisplayView(containerId, product, func) {
        callback = func;

        let containerElement = $("#" + containerId);

        containerElement.empty();

        let prodElement = `  
            <div class="product">
                <div><img src="/carstore/image/${product.image}" alt="product image"/></div>             
                <div><strong>Title: </strong> ${product.name}</div>
                <div><strong>Make: </strong> ${product.make}</div>
                <div><strong>Model: </strong> ${product.model}</div>
                <div><strong>Year: </strong> ${product.year}</div>
                <div><strong>Description: </strong> ${product.description}</div>
                <div><strong>Price: </strong><span class="price">$${product.unitPrice.toLocaleString()}</span></div>
                <div class="center">
                    <input id="ok_${product.id}" class="button" type="button" value=" OK " data-productid = "${product.id}">
                </div>
            </div>
        <div class="clear"></div>
        `;

        containerElement.append(prodElement);

        $("#ok_" + product.id).click(function() {
            okHandler($(this).attr("data-productid"));
        });
    }

    function buildEditView(containerId, product, func) {
        callback = func;

        let containerElement = $("#" + containerId);

        containerElement.empty();

        let prodElement = `  
            <div class="product">
                <div><img src="/carstore/image/${product.image}" alt="product image"/></div>
                <div><label>Title: <input id="name" name="name" type="text" value="${product.name}" required/></label></div>
                <div><label>Make: <input id="make" name="make" type="text" value="${product.make}" required/></label></div>
                <div><label>Model: <input id="model" name="model" type="text" value="${product.model}" required/></label></div>
                <div><label>Year: <input id="year" name="year" type="number" value="${product.year}" pattern="\d{4}"/></label></div>
                <div><label>Description: <textarea id="description" name="description" rows="3" value="${product.description}"></textarea></label></div>
                <div><label>Price: <input id="year" name="year" type="number" value="${product.unitPrice}" required pattern="\d{1,}\.\d{2}"/></label></div>
                <div class="center">
                    <input id="save_${product.id}" class="button" type="button" value=" OK " data-productid = "${product.id}">
                    <input id="cancel_${product.id}" class="button" type="button" value=" Cancel " data-productid = "${product.id}">
                </div>
            </div>
        <div class="clear"></div>
        `;

        containerElement.append(prodElement);

        $("#save_" + product.id).click(function() {
            saveHandler($(this).attr("data-productid"));
        });

        $("#cancel_" + product.id).click(function() {
            cancelHandler($(this).attr("data-productid"));
        });
    }

    return {
        buildProductView: buildProductView,
        buildProductEdit: buildProductEdit
    };

})();