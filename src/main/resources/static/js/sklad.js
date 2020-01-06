$(document).ready(function() {
    redirect2();
    loadStorage();
});

function loadStorage(){
    var x = localStorage.getItem("styleFormation");
    var $formationContainer = $("#formationContainer");
    if(x!=null) {
        $formationContainer.removeClass("formation4-5-1 formation5-4-1 formation5-3-2 formation3-4-3 formation4-4-2");
        $formationContainer.addClass(x);
    }
    else
    {
        $formationContainer.removeClass("formation4-5-1 formation5-4-1 formation5-3-2 formation3-4-3 formation4-4-2");
        $formationContainer.addClass('3-4-3');
    }
}

function redirect2() {
        var $select = $("#teams");

        $select.on('change', function() {
            var id = $(this).children(":selected").attr("id");
            var x = "/sklad?id="+id;

            var value = $(this).children(":selected").attr("value");
            var value2 = $(this).children(":selected").html();
            console.log("xd"+value2);
            var className = "formation4-4-2";

                    switch(value) {
                        case '4-5-1':
                            className = "formation4-5-1";
                            break;
                        case '5-4-1':
                            className = "formation5-4-1";
                            break;
                        case '5-3-2':
                            className = "formation5-3-2";
                            break;
                        case '3-4-3':
                            className = "formation3-4-3";
                            break;
                    }

                    var $formationContainer = $("#formationContainer");

                    $formationContainer.removeClass("formation4-5-1 formation5-4-1 formation5-3-2 formation3-4-3 formation4-4-2");
                    $formationContainer.addClass(className);
                    localStorage.setItem("styleFormation",className);
            window.location = x;
        });
}