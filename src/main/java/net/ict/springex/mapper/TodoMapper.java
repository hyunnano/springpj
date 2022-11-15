// MyBatis와 스프링을 이용한 영속처리 2단계. Mapper 인터페이스 개발

package net.ict.springex.mapper;

import net.ict.springex.domain.TodoVO;

public interface TodoMapper {
    String getTime();
    void insert(TodoVO todoVO); // insert 기능(스펙) 추가, TodoVO 타입으로 todoVO 파라미터값을 받는다
}
