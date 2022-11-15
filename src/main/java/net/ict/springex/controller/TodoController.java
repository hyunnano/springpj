package net.ict.springex.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.springex.dto.TodoDTO;
import net.ict.springex.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/todo") // RequestMapping의 값은 /todo
@Log4j2
@RequiredArgsConstructor // 생성자 주입을 위한 애너테이션 (필드주입안함)
public class TodoController {

    private final TodoService todoService;

    @RequestMapping("/list") // 최종 경로는 /todo/list가 됨
    public void list(Model model){
        log.info("todo list..........");

        // model에 todoService.getAll()해서 dtoList이름으로 목록데이터를 담아서 넘겨준다
        model.addAttribute("dtoList",todoService.getAll());
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void register(){
        log.info("todo register..........");
    }

    // /todo/register 를 POST방식으로 처리하는 메소드 TodoDTO를 파라미터로 적용
    @PostMapping("/register") // 요청 페이지 분기, Post 방식
    // @Valid 애너테이션을 사용해서 TodoDTO 가 검증 대상이라고 지정
    // BindingResult
    // RedirectAttributes 를 이용해서 TodoDTO 여기에 저장해줌
    public String registerPost(@Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        log.info("POST todo register..........");

        if(bindingResult.hasErrors()){
            log.error("has errors.....");
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors()); // addFlashAttribute는 한번 사용하고 날아가는 값
            return "redirect:/todo/register";
        }

        log.info(todoDTO);
        todoService.register(todoDTO);
        return "redirect:/todo/list"; // 페이지 list.jsp 로 이동해라~
    }

}
