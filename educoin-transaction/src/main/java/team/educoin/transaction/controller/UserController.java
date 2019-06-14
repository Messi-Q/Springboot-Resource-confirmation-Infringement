package team.educoin.transaction.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;
import team.educoin.common.CommonResponse;
import team.educoin.transaction.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/user")
@Api(value = "/user", description = "用户相关接口")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping(method = RequestMethod.GET)
    public CommonResponse testFabricRequest() {
        CommonResponse res = new CommonResponse();
        res.setStatus(0);
        res.setMessage("success");
        res.setData(userServiceImpl.getUserInfo());
        return res;
    }

}
