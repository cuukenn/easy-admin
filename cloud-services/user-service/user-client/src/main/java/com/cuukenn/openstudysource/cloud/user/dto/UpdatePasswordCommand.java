package com.cuukenn.openstudysource.cloud.user.dto;

import com.cuukenn.openstudysource.cloud.framework.dto.Command;

import javax.validation.constraints.NotEmpty;

/**
 * @author changgg
 */
public class UpdatePasswordCommand extends Command {
    private static final long serialVersionUID = -3481222788415984564L;
    @NotEmpty
    private String username;
    @NotEmpty
    private String newPassword;

    public UpdatePasswordCommand() {
    }

    public UpdatePasswordCommand(String username, String newPassword) {
        this.username = username;
        this.newPassword = newPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
