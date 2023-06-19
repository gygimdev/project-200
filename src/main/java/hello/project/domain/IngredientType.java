package hello.project.domain;

public enum IngredientType {
    FRUIT("FRUIT"),
    VEGES("VEGES"),
    GRAIN("GRAIN"),
    DAIRY("DAIRY"),
    MEAT("MEAT"),
    NOODLE("NOODLE"),
    CAN("CAN"),
    ;

    private String name;

    private IngredientType(String name) {
        this.name = name;
    }

    public String getIngredientTypeName(){
        return name;
    }
}
