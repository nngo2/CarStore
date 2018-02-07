$(function() {

    $("#sameAddress").change(function (e) {
        if ($('#sameAddress').prop('checked') == true) {
            $('#shipName').val($('#billName').val());
            $('#shipAddress1').val($('#billAddress1').val());
            $('#shipAddress2').val($('#billAddress2').val());
            $('#shipCity').val($('#billCity').val());
            $('#shipState').val($('#billState').val());
            $('#shipZip').val($('#billZip').val());
        }
        else {
            $('#shipName').val('');
            $('#shipAddress1').val('');
            $('#shipAddress2').val('');
            $('#shipCity').val('');
            $('#shipState').val('');
            $('#shipZip').val('');
        }
    });

    // this is to set up header buttons based on app states
    window.onload = function() {
        CarStoreCommon.resetHeaderButtons().done(function() {
        });
    }
});