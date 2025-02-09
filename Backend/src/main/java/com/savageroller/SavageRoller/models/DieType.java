package com.savageroller.SavageRoller.models;

public enum DieType {

    D4("d4"),
    D6("d6"),
    D8("d8"),
    D10("d10"),
    D12("d12"),
    D20("d20");

    private final String dieValue;

    DieType(String dieValue) {
        this.dieValue = dieValue;
    }

    public String getDieValue() {
        return dieValue;
    }

    public static DieType fromString(String text){
        for (DieType type : DieType.values()) {
            if(type.dieValue.equalsIgnoreCase(text)){
                return type;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "DieType{" +
                "dieValue='" + dieValue + '\'' +
                '}';
    }
}
