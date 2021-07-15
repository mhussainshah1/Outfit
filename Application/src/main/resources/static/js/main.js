$(document).ready(function () {
    $('.div .delBtn, .table .delBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#myModal #delRef').attr('href', href);
        $('#myModal').modal();
    });
});

$(document).ready(function () {
    $('#checkBtn').click(function(event) {
        checked = $("input[type=checkbox]:checked").length;
        if(!checked) {
            event.preventDefault();
            alert("You must check at least one checkbox.");
            return false;
        }
    });
});
