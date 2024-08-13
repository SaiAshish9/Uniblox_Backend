package com.sai.Uniblox.api.user;

import com.sai.Uniblox.dto.UserRequestDTO;
import com.sai.Uniblox.entity.User;
import com.sai.Uniblox.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "Retrieve a user")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "body",
                    value = "Sample: \n\n" +
                            "userId: \n  b75d9787-ab35-45a3-8d1c-e865d9d244ea\n\n",
                    dataType = "User"
            )
    })
    public User getUser(UserRequestDTO userRequestDTO) {
        if (userRequestDTO.getUserID() == null) {
            throw new RuntimeException("Invalid UserID At UserApiController.getUser(UserRequestDTO userRequestDTO)");
        }
        return userService.getUser(userRequestDTO.getUserID());
    }

}
