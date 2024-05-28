

// ============ 전역 변수 ===========
const BASE_URL = 'http://localhost:8383/api/v1/replies';
const bno = document.getElementById('wrap').dataset.bno; // 게시물 글번호
console.log('글번호: ', bno);


// ============ 함수 정의 ===========
async function fetchReplies() {
    const res = await fetch(`${BASE_URL}/${bno}`);
    const replies = await res.json();
    console.log(replies.length);
    document.getElementById('replyCnt').textContent = replies.length;
}

// ============ 실행 코드 ============

// 댓글 목록 서버에서 불러오기
fetchReplies();