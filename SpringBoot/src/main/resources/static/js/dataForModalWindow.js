$(document).ready(getAllUsers());

function getAllUsers() {
    $("#table").empty();
    $.ajax({
        type: 'GET',
        url: '/api/admin/allUsers',
        success: function (data) {
            console.log(data);
            $.each(data, function (i, user) {
                $('#table').append($('<tr>').append(
                    $('<td>').append($('<span>')).text(user.id),
                    $('<td>').append($('<span>')).text(user.username),
                    $('<td>').append($('<span>')).text(user.lastname),
                    $('<td>').append($('<span>')).text(user.age),
                    $('<td>').append($('<span>')).text(user.email),
                    $('<td>').append($('<span>')).text(user.roles),
                    $('<td>').append($('<button>').text("Edit").attr({
                        "type": "button",
                        "class": "btn btn-info edit",
                        "data-toggle": "modal",
                        "data-target": "#myModalEdit",
                    }).data("user", user)),
                    $('<td>').append($('<button>').text("Delete").attr({
                        "type": "button",
                        "class": "btn btn-danger delete",
                        "data-toggle": "modal",
                        "data-target": "#myModalDelete",
                    }).data("user", user))
                ))
            });
        }
    });
}

$(document).on("click", ".edit", function () {
    let user = $(this).data('user');

    $('#firstNameInput').val(user.username);
    $('#lastNameInput').val(user.lastname);
    $('#emailInput').val(user.email);
    $('#idInput').val(user.id);
    $('#ageInput').val(user.age);
    $('#roleInput').val(user.roles);
});

$(document).on("click", ".editUser", function () {
    let formData = $(".formEditUser").serializeArray();
    $.ajax({
        type: 'PUT',
        url: '/api/admin/update',
        data: formData,
        success: function () {
            getAllUsers();
        }
    });
});

$(document).on("click", ".delete", function () {
    let user = $(this).data('user');

    $('#firstName').val(user.username);
    $('#lastName').val(user.lastname);
    $('#email').val(user.email);
    $('#id').val(user.id);
    $('#age').val(user.age);

    $(document).on("click", ".deleteUser", function () {
        $.ajax({
            type: 'DELETE',
            url: '/api/admin/remove',
            data: {id: $('#id').val()},
            success: function () {
                getAllUsers();
            }
        });
    });

});

$('.addUser').click(function () {
    $('#usersTable').trigger('click');
    let formData = $('.formAddUser').serializeArray();
    $.ajax({
        type: 'POST',
        url: '/api/admin/addUser',
        data: formData,
        success: function () {
            $('.formAddUser')[0].reset();
            getAllUsers();
        }
    });
});
$(function () {
    $("#userTable").empty();
    $.ajax({
        type: 'GET',
        url: '/api/user/getUser',
        error: function () {
            $('#blockMenuForUser').hide();
        },
        success: function (data) {
            console.log(data);
            $.each(data, function (i, user) {
                if (user.roles === "USER") {
                    $('#menuUser').trigger('click');
                    $('#main2').trigger('click');
                    $('#blockMenuForAdmin').hide();
                }
                $('#userTable').append($('<tr>').append(
                    $('<td>').append($('<span>')).text(user.id),
                    $('<td>').append($('<span>')).text(user.username),
                    $('<td>').append($('<span>')).text(user.lastname),
                    $('<td>').append($('<span>')).text(user.age),
                    $('<td>').append($('<span>')).text(user.email),
                    $('<td>').append($('<span>')).text(user.roles)
                ))
            });
        }
    });
});