package team.educoin.transaction.service;

import team.educoin.transaction.pojo.FileInfo;

import java.util.List;
import java.util.Map;

public interface FileService {

    //测试获取fabric service
    Map<String, Object> getFileInfo();

    //注册service到fabric
    Object registerService(FileInfo fileInfo);

    //删除fabric中的service
    int deleteService(String id);

    //修改fabric中的service
    Object updateService(FileInfo fileInfo);

    //注册新资源
    int registService(FileInfo fileInfo);

    //根据id获取某个文件信息
    List<FileInfo> queryFileById(String id);

    //获取所有未被审查的资源
    List<FileInfo> queryAllUnCheckedFile();

    //获取所有已经审查的资源
    List<FileInfo> queryAllCheckedFile();

    //查询所有资源列表
    List<FileInfo> queryAllFile();

    //审核资源信息(通过)
    int checkFileInfo(String id);

    //审核资源信息(拒绝)
    int rejectFileInfo(String id);

    //根据id删除某个资源
    int deleteFile(String id);

    //修改资源信息
    int updateFileInfo(FileInfo fileInfo);

    //修改资源阅读权价格
    int updateServiceReadPrice(FileInfo fileInfo);

    //修改资源所有权价格
    int updateServiceOwnerShipPrice(FileInfo fileInfo);
}
