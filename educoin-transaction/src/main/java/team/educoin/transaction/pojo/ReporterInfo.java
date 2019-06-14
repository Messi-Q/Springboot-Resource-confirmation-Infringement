package team.educoin.transaction.pojo;

public class ReporterInfo {
    private Integer id;
    private String email;
    private String qq;
    private String identityCard;
    private String address;
    private String bankAccount;

    public ReporterInfo() {
    }

    public ReporterInfo(String email, String qq, String identityCard,
                        String address, String bankAccount) {
        this.email = email;
        this.qq = qq;
        this.identityCard = identityCard;
        this.address = address;
        this.bankAccount = bankAccount;
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

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
}
