/*
 * Created by Shravan on  4/2/18 7:46 PM.
 * Copyright (c) 2018. All rights reserved.
 */

$(function() {
    $('#nav-search-select').change(function() {
        var selectedText = $(this).find('option:selected').text();
        $('#nav-search').find('.nav-search-label').html(selectedText);
    });
});