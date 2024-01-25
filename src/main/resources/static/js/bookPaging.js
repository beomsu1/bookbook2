function makePage(page, size, total) {
    let pagingResult = "";
  
    // 전체 페이지 수 계산
    const totalPages = Math.ceil(total / size);
  
    // 이전 페이지 버튼 생성
    if (page > 1) {
      pagingResult += `<a href="${page - 1}" class="btn btn-primary"><</a>`;
    }
  
    // 페이징 번호 생성
    for (let i = 1; i <= totalPages; i++) {
      if (i === page) {
        pagingResult += `<a href="${i}" class="btn btn-primary active">${i}</a>`;
      } else {
        pagingResult += `<a href="${i}" class="btn btn-primary">${i}</a>`;
      }
    }
  
    // 다음 페이지 버튼 생성
    if (page < totalPages) {
      pagingResult += `<a href="${page + 1}" class="btn btn-primary">></a>`;
    }
  
    return pagingResult;
  }
  