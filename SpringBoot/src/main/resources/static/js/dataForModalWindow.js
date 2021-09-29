$("#myModalEdit").on('show.bs.modal', function (e) {
    let userId = $(e.relatedTarget).data('user-id');

    let cols = $('#' + userId + ' td');
    let firstname = $(cols[1]).text();
    let id = $(cols[0]).text();
    let lastname = $(cols[2]).text();
    let email = $(cols[4]).text();
    let age = $(cols[3]).text();
    let roles = $(cols[5]).text();

    $('#firstNameInput').val(firstname);
    $('#lastNameInput').val(lastname);
    $('#emailInput').val(email);
    $('#idInput').val(id);
    $('#ageInput').val(age);
    $('#roleInput').val(roles);

});

$("#myModalDelete").on('show.bs.modal', function (e) {
    let user = $(e.relatedTarget).data('user-delete');

    let col = $('#' + user + ' td');
    let firstname = $(col[1]).text();
    let id = $(col[0]).text();
    let lastname = $(col[2]).text();
    let email = $(col[4]).text();
    let age = $(col[3]).text();
    let roles = $(col[5]).text();

    $('#firstName').val(firstname);
    $('#lastName').val(lastname);
    $('#email').val(email);
    $('#id').val(id);
    $('#age').val(age);


});