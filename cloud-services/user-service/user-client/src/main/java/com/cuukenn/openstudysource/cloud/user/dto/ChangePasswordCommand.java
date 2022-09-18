package com.cuukenn.openstudysource.cloud.user.dto;

import com.cuukenn.openstudysource.cloud.framework.dto.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

/**
 * @author changgg
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class ChangePasswordCommand extends Command {
    private static final long serialVersionUID = -3481222788415984564L;
    @NotEmpty
    @ToString.Exclude
    private String oldPassword;
    @NotEmpty
    @ToString.Exclude
    private String newPassword;
}
