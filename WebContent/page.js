function addAcc(account){
    let tr = $('<tr></tr>');
    let tdHealthInsuranceNbr = $('<td>'+account.healthInsuranceNbr+'</td>');
    let tdName = $('<td>'+account.name+'</td>');
    let tdLastname = $('<td>'+account.lastname+'</td>');
    let tdDateOfBirth =  $('<td>'+account.dateOfBirth+'</td>');
    let tdGender = $('<td>'+account.gender+'</td>');
    let tdHealthStatus = $('<td>'+account.healthStatus+'</td>');
    let tdTest = $('<td></td>');

    tr.append(tdHealthInsuranceNbr).append(tdName).append(tdLastname).append(tdDateOfBirth).append(tdGender).append(tdHealthStatus).append(tdTest);
    $('#tbPatients tbody').append(tr);
}

$(document).ready(function() {
    $('a[href=off]').click(function(event) {
        event.preventDefault();
        $.get({
            url : 'rest/logout',
            success : function() {
                window.location.href = 'login.html';
            }
        });
    });
});