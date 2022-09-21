package com.cuukenn.openstudysource.cloud.user.dto;

import com.cuukenn.openstudysource.cloud.framework.dto.Dto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author changgg
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class AuthUserDto extends Dto {
    private static final long serialVersionUID = 8835185770370121245L;
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    @ToString.Exclude
    private String password;
    private Boolean enabled;
    private Boolean locked;
    private Boolean accountExpired;
    private Boolean passwordExpired;
    private Boolean isAdmin;
}
