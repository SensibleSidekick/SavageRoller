package com.savageroller.poc_savageroller.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashMap;
import java.util.Map;

@Entity
public class PlayerCharacter extends Attributes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String characterName;

    @Size(max = 500, message = "Cannot be longer than 500 characters.")
    private String description;


    @ElementCollection
    @MapKeyColumn(name= "level")
    @Column(name = "isTrue")
    @CollectionTable(name="advance", joinColumns = @JoinColumn(name="player_character_advance_id"))
    private Map<String, Boolean> advance = new HashMap<String, Boolean>();
    private int benny = 3;
    private String race;
    private int conviction;
    private int height;
    private int weight;
    private String size;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //Constructors

    public PlayerCharacter(){}

    public PlayerCharacter(String characterName, Map<String, Boolean> advance, User user, String description) {
        super(4,4,4,4,4);
        this.characterName = characterName;
        this.advance = advance;
        this.user = user;
        this.description = description;

        initializeAdvanceMap();
    }

    private void initializeAdvanceMap(){
        String[] levels= {"Novice", "Seasoned", "Veteran","Hero", "Legend"};

        for (String level : levels){
            for(int i = 1; i <= 4; i++){
                String key = level + " " + i;
                advance.put(key, level.equals("Novice") && i == 1);
            }
        }
    }

    //Getters and Setters
    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public Map<String, Boolean> getAdvances() {
        return advance;
    }

    public void setAdvance(String key, boolean value){
       advance.put(key, value);
    }


    public int getBenny() {
        return benny;
    }

    public void setBenny(int benny) {
        this.benny = benny;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getConviction() {
        return conviction;
    }

    public void setConviction(int conviction) {
        this.conviction = conviction;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public @Size(max = 500, message = "Cannot be longer than 500 characters.") String getDescription() {
        return description;
    }

    public void setDescription(@Size(max = 500, message = "Cannot be longer than 500 characters.") String description) {
        this.description = description;
    }
}
