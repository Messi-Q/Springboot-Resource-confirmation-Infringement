package team.educoin.transaction.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.educoin.transaction.dao.TransactionMapper;
import team.educoin.transaction.fabric.FileFabricClient;
import team.educoin.transaction.fabric.TransactionFabricClient;
import team.educoin.transaction.pojo.FileInfo;
import team.educoin.transaction.pojo.fabric.FabricAgencyConsumeInfo;
import team.educoin.transaction.pojo.fabric.FabricUserConsumeInfo;
import team.educoin.transaction.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private TransactionFabricClient transactionFabricClient;

    // 普通用户购买资源阅读权
    public int userConsumeService(String email, FileInfo fileInfo){
        FabricUserConsumeInfo fabricUserConsumeInfo = new FabricUserConsumeInfo("org.education.UserConsumeService",
                fileInfo.getId(), email);
        int flag = 0;
        JSONObject jsonObject = null;

        try {
            jsonObject = JSONObject.parseObject((transactionFabricClient.userConsumeService(fabricUserConsumeInfo)));
            flag = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    // 机构用户购买资源所有权
    @Transactional
    public int agencyConsumeService(String email, FileInfo fileInfo){
        FabricAgencyConsumeInfo fabricAgencyConsumeInfo = new FabricAgencyConsumeInfo("org.education.CompanyBuyOnwership",
                fileInfo.getId(), email);
        JSONObject jsonObject = null;
        int res = 0;

        try {
            jsonObject = JSONObject.parseObject((transactionFabricClient.agencyConsumeService(fabricAgencyConsumeInfo)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (jsonObject != null) {
            res = transactionMapper.updateAgencyConsumer(fileInfo);
        }

        return res;
    }
}
