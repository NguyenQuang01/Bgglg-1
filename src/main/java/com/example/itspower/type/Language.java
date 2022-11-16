package com.example.itspower.type;

import org.apache.commons.lang3.StringUtils;

public enum Language {
    Chinese_Simplified(1, "chinese_simplified"),
    English(2, "english"),
    Japanese(3, "japanese"),
    Arabic(4, "arabic"),
    BahasaIndonesia(5, "bahasaindonesia"),
    Bulgarian(6, "bulgarian"),
    Chinese_Traditional(7, "chinese_traditional"),
    Croatian(8, "croatian"),
    Czech(9, "czech"),
    Danish(10, "danish"),
    Dutch(11, "dutch"),
    Estonian(12, "estonian"),
    Finnish(13, "finnish"),
    French(14, "french"),
    German(15, "german"),
    Greek(16, "greek"),
    HaitianCreole(17, "haitiancreole"),
    Hebrew(18, "hebrew"),
    Hungarian(19, "hungarian"),
    Italian(20, "italian"),
    Korean(21, "korean"),
    Latvian(22, "latvian"),
    Lithuanian(23, "lithuanian"),
    Norwegian(24, "norwegian"),
    Persian(25, "persian"),
    Polish(26, "polish"),
    Portuguese(27, "portuguese"),
    Romanian(28, "romanian"),
    Russian(29, "russian"),
    Serbian(30, "serbian"),
    Slovak(31, "slovak"),
    Slovenian(32, "slovenian"),
    Spanish(33, "spanish"),
    Swedish(34, "swedish"),
    Thai(35, "thai"),
    Turkish(36, "turkish"),
    Ukrainian(37, "ukrainian"),
    Vietnamese(38, "vietnamese");

    Integer id;
    String name;

    Language(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Boolean isExist(String name) {
        if(StringUtils.isBlank(name)) {
            return false;
        }

        for(Language item : Language.values()) {
            if(name.equalsIgnoreCase(item.name)) {
                return true;
            }
        }
        return false;
    }
}
