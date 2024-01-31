package org.bs.rental.util.page;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageRequestDTO {

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;

    private String type;
    private String keyword;
 
    private String link;

    private boolean replyLast;

    // 페이지 설정
    public void setPage(int page){

        if(page < 0){
            this.page = 1;
        } else{
            this.page = page;
        }        
    }

    // 사이즈 설정
    public void setSize(int size){
        
        if(size < 0){
            this.size = 10;
        } else{
            this.size = size;
        }
    }

    // skip
    public int getSkip(){

        return (this.page - 1) * this.size;
    }

    // end
    public int getEnd(){

        return this.page * this.size;
    }

    // 끝 위치
    public int getCountEnd(){

        int result = (int) Math.ceil(this.page/10.0) * (this.size * 10);

        return result +1;
    }

    // type
    public String[] getTypes(){

        if(this.type == null || this.type.isEmpty()){
            return null;
        }

        // ""로 잘라서 배열 반환
        return this.type.split("");
    }

    // link
    public String getLink(){

        // String 객체 생성
        StringBuilder stringBuilder = new StringBuilder();

        // page, size
        stringBuilder.append("page=" + this.page);
        stringBuilder.append("size=" + this.size);

        // type
        if(type != null && type.length() >0 ){

            stringBuilder.append("type=" + this.type);
        }

        // keyword
        if(keyword != null){

            try {
                stringBuilder.append("keyword=" + URLEncoder.encode(keyword, "UTF-8"));

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            link = stringBuilder.toString();
        }

        return link;

    }





}
