package team.educoin.transaction.pojo.fabric;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FabricReadPriceInfo {
    @JsonProperty(value = "$class")
    private String className;
    private String serviceID;  // 产品标识符
    private Double readPrice;  // 更新阅读权价

    public FabricReadPriceInfo() {
    }

    public FabricReadPriceInfo(String className, String serviceID, Double readPrice) {
        this.className = className;
        this.serviceID = serviceID;
        this.readPrice = readPrice;
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

    public Double getReadPrice() {
        return readPrice;
    }

    public void setReadPrice(Double readPrice) {
        this.readPrice = readPrice;
    }
}
