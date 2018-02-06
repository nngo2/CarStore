$(function(){
    let currentPage = null;

    function getProductData(pageNo) {
        let postData = {command: "get-product",  paging: { currentPage: pageNo}};
        return  $.post(CarStoreCommon.targetUrl + "productdata", {command: JSON.stringify(postData)});
    }

    function addProductToCart(id) {
        let postData = {command: "add-product-cart",  productIdAddToCart: id};
        return  $.post(CarStoreCommon.targetUrl + "additem", {command: JSON.stringify(postData)});
    }

    function deleteProductData(id) {
        let postData = {command: "delete-product",  productId: id};
        return  $.post(CarStoreCommon.targetUrl + "productdata", {command: JSON.stringify(postData)});
    }

    function loadPagedProducts(page) {
        CarStoreCommon.showSpinner();
        getProductData(page).done(function(data) {
            let pagedProduct = JSON.parse(data);
            displayProduct(pagedProduct.products);
            configurePaging(pagedProduct.paging);
            CarStoreCommon.hideSpinner();
        });
    }

    function configurePaging(page) {
        currentPage = page;

        // recalculate total page, since Java Math.ceil does not always round up!
        currentPage.totalPage = Math.ceil(currentPage.totalCount / currentPage.pageSize);

        if (currentPage.currentPage >= currentPage.totalPage) {
            $("#next")
                .attr('disabled',true);

        } else {
            $("#next")
                .attr('disabled',false)
                .off('click')
                .on('click',function() {
                    currentPage.currentPage++;
                    if (currentPage.currentPage > page.totalPage) {
                        currentPage.currentPage = page.totalPage;
                    }
                    loadPagedProducts(currentPage.currentPage);
                });
        }
        if ((page.currentPage - 1) <= 0) {
            $("#back").attr('disabled',true);
        } else {
            $("#back")
                .attr('disabled',false)
                .off('click')
                .on('click', function() {
                    currentPage.currentPage--;
                    if (currentPage.currentPage <= 1) {
                        currentPage.currentPage = 1;
                    }
                    loadPagedProducts(currentPage.currentPage);
                });
        }
    }

    function addToCart(itemId) {
        addProductToCart(itemId);
        CarStoreCommon.setCartButtonStatus(true);
        CarStoreCommon.isCartEmpty = false;
        CarStoreCommon.toasterInfo("Car has been added to cart");
    }

    function viewDetail(itemId) {
        ProuctDetail.buildProductView("popup", itemId, viewDetailCallback);
        $("#popup").dialog({ modal: true, minWidth: 750, maxWidth: 750, minHeight: 600, title: "Car Details" });
    }

    function viewDetailCallback() {
        $("#popup").dialog("close");
    }

    function editProduct(itemId) {
        ProuctDetail.buildProductEdit("popup", itemId, editDetailCallback);
        $("#popup").dialog({ modal: true, minWidth: 750, maxWidth: 750, minHeight: 600, title: "Edit Car Details" });
    }

    function editDetailCallback() {
        $("#popup").dialog("close");
        loadPagedProducts(currentPage.currentPage);
    }

    function showAddProductView () {
        ProuctDetail.buildAddView("popup", addCallback);
        $("#popup").dialog({ modal: true, minWidth: 750, maxWidth: 750, minHeight: 600, title: "Add New Car" });
    }

    function addCallback(data) {
        $("#popup").dialog("close");
        loadPagedProducts(currentPage.totalPage);
    }

    function deleteProduct(id) {
        deleteProductData(id).done(function() {
            CarStoreCommon.toasterInfo("Car has been deleted from the system");
            loadPagedProducts(currentPage.currentPage);
        });
    }

    function constructProductDetailActions(id) {
        if (CarStoreCommon.isAdmin) {
            return `
                <input id="edit_${
                id}" class="button" type="button" value=" Edit " data-productid = "${id}">
                <input id="delete_${id}" class="button" type="button" value=" Delete " data-productid = "${id}">
                 `;
        } else {
            return `
                <input id="add_${id}" class="button" type="button" value=" Add To Cart " data-productid = "${id}">
                <input id="view_${id}" class="button" type="button" value=" View Details " data-productid = "${id}">   
                `;
        }
    }

    function constructProductActions() {
        if (CarStoreCommon.isAdmin) {
            $("#add-product-action").append($("<input id='addprod' class='button' type='button' value=' Add Car '/>"));
            $("#addprod").off('click').on('click', function() {
                showAddProductView();
            });
        }
    }

    function displayProduct(products) {
        $("#product-container").empty();
        for (let i = 0; i < products.length; i++) {
            var prodElement = `  
                <div class="product">
                    <img src="/carstore/image/${products[i].image}" alt="product image"/>
                    <p class="title">${products[i].name}</p>
                    <p>${products[i].description}</p>
                    <p class="price">$${products[i].unitPrice.toLocaleString()}</p>
                    <p>
                        ${constructProductDetailActions(products[i].id)}
                    </p>
                </div>
            <div class="clear"></div>
            `;

            $("#product-container").append(prodElement);

            $("#add_" + products[i].id).click(function() {
                addToCart($(this).attr("data-productid"));
            });

            $("#view_" + products[i].id).click(function() {
                viewDetail($(this).attr("data-productid"));
            });

            $("#edit_" + products[i].id).click(function() {
                editProduct($(this).attr("data-productid"));
            });

            $("#delete_" + products[i].id).click(function() {
                deleteProduct($(this).attr("data-productid"));
            });
        }
    }

    window.onload = function() {
        CarStoreCommon.resetHeaderButtons().done(function () {
            constructProductActions();
        });
        loadPagedProducts(1);
    }
});