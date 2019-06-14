package team.educoin.transaction.pojo.fabric;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FabricUserConsumeInfo {
    @JsonProperty(value = "$class")
    private String className;
    private String serviceID;  // 产品标识符
    private String user;  // 当前购买资源阅读权人的ID(邮箱)

    public FabricUserConsumeInfo() {
    }

    public FabricUserConsumeInfo(String className, String serviceID, String user) {
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
