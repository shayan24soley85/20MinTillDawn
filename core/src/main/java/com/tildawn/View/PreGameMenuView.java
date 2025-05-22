package com.tildawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tildawn.Controller.PreGameMenuController;
import com.tildawn.Enums.CharacterType;
import com.tildawn.Enums.WeaponType;
import com.tildawn.Main;

public class PreGameMenuView implements Screen {
    private Stage stage;
    private final Label gameTitle;
    private final Label heroTitle;
    private final Label weaponTitle;
    private final Label timeTitle;
    private SelectBox<CharacterType> heroSelect;
    private SelectBox<WeaponType> weaponSelect;
    private SelectBox<Integer> timeSelect;
    private final TextButton playButton;
    private TextButton backButton;
    public Table table;
    private PreGameMenuController controller;

    public PreGameMenuView(PreGameMenuController controller, Skin skin) {
        this.gameTitle = new Label(com.tildawn.Enums.Label.PRE_GAME_MENU.getText(), skin);
        this.playButton = new TextButton(com.tildawn.Enums.Label.PLAY.getText(), skin);
        this.table = new Table();
        this.controller = controller;
        heroTitle = new Label(com.tildawn.Enums.Label.SELECT_HERO.getText(), skin);
        controller.setView(this);
        heroSelect = new SelectBox<>(skin);
        weaponTitle=new Label(com.tildawn.Enums.Label.SELECT_WEAPON.getText(), skin);
        timeTitle=new Label(com.tildawn.Enums.Label.SELECT_TIME.getText(), skin);
        heroSelect.setItems(CharacterType.Dasher,CharacterType.Diamond,CharacterType.Lilith,CharacterType.Scarlet,CharacterType.Shana);

        weaponSelect = new SelectBox<>(skin);
        weaponSelect.setItems(WeaponType.dualSmg, WeaponType.shotgun, WeaponType.revolver);
        heroTitle.setColor(Color.BLUE);
        weaponTitle.setColor(Color.BLUE);
        timeTitle.setColor(Color.BLUE);
        gameTitle.setColor(Color.GOLD);
        timeSelect = new SelectBox<>(skin);
        timeSelect.setItems(2,5,10,20);
        backButton = new TextButton(com.tildawn.Enums.Label.BACK.getText(), skin);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());

        Texture backgroundTexture = new Texture(Gdx.files.internal("backgrounds/11.png"));
        Image backgroundImage = new Image(backgroundTexture);
        backgroundImage.setFillParent(true);
        stage.addActor(backgroundImage);


        table.setFillParent(true);
        table.center().top().padTop(80);
        stage.addActor(table);


        table.add(gameTitle).padBottom(80);
        table.row();


        table.add(heroTitle).padBottom(10).center();
        table.row();
        table.add(heroSelect).padBottom(40).width(300);
        table.row();


        table.add(weaponTitle).padBottom(10).center();
        table.row();
        table.add(weaponSelect).padBottom(40).width(300);
        table.row();


        table.add(timeTitle).padBottom(10).center();
        table.row();
        table.add(timeSelect).padBottom(50).width(300);
        table.row();


        Table buttonRow = new Table();
        buttonRow.add(backButton).padRight(20).width(200).height(100);
        buttonRow.add(playButton).width(200).height(100);

        table.add(buttonRow).padBottom(20).center();
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleButtonClick();
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

    public Label getHeroTitle() {
        return heroTitle;
    }

    public Label getWeaponTitle() {
        return weaponTitle;
    }

    public Label getTimeTitle() {
        return timeTitle;
    }

    public SelectBox<CharacterType> getHeroSelect() {
        return heroSelect;
    }

    public void setHeroSelect(SelectBox<CharacterType> heroSelect) {
        this.heroSelect = heroSelect;
    }

    public SelectBox<WeaponType> getWeaponSelect() {
        return weaponSelect;
    }

    public void setWeaponSelect(SelectBox<WeaponType> weaponSelect) {
        this.weaponSelect = weaponSelect;
    }

    public SelectBox<Integer> getTimeSelect() {
        return timeSelect;
    }

    public void setTimeSelect(SelectBox<Integer> timeSelect) {
        this.timeSelect = timeSelect;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public void setBackButton(TextButton backButton) {
        this.backButton = backButton;
    }
}
