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
        eventSources: [
            {
                url: '/api/event/all',
            }
        ],
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

