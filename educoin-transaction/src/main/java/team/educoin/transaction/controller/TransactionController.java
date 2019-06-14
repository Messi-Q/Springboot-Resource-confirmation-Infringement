package team.educoin.transaction.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import team.educoin.transaction.dao.TransactionMapper;
import team.educoin.transaction.pojo.*;
import team.educoin.transaction.service.impl.FileServiceImpl;
import team.educoin.transaction.service.impl.TransactionServiceImpl;
import team.educoin.transaction.service.impl.UserServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/transaction")
@Api(value = "/transaction", description = "交易相关接口")
public class TransactionController {

    @Autowired
    private FileServiceImpl fileServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private TransactionServiceImpl transactionServiceImpl;

    @Autowired
    private TransactionMapper transactionMapper;

    /*
     * 普通用户购买资源阅读权
     */
    @ResponseBody
    @Transactional
    @RequestMapping(value = "/userConsume/{id}/{email}", method = RequestMethod.POST)
    @ApiOperation(value = "普通用户购买资源阅读权", notes = "根据资源id，用户email，普通用户购买资源阅读权")
    public String userConsumeService(@PathVariable("id") String id, @PathVariable("email") String email) {
        List<UserInfo> user = userServiceImpl.queryUserById(email);
        Double accountBalance = 0.0;

        for (UserInfo item : user) {
            accountBalance = item.getAccountBalance();
        }

        List<FileInfo> file = fileServiceImpl.queryFileById(id);  // 根据文件id获取文件名
        String servicId = null;
        String fileTitle = null;
        Double fileReadPrice = null;
        String fileName = null;

        for (FileInfo item : file) {
            servicId = item.getId();
            fileTitle = item.getFileTitle();
            fileReadPrice = item.getFileReadPrice();
            fileName = item.getFileName();
        }

        try {
            if (accountBalance > fileReadPrice) {
                System.out.println("余额充足" + accountBalance);
                double accountNewBalance = accountBalance - fileReadPrice;  // 更新用户余额
                // 将更新后的余额插入到用户表中
                UserInfo userInfo = new UserInfo();
                userInfo.setEmail(email);
                userInfo.setAccountBalance(accountNewBalance);

                FileInfo fileInfo = new FileInfo();
                fileInfo.setId(id);
                fileInfo.setFileOwner(email);
                int res = transactionServiceImpl.userConsumeService(email, fileInfo);

                if (res != 0) {
                    UserConsumeInfo userConsumeInfo = new UserConsumeInfo(email, servicId, fileTitle,
                            fileReadPrice, fileName);

                    userServiceImpl.updateUserAccountBalance(userInfo);  // 更新用户余额
                    int result = transactionMapper.insertCommonConsume(userConsumeInfo);

                    if (result != 0) {
                        // 执行一些跳转操作等
                        System.out.println(result + "创建购买记录成功");
                    } else {
                        return "创建购买记录失败";
                    }
                } else {
                    return "购买失败";
                }
            } else {
                return "账户余额不足，请充值";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "购买成功";

    }

    /*
     * 机构用户购买资源所有权
     */
    @ResponseBody
    @Transactional
    @RequestMapping(value = "/agencyConsume/{id}/{email}", method = RequestMethod.POST)
    @ApiOperation(value = "机构用户购买资源所有权", notes = "根据资源id，用户email，机构用户购买资源所有权")
    public String agencyConsumeService(@PathVariable("id") String id, @PathVariable("email") String email) {
        List<AgencyInfo> agencyInfo = userServiceImpl.queryAgencyById(email);
        Double accountBalance = 0.0;

        for (AgencyInfo item : agencyInfo) {
            accountBalance = item.getAccountBalance();
        }

        List<FileInfo> file = fileServiceImpl.queryFileById(id);  // 根据文件id获取文件名
        String servicId = null;
        String fileTitle = null;
        Double fileOwnerShipPrice = null;
        String fileName = null;

        for (FileInfo item : file) {
            servicId = item.getId();
            fileTitle = item.getFileTitle();
            fileOwnerShipPrice = item.getFileOwnerShipPrice();
            fileName = item.getFileName();
        }

        try {
            if (accountBalance > fileOwnerShipPrice) {
                System.out.println("余额充足" + accountBalance);
                double accountNewBalance = accountBalance - fileOwnerShipPrice;  // 更新用户余额
                // 将更新后的余额插入到用户表中
                AgencyInfo agency = new AgencyInfo();
                agency.setEmail(email);
                agency.setAccountBalance(accountNewBalance);
                FileInfo fileInfo = new FileInfo();
                fileInfo.setId(id);
                fileInfo.setFileOwner(email);
                int res = transactionServiceImpl.agencyConsumeService(email, fileInfo);

                if (res != 0) {
                    AgencyConsumerInfo agencyConsumerInfo = new AgencyConsumerInfo(email, servicId, fileTitle,
                            fileOwnerShipPrice, fileName);

                    userServiceImpl.updateAgencyAccountBalance(agency);  // 更新机构用户余额

                    int result = transactionMapper.insertAgencyConsume(agencyConsumerInfo);

                    if (result != 0) {
                        return "创建购买记录成功";
                    }
                } else {
                    return "购买失败";
                }
            } else {
                return "账户余额不足，请充值";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "购买成功";
    }
}
