package team.educoin.transaction.fabric;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import team.educoin.transaction.pojo.fabric.*;

import java.util.List;

@Component
@FeignClient(value = "fabricFileClient", url = "${educoin.fabric.url}")
@RequestMapping("/api")
public interface FileFabricClient {
    // 获取fabric上的资源信息(测试，真正的获取直接从数据库获取)
    @RequestMapping(value = "/Service", method = RequestMethod.GET)
    List<FabricFileInfo> getFile();
    // 注册资源
    @RequestMapping(value = "/RegisterService", method = RequestMethod.POST, consumes = "application/json")
    String registerService(@RequestBody FabricFileInfo fabricFileInfo);
    // 删除资源
    @RequestMapping(value = "/Service/{id}", method = RequestMethod.DELETE)
    int deleteService(@PathVariable("id") String id);
    // 修改资源信息
    @RequestMapping(value = "/Service/{id}", method = RequestMethod.PUT)
    String updateService(@PathVariable("id") String id, @RequestBody FabricFileInfo fabricFileInfo);
    // 更新资源所有权价格
    @RequestMapping(value = "/UpdateServiceownershipPrice", method = RequestMethod.POST, consumes = "application/json")
    String updateServiceOwnershipPrice(@RequestBody FabricOwnerShipPriceInfo fabricOwnershipPriceInfo);
    // 更新资源阅读权价格
    @RequestMapping(value = "/UpdateServicereadPrice", method = RequestMethod.POST, consumes = "application/json")
    String updateServiceReadPrice(@RequestBody FabricReadPriceInfo fabricReadPriceInfo);
}
