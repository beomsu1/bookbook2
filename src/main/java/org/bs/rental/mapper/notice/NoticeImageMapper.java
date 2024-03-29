package org.bs.rental.mapper.notice;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeImageMapper {

    // 이미지 추가
    int insertImage(List<Map<String, String>> images);

    // 이미지 삭제
    int deleteImage(Long book_number);

}
