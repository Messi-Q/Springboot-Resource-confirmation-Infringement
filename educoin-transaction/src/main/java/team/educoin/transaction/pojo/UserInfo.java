package team.educoin.transaction.pojo;

public class UserInfo {
    private String email;              // 邮箱
    private String password;           // 密码
    private String fringerprint;       // 自然人指纹信息
    private String iris;               // 自然人虹膜信息
    private String qq;                 // 自然人 QQ
    private String identityCard;       // 自然人身份证
    private String buyerType;          // 自然人用户类型
    private Integer age;               // 自然人年龄
    private String sexual;             // 自然人性别
    private String educationLevel;     // 自然人教育水平
    private String address;            // 自然人地址
    private Double accountBalance;     // 自然人课程币余额
    private String bankAccount;        // 绑定银行卡号


    public UserInfo() {
    }

    public UserInfo(String email, Double accountBalance) {
        this.email = email;
        this.accountBalance = accountBalance;
    }

    public UserInfo(String email, String password, String fringerprint, String iris,
                    String qq, String identityCard, String buyerType, Integer age, String sexual,
                    String educationLevel, String address, Double accountBalance, String bankAccount) {
        this.email = email;
        this.password = password;
        this.fringerprint = fringerprint;
        this.iris = iris;
        this.qq = qq;
        this.identityCard = identityCard;
        this.buyerType = buyerType;
        this.age = age;
        this.sexual = sexual;
        this.educationLevel = educationLevel;
        this.address = address;
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

    public String getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
