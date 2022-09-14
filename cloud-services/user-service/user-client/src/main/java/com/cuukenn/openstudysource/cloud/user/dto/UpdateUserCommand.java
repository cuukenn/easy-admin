package com.cuukenn.openstudysource.cloud.user.dto;

import com.cuukenn.openstudysource.cloud.common.dto.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author changgg
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class UpdateUserCommand extends Command {
    private static final long serialVersionUID = -2596953746606382001L;
    private String username;
}
