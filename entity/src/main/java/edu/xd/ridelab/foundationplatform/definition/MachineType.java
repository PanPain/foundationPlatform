package edu.xd.ridelab.foundationplatform.definition;

/**
 * 设备类型
 * @author 蔡依婷
 * @date 2018/5/15
 * @since 1.0
 */
public enum MachineType {
	固定采集设备("1"), 移动车载采集设备("2"),
	单兵采集设备("3"), 其他("9");
	
	public static MachineType getType(String code) {
		for (MachineType type : MachineType.values()) {
			if (type.code().equals(code)) {
				return type;
			}
		}
		return null;
	}
	
	private String code;
	MachineType(String code) {
		this.code = code;
	}
	
	public String code() {
		return code;
	}
	
}
