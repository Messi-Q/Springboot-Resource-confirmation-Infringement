package team.educoin.transaction.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.educoin.common.CommonResponse;
import team.educoin.common.StatusCode;
import team.educoin.transaction.dao.FileMapper;
import team.educoin.transaction.fabric.FileFabricClient;
import team.educoin.transaction.pojo.fabric.FabricFileInfo;
import team.educoin.transaction.pojo.FileInfo;
import team.educoin.transaction.pojo.fabric.FabricOwnerShipPriceInfo;
import team.educoin.transaction.pojo.fabric.FabricReadPriceInfo;
import team.educoin.transaction.service.FileService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private FileFabricClient fileFabricClient;

    //测试获取fabric service
    @Override
    public Map<String, Object> getFileInfo() {
        Map<String, Object> fileInfoMap = new HashMap<>();
        fileInfoMap.put("fabricFileInfo", fileFabricClient.getFile());
        return fileInfoMap;
    }

    //注册service到fabric
    @Override
    @Transactional
    public Object registerService(FileInfo fileInfo) {
        FabricFileInfo fabricFileInfo = new FabricFileInfo("org.education.RegisterService",
                fileInfo.getId(), fileInfo.getFileTitle(), fileInfo.getFileReadPrice(),
                fileInfo.getFileOwnerShipPrice(), fileInfo.getFileOwner());
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.parseObject((fileFabricClient.registerService(fabricFileInfo)));
        } catch (Exception e) {
            return new CommonResponse(StatusCode.FABRICERROR, e.toString());
        }

        return new CommonResponse(StatusCode.SUCCESS, "注册资源成功", fileInfo);
    }

    //删除fabric中的service
    public int deleteService(String id) {
        return fileFabricClient.deleteService(id);
    }

    //修改fabric中的service
    @Transactional
    public Object updateService(FileInfo fileInfo) {
        FabricFileInfo fabricFileInfo = new FabricFileInfo("org.education.Service",
                fileInfo.getId(), fileInfo.getFileTitle(), fileInfo.getFileReadPrice(),
                fileInfo.getFileOwnerShipPrice(), fileInfo.getFileOwner());
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.parseObject((fileFabricClient.updateService(fileInfo.getId(), fabricFileInfo)));
        } catch (Exception e) {
            return new CommonResponse(StatusCode.FABRICERROR, e.toString());
        }
        return new CommonResponse(StatusCode.SUCCESS, "修改资源成功", fileInfo);
    }

    //注册service到mysql
    @Override
    public int registService(FileInfo fileInfo) {
        return fileMapper.registService(fileInfo);
    }

    //根据id获取某个文件信息
    @Override
    public List<FileInfo> queryFileById(String id) {
        return fileMapper.queryFileById(id);
    }

    //获取所有未被审查的资源
    @Override
    public List<FileInfo> queryAllUnCheckedFile() {
        return fileMapper.queryAllUnCheckedFile();
    }

    //获取所有已经审查的资源
    @Override
    public List<FileInfo> queryAllCheckedFile() {
        return fileMapper.queryAllCheckedFile();
    }

    //查询用户列表
    @Override
    public List<FileInfo> queryAllFile() {
        return fileMapper.queryAllFile();
    }

    //审核资源信息(通过)
    @Override
    public int checkFileInfo(String id) {
        return fileMapper.checkFileInfo(id);
    }

    //审核资源信息(拒绝)
    @Override
    public int rejectFileInfo(String id) {return fileMapper.rejectFileInfo(id);}

    //删除资源
    @Override
    public int deleteFile(String id) { return fileMapper.deleteFile(id); }

    //修改资源信息
    @Override
    public int updateFileInfo(FileInfo fileInfo){ return fileMapper.updateFileInfo(fileInfo); }

    //修改资源阅读权价
    @Override
    @Transactional
    public int updateServiceReadPrice(FileInfo fileInfo) {
        FabricReadPriceInfo fabricReadPriceInfo = new FabricReadPriceInfo("org.education.UpdateServicereadPrice",
                fileInfo.getId(), fileInfo.getFileReadPrice());
        int res = 0;
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.parseObject((fileFabricClient.updateServiceReadPrice(fabricReadPriceInfo)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (jsonObject != null) {
            res = fileMapper.updateFileReadPrice(fileInfo);
        }

        return res;
    }

    //修改资源所有权价
    @Override
    @Transactional
    public int updateServiceOwnerShipPrice(FileInfo fileInfo) {
        FabricOwnerShipPriceInfo fabricOwnerShipPriceInfo = new FabricOwnerShipPriceInfo("org.education.UpdateServiceownershipPrice",
                fileInfo.getId(), fileInfo.getFileOwnerShipPrice());
        int res = 0;
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.parseObject((fileFabricClient.updateServiceOwnershipPrice(fabricOwnerShipPriceInfo)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (jsonObject != null) {
            res = fileMapper.updateFileOwnerShipPrice(fileInfo);
        }

        return res;
    }
}
