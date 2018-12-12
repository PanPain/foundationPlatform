package edu.xd.ridelab.foundationplatform.definition;

/**
 * 场所服务类型
 * @author 蔡依婷
 * @date 2018/5/15
 * @since 1.0
 */
public enum PlaceServiceType {
	旅店宾馆类("1"), 图书馆阅览室("2"), 电脑培训中心类("3"),
	娱乐场所类("4"), 交通枢纽("5"), 公共交通工具("6"), 餐饮服务场所("7"),
	金融服务场所("8"), 购物场所("A"), 公共服务场所("B"), 文化服务场所("C"),
	公共休闲场所("D"), 其他("9");
	
	public static PlaceServiceType getType(String code) {
		for (PlaceServiceType type : PlaceServiceType.values()) {
			if (type.code().equals(code)) {
				return type;
			}
		}
		return null;
	}
	
	private String code;
	PlaceServiceType(String code) {
		this.code = code;
	}
	
	public String code() {
		return code;
	}
}
