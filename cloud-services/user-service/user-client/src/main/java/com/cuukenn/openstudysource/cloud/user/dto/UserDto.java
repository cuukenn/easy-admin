package com.cuukenn.openstudysource.cloud.user.dto;

import com.cuukenn.openstudysource.cloud.framework.dto.Dto;

import javax.validation.constraints.NotBlank;

/**
 * @author changgg
 */
public class UserDto extends Dto {
    private static final long serialVersionUID = 8835185770370121245L;
    private Long id;
    @NotBlank
    private String username;

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
}
