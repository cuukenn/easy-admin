package io.github.cuukenn.easyframework.core.constants;

/**
 * @author changgg
 */
public final class AutoConfigurationOrderConstant {
	/**
	 * base order
	 */
	public static final int HIGHEST_ORDER = Integer.MAX_VALUE;
	/**
	 * redis starter
	 */
	public static final int REDIS = HIGHEST_ORDER;

	/**
	 * mybatis plus starter
	 */
	public static final int MYBATIS_PLUS = HIGHEST_ORDER;

	/**
	 * jpa starter
	 */
	public static final int JPA = HIGHEST_ORDER;

	/**
	 * excel starter
	 */
	public static final int EXCEL = HIGHEST_ORDER;

	/**
	 * captcha starter
	 */
	public static final int CAPTCHA = HIGHEST_ORDER;

	/**
	 * biz tenant starter
	 */
	public static final int BIZ_TENANT = HIGHEST_ORDER;

	/**
	 * biz social starter
	 */
	public static final int BIZ_SOCIAL = HIGHEST_ORDER;

	/**
	 * biz operation log starter
	 */
	public static final int BIZ_OPERATION_LOG = HIGHEST_ORDER;

	/**
	 * protection starter
	 */
	public static final int PROTECTION = HIGHEST_ORDER;

	/**
	 * security starter
	 */
	public static final int SECURITY = HIGHEST_ORDER;

	/**
	 * web starter
	 */
	public static final int WEB = HIGHEST_ORDER;
}
