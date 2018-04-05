/*
 * Created by Shravan on  2/4/18 11:58 PM.
 * Copyright (c) 2018. All rights reserved.
 */

$("#quantity").on("click", "pre", function() {
    var $pre = $(this);
    var $textarea = $("<textarea>");
    $textarea.append($pre.contents());
    $pre.replaceWith($textarea);
    return false;
});

