package com.tildawn.Controller;

import com.tildawn.Enums.SFX;
import com.tildawn.Enums.SortBy;
import com.tildawn.Main;
import com.tildawn.Model.GameAssetManager;
import com.tildawn.Model.User;
import com.tildawn.View.MainMenuView;
import com.tildawn.View.ScoreBoardMenuView;

import java.util.ArrayList;
import java.util.Comparator;

public class ScoreBoardMenuController {
    private ScoreBoardMenuView view;
    public void setView(ScoreBoardMenuView view) {
        this.view = view;
    }

    public ScoreBoardMenuView getView() {
        return view;
    }
    public void handleButtonClick() {
        if (view!=null){
            if (view.getBackButton().isChecked()){
                SFX.CLICK_BUTTON.play();
                view.getBackButton().setChecked(false);
                Main.getMain().setScreen(new MainMenuView
                    (new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }else if (view.getConfirmButton().isChecked()) {
                SFX.CLICK_BUTTON.play();
                view.getConfirmButton().setChecked(false);

                SortBy selectedSort = view.getSortSelectBox().getSelected();

                Main.getMain().getApp().setLastSortBy(selectedSort);

                applySort(view.getUsers(), selectedSort);

                Main.getMain().setScreen(new ScoreBoardMenuView(
                    GameAssetManager.getGameAssetManager().getSkin(),
                    view.getUsers(),
                    new ScoreBoardMenuController()
                ));
            }
        }
    }
    private ArrayList<User> applySort(ArrayList<User> userList, SortBy sortType) {
        Comparator<User> comparator;
        if (sortType == SortBy.username) {
            comparator = Comparator.comparing(p -> p.getUsername().toLowerCase());
        } else if (sortType == SortBy.killCount) {
            comparator = Comparator.comparingInt(p -> -p.getTotalEliminations());
        } else if (sortType == SortBy.TimeSurvived) {
            comparator = Comparator.comparingInt(p -> (int) -p.getMostTimeAlive());
        } else {
            comparator = Comparator.comparingInt(p -> -p.getScore());
        }
        userList.sort(comparator);
        return userList;
    }
}
