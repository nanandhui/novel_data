package com.novel.enums;

public enum NovelTypeEnum {

    URBAN("都市",2),
    FANTASY("玄幻",2),
    ROMANCE("言情",2),
    HISTORY("历史",2),
    SCIENCE_FICTION("科幻",2),
    ONLINE_GAMES("网游",2),
    IMMORTAL_KNIGHT("仙侠",2),
    END("全本",2);


    // 成员变量
    private String name;
    private int index;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (NovelTypeEnum c : NovelTypeEnum.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    public static int getIndex(String name) {
        for (NovelTypeEnum c : NovelTypeEnum.values()) {
            if (c.getName().equals(name)) {
                return c.index;
            }
        }
        return 100;//枚举类中未存在该字段
    }

    NovelTypeEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    @Override
    public String toString() {
        return "ConfigInfoLevelEnum{" +
                "index=" + index +
                ", name='" + name + '\'' +
                '}';
    }
}
