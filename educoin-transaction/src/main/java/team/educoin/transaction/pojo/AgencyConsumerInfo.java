package team.educoin.transaction.pojo;

public class AgencyConsumerInfo {
    private Integer id; // 自增的表字段
    private String email;
    private String serviceId;
    private String fileTitle;
    private Double fileOwnerShipPrice;
    private String fileName;

    public AgencyConsumerInfo() {
    }

    public AgencyConsumerInfo(String email, String serviceId,
                              String fileTitle, Double fileOwnerShipPrice, String fileName) {
        this.email = email;
        this.serviceId = serviceId;
        this.fileTitle = fileTitle;
        this.fileOwnerShipPrice = fileOwnerShipPrice;
        this.fileName = fileName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }

    public Double getFileOwnerShipPrice() {
        return fileOwnerShipPrice;
    }

    public void setFileOwnerShipPrice(Double fileOwnerShipPrice) {
        this.fileOwnerShipPrice = fileOwnerShipPrice;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
