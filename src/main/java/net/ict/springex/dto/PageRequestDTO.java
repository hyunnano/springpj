package net.ict.springex.dto;

// 페이지처리는 현재페이지 번호(page), 한 페이지당 데이터수(size) 기본적으로 필요

import com.sun.org.apache.bcel.internal.generic.PUSH;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default // page나 size의 기본값, 상수처리
    @Min(value = 1) // 최소값 1
    @Positive
    private int page = 1; // 음수처리 안됨

    @Builder.Default // page나 size의 기본값, 상수처리
    @Min(value = 10) // 가져오는 최소 데이터 수 10개로 지정
    @Max(value = 100)
    @Positive
    private int size = 10;

    public int getSkip(){ // 스킵값
        return (page -1) * 10;} // 값을 만들어서 리턴

    private String[] types;
    private String keyword;
    private boolean finished;
    private LocalDate from;
    private LocalDate to;

    public boolean checkType(String type){
        if(types == null || types.length == 0){
            return false;
        }
        return Arrays.stream(types).anyMatch(type::equals);
    }

    public String getLink(){
        StringBuilder builder = new StringBuilder();
        builder.append("page="+this.page);
        builder.append("size="+this.size);

        if(finished){
            builder.append("&finished=on");
        }
        if(types != null && types.length > 0){
            for(int i = 0; i <types.length; i++){
                builder.append("&types="+types[i]);
            }
        }
        if(keyword != null){
            try{
                builder.append("&keyword="+ URLEncoder.encode(keyword,"UTF-8"));
            } catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
        }
        if(from != null){
            builder.append("&from="+from.toString());
        }
        if(to != null){
            builder.append("&to="+to.toString());
        }
        return builder.toString();
    }

}
