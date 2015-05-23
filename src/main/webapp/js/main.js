$(".toggle-details").click(function(){
    $(this).parent().parent().toggleClass("panel-primary panel-default");
    $(this).parent().parent().find(".panel-body").toggleClass("hide");
});