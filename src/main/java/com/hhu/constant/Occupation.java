package com.hhu.constant;

public enum Occupation {
    OTHER(0, "其他"),
    ACADEMIC_EDUCATION(1, "教育"),
    ARTIST(2, "艺术家"),
    CLERICAL_ADMIN(3, "行政"),
    COLLEGE_GRAD_STUDENT(4, "大学生"),
    CUSTOMER_SERVICE(5, "客户服务"),
    DOCTOR_HEALTH_CARE(6, "医疗"),
    EXECUTIVE_MANAGERIAL(7, "管理"),
    FARMER(8, "农民"),
    HOMEMAKER(9, "家庭主妇"),
    K12_STUDENT(10, "中小学生"),
    LAWYER(11, "律师"),
    PROGRAMMER(12, "程序员"),
    RETIRED(13, "退休"),
    SALES_MARKETING(14, "市场营销"),
    SCIENTIST(15, "科学家"),
    SELF_EMPLOYED(16, "个体户"),
    TECHNICIAN_ENGINEER(17, "工程师"),
    TRADESMAN_CRAFTSMAN(18,"工匠"),
    UNEMPLOYED(19, "失业"),
    WRITER(20, "作家");


    private int value;
    private String chineseName;

    private Occupation(int value, String chineseName) {
        this.value = value;
        this.chineseName = chineseName;
    }

    public int getValue() {
        return value;
    }

    public String getChineseName() {
        return chineseName;
    }

    public static Occupation fromValue(int value) {
        for (Occupation occupation : Occupation.values()) {
            if (occupation.getValue() == value) {
                return occupation;
            }
        }
        throw new IllegalArgumentException("无效的值: " + value);
    }

    public static Occupation fromChineseName(String chineseName) {
        for (Occupation occupation : Occupation.values()) {
            if (occupation.getChineseName().equals(chineseName)) {
                return occupation;
            }
        }
        throw new IllegalArgumentException("无效的中文职业名: " + chineseName);
    }
    //获取中文名
    public static String getChineseName(int value) {
        for (Occupation occupation : Occupation.values()) {
            if (occupation.getValue() == value) {
                return occupation.getChineseName();
            }
        }
        throw new IllegalArgumentException("无效的值: " + value);
    }
}
