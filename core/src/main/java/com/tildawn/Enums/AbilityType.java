package com.tildawn.Enums;

public enum AbilityType {
    SPEEDY("Speedy",Label.ABILITY_3.getText(),0),
    DAMAGER("Damage",Label.ABILITY_2.getText(),1),
    VITALITY("Vitality",Label.ABILITY_1.getText(),2),
    PROCREASE("Procrease",Label.ABILITY_4.getText(),3),
    AMOCREASE("Amorecase",Label.ABILITY_5.getText(),4);
    private final String name;
    private final String description;
    private final int id;
    AbilityType(String name, String description,int id) {
        this.name = name;
        this.description = description;
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    public static AbilityType getById(int id) {
        for(AbilityType ability : AbilityType.values()){
            if (id==ability.id){
                return ability;
            }
        }
        return null;
    }
}
