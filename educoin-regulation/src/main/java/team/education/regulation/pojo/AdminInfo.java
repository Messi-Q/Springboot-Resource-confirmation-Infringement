package team.education.regulation.pojo;

public class AdminInfo {
    private String email;
    private String password;
    private String fringerprint;
    private String iris;
    private Double accountBalance;
    private String bankAccount;

    public AdminInfo() {
    }

    public AdminInfo(String email, String password, String fringerprint, String iris,
                     Double accountBalance, String bankAccount) {
        this.email = email;
        this.password = password;
        this.fringerprint = fringerprint;
        this.iris = iris;
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
