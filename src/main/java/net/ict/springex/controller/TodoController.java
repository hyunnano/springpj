package net.ict.springex.controller;

import lombok.extern.log4j.Log4j2;
import net.ict.springex.dto.TodoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/todo") // RequestMapping의 값은 /todo
@Log4j2
public class TodoController {

    @RequestMapping("/list") // 최종 경로는 /todo/list가 됨
    public void list(Model model){
        log.info("todo list..........");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void register(){
        log.info("todo register..........");
    }

    // /todo/register 를 POST방식으로 처리하는 메소드 TodoDTO를 파라미터로 적용
    @PostMapping("/register") // 요청 페이지 분기, Post 방식
    public String registerPost(TodoDTO todoDTO, RedirectAttributes redirectAttributes){ // RedirectAttributes 를 이용해서 TodoDTO 여기에 저장해줌
        log.info("POST todo register..........");
        log.info(todoDTO);
        return "redirect:/todo/list"; // 페이지 list.jsp 로 이동해라~
    }






}
