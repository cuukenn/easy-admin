package com.cuukenn.openstudysource.cloud.user.dto;

import com.cuukenn.openstudysource.cloud.common.dto.Dto;
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
public class UserDto extends Dto {
    private static final long serialVersionUID = 8835185770370121245L;
    private Long id;
    @NotBlank
    private String username;
}
