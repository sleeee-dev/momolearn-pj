
// 프로필 테이블 그리는 함수
// 분야, 포트폴리오 링크 주소, 자기소개 정보 가져옴


// 버튼 클릭 함수1 : 개설된 강좌 목록을 가져오는 함수
// lecture 이름, 썸네일 가져옴 카드 형태로 만들기?? foreach 돌려서...



// 버튼 클릭 함수1 : 화면 처리 되자마자 자동으로 실행되는 함수
// 이름, 프로필사진 정보 가져옴



// axios 비동기 통신
// get 방식
// 매핑주소
// then ~

window.onload = function () {
    const urlParams = new URLSearchParams(window.location.search);
    const teacherNo = urlParams.get('teacherNo');
    console.log(urlParams)
    console.log(teacherNo) // null ...ㅠㅠㅠㅠㅠㅠ
    axios({
        method: "GET",
        url: `/../../momolearn/teachers/t-list/${teacherNo}`
    })
        .then(function (response) {
            console.log(response)
            const teacher = JSON.parse(response.data)[0];

        })
        .catch(function (error) {
            console.log(error);
        });
}



// 버튼 클릭시 발생 함수
// 테이블은 닫는 함수
