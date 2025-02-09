package com.savageroller.SavageRoller.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.HashMap;
import java.util.Map;

public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private User userId;

    private String name;
    private String description;
    private String race;
    private int bennies;



    private DieType agility;
    private DieType smarts;
    private DieType spirit;
    private DieType strength;
    private DieType vigor;

    private Map<String, Skill> skills = new HashMap<>();

    public Character(int id, User userId, String name, String description, String race, int bennies, DieType agility, DieType smarts, DieType spirit, DieType strength, DieType vigor, Map<String, Skill> skills) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.race = race;
        this.bennies = bennies;
        this.agility = agility;
        this.smarts = smarts;
        this.spirit = spirit;
        this.strength = strength;
        this.vigor = vigor;
        this.skills = skills;
    }

    public void addSkill(String skillName, String attribute, DieType dieType, boolean isCore){
        skills.put(skillName, new Skill(skillName, attribute, dieType, isCore));
    }

    public Skill getSkill(String skillName){
        return skills.get(skillName);
    }

    public int getId() {
        return id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getBennies() {
        return bennies;
    }

    public void setBennies(int bennies) {
        this.bennies = bennies;
    }

    public DieType getAgility() {
        return agility;
    }

    public void setAgility(DieType agility) {
        this.agility = agility;
    }

    public DieType getSmarts() {
        return smarts;
    }

    public void setSmarts(DieType smarts) {
        this.smarts = smarts;
    }

    public DieType getSpirit() {
        return spirit;
    }

    public void setSpirit(DieType spirit) {
        this.spirit = spirit;
    }

    public DieType getStrength() {
        return strength;
    }

    public void setStrength(DieType strength) {
        this.strength = strength;
    }

    public DieType getVigor() {
        return vigor;
    }

    public void setVigor(DieType vigor) {
        this.vigor = vigor;
    }

    public Map<String, Skill> getSkills() {
        return skills;
    }

    public void setSkills(Map<String, Skill> skills) {
        this.skills = skills;
    }
}
