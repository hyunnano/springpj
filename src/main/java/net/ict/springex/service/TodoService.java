package net.ict.springex.service;

import net.ict.springex.dto.TodoDTO;

import java.util.List;

public interface TodoService {
    void register(TodoDTO todoDTO); // 등록 기능(스펙) 추가
    List<TodoDTO> getAll(); // 목록 기능 추가
    TodoDTO getOne(Long tno);
    void remove(Long tno);
    void modify(TodoDTO todoDTO);

}
