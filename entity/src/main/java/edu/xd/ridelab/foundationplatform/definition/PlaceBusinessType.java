package edu.xd.ridelab.foundationplatform.definition;

/**
 * 场所经营类型
 * @author 蔡依婷
 * @date 2018/5/15
 * @since 1.0
 */
public enum PlaceBusinessType {
	经营("0"), 非经营("1"), 其他("3");
	
	public static PlaceBusinessType getType(String code) {
		for (PlaceBusinessType type : PlaceBusinessType.values()) {
			if (type.code().equals(code)) {
				return type;
			}
		}
		return null;
	}
	
	private String code;
	PlaceBusinessType(String code) {
		this.code = code;
	}
	
	public String code() {
		return code;
	}
	
}
