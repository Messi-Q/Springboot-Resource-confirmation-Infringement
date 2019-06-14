package team.educoin.transaction.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.educoin.transaction.dao.UserMapper;
import team.educoin.transaction.fabric.UserFabricClient;
import team.educoin.transaction.pojo.AgencyInfo;
import team.educoin.transaction.pojo.UserInfo;
import team.educoin.transaction.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserFabricClient userFabricClient;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> getUserInfo() {
        Map<String, Object> userInfoMap = new HashMap<>();
        userInfoMap.put("fabricUserInfo", userFabricClient.getUser());
        userInfoMap.put("mysqlUserInfo",userMapper.selectAllUser() );
        return userInfoMap;
    }

    // 根据用户email查询用户信息
    public List<UserInfo> queryUserById(String email) {
        return userMapper.queryUserById(email);
    }

    // 根据机构email查询机构信息
    public List<AgencyInfo> queryAgencyById(String email) {
        return userMapper.queryAgencyById(email);
    }

    // 更新用户余额
    public int updateUserAccountBalance(UserInfo userInfo) {
        return userMapper.updateUserAccountBalance(userInfo);
    }

    // 更新机构余额
    public int updateAgencyAccountBalance(AgencyInfo agencyInfo) {
        return userMapper.updateAgencyAccountBalance(agencyInfo);
    }

}
