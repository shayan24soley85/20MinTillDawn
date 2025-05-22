package com.tildawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tildawn.Controller.PreGameMenuController;

public class PreGameMenuView implements Screen {
    private Stage stage;
    private final Label gameTitle;
    private final TextButton playButton;
    private final SelectBox selectHero;
    public Table table;
    private PreGameMenuController controller;

    public PreGameMenuView(PreGameMenuController controller, Skin skin) {
        this.gameTitle = new Label(com.tildawn.Enums.Label.PRE_GAME_MENU.getText(), skin);
        this.selectHero = new SelectBox<>(skin);
        this.playButton = new TextButton(com.tildawn.Enums.Label.PLAY.getText(), skin);
        this.table = new Table();
        this.controller = controller;
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Array<String> hero = new Array<>();

        hero.add("hero1");
        hero.add("hero2");
        hero.add("hero3");

        selectHero.setItems(hero);

        table.setFillParent(true);
        table.center();
        table.row().pad(10, 0 , 10 , 0);
        table.add(gameTitle);
        table.row().pad(10, 0 , 10 , 0);
        table.add(selectHero);
        table.row().pad(10, 0 , 10 , 0);
        table.add(playButton);

        stage.addActor(table);
    }

    @Override
    public void render(float v) {

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Label getGameTitle() {
        return gameTitle;
    }

    public TextButton getPlayButton() {
        return playButton;
    }

    public SelectBox getSelectHero() {
        return selectHero;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public PreGameMenuController getController() {
        return controller;
    }

    public void setController(PreGameMenuController controller) {
        this.controller = controller;
    }
}
