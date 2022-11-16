package net.ict.springex.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.springex.dto.PageRequestDTO;
import net.ict.springex.dto.TodoDTO;
import net.ict.springex.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
    public void list(@Valid PageRequestDTO pageRequestDTO,BindingResult bindingResult, Model model){
        log.info("todo list..........");
        if(bindingResult.hasErrors()){
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        // model에 todoService.getAll()해서 dtoList이름으로 목록데이터를 담아서 넘겨준다
        model.addAttribute("responseDTO",todoService.getList(pageRequestDTO));
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void register(){
        log.info("todo register..........");
    }

    // /todo/register 를 POST방식으로 처리하는 메소드 TodoDTO를 파라미터로 적용
    @PostMapping("/register") // 요청 페이지 분기, Post 방식
    // @Valid 애너테이션을 사용해서 TodoDTO 가 검증 대상이라고 지정
    // BindingResult 검증 오류를 보관하는 객체, 실패하면 오류처리 성공하면 컨트롤러 호출
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

    @GetMapping({"/read","/modify"}) // 배열 방식으로 동시에 입력 가능
    public void read(Long tno, Model model){
        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);
        model.addAttribute("dto",todoDTO);
    }

    @PostMapping("/remove")
    public String remove(Long tno, RedirectAttributes redirectAttributes){
        log.info("todo remove.....");
        log.info("tno:"+tno);
        todoService.remove(tno);
        return "redirect:/todo/list";
    }

    @PostMapping("modify")
    public String modify(@Valid TodoDTO todoDTO, BindingResult bindingResult,RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            log.info("has errors......");
            redirectAttributes.addFlashAttribute("error",bindingResult.getAllErrors());
            redirectAttributes.addAttribute("tno", todoDTO.getTno());
            return "redirect:/todo/modify";
        }
        log.info(todoDTO);
        todoService.modify(todoDTO);
        return "redirect:/todo/list";
    }

}
