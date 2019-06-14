package team.educoin.transaction.service;

import team.educoin.transaction.pojo.AgencyInfo;
import team.educoin.transaction.pojo.UserInfo;

import java.util.List;
import java.util.Map;

public interface UserService {
    // 测试获取所有的信息
    Map<String, Object> getUserInfo();
    // 根据用户email获取用户信息
    List<UserInfo> queryUserById(String email);
    // 根据机构email获取机构信息
    List<AgencyInfo> queryAgencyById(String email);
    // 更新用户余额
    int updateUserAccountBalance(UserInfo userInfo);
    // 更新机构余额
    int updateAgencyAccountBalance(AgencyInfo agencyInfo);
}
