package team.educoin.transaction.pojo;

public class AgencyInfo {
    private String email;
    private String password;
    private String fringerprint;
    private String iris;
    private String registrationNumber;
    private String address;
    private String yycode;
    private String time;
    private String bussinessTerm;
    private String qq;
    private String identityCard;
    private String legalRepresentative;
    private Integer age;
    private String sexual;
    private String educationLevel;
    private Double accountBalance;
    private String bankAccount;

    public AgencyInfo() {
    }

    public AgencyInfo(String email, String password, String fringerprint, String iris,
                      String registrationNumber, String address, String yycode, String time,
                      String bussinessTerm, String qq, String identityCard, String legalRepresentative,
                      Integer age, String sexual, String educationLevel, Double accountBalance,
                      String bankAccount) {
        this.email = email;
        this.password = password;
        this.fringerprint = fringerprint;
        this.iris = iris;
        this.registrationNumber = registrationNumber;
        this.address = address;
        this.yycode = yycode;
        this.time = time;
        this.bussinessTerm = bussinessTerm;
        this.qq = qq;
        this.identityCard = identityCard;
        this.legalRepresentative = legalRepresentative;
        this.age = age;
        this.sexual = sexual;
        this.educationLevel = educationLevel;
        this.accountBalance = accountBalance;
        this.bankAccount = bankAccount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFringerprint() {
        return fringerprint;
    }

    public void setFringerprint(String fringerprint) {
        this.fringerprint = fringerprint;
    }

    public String getIris() {
        return iris;
    }

    public void setIris(String iris) {
        this.iris = iris;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getYycode() {
        return yycode;
    }

    public void setYycode(String yycode) {
        this.yycode = yycode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBussinessTerm() {
        return bussinessTerm;
    }

    public void setBussinessTerm(String bussinessTerm) {
        this.bussinessTerm = bussinessTerm;
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

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSexual() {
        return sexual;
    }

    public void setSexual(String sexual) {
        this.sexual = sexual;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
}
