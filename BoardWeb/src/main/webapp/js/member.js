/**
 * member.js
 */
// 삭제 함수
function deleteRow(id) {
	fetch("removeMember.do?mid=" + id) // 서버데이터삭제
		.then(function(result) {
			return result.json();
		})
		.then((result) => {
			console.log(result.retCode);
			if (result.retCode == "OK") {
				//document.querySelector(`tr[name="${id}"]`).remove(); // 화면삭제
				document.querySelector('#tr_' + id).remove(); // 화면삭제
			} else if (result.retCode == "NG") {
				alert('삭제오류 발생');
			} else {
				alert('알수 없는 코드입니다.');
			}
		})
} // end of deleteRow(id)

fetch("testData.do")
	.then(function(result) {
		console.log(result);
		return result.json(); // stream -> object
	})
	.then(function(result) {
		const memberAry = result;
		memberAry.forEach(function(member) {
			console.log(member);
			const target = document.querySelector('#list');
			//const html = `<tr name="${member.memberId}">
			const html = `<tr id=tr_${member.memberId}>
			                <td>${member.memberId}</td>
							<td>${member.passwd}</td>
							<td>${member.memberName}</td>
							<td>${member.responsibility}</td>
							<td><button onclick="deleteRow('${member.memberId}')" class="btn btn-danger">삭제</button></td>
						 </tr>`;
			target.insertAdjacentHTML('beforeend', html);
		});
	})

// 추가 버튼 이벤트
document.querySelector('#addMember').addEventListener('click', function(e) {
	//alert('클릭됨');
	let id = document.querySelector('input[name="mid"]').value;
	let pw = document.querySelector('input[name="mpw"]').value;
	let name = document.querySelector('input[name="mname"]').value;
	
	if (!id || !pw || !name){
		alert('필수입력값을 입력하세요');
		return;
	}
	
	if (!confirm("추가하겠습니까?")){
		return;
	}
	
	// 서블릿 호출
	fetch("addMember.do?mid=" + id + "&mpw=" + pw + "&mname=" + name)
		.then(function(result) {
			return result.json();
		})
		.then(function(result){
			if(result.retCond == 'OK'){
				const target = document.querySelector('#list');
				const html = `<tr id=tr_${member.memberId}>
							    <td>${member.memberId}</td>
								<td>${member.passwd}</td>
								<td>${member.memberName}</td>
								<td>${member.responsibility}</td>
								<td><button onclick="deleteRow('${member.memberId}')" class="btn btn-danger">삭제</button></td>
							</tr>`;
			target.insertAdjacentHTML('beforeend', html);
			} else {
				alert('error');
			}
		})
})

