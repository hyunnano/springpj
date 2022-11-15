// MyBatis와 스프링을 이용한 영속처리 1단계. VO 선언

package net.ict.springex.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoVO {
    private Long tno;
    private  String title;
    private LocalDate dueDate;
    private  boolean finished;
    private String writer; // 작성자를 의미
}