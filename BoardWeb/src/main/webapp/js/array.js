/**
 * js/array.js
 * 
 * 배열관련 메소드forEach, filter, map, reduce  -> 특: 기억안남
 */

let ary = [
	{ id: 100, name: "홍길동", score: 345 },
	{ id: 101, name: "김말숙", score: 456 },
	{ id: 102, name: "최선기", score: 232 }
]

/*reduce 누적된 값을 담는 acc, 함수 뒤의 문자 = acc 누적값의 초기값, acc= 이전 순번(idx)이 현재 acc의 초기값이 된다 x*/
/*ary.reduce((acc, item, idx, array) => {
	console.log(acc, item, idx, array);
	return item;
}, {});*/

let result = ary.reduce((acc, item, idx, array) => {
	console.log(acc, item, idx, array);
	return acc + item.score; // acc : accumulator 누적하다
}, 0);
console.log('최종Sum: ' + result);

result = ary.reduce((acc, item) => {
	return acc > item.score ? acc : item.score; // Max값 반환
}, 0);
console.log('최종Max: ' + result);

result = ary.reduce((acc, item) => {
	return acc < item.score ? acc : item.score; // Max값 반환
}, 1000);
console.log('최종Min: ' + result);

/*reduce를 활용해서 filter기능 구현 score 300점 이상*/
result = ary.reduce((acc, item) => {
	if (item.score > 300) {
		acc.push(item); // [{}] 
	}
	return acc;
}, []);
console.log('최종결과: ' + result);

result = ary.reduce((acc, item) => {
	let li = document.createElement('li');
	li.innerHTML = 'id: '+ item.id + ', name: ' +item.name;
	acc.appendChild(li);
	return acc; // <ul></ul>
}, document.getElementById('list')); 


/*ary.forEach(function(item, idx, array){
	console.log(item, idx, array);
})*/

/*ary.forEach((item, idx, array) => {
	console.log(item, idx, array);
})

조건을 만족하는 것(true)을 새로운 배열에 담아주는 filter
let filAry = ary.filter(item => {
	if (item.score >= 400) {
		return true;
	}
	return false;
})
console.log(filAry);

map(ping) 추가적인 정보(속성추가)를 담으면서 새로운 배열을 반환 
let mapAry = ary.map(item => {
	// A : 400 , B: 300, C:그외
	if (item.score > 400) {
		item.group = 'A';
	} else if (item.score > 300) {
		item.group = 'B';
	} else {
		item.group = 'C';
	}
	return item;
});
console.log(mapAry);*/