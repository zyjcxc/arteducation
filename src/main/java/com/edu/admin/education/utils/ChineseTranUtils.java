package com.edu.admin.education.utils;

import com.edu.admin.education.enums.ConstantsEnum;
import com.edu.admin.education.enums.PublicState;

/**
 * @author mengqa
 * @create 2018-05-22 11:11
 **/
public class ChineseTranUtils {

    public static String getSexChinese(String sexCode) {
        if (ConstantsEnum.SEX_MAN.getDataBase().equalsIgnoreCase(sexCode)) {
            return ConstantsEnum.SEX_MAN.getMessage();
        } else if (ConstantsEnum.SEX_WOMAN.getDataBase().equalsIgnoreCase(sexCode)) {
            return ConstantsEnum.SEX_WOMAN.getMessage();
        } else {
            return ConstantsEnum.NO_SEX.getMessage();
        }
    }

    public static String getStateChinese(String stateCode) {
        if (PublicState.NORMAL.getDataBase().equalsIgnoreCase(stateCode)) {
            return PublicState.NORMAL.getMessage();
        } else if (PublicState.DELETE.getDataBase().equalsIgnoreCase(stateCode)) {
            return PublicState.DELETE.getMessage();
        } else if (PublicState.FREZION.getDataBase().equalsIgnoreCase(stateCode)) {
            return PublicState.FREZION.getMessage();
        }
        return null;
    }
}
