package com.cuukenn.openstudysource.cloud.user.dto;

import com.cuukenn.openstudysource.cloud.framework.dto.Dto;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author changgg
 */
public class UserDto extends Dto {
    private static final long serialVersionUID = 8835185770370121245L;
    private Long id;
    @NotBlank
    private String username;
    private Boolean admin;
    private Boolean enabled;
    private Boolean locked;
    private Date accountInvalidTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Date getAccountInvalidTime() {
        return accountInvalidTime;
    }

    public void setAccountInvalidTime(Date accountInvalidTime) {
        this.accountInvalidTime = accountInvalidTime;
    }
}
