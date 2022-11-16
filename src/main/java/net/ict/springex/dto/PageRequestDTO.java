package net.ict.springex.dto;

// 페이지처리는 현재페이지 번호(page), 한 페이지당 데이터수(size) 기본적으로 필요

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

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
        return (page -1) * 10; // 값을 만들어서 리턴
    }


}
