package com.tildawn.Controller;

import com.tildawn.Enums.AbilityType;
import com.tildawn.Enums.SFX;
import com.tildawn.Main;
import com.tildawn.Model.Ability;
import com.tildawn.Model.Character;
import com.tildawn.View.ChooseAbilityView;

public class ChooseAbilityController {
    private  ChooseAbilityView view;
    public void setView(ChooseAbilityView view) {
        this.view=view;
    }

    public ChooseAbilityView getView() {
        return view;
    }
    public void handleButtonClicked(AbilityType abilityType) {
        if (view!=null){
            if (view.getConfirmButton().isChecked()){
                Character character=Main.getMain().getApp().getCurrentGame().getCharacter();
                if(!character.getAbilities().containsKey(abilityType)){
                    float duration=abilityType.equals(AbilityType.DAMAGER)||abilityType.equals(AbilityType.SPEEDY)?10000:Float.POSITIVE_INFINITY;
                    character.getAbilities().put(abilityType,new Ability(abilityType,duration));
                } else if (!character.getAbilities().get(abilityType).isEnabled()) {
                    character.getAbilities().remove(abilityType);
                    float duration=abilityType.equals(AbilityType.DAMAGER)||abilityType.equals(AbilityType.SPEEDY)?10000:Float.POSITIVE_INFINITY;
                    character.getAbilities().put(abilityType,new Ability(abilityType,duration));
                }
                SFX.CLICK_BUTTON.play();
                view.getConfirmButton().setChecked(false);
                Main.getMain().setScreen(Main.getMain().getApp().getCurrentGame().getGameView());
            }
        }
    }
}
