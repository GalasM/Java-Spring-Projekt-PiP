document.addEventListener('DOMContentLoaded', function() {
    var calendarDiv = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarDiv, {
        plugins: [ 'dayGrid', 'list' ],
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
        ]
    });

    calendar.render();
});