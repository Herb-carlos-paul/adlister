"use strict";

$(document).ready(function() {
    $("#confirm_password").keyup(function() {
        if ($("#password").val() !== $("#confirm_password").val()) {
            $(".error").text("Passwords do not match").fadeIn("slow");
        } else {
            $(".error").fadeOut("slow");
        }
    });

    $(document).ready(function() {
        $("#email").keyup(function() {
            if (!validateEmail($("#email").val())) {
                $(".error").text("Email incorrect").fadeIn("slow");
            } else {
                $(".error").fadeOut("slow");
            }
        });
    });

    function validateEmail(email) {
        if (email.length > 0 && email.includes("@") && email.includes(".")) {
            return true;
        } else {
            return false;
        }
    }
});
