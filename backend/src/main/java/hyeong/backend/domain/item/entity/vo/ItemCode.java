package hyeong.backend.domain.item.entity.vo;


public enum ItemCode {

    // Digital , CLOTHES , FOOD , CAFE , PUB , KIDS , THEMEPARK

    // Digital
    DESKTOP("D001"),
    PHONE("D002"),
    TV("003"),

    // CLOTHES
    OUTER("C001"),
    JACKET("C002"),
    COAT("C002"),
    BLAZER("C003"),
    T_SHIRTS("C004"),

    // FOOD
    KOREAN("F001"),
    JAPAN("F002"),
    CHINA("F003"),
    WESTERN("F004"),
    FASTFOOD("F005"),
    CUISION("F006"),
    RESTAURANTS("F007"),

    // CAFE
    BAKERY("C001"),
    DESSERT("C002"),
    THEMECAFE("C003"),

    // PUB
    PUB("P001"),
    DATINGPUB("P002"),
    CASIONPUB("P003"),
    CLUB("P004"),
    THEMEPUB("P005"),

    // KIDS
    KIDSCAFE("K001"),
    KINDERGARDEN("K002"),
    KIDSSPORTS("K003"),

    // THEME PARK
    AMUSEMENT("T001"),
    EXTREME("T002"),
    FIELDTRIP("T003")


;
    private String code;

    ItemCode(final String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}
