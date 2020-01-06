// App custom scripts

$(document).ready(function() {
    changeFormation();
});

function changeFormation() {
    if($("#changeFormationSelect").length) {
        var $select = $("#changeFormationSelect");

        $select.on('change', function() {
            var value = $select.val();
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
                default:
                    className = "formation4-4-2";
            }

            var $formationContainer = $("#formationContainer");

            $formationContainer.removeClass("formation4-5-1 formation5-4-1 formation5-3-2 formation3-4-3 formation4-4-2");
            $formationContainer.addClass(className);
        });
        
    }
}
