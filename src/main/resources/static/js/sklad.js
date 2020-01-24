$(document).ready(function() {
    redirect2();
    redirect1()
});


function redirect2() {
        var $select = $("#teams");

        $select.on('change', function() {
            var id = $(this).children(":selected").attr("id");
            var x = "/sklad?id="+id;

            window.location = x;
        });
}

function redirect1() {
    var $select = $("#formationSelect");

    $select.on('change', function() {
        var id = $(this).children(":selected").attr("value");
        var value = $(this).children(":selected").attr("id");
        var x = "/updateFormation?formation="+value+"&idTeam="+id;

        window.location = x;
    });

}