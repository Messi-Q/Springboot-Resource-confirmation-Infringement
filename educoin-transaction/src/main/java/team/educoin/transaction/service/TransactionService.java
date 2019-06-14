package team.educoin.transaction.service;

import team.educoin.transaction.pojo.FileInfo;

public interface TransactionService {

    // 普通用户购买资源阅读权
    int userConsumeService(String email, FileInfo fileInfo);  // 当前购买资源阅读权的普通用户email和资源信息

    // 机构用户购买资源所有权
    int agencyConsumeService(String email, FileInfo fileInfo);  // 当前购买资源所有权的机构用户email和资源信息
}
