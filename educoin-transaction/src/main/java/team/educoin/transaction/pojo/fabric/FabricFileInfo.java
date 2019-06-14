package team.educoin.transaction.pojo.fabric;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FabricFileInfo {

    @JsonProperty(value = "$class")
    private String className;
    private String serviceID;  // 产品标识符
    private String serviceName;  // 产品名称
    private Double readPrice;  // 产品阅读权价格
    private Double ownershipPrice;  // 产品所有权价格
    private String company;  // 当前注册产品拥有者的ID(自动获取当前用户的ID)

    public FabricFileInfo() {
    }

    public FabricFileInfo(String className, String serviceID, String serviceName, Double readPrice,
                          Double ownershipPrice, String company) {
        this.className = className;
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.readPrice = readPrice;
        this.ownershipPrice = ownershipPrice;
        this.company = company;
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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Double getReadPrice() {
        return readPrice;
    }

    public void setReadPrice(Double readPrice) {
        this.readPrice = readPrice;
    }

    public Double getOwnershipPrice() {
        return ownershipPrice;
    }

    public void setOwnershipPrice(Double ownershipPrice) {
        this.ownershipPrice = ownershipPrice;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
