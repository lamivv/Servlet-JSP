/**
 * js/reply.js
 */
console.log(svc.showName());
let page = 1; // 페이징
let onePageLength = 0;

// 댓글
function makeReply(reply = {}) { // 객체라는 의미
	let html = `<li data-id="${reply.replyNo}">
					<span class="col-sm-2">${reply.replyNo}</span>
					<span class="col-sm-5">${reply.reply}</span>
					<span class="col-sm-2">${reply.replyer}</span>
					<span class="col-sm-2"><button onclick="deleteRow('${reply.replyNo}')">삭제</button></span>
				</li>`;
	return html;
}

// 글 삭제 함수
function deleteRow(rno) {
	if (!confirm("삭제하시겠습니까?")) {
		alert('취소합니다');
		return;
	}

	svc.removeReply(rno // 댓글번호
		, // 성공함수
		function(result) {
			if (result.retCode == 'OK') {
				document.querySelector('li[data-id="' + rno + '"]').remove();
				if (onePageLength == 1 && page != 1) {
					page = page - 1;
				}
				showPageList();
				showPagingList();
			}
		}
		, // 실패함수
		function(err) { console.log(err); })
}

// 댓글목록 출력함수
function showPageList() {
	svc.replyList({ bno, page } //원본글번호
		, // 성공함수
		function(result) {
			// 기존 목록 지우기
			document.querySelectorAll('li[data-id]').forEach(function(elem) {
				elem.remove();
			});
			let resultAry = result;
			onePageLength = resultAry.length;
			resultAry.forEach(function(reply) {
				let target = document.querySelector('.reply>.content>ul');
				target.insertAdjacentHTML('beforeend', makeReply(reply));
			});
		}
		, // 실패함수
		function(err) {
			console.log(err);
		}
	);
}

// 목록
showPageList();

// 페이징 생성
function showPagingList() {
	svc.makePaging(bno
		, // 성공함수
		function(result) {
			console.log(result); // {totalCnt : 158}
			const totalCnt = result.totalCnt;
			// startPage, endPage, currPage
			// prev, next 계산
			let currPage = page;
			let endPage = Math.ceil(currPage / 10) * 10;
			let startPage = endPage - 9;
			let realEnd = Math.ceil(totalCnt / 5);
			endPage = endPage > realEnd ? realEnd : endPage;
			let prev = startPage != 1 ? true : false;
			let next = endPage != realEnd ? true : false;

			// a태그 생성 (새로운 페이징 생성)
			let target = document.querySelector('div.footer>nav>ul');
			target.innerHTML = ''; // 기존에 생성되었던 페이징정보 삭제
			let html = '';
			// 이전페이지 존재여부 previous버튼
			if (prev) {
				html = `<li class="page-item"><a class="page-link" href="#" data-page="${startPage - 10}">Previous</a></li>`;
			} else {
				html = `<li class="page-item disabled"><a class="page-link">Previous</a></li>`;
			}
			target.insertAdjacentHTML('beforeend', html);
			// 실제 페이지 구성
			for (let p = startPage; p <= endPage; p++) {
				if (p == currPage) {
					html = `<li class="page-item active"> <span class="page-link">${p}</span></li>`;
				} else {
					html = `<li class="page-item"><a class="page-link" href="#" data-page="${p}">${p}</a></li>`;
				}
				target.insertAdjacentHTML('beforeend', html);
			}
			// 이후페이지 존재여부 next버튼
			if (next) {
				html = `<li class="page-item"><a class="page-link" href="#" data-page="${endPage + 1}">Next</a></li>`;
			} else {
				html = `<li class="page-item disabled"><a class="page-link">Next</a></li>`;
			}
			target.insertAdjacentHTML('beforeend', html);

			// event
			addLinkEvent(); // 화면의 a태그에 이벤트 등록
		}
		, // 실패함수
		function(err) { console.log(err); })
} // end of showPagingList();
showPagingList();


// 댓글등록 이벤트 id="addReply""
document.querySelector('#addReply').addEventListener('click', function() {
	// 글번호 : bno, 작성자 : logid, 댓글내용: id="reply"
	const reply = document.querySelector('#reply').value;
	const replyer = logid;
	if (!reply || !replyer) {
		alert('필수입력값을 확인');
		return;
	}
	const parm = { bno, reply, replyer };
	svc.addReply(parm//
		, // 성공함수
		function(result) {
			if (result.retCode == 'OK') {
				const html = makeReply(result.retVal);
				console.log(html);
				let target = document.querySelector('.reply>.content>ul>li');
				target.insertAdjacentHTML('afterend', html);
				page = currPage = 1;
				showPageList();
				showPagingList();
			} else {
				alert('처리 예외 발생');
			}
		}
		, // 실패함수
		function(err) { console.log(err) });
});

// 페이징 목록의 링크() 이벤트 등록 [selectorAll -> a, a, a, a, a.... a]
function addLinkEvent() {
	document.querySelectorAll('div.footer>nav a').forEach(function(item) {
		item.addEventListener('click', function(e) {
			e.preventDefault(); // a태그 페이지이동 기능을 차단
			console.log(e.target.getAttribute('data-page'));
			page = e.target.getAttribute('data-page'); // 링크를 클릭하면 페이지정보를 바꿔줌
			//목록보기
			showPageList();
			//페이지 생성
			showPagingList();
		});
	});
}
addLinkEvent();