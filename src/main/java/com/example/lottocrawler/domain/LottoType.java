package com.example.lottocrawler.domain;

public enum LottoType {
    AUTO("자동"),
    MANUAL("수동"),
    HALF_AUTO("반자동");

    private final String name;

    LottoType(String name) {
        this.name = name;
    }

    public static LottoType get(String name) {
        switch (name) {
            case "자동" :
                return LottoType.AUTO;
            case "수동" :
                return LottoType.MANUAL;
            case "반자동" :
                return LottoType.HALF_AUTO;
            default:
                throw new AssertionError("Unknown name : " + name);
        }
    }
}
