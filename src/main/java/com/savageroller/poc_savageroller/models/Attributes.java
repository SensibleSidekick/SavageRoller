package com.savageroller.poc_savageroller.models;

public abstract class Attributes {

    private int agility;
    private int smarts;
    private int spirit;
    private int strength;
    private int vigor;

    public Attributes(int agility, int smarts, int spirit, int strength, int vigor) {
        this.agility = agility;
        this.smarts = smarts;
        this.spirit = spirit;
        this.strength = strength;
        this.vigor = vigor;
    }

    public Attributes(){}
    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getSmarts() {
        return smarts;
    }

    public void setSmarts(int smarts) {
        this.smarts = smarts;
    }

    public int getSpirit() {
        return spirit;
    }

    public void setSpirit(int spirit) {
        this.spirit = spirit;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getVigor() {
        return vigor;
    }

    public void setVigor(int vigor) {
        this.vigor = vigor;
    }

}
