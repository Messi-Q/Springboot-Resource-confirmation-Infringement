package team.educoin.transaction.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import team.educoin.common.CommonResponse;
import team.educoin.transaction.pojo.FileInfo;
import team.educoin.transaction.service.FileService;
import team.educoin.transaction.service.impl.FileServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/service")
@Api(value = "/service", description = "文件相关接口")
public class FileController {

    @Autowired
    private FileServiceImpl fileServiceImpl;

    @Autowired
    private FileService fileService;

    /*
     * 跳转到上传页面
     */
    @RequestMapping(method = RequestMethod.GET)
    public String uploadHtml() {
        return "upload";
    }

    /*
     * 注册新资源
     */
    @Transactional
    @RequestMapping(value = "/registService", method = RequestMethod.POST)
    @ApiOperation(value = "上传资源", notes = "上传资源文件，提交资源基本信息")
    public String registService(@RequestParam("fileTitle") String fileTitle,
                                @RequestParam("fileImage") String fileImage,
                                @RequestParam("fileDescription") String fileDescription,
                                @RequestParam("fileReadPrice") Double fileReadPrice,
                                @RequestParam("fileOwnerShipPrice") Double fileOwnerShipPrice,
                                @RequestParam("fileKeyWord") String fileKeyWord,
                                @RequestParam("fileContentType") String fileContentType,
                                @RequestParam("fileInitialProvider") String fileInitialProvider,
                                @RequestParam MultipartFile file) throws IOException {

        if (file.getSize() == 0) {
            return "文件是空文件！";
        }
        if (StringUtils.isEmpty(fileTitle)) {
            return "标题不能为空";
        }
        if (StringUtils.isEmpty(fileImage)) {
            return "图片不能为空";
        }
        if (StringUtils.isEmpty(fileDescription)) {
            return "描述不能为空";
        }
        if (StringUtils.isEmpty(fileReadPrice)) {
            return "资源阅读价不能为空";
        }
        if (StringUtils.isEmpty(fileOwnerShipPrice)) {
            return "资源所有价不能为空";
        }
        if (StringUtils.isEmpty(fileKeyWord)) {
            return "资源关键字不能为空";
        }
        if (StringUtils.isEmpty(fileContentType)) {
            return "资源内容类型不能为空";
        }
        if (StringUtils.isEmpty(fileInitialProvider)) {
            return "资源初始提供者不能为空";
        }

        // 获取文件的MD5码
        String fileMD5 = DigestUtils.md5DigestAsHex(file.getBytes());
        System.err.println("文件md5码： " + fileMD5);
        // 获取文件的后缀名
        String fileName = file.getOriginalFilename();
        String type = fileName.substring(fileName.lastIndexOf("."));
        System.out.println("文件的后缀名为：" + type);
        // 获取文件ID，随机产生
        String fileId = UUID.randomUUID().toString();
        System.out.println("文件的ID为：" + fileId);
        // 设置文件上传路径
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        System.out.println("Url：" + ResourceUtils.getURL("classpath:"));
        if (!path.exists()) path = new File("");  // 检测是否存在路径
        System.out.println(path.getPath());
        File upload = new File(path.getAbsolutePath(), "upload/");
        if (!upload.exists()) upload.mkdirs();  // 检测是否存在目录
        System.out.println(upload.getPath());
        File dest = new File(upload.getPath() + "/" + fileName);
        if (!dest.getParentFile().exists()) dest.getParentFile().mkdirs();  // 检测是否存在目录
        System.out.println(dest.getPath());

        // 资源注册操作
        FileInfo fileInfo = new FileInfo(fileId, fileInitialProvider, fileInitialProvider, fileTitle, fileImage,
                fileDescription, fileReadPrice, fileOwnerShipPrice, file.getOriginalFilename(), fileKeyWord, fileContentType,
                file.getContentType(), getFormatSize(file.getSize()));
        int resMySql = 0;
        Object resFabric = null;
        try {
            resFabric = fileServiceImpl.registerService(fileInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (resFabric != null) {
            file.transferTo(dest);// 文件写入
            resMySql = fileServiceImpl.registService(fileInfo);
        } else {
            return "资源上链失败";
        }
        if (resMySql != 0) {
            return "success";
        } else {
            return "error";
        }
    }

    // 工具函数：文件大小形式化(例：convert 1024 to 1KB)
    private static String getFormatSize(long size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte(s)";
        }
        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }
        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }
        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

    /*
     * 批量上传文件
     */
    @RequestMapping(value = "/registBatchService", method = RequestMethod.POST)
    @ApiOperation(value = "批量上传资源", notes = "批量上传资源文件")
    public String handleFileUpload(HttpServletRequest request) throws IOException {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        File upload = new File(path.getAbsolutePath(), "upload/");
        if (!upload.exists()) upload.mkdirs();

        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(upload.getPath() + file.getOriginalFilename())));//设置文件路径及名字
                    stream.write(bytes);// 写入
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    return "第 " + i + " 个文件上传失败 ==> " + e.getMessage();
                }
            } else {
                return "第 " + i + " 个文件上传失败因为文件为空";
            }
        }
        return "上传成功";
    }


    /*
     * 根据文件id下载文件
     */
    @ResponseBody
    @RequestMapping(value = "/downloadService/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "下载资源", notes = "根据文件id下载文件")
    public String downloadService(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response)
            throws FileNotFoundException, UnsupportedEncodingException {
        List<FileInfo> fileInfo = fileServiceImpl.queryFileById(id);  // 根据文件id获取文件名
        String fileName = null;

        for (FileInfo item : fileInfo) {
            fileName = item.getFileName();
        }
        System.out.println(fileName);

        response.setContentType("application/force-download");  //设置强制下载不打开
        response.addHeader("Content-Disposition",
                "attachment;fileName=" + new String(fileName.getBytes("UTF-8"), "iso-8859-1"));// 设置文件名
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;

        File file = new File(new File(ResourceUtils.getURL("classpath:").getPath()).getAbsolutePath() + "/upload/"
                + fileName);
        System.out.println(ResourceUtils.getURL("classpath:").getPath());
        System.out.println(new File(ResourceUtils.getURL("classpath:").getPath()).getAbsolutePath() + "/upload/"
                + fileName);
        // 文件下载：将文件写到输出流中
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                os.flush();  // flush()表示强制将缓冲区中的数据发送出去,不必等到缓冲区满;
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "下载成功";
    }

    /*
     * 测试获取fabric上的资源
     */
    @ResponseBody
    @RequestMapping(value = "/testGetFabricService", method = RequestMethod.GET)
    public CommonResponse testQueryFabricFile() {
        CommonResponse res = new CommonResponse();
        res.setStatus(0);
        res.setMessage("success");
        res.setData(fileServiceImpl.getFileInfo());
        return res;
    }

    /*
     * 测试注册service到fabric
     */
    @ResponseBody
    @RequestMapping(value = "/testRegistFabricService", method = RequestMethod.POST)
    public CommonResponse testRegisterService(@RequestBody FileInfo fileInfo) {
        System.out.println(fileInfo);
        return (CommonResponse) fileService.registerService(fileInfo);
    }

    /*
     * 查看所有资源(从数据库中获取)
     */
    @RequestMapping(value = "/queryAllService", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有资源列表", notes = "获取所有资源列表")
    public String queryAllService(Model model) {
        CommonResponse res = new CommonResponse();
        List<FileInfo> list = fileServiceImpl.queryAllFile();
        res.setStatus(0);
        res.setMessage("success");
        res.setData(list);
        model.addAttribute("fileList", list);
        return "showAllFile";
    }

    /*
     * 查看所有未审核的资源(从数据库中获取)
     */
    @RequestMapping(value = "/queryAllUnCheckedService", method = RequestMethod.GET)
    @ApiOperation(value = "获取未审核资源列表", notes = "根据fileChecked来获取未审核的资源")
    public String queryAllUnCheckedService(Model model) {
        List<FileInfo> list = fileServiceImpl.queryAllUnCheckedFile();
        model.addAttribute("unCheckedFileList", list);
        System.out.println(list);
        return "showAllFile";
    }

    /*
     * 查看所有已审查的资源(从数据库中获取)
     */
    @RequestMapping(value = "/queryAllCheckedService", method = RequestMethod.GET)
    @ApiOperation(value = "获取已审核资源列表", notes = "根据fileChecked来获取已审核的资源")
    public String queryAllCheckedService(Model model) {
        List<FileInfo> list = fileServiceImpl.queryAllCheckedFile();
        model.addAttribute("checkedFileList", list);
        System.out.println(list);
        return "showAllFile";
    }

    /*
     * 审核资源信息(通过) fileChecked 0->1
     */
    @ResponseBody
    @Transactional
    @RequestMapping(value = "/checkService/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "审核资源(通过)", notes = "根据资源ID审核资源(通过)")
    public String checkServiceInfo(@ApiParam(value = "资源文件id") @PathVariable("id") String id) {
        System.out.println("id" + id);
        try {
            int res = fileServiceImpl.checkFileInfo(id);
            System.out.println("res " + res);
            if (res != 0) {
                return "审核通过！";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "审核通过！";
    }

    /*
     * 审核资源信息(拒绝) fileChecked 0->2
     */
    @ResponseBody
    @Transactional
    @RequestMapping(value = "/rejectService/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "审核资源(拒绝)", notes = "根据资源ID审核资源(拒绝)")
    public String rejectServiceInfo(@ApiParam(value = "资源文件id") @PathVariable("id") String id) {
        System.out.println("id" + id);
        try {
            int res = fileServiceImpl.rejectFileInfo(id);
            System.out.println("res " + res);
            if (res != 0) {
                return "资源审核不通过!";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "资源审核不通过!";
    }

    /*
     * 修改资源信息
     */
    @ResponseBody
    @Transactional
    @RequestMapping(value = "/updateService/{id}/{email}", method = RequestMethod.POST)
    @ApiOperation(value = "修改资源信息", notes = "根据资源ID修改资源信息")
    public String updateServiceInfo(@PathVariable("id") String id, @PathVariable("email") String email,
                                    @RequestParam("fileTitle") String fileTitle,
                                    @RequestParam("fileImage") String fileImage,
                                    @RequestParam("fileDescription") String fileDescription,
                                    @RequestParam("fileReadPrice") Double fileReadPrice,
                                    @RequestParam("fileOwnerShipPrice") Double fileOwnerShipPrice,
                                    @RequestParam("fileKeyWord") String fileKeyWord,
                                    @RequestParam("fileContentType") String fileContentType) {
        FileInfo fileInfo = new FileInfo(id, email, fileTitle, fileImage, fileDescription,
                fileReadPrice, fileOwnerShipPrice, fileKeyWord, fileContentType);
        System.out.println("fileInfo" + fileInfo);
        // 资源信息修改操作
        int resMySql = 0;
        Object resFabric = null;
        try {
            resFabric = fileServiceImpl.updateService(fileInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (resFabric != null) {
            resMySql = fileServiceImpl.updateFileInfo(fileInfo);
        } else {
            return "链上资源信息修改失败";
        }
        if (resMySql != 0) {
            return "资源信息修改成功";
        } else {
            return "资源信息修改失败";
        }

    }

    /*
     * 删除资源
     */
    @ResponseBody
    @Transactional
    @RequestMapping(value = "/deleteService/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除资源", notes = "根据资源ID删除资源")
    public String deleteService(@PathVariable("id") String id) {
        System.out.println("id " + id);
        try {
            int resMySql = fileServiceImpl.deleteFile(id);
            int resFabric = fileServiceImpl.deleteService(id);
            if (resFabric != 0 && resMySql != 0) {
                System.out.println(resMySql + resFabric);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "删除资源成功!";
    }

    /*
     * 修改资源阅读权价格
     */
    @ResponseBody
    @RequestMapping(value = "/updateServiceReadPrice/{id}", method = RequestMethod.POST)
    @ApiOperation(value = "修改资源阅读权价", notes = "根据资源ID修改资源阅读权价")
    public String updateServiceReadPrice(@PathVariable("id") String id,
                                         @RequestParam("fileReadPrice") Double fileReadPrice) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setId(id);
        fileInfo.setFileReadPrice(fileReadPrice);

        int res = fileServiceImpl.updateServiceReadPrice(fileInfo);

        if (res != 0) {
            return "修改资源阅读权价格成功";
        } else {
            return "修改资源阅读权价格成功";
        }
    }

    /*
     * 修改资源所有权价格
     */
    @ResponseBody
    @RequestMapping(value = "/updateServiceOwnerShipPrice/{id}", method = RequestMethod.POST)
    @ApiOperation(value = "修改资源所有权价", notes = "根据资源ID修改资源所有权价")
    public String updateServiceOwnerShipPrice(@PathVariable("id") String id,
                                              @RequestParam("fileOwnerShipPrice") Double fileOwnerShipPrice) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setId(id);
        fileInfo.setFileOwnerShipPrice(fileOwnerShipPrice);

        int res = fileServiceImpl.updateServiceOwnerShipPrice(fileInfo);

        if (res != 0) {
            return "修改资源所有权价格成功";
        } else {
            return "修改资源所有权价格成功";
        }
    }

}
