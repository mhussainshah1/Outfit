/*
$('.dropdown-menu a.dropdown-toggle').on('click', function(e) {
    if (!$(this).next().hasClass('show')) {
        $(this).parents('.dropdown-menu').first().find('.show').removeClass("show");
    }
    var $subMenu = $(this).next(".dropdown-menu");
    $subMenu.toggleClass('show');


    $(this).parents('li.nav-item.dropdown.show').on('hidden.bs.dropdown', function(e) {
        $('.dropdown-submenu .show').removeClass("show");
    });


    return false;
});*/

// Function to check whether both passwords are same or not.
/*function checkPassword(form) {
    let password1 = form.password.value;
    let password2 = form.confirmPassword.value;

    // If not same return false.
    if (password1 !== password2) {
        alert ("\nPassword did not match: Please try again... password 1 = " + password1 + " ,password 2 = " + password2)
        return false;
    }
}*/

/*document.querySelector('.button').onclick = function () {
    var password = document.querySelector('.password').value;
    var confirmPassword = document.querySelector('.confirmPassword').value;

    if (password === '') {
        alert("Field cannot be empty.");
    } else if (password !== confirmPassword) {
        alert("Password didn't match try again");
    } else if (password === confirmPassword) {
        alert("Password match")
    }
}*/
