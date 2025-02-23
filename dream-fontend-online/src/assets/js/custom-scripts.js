$(document).ready(function() {
    if (typeof jQuery === "undefined") {
        console.error("jQuery is not loaded!");
        return;
    } else {
        console.log("✅ jQuery is loaded!");
    }

    // Select2
    $(".js-select2").each(function(){
        $(this).select2({
            minimumResultsForSearch: 20,
            dropdownParent: $(this).next('.dropDownSelect2')
        });
    });

    // Parallax
    $('.parallax100').parallax100();

    // Magnific Popup
    $('.gallery-lb').each(function() { 
        $(this).magnificPopup({
            delegate: 'a', 
            type: 'image',
            gallery: { enabled: true },
            mainClass: 'mfp-fade'
        });
    });

    // Wishlist
    $('.js-addwish-b2').on('click', function(e){
        e.preventDefault();
    });

    $('.js-addwish-b2').each(function(){
        var nameProduct = $(this).parent().parent().find('.js-name-b2').html();
        $(this).on('click', function(){
            swal(nameProduct, "is added to wishlist!", "success");
            $(this).addClass('js-addedwish-b2');
            $(this).off('click');
        });
    });

    $('.js-addcart-detail').each(function(){
        var nameProduct = $(this).parent().parent().parent().parent().find('.js-name-detail').html();
        $(this).on('click', function(){
            swal(nameProduct, "is added to cart!", "success");
        });
    });

    // Perfect Scrollbar
    $('.js-pscroll').each(function(){
        $(this).css('position', 'relative');
        $(this).css('overflow', 'hidden');
        var ps = new PerfectScrollbar(this, {
            wheelSpeed: 1,
            scrollingThreshold: 1000,
            wheelPropagation: false,
        });

        $(window).on('resize', function(){
            ps.update();
        });
    });
});
