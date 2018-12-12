package edu.xd.ridelab.foundationplatform.definition;

/** 场所证件类型
 * @author 蔡依婷
 * @date 2018/5/15
 * @since 1.0
 */
public enum PlaceCertType {
    身份证("00"),
    户口本("01"),
    居住证("02"),
    签证("03"),
    护照("04"),
    军人证("05"),
    港澳通行证("06"),
    其他("99");

    public static PlaceCertType getType(String code) {
        for (PlaceCertType type : PlaceCertType.values()) {
            if (type.code().equals(code)) {
                return type;
            }
        }
        return null;
    }

    private String code;
    PlaceCertType(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }

}
