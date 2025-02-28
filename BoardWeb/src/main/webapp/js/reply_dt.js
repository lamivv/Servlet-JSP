/**
 * js/reply_dt.js
 */

let table = new DataTable('#example', {
	ajax: 'datatable.do?bno=' + bno,
	lengthMenu: [
		[5, 10, 25, 50, -1],
		[5, 10, 25, 50, 'All']
	]
});

// 화면 row(행) 추가
let counter = 1;
function addNewRow() {
	let reply = document.querySelector('#reply').value;
	let param = { bno, reply, replyer: logid };

	svc.addReply(param
		, function(result) {
			let rvo = result.retVal;
			table.row.add([
				rvo.replyNo,
				rvo.reply,
				rvo.replyer,
				rvo.replyDate
			]).draw(false);
		}
		, function(err) {
		}
	)
}
	

document.querySelector('#addReply').addEventListener('click', addNewRow);

// Automatically add a first row of data
// addNewRow();

// tr 선택 및 선택 해제
let delNo = 0;
table.on('click', 'tbody tr', (e) => {
	let classList = e.currentTarget.classList; // .classList요소의 클래스속성을 배열로 보여준다['selecte']
	console.log(e.currentTarget.children[0].innerText);


	if (classList.contains('selected')) { //contains 클래스속성에 해당하는게 있는지 ..
		classList.remove('selected');
	}
	else {
		table.rows('.selected').nodes().each((row) => row.classList.remove('selected')); // radio기능 처럼 생각하면 되는 구현..
		classList.add('selected');
		delNo = e.currentTarget.children[0].innerText; // 댓글번호	
	}
});

document.querySelector('#button').addEventListener('click', function() {
	svc.removeReply(delNo // db삭제
		, function(result) {
			if (result.retCode == 'OK') {
				table.row('.selected').remove().draw(false); // 화면 삭제
			} else {
				alert('처리 오류');
			}
		}
		, function(err) {
			console.log(err);
		})

});

