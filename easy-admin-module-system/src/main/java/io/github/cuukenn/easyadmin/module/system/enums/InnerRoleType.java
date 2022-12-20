package io.github.cuukenn.easyadmin.module.system.enums;

import io.github.cuukenn.easyframework.core.exception.BizException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 内置角色
 *
 * @author changgg
 */
@Getter
@RequiredArgsConstructor
public enum InnerRoleType {
	/**
	 * 管理员
	 */
	ADMIN("ADMIN");
	private final String permission;

	/**
	 * 校验内置角色
	 *
	 * @param permission 权限标识
	 */
	public static void check(String permission) {
		for (InnerRoleType type : InnerRoleType.values()) {
			if (type.getPermission().equalsIgnoreCase(permission)) {
				throw new BizException(-603, "内置角色无法修改");
			}
		}
	}
}
