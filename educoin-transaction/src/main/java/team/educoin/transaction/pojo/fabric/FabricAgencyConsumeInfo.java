package team.educoin.transaction.pojo.fabric;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FabricAgencyConsumeInfo {

    @JsonProperty(value = "$class")
    private String className;
    private String serviceID;  // 产品标识符
    private String user;  // 当前购买资源所以权机构的ID(邮箱)

    public FabricAgencyConsumeInfo() {
    }

    public FabricAgencyConsumeInfo(String className, String serviceID, String user) {
        this.className = className;
        this.serviceID = serviceID;
        this.user = user;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
