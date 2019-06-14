package team.educoin.transaction.fabric;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import team.educoin.transaction.pojo.fabric.FabricUserInfo;

import java.util.List;

@Component
@FeignClient(value = "fabricUserClient", url = "${educoin.fabric.url}")
@RequestMapping("/api")
public interface UserFabricClient {
    @RequestMapping(value = "/User", method = RequestMethod.GET)
    List<FabricUserInfo> getUser();
}
