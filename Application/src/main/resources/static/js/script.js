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

document.addEventListener("DOMContentLoaded", function(){
// make it as accordion for smaller screens
    if (window.innerWidth < 992) {

        // close all inner dropdowns when parent is closed
        document.querySelectorAll('.navbar .dropdown').forEach(function(everydropdown){
            everydropdown.addEventListener('hidden.bs.dropdown', function () {
                // after dropdown is hidden, then find all submenus
                this.querySelectorAll('.submenu').forEach(function(everysubmenu){
                    // hide every submenu as well
                    everysubmenu.style.display = 'none';
                });
            })
        });

        document.querySelectorAll('.dropdown-menu a').forEach(function(element){
            element.addEventListener('click', function (e) {
                let nextEl = this.nextElementSibling;
                if(nextEl && nextEl.classList.contains('submenu')) {
                    // prevent opening link if link needs to open dropdown
                    e.preventDefault();
                    if(nextEl.style.display === 'block'){
                        nextEl.style.display = 'none';
                    } else {
                        nextEl.style.display = 'block';
                    }

                }
            });
        })
    }
// end if innerWidth
});
// DOMContentLoaded  end