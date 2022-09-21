package com.cuukenn.openstudysource.cloud.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cuukenn.openstudysource.cloud.framework.dto.IResult;
import com.cuukenn.openstudysource.cloud.framework.entity.AbstractMybatisEntity;
import com.cuukenn.openstudysource.cloud.framework.exception.BizException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

/**
 * 用户数据表
 *
 * @author changgg
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@TableName("sys_user")
@Slf4j
public class User extends AbstractMybatisEntity {
    private static final long serialVersionUID = -1646039228536691577L;
    /* 用户核心数据 */
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    @ToString.Exclude
    private String password;
    /* 用户状态数据 */
    /**
     * 是否被锁定
     */
    private Boolean locked;
    /**
     * 是否启用
     */
    private Boolean enabled;
    /**
     * 账户过期时间
     */
    private Date accountInvalidTime;
    /**
     * 密码过期时间
     */
    private Date passwordInvalidTime;
    /**
     * 账户是否为管理员账户
     */
    private Boolean isAdmin;

    /**
     * 检查账户状态
     *
     * @return 账户状态
     */
    public AccountStatus check() {
        //是否锁定
        if (Boolean.TRUE.equals(getLocked())) {
            return AccountStatus.LOCKED;
        }
        //是否启用
        if (Boolean.FALSE.equals(getEnabled())) {
            return AccountStatus.DISABLED;
        }
        //账户是否过期
        if (Boolean.TRUE.equals(isAccountInvalid())) {
            return AccountStatus.ACCOUNT_EXPIRE;
        }
        //账户密码是否过期
        if (Boolean.TRUE.equals(isPasswordInvalid())) {
            return AccountStatus.PASSWORD_EXPIRE;
        }
        return AccountStatus.OK;
    }

    /**
     * 检查账户状态
     */
    public void checkAndThrow() {
        AccountStatus status = check();
        if (status != AccountStatus.OK) {
            log.error("user check failed,msg:{},username:{}", status.getMessage(), getUsername());
            throw new BizException(status);
        }
    }

    /**
     * 检查密码是否正确
     *
     * @param rawPassword 密码
     */
    public void checkPassword(PasswordEncoder passwordEncoder, String rawPassword) {
        if (!passwordEncoder.matches(rawPassword, getPassword())) {
            log.error("user check failed,msg:{},username:{}", User.AccountStatus.U_OR_P_ERROR.getMessage(), getUsername());
            throw new BizException(User.AccountStatus.U_OR_P_ERROR);
        }
    }

    /**
     * 账户是否过期
     *
     * @return 账户是否过期
     */
    public boolean isAccountInvalid() {
        if (getAccountInvalidTime() == null) {
            return false;
        }
        return getAccountInvalidTime().before(new Date());
    }

    /**
     * 账户密码是否过期
     *
     * @return 账户密码是否过期
     */
    public boolean isPasswordInvalid() {
        if (getPasswordInvalidTime() == null) {
            return false;
        }
        return getPasswordInvalidTime().before(new Date());
    }

    @Getter
    @RequiredArgsConstructor
    @ToString
    public enum AccountStatus implements IResult {
        /**
         * 账户正常
         */
        OK(200, "OK"),
        /**
         * 用户名或密码错误
         */
        U_OR_P_ERROR(-299, "username or password is incorrect"),
        /**
         * 指定用户未找到
         */
        NOT_FOUND(-300, "account not found"),
        /**
         * 账户被锁
         */
        LOCKED(-301, "account locked"),
        /**
         * 账户未启用
         */
        DISABLED(-302, "account disabled"),
        /**
         * 账户过期
         */
        ACCOUNT_EXPIRE(-303, "account invalid"),
        /**
         * 账户密码过期
         */
        PASSWORD_EXPIRE(-304, "account password invalid"),
        ;
        private final Integer code;
        private final String message;
    }
}
