package com.cuukenn.openstudysource.cloud.user.dto;

import com.cuukenn.openstudysource.cloud.framework.dto.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author changgg
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CheckPasswdCommand extends Command {
    private static final long serialVersionUID = 1621711368822115830L;
    @NotBlank
    private String username;
    @NotBlank
    @ToString.Exclude
    private String password;
}
