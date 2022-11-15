package net.ict.springex.dto;

import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;


// 객체 자료형은 파라미터로 처리하기 위해서는 객체로 생성되고 setXXX()이용해서 처리
@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {
    private Long tno;

    @NotEmpty // not null ＆ not empty, 검증 애너테이션
    private String title;

    @Future // 현재보다 미래인 값이 들어왔는지 체크, 검증 애너테이션
    private LocalDate dueDate;

    private boolean finished;

    @NotEmpty // not null ＆ not empty, 검증 애너테이션
    private String writer; // 작성자를 의미
}
