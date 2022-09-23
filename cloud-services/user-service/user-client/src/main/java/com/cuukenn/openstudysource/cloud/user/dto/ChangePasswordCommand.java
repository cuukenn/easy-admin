package com.cuukenn.openstudysource.cloud.user.dto;

import com.cuukenn.openstudysource.cloud.framework.dto.Command;

import javax.validation.constraints.NotEmpty;

/**
 * @author changgg
 */
public class ChangePasswordCommand extends Command {
    private static final long serialVersionUID = -3481222788415984564L;
    @NotEmpty
    private String oldPassword;
    @NotEmpty
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
