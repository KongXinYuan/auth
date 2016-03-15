/**
 * Created by kent on 3/12/16.
 */
$(function () {

    $("table thead :checkbox").click(function () {
        if ($(this).is(":checked")) {
            $(this).closest("table").find("tbody :checkbox").prop('checked',true);
        } else {
            $(this).closest("table").find("tbody :checkbox").prop('checked',false);
        }
    });

    $("table tbody :checkbox").click(function () {
        if ($(this).is(":checked")) {
            var totalnum = $(this).closest("table").find("tbody :checkbox").length;
            var checknum = $(this).closest("table").find("tbody :checked").length;
            if (totalnum == checknum) {
                $(this).closest("table").find("thead :checkbox").prop('checked',true);
            }
        } else {
            $(this).closest("table").find("thead :checkbox").prop('checked',false);
        }
    });
    
})