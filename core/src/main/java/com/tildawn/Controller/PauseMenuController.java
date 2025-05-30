package com.tildawn.Controller;

import com.tildawn.Enums.SFX;
import com.tildawn.Main;
import com.tildawn.Model.Game;
import com.tildawn.Model.GameAssetManager;
import com.tildawn.View.*;

public class PauseMenuController {
    private PauseMenuView view;
    public void setView(PauseMenuView view) {
        this.view = view;
    }

    public PauseMenuView getView() {
        return view;
    }
    public void handleButtonPressed() {
       if(view!=null){
           if (view.getResumeButton().isChecked()){
               SFX.CLICK_BUTTON.play();
               view.getResumeButton().setChecked(false);
               Main.getMain().getApp().getCurrentGame().setInPause(false);
               Main.getMain().setScreen(Main.getMain().getApp().getCurrentGame().getGameView());
           } else if (view.getSettingsButton().isChecked()) {
               SFX.CLICK_BUTTON.play();
               view.getSettingsButton().setChecked(false);
               Main.getMain().setScreen(new SettingMenuView(new SettingMenuController()
                   , GameAssetManager.getGameAssetManager().getSkin()));
           } else if (view.getTalentButton().isChecked()) {
               SFX.CLICK_BUTTON.play();
               view.getTalentButton().setChecked(false);
               Main.getMain().setScreen(new TalentMenuView
                   (new TalentMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
           } else if (view.getGiveUpButton().isChecked()) {
               SFX.CLICK_BUTTON.play();
               Main.getMain().getApp().setNewGame(new Game());
               Main.getMain().getApp().getCurrentGame().setLost(true);
               view.getGiveUpButton().setChecked(false);
               Game game = Main.getMain().getApp().getCurrentGame();
               Main.getMain().getApp().getSaving().saveUsersDetails(game.getCharacter().getEliminations(),
                   game.getCharacter().getEliminations()*
                       game.getGameView().getElapsedSeconds(),game.getGameView().getElapsedSeconds());
               Main.getMain().setScreen(new EndGameView
                   (new EndGameController(), GameAssetManager.getGameAssetManager().getSkin()
                       , (int)((System.currentTimeMillis() -
                       Main.getMain().getApp().getCurrentGame().getGameView().getStartTimeMillis()) / 1000)));
           } else if (view.getSaveAndExit().isChecked()) {
               SFX.CLICK_BUTTON.play();
               view.getSaveAndExit().setChecked(false);
               Main.getMain().getApp().getCurrentGame().setInPause(false);
               Main.getMain().getApp().setSavedGameId(Main.getMain().getApp().getCurrentGame().getId());
               Main.getMain().getApp().setNewGame(new Game());
               Main.getMain().setScreen(new MainMenuView
                   (new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
           }

       }
    }
}
