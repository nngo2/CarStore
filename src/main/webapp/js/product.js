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
        if ((page.currentPage - 1) >= page.totalPage) {
            $("#next")
                .attr('disabled',true);

        } else {
            $("#next")
                .attr('disabled',false)
                .off('click')
                .on('click',function() {
                    currentPage.currentPage++;
                    if (currentPage.currentPage > (page.totalPage + 1)) {
                        currentPage.currentPage = page.totalPage + 1;
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
        toastr.info("Car has been added to cart");
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
                        <input id="add_${products[i].id}" class="button" type="button" value=" Add To Cart " data-productid = "${products[i].id}">
                        <input id="view_${products[i].id}" class="button" type="button" value=" View Details " data-productid = "${products[i].id}">
                        <input id="edit_${products[i].id}" class="button" type="button" value=" Edit " data-productid = "${products[i].id}">
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
        }
    }

    window.onload = function() {
        CarStoreCommon.resetHeaderButtons();
        loadPagedProducts(1);
        $("#addprod").off('click').on('click', function() {
            showAddProductView();
        });
    }
});