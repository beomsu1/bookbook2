function makePage(page, size, total) {
    let pagingResult = "";

    const startNum = (Math.ceil(page / 9) * 9) - 8;

    startNum === 1 ? "" : pagingResult += `<a href="${startNum - 1}" class="btn btn-primary"><</a>`;

    // 페이징 번호 변수
    let temp = startNum;

    // 페이징 버튼 동적 생성 / while(true) 무한 반복문 사용
    while (true) {
        // 페이징 번호 * 9 이 total 보다 크면 break
        if (temp * size > total) {
            break;
        }

        // temp == page -> 활성화
        temp === page ? pagingResult += `<a href="${temp}" class="btn btn-primary active">${temp}</a>` : pagingResult += `<a href="${temp}" class="btn btn-primary">${temp}</a>`;

        temp++;
    }

    // total이 size * 9을 나눈 나머지가 0보다 크면 다음 페이지 노출
    total % (size * 9) > 0 ? pagingResult += `<a href="${temp}" class="btn btn-primary">></a>` : "";

    return pagingResult;
}
