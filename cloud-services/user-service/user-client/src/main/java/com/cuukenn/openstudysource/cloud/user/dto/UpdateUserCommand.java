package com.cuukenn.openstudysource.cloud.user.dto;

import com.cuukenn.openstudysource.cloud.framework.dto.Command;

/**
 * @author changgg
 */
public class UpdateUserCommand extends Command {
    private static final long serialVersionUID = -2596953746606382001L;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
