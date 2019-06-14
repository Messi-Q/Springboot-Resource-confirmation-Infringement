package team.educoin.transaction.pojo.fabric;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FabricOwnerShipPriceInfo {
    @JsonProperty(value = "$class")
    private String className;
    private String serviceID;  // 产品标识符
    private Double ownershipPrice;  // 更新所有权价

    public FabricOwnerShipPriceInfo() {
    }

    public FabricOwnerShipPriceInfo(String className, String serviceID, Double ownershipPrice) {
        this.className = className;
        this.serviceID = serviceID;
        this.ownershipPrice = ownershipPrice;
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

    public Double getOwnershipPrice() {
        return ownershipPrice;
    }

    public void setOwnershipPrice(Double ownershipPrice) {
        this.ownershipPrice = ownershipPrice;
    }
}
