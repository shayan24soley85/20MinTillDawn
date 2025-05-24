package com.tildawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tildawn.Controller.TalentMenuController;
import com.tildawn.Enums.CharacterType;
import com.tildawn.Main;

public class TalentMenuView implements Screen {
private Stage stage;
private Viewport viewport;
private Table table;
private TextButton backButton;
    private final Skin skin;
private TalentMenuController controller;
public TalentMenuView(TalentMenuController controller,Skin skin) {
    this.skin = skin;
    this.controller = controller;
    controller.setView(this);
}
    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        Texture backgroundTexture = new Texture(Gdx.files.internal("backgrounds/16.png"));
        Image backgroundImage = new Image(backgroundTexture);
        backgroundImage.setFillParent(true);
        stage.addActor(backgroundImage);

        Table rootTable = new Table();
        rootTable.setFillParent(true);
        stage.addActor(rootTable);

        Table scrollContent = new Table();
        scrollContent.top().bottom().pad(10);

        Label titleLabel = new Label("CHARACTERS:", skin);
        titleLabel.setColor(Color.BLACK);
        Container<Label> titleContainer = new Container<>(titleLabel);
        titleContainer.left().padLeft(-60);
        scrollContent.add(titleContainer).left().padBottom(20).row();

        for (CharacterType type : CharacterType.values()) {
            Label label = new Label(type.getName(), skin);
            label.setColor(Color.BLUE);
            scrollContent.add(label).left().row();
            scrollContent.add(new Label(type.print(), skin)).bottom().padBottom(40).row();
        }

        Label controlLabel = new Label("CONTROLS:", skin);
        controlLabel.setColor(Color.BLACK);
        Container<Label> controlContainer = new Container<>(controlLabel);
        controlContainer.left().padLeft(-60);
        scrollContent.add(controlContainer).left().padBottom(20).row();

        Label moveLabel = new Label("MOVEMENT:", skin);
        moveLabel.setColor(Color.RED);
        scrollContent.add(moveLabel).padBottom(20).left().row();
        scrollContent.add(new Label("W : UP       S : DOWN     A : LEFT     D : RIGHT", skin)).left().row();
        Label defaultLabel = new Label("SIMPLE KEYS:", skin);
        defaultLabel.setColor(Color.RED);
        scrollContent.add(defaultLabel).padBottom(20).left().row();
        scrollContent.add(new Label("R:RELOAD       MOUSE LEFT CLICK:SHOOT     P:PAUSE THE GAME", skin)).left().row();


        Label cheatLabel = new Label("CHEATS:", skin);
        cheatLabel.setColor(Color.BLACK);
        Container<Label> cheat = new Container<>(cheatLabel);
        cheat.left().padLeft(-60);
        scrollContent.add(cheat).left().padBottom(20).row();
        scrollContent.add(new Label("CTRL+Z :    Reduce remaining game time by 1 minute", skin)).left().row();
        scrollContent.add(new Label("CTRL+X :   Level up character with full animation and ability selection", skin)).left().row();
        scrollContent.add(new Label("CTRL+C :    Restore health when empty (only works with low health)", skin)).left().row();
        scrollContent.add(new Label("CTRL+V :    Add 10000 Ammo to Player's Weapon", skin)).left().row();
        scrollContent.add(new Label("CTRL+B :    Start bus fight", skin)).left().row();

        Label abilities = new Label("ABILITIES:", skin);
        abilities.setColor(Color.BLACK);
        Container<Label> Ability = new Container<>(abilities);
        Ability.left().padLeft(-60);
        scrollContent.add(Ability).left().padBottom(20).row();
        Label label = new Label("VITALITY:", skin);
        label.setColor(Color.FIREBRICK);
        scrollContent.add(label).left().padBottom(20).row();
        scrollContent.add(new Label("Increase maximum HP by 1 unit", skin)).left().row();
        Label label2 = new Label("DAMAGER:", skin);
        label2.setColor(Color.FIREBRICK);
        scrollContent.add(label2).left().padBottom(20).row();
        scrollContent.add(new Label("Increase weapon damage by 25% for 10 seconds", skin)).left().row();
        Label label3 = new Label("SPEEDY:", skin);
        label3.setColor(Color.FIREBRICK);
        scrollContent.add(label3).left().padBottom(20).row();
        scrollContent.add(new Label("Double movement speed for 10 seconds", skin)).left().row();
        Label label4 = new Label("PROCREASE:", skin);
        label4.setColor(Color.FIREBRICK);
        scrollContent.add(label4).left().padBottom(20).row();
        scrollContent.add(new Label("Increase weapon projectile count by 1", skin)).left().row();
        Label label5 = new Label("AMOCREASE:", skin);
        label5.setColor(Color.FIREBRICK);
        scrollContent.add(label5).left().padBottom(20).row();
        scrollContent.add(new Label("Increase maximum ammo by 5", skin)).left().row();

        ScrollPane scrollPane = new ScrollPane(scrollContent, skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);

        backButton= new TextButton("Back", skin);
        rootTable.top().pad(10);
        rootTable.add(scrollPane).expand().fill().row();
        rootTable.add(backButton).padTop(10);
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public void setBackButton(TextButton backButton) {
        this.backButton = backButton;
    }

    public Skin getSkin() {
        return skin;
    }

    public TalentMenuController getController() {
        return controller;
    }

    public void setController(TalentMenuController controller) {
        this.controller = controller;
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
        stage.dispose();
//        if (clearErrorTask != null) clearErrorTask.cancel();
    }
}
