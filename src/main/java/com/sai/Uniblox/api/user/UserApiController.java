package com.sai.Uniblox.api.user;

import com.sai.Uniblox.dto.UserRequestDTO;
import com.sai.Uniblox.entity.User;
import com.sai.Uniblox.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/u")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @GetMapping("/user")
    public User getUser(UserRequestDTO userRequestDTO) {
        if (userRequestDTO.getUserID() == null) {
            throw new RuntimeException("Invalid UserID At UserApiController.getUser(UserRequestDTO userRequestDTO)");
        }
        return userService.getUser(userRequestDTO.getUserID());
    }

}
