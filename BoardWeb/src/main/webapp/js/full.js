/**
 * js/full.js
 */

document.addEventListener('DOMContentLoaded', function() {
	let eventAll = [];
	fetch('fullData.do')
		.then(result => result.json())
		.then(result => {
			console.log(result);
			eventAll = result;
			/*for (let i = 0; i < result.length; i++){
				eventAll[i]= result[i];
			}*/
			fullCalendarFunc()
			/*console.log(eventAll);*/
		})
		.catch(err => console.log(err));

	function fullCalendarFunc() {
		var calendarEl = document.getElementById('calendar');

		console.log(eventAll);

		var calendar = new FullCalendar.Calendar(calendarEl, {
			headerToolbar: {
				left: 'prev,next today',
				center: 'title',
				right: 'dayGridMonth,timeGridWeek,timeGridDay'
			},
			initialDate: new Date(),
			navLinks: true, // can click day/week names to navigate views
			selectable: true,
			selectMirror: true,
			select: function(arg) {
				var title = prompt('일정을 등록하시겠습니까?');
				console.log(title, arg.startStr, arg.endStr);
				// db에 등록
				fetch('fullAddData.do?title=' + title + '&start=' + arg.startStr + '&end=' + arg.endStr)
					.then(result => result.json())
					.then(result => {
						if (result.retCode == "OK") {
							// 화면출력.
							if (title) {
								calendar.addEvent({
									title: title,
									start: arg.start,
									end: arg.end,
									allDay: arg.allDay
								})
							}
							calendar.unselect(); // 화면출력.
						} else {
							alert('처리오류!');
						}
					})
					.catch(err => console.log(err))
			},
			eventClick: function(arg) {
				console.log(arg.event.title, arg.event.startStr, arg.event.endStr);
				if (confirm('일정을 삭제하시겠습니까?')) {
					arg.event.remove() // 화면삭제
					// db 삭제
					fetch('fullDeleteData.do?title=' + arg.event.title + '&start=' + arg.event.startStr + '&end=' + arg.event.endStr)
						.then(result => result.json())
						.then(result => {
							if (result.retCode == "OK") {
								// 화면출력.
								arg.event.remove() // 화면삭제
							} else {
								alert('처리오류!');
							}
						})
						.catch(err => console.log(err))
				}
			},
			editable: true,
			dayMaxEvents: true, // allow "more" link when too many events
			events: eventAll
		});
		calendar.render();
	}
});