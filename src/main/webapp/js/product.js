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

    function resetHeaderButtons() {
        CarStoreCommon.getAppStatus().done(function() {
            if (CarStoreCommon.isCartEmpty) {
                CarStoreCommon.setCartButtonStatus(false);
            } else {
                CarStoreCommon.setCartButtonStatus(true);
            }

            if (CarStoreCommon.isLoggedin) {
                CarStoreCommon.setLogoutButtonStatus(true);
                CarStoreCommon.setLoginButtonStatus(false);
            } else {
                CarStoreCommon.setLogoutButtonStatus(false);
                CarStoreCommon.setLoginButtonStatus(true);
            }
        });
    }

    function getProducts(page) {
        getProductData(page).done(function(data) {
            let pagedProduct = JSON.parse(data);
            displayProduct(pagedProduct.products);
            configurePaging(pagedProduct.paging);
        });
    }

    function configurePaging(page) {
        currentPage = page;
        if (page.currentPage >= page.totalPage) {
            $("#next")
                .attr('disabled',true);

        } else {
            $("#next")
                .attr('disabled',false)
                .click(function() {
                    currentPage.currentPage++;
                    if (currentPage.currentPage > page.totalPage) {
                        currentPage.currentPage = page.totalPage;
                    }
                    getProducts(currentPage.currentPage);
                });
        }
        if (page.currentPage <= 0) {
            $("#back").attr('disabled',true);
        } else {
            $("#back").attr('disabled',false)
                .click(function() {
                    currentPage.currentPage--;
                    if (currentPage.currentPage < 0) {
                        currentPage.currentPage = 0;
                    }
                    getProducts(currentPage.currentPage);
                });
        }
    }

    function addToCart(itemId) {
        addProductToCart(itemId);
        CarStoreCommon.setCartButtonStatus(true);
        CarStoreCommon.isCartEmpty = false;

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
                    </p>
                </div>
            <div class="clear"></div>
            `;
            $("#product-container").append(prodElement);
            $("#add_" + products[i].id).click(function() {
                addToCart($(this).attr("data-productid"));
            });
        }
    }

    window.onload  = function() {
        resetHeaderButtons();
        getProducts(0);
    }
});