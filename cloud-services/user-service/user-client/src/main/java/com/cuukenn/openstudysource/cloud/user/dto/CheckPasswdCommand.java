package com.cuukenn.openstudysource.cloud.user.dto;

import com.cuukenn.openstudysource.cloud.framework.dto.Command;

import javax.validation.constraints.NotBlank;

/**
 * @author changgg
 */
public class CheckPasswdCommand extends Command {
    private static final long serialVersionUID = 1621711368822115830L;
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
