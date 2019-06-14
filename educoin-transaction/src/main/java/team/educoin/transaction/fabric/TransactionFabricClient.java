package team.educoin.transaction.fabric;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import team.educoin.transaction.pojo.fabric.FabricAgencyConsumeInfo;
import team.educoin.transaction.pojo.fabric.FabricUserConsumeInfo;

@Component
@FeignClient(value = "fabricTransactionClient", url = "${educoin.fabric.url}")
@RequestMapping("/api")
public interface TransactionFabricClient {
    // 普通用户购买阅读权
    @RequestMapping(value = "/CompanyBuyOnwership", method = RequestMethod.POST, consumes = "application/json")
    String userConsumeService(@RequestBody FabricUserConsumeInfo fabricUserConsumeInfo);
    // 机构用户购买所有权
    @RequestMapping(value = "/UserConsumeService", method = RequestMethod.POST, consumes = "application/json")
    String agencyConsumeService(@RequestBody FabricAgencyConsumeInfo fabricAgencyConsumeInfo);
    // 校验普通用户阅读权
    @RequestMapping(value = "/queries/GetUserConsumeServiceU", method = RequestMethod.GET)
    String checkUserReadRight(@RequestParam("user") String user);
    // 校验机构用户所有权
    @RequestMapping(value = "/queries/GetCompanyBuyOnwership", method = RequestMethod.GET)
    String checkAgencyOwnership(@RequestParam("serviceID") String serviceID);
}
