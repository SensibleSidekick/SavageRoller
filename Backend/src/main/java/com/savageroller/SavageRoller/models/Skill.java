package com.savageroller.SavageRoller.models;

public class Skill {

    private String name;
    private String attribute;
    private DieType dieType;
    private boolean isCore;

    public Skill() {
    }

    public Skill(String name, String attribute, DieType dieType, boolean isCore) {
        this.name = name;
        this.attribute = attribute;
        this.dieType = dieType != null ? dieType : DieType.D4;
        this.isCore = isCore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public DieType getDieType() {
        return dieType;
    }

    public void setDieType(DieType dieType) {
        this.dieType = dieType;
    }

    public boolean isCore() {
        return isCore;
    }

    public void setCore(boolean core) {
        isCore = core;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                ", attribute='" + attribute + '\'' +
                ", dieType=" + dieType +
                ", isCore=" + isCore +
                '}';
    }
}
