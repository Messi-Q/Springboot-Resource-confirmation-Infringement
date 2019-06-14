package team.educoin.common;

public class StatusCode {
    public static final int SUCCESS = 100000;//成功
    public static final int ERROR = 100001;//失败
    public static final int FABRICERROR = 100002;//失败
    public static final int NOTEXISTERROR = 100003;//不存在
    public static final int PASSWORDERROR = 100004;//密码
    public static final int PERMISSIONERROR = 100005;//无权限
    public static final int CAPACITYERRPR = 100006;//容量不足
    public static final int BALANCEINADEQUATE = 100007;//余额不足
    public static final int USER = 0;
    public static final int COMPANY = 1;
    public static final int ADMIN = 2;
}
