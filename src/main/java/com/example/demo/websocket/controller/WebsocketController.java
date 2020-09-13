package com.example.demo.websocket.controller;

import com.example.demo.websocket.domain.SubscribeDto;
import com.example.demo.websocket.domain.SubscribeType;
import com.example.demo.websocket.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class WebsocketController {

    private final SimpMessagingTemplate template;

    //1. 로그인을 하면 커넥트 + 구독
    //2. 구독한 정보로만 뭐 보내기..???

    @GetMapping("/login")
    public ModelAndView login(String id) throws Exception {

        List<User> users = getUsers();
        ModelAndView mv = new ModelAndView();
        if (users.stream().anyMatch(t -> t.getId().equals(id))) {

            User user = users.stream().filter(t -> t.getId().equals(id)).findAny().orElse(null);

            List<SubscribeDto> dtos = user.getType().stream()
                    .map(t -> new SubscribeDto(t.getValue(), id))
                    .collect(Collectors.toList());

            mv.addObject("subscribes", dtos);
            mv.setViewName("main");

            return mv;
        } else {
            throw new Exception("없는 회원입니다. ");
        }
    }

    private List<User> getUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(new User(String.valueOf(i), "User" + i));
        }
        return users;
    }


    @GetMapping("/call/admin")
    @ResponseBody
    public void join() {
        List<User> users = getUsers();
        for (User user : users) {
            if (user.getType().stream().anyMatch(t -> t.equals(SubscribeType.ADMIN))) {
                SubscribeDto dto = new SubscribeDto(SubscribeType.ADMIN.getValue(), user.getId());
                template.convertAndSend(dto.getValue(), "admin입니다");
            }

        }
    }

    @GetMapping("/test")
    public ModelAndView test() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("callAdmin");
        return mv;
    }

    @MessageMapping("/message")
    public void getMessage(String res) {
        System.out.println(res);
        template.convertAndSend("/topic/1", "안뇽안요요뇨요뇨");
    }

}
