/**
 * js/test.js 
 * JSON 포맷 (문자열 - 객체)
 */
console.log('경로정상');

// 문자열을 자바스크립트 계열로 쉽게 변경

let json = `{ "name": "홍길동", "age": 20 }`;
let obj = JSON.parse(json);
document.querySelector('input[name="name"]').value = obj.name;
document.querySelector('input[name="age"]').value = obj.age;

// 서버(서블릿) - 구현(jsp페이지)
// Asynchronous Javascript And Xml
// 동기방식 - > 코드가 나열된 순서대로 실행
// 비동기방식 - > ... 그렇지 않은거 ..
console.log('1');

fetch('testData.do')
	.then(function(result) {
		console.log(result); // body: stream
		return result.json(); // stream(json문자열)
	})
	.then(function(result) {
		document.querySelector('input[name="name"]').value = result.name;
		document.querySelector('input[name="age"]').value = result.age;
		console.log('2');
	})

console.log('3');