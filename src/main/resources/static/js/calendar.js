document.addEventListener('DOMContentLoaded', function() {
    var calendarDiv = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarDiv, {
        plugins: [ 'dayGrid', 'list'],
        defaultView: 'dayGridMonth',
        header: {
            left: 'today prev,next',
            center: 'title',
            right: 'dayGridMonth,listMonth'
        },
        buttonText: {
            today: 'Today',
            month: 'Grid',
            list: 'List'
        },
        allDayText: '',
        height: 'auto',
        events:
            {
                url: '/api/event/all1',
                extraParams:{
                    id: localStorage.getItem("player")
                }
            } /*function() { {
                    var xmlHttp = new XMLHttpRequest();
                    xmlHttp.open( "GET", "/api/event/dupa", false ); // false for synchronous request
                    xmlHttp.send( null );
                }
                    return {
                        id: xmlHttp.responseText
                    };
                }
                }*/
        ,
        editable: true,
        eventClick: function(info) {
            var eventObj = info.event;
            var whatDo = confirm('Chcesz usunąć wydarzenie?', {
                buttons: {
                    Ok: true,
                    Cancel: false
                }
            });
            if(whatDo) {
                var myRedirect = function(redirectUrl, arg, value) {
                    var form = $('<form action="' + redirectUrl + '" method="post">' +
                        '<input type="hidden" name="'+ arg +'" value="' + value + '"></input>' + '</form>');
                    $('body').append(form);
                    $(form).submit();
                };
                myRedirect("/removeEvent", "id", eventObj.id);
                eventObj.remove();
            }
        }
    });

    calendar.render();
});

function showInfo(){
    let type = document.getElementById("type").value;
    var x =document.getElementById("emptyField");
    var x1 = document.getElementById("trainingDate");
    if(type==="match"){
        x.style.display = "block";
        x1.required= true;
    }
    else{
        x.style.display = "none";
        x1.required= false;
    }
}


$(document).ready(function() {
    $('#form1').bootstrapValidator({
        fields: {
            trainingDate: {
                validators: {
                    date: {
                        format: 'YYYY-MM-DD'
                    },
                    callback: {
                        message: 'Trening musi się odbyć przed meczem!',
                        callback: function(value, validator) {
                            var match = document.getElementById('start');
                            var m = new moment(value, 'YYYY-MM-DD', true);
                            if (!m.isValid()) {
                                return false;
                            }
                            return m.isBefore(match.value);
                        }
                    }
                }
            }
        }
    });
    $('#allFootballers').on('change', function() {
        var x = $("#allFootballers option:selected").val()
        localStorage.setItem("player",x);
        window.location="/calendar"
    })
});

$(document).ready(function() {
    loadStorage();
});

function loadStorage() {
    var x = localStorage.getItem("player");
}

