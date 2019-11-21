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
                url: '/api/event/all', // use the `url` property
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
                eventObj.remove();
                jQuery.post("/removeEvent", {"id": eventObj.id});
            }

        }
    });

    calendar.render();
});

