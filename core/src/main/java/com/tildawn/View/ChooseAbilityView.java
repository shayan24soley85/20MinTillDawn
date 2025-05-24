package com.tildawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tildawn.Controller.ChooseAbilityController;

import com.tildawn.Enums.AbilityType;
import com.tildawn.Main;

public class ChooseAbilityView implements Screen {
    private Stage stage;

    private final ChooseAbilityController controller;
    public Table table;
    private final Skin skin;
    private   TextButton confirmButton;
    private CheckBox ability1 ;
    private CheckBox ability2 ;
    private CheckBox ability3 ;
    private AbilityType abilityType1;
    private AbilityType abilityType2;
    private AbilityType abilityType3;


    public ChooseAbilityView(ChooseAbilityController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);
        this.skin = skin;


        this.table = new Table();
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        Texture backgroundTexture = new Texture(Gdx.files.internal("backgrounds/22.png"));
        Image backgroundImage = new Image(backgroundTexture);

        backgroundImage.setFillParent(true);

        stage.addActor(backgroundImage);
        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);


        Label title = new Label(com.tildawn.Enums.Label.REACHED_LEVEL.getText()
            + Main.getMain().getApp().getCurrentGame().getCharacter().getLevel()+
            " \n"+ com.tildawn.Enums.Label.CHOOSE_ABILITY, skin, "title");
        title.setFontScale(0.6f);
        title.setColor(Color.YELLOW);
        root.add(title).padBottom(40).colspan(7).center().row();

        int id1=Main.getMain().getApp().getRand().nextInt(5);
        int id2;
        int id3;
        do {
            id2 = Main.getMain().getApp().getRand().nextInt(5);
        } while (id1 == id2);
        do {
            id3 = Main.getMain().getApp().getRand().nextInt(5);
        } while (id2 == id3 || id3 == id1);
         abilityType1=AbilityType.getById(id1);
        abilityType2=AbilityType.getById(id2);
         abilityType3=AbilityType.getById(id3);

         ability1 = new CheckBox(abilityType1.getName()+"\n"+abilityType1.getDescription(), skin);
         ability2 = new CheckBox(abilityType2.getName()+"\n"+abilityType2.getDescription(), skin);
        ability3 = new CheckBox(abilityType3.getName()+"\n"+abilityType3.getDescription(), skin);


        ability1.getLabel().setFontScale(0.5f);
        ability2.getLabel().setFontScale(0.5f);
        ability3.getLabel().setFontScale(0.5f);


        ButtonGroup<CheckBox> abilityGroup = new ButtonGroup<>(ability1, ability2, ability3);
        abilityGroup.setMaxCheckCount(1);
        abilityGroup.setMinCheckCount(1);
        abilityGroup.setUncheckLast(true);


        root.add(ability1).width(400).padRight(100);
        root.add(ability2).width(400).padLeft(100).padRight(100);
        root.add(ability3).width(400).padLeft(100).row();


       confirmButton= new TextButton(com.tildawn.Enums.Label.CONFIRM.getText(), skin);
        confirmButton.pad(10);

        root.add(confirmButton).colspan(3).padTop(60);
    }



    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleButtonClicked(ability1.isChecked()?abilityType1:ability2.isChecked()?abilityType2:abilityType3);
    }

    public TextButton getConfirmButton() {
        return confirmButton;
    }

    public void setConfirmButton(TextButton confirmButton) {
        this.confirmButton = confirmButton;
    }

    public CheckBox getAbility1() {
        return ability1;
    }

    public void setAbility1(CheckBox ability1) {
        this.ability1 = ability1;
    }

    public CheckBox getAbility2() {
        return ability2;
    }

    public void setAbility2(CheckBox ability2) {
        this.ability2 = ability2;
    }

    public CheckBox getAbility3() {
        return ability3;
    }

    public void setAbility3(CheckBox ability3) {
        this.ability3 = ability3;
    }

    public AbilityType getAbilityType1() {
        return abilityType1;
    }

    public void setAbilityType1(AbilityType abilityType1) {
        this.abilityType1 = abilityType1;
    }

    public AbilityType getAbilityType2() {
        return abilityType2;
    }

    public void setAbilityType2(AbilityType abilityType2) {
        this.abilityType2 = abilityType2;
    }

    public AbilityType getAbilityType3() {
        return abilityType3;
    }

    public void setAbilityType3(AbilityType abilityType3) {
        this.abilityType3 = abilityType3;
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override
    public void dispose() {
        stage.dispose();

    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public ChooseAbilityController getController() {
        return controller;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Skin getSkin() {
        return skin;
    }

}
