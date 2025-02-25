/**
 * js/replyService.js 
 */

const svc = {
	name: "hong",
	showName: function() {
		return this.name;
	},
	// 목록메소드
	replyList: function(bno, successCallback, errorCallback) {
		fetch('replyList.do?bno=' + bno) // jsp에서는 어트리부트를 가지고 서브릿컨트롤러 form .. 자바스크립트에서는 fetch
			.then((result) => result.json()) // json문자열을 자바스크립트 객체화 object
			.then(successCallback) // (정상처리시)성공했을 때 실행할 함수
			.catch(errorCallback) // 에러가 발생했을 때 실행항 함수
	},
	// 등록메소드
	addReply(param = { bno, reply, replyer }, successCallback, errorCallback) {
		fetch('addReply.do?bno=' + param.bno + '&reply=' + param.reply + '&replyer=' + param.replyer)
			.then(result => result.json()) // 화살표함수
			.then(successCallback) // (정상처리시)성공했을 때 실행할 함수
			.catch(errorCallback) // 에러가 발생했을 때 실행항 함수
	},
	// 삭제메소드
	removeReply(rno = 1, successCallback, errorCallback) {
		fetch('removeReply.do?rno=' + rno)
			.then(result => result.json()) // 화살표함수
			.then(successCallback) // (정상처리시)성공했을 때 실행할 함수
			.catch(errorCallback) // 에러가 발생했을 때 실행항 함수
	}
}