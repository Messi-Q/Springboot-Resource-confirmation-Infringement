package team.educoin.transaction.pojo;

public class TrackRecordInfo {
    private Integer id;
    private String infringerId;
    private String infringedId;
    private String serviceId;
    private String infringeDescription;

    public TrackRecordInfo() {
    }

    public TrackRecordInfo(String infringerId, String infringedId, String serviceId,
                           String infringeDescription) {
        this.infringerId = infringerId;
        this.infringedId = infringedId;
        this.serviceId = serviceId;
        this.infringeDescription = infringeDescription;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfringerId() {
        return infringerId;
    }

    public void setInfringerId(String infringerId) {
        this.infringerId = infringerId;
    }

    public String getInfringedId() {
        return infringedId;
    }

    public void setInfringedId(String infringedId) {
        this.infringedId = infringedId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getInfringeDescription() {
        return infringeDescription;
    }

    public void setInfringeDescription(String infringeDescription) {
        this.infringeDescription = infringeDescription;
    }
}
