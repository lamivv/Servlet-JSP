/**
 * js/api.js
 */
let centerAll = [];

// 이벤트 (select태그) 등록
document.getElementById('centerList').addEventListener('change', function(e) {
	console.log(e.target.value);
	let sidoName = e.target.value; // "서울특별시", "인천광역시"...
	let filterSido = [];
	filterSido = centerAll.filter(item => {
		if (item.sido == sidoName) {
			return true;
		}
		return false;
	})
	console.log(filterSido);
	makeCenterList(filterSido);
})

function makeCenterList(centerAry = []) {
	let fields = ['id', 'centerName', 'phoneNumber', 'sido'];
	// 기존 목록 삭제
	document.getElementById('list').innerHTML = '';
	// 센터정보
	centerAry.forEach(center => {
		// tr>tr*4
		let tr = document.createElement('tr');
		tr.addEventListener('click', function() {
			console.log(center.lat, center.lng);
			window.open('map.do?lat='+center.lat+'&lng='+center.lng+'&name='+(center.centerName).slice(6, -7)+' 센터');
		});
		for (let i = 0; i < fields.length; i++) {
			let td = document.createElement('td');
			td.innerHTML = center[fields[i]];
			tr.appendChild(td);
		}
		document.getElementById('list').appendChild(tr);
	});
}
// tr에 이벤트 등록 

// Ajax
fetch('https://api.odcloud.kr/api/15077586/v1/centers?page=1&perPage=284&serviceKey=I2llHwdGlgRaHth%2BP471Fbdr0E1ivgeEXKITrVeRc561zvXwMHm0m%2FXIy8erIeNX33tAxKUsV8D6nSUlWh5NNg%3D%3D')
	.then(result => result.json())
	.then(result => {
		console.log(result.data);
		centerAll = result.data;
		makeSidoList();
	})
	.catch(err => console.log(err));


// 시도 정보 중복 제거 후 화면 출력
function makeSidoList() {
	let sidoList = []; // ['서울특별시','인천광역시','대전광역시','광주광역시'...]
	for (let i = 0; i < centerAll.length; i++) {
		if (sidoList.indexOf(centerAll[i].sido) == -1) {
			sidoList.push(centerAll[i].sido);
		}
	}
	// console.log(sidoList.sort);
	sidoList.forEach(sido => {
		let opt = document.createElement('option');
		opt.innerHTML = sido; // <option>서울특별시</option>
		document.getElementById('centerList').appendChild(opt)
	})
}
