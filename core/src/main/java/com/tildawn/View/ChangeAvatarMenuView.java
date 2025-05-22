package com.tildawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tildawn.Controller.ChangeAvatarMenuController;
import com.tildawn.Enums.Avatar;
import com.tildawn.Main;
import com.tildawn.Model.User;

import javax.swing.*;
import java.io.File;

public class ChangeAvatarMenuView implements Screen {
    private Stage stage;
    private Skin skin;
    private Image avatarPreview;
    private ChangeAvatarMenuController controller;
    public Table table;
    private TextButton backBtn ;
    private TextButton confirmBtn ;
    private TextButton selectFileBtn ;
    private File selectedFile;
    private Texture selectedTexture;
    private final Label nameLabel ;
    private final Label scoreLabel ;
    private String avatarPath;
    private User user=Main.getMain().getApp().getCurrentUser();
    private final Label errorLabel;
    private final Label SuccessMessageLabel;
    private com.badlogic.gdx.utils.Timer.Task clearErrorTask;
    private Timer.Task clearErrorTask2;
    public ChangeAvatarMenuView(ChangeAvatarMenuController controller, Skin skin) {
        controller.setView(this);
        this.skin = skin;
        this.controller = controller;
        backBtn = new TextButton("back", skin);
        confirmBtn = new TextButton("confirm", skin);
         selectFileBtn = new TextButton("custom avatar", skin);
        this.table = new Table();
        nameLabel = new Label("Username: " +user.getUsername(), skin);
        scoreLabel = new Label("Score: " + user.getScore(), skin);
        avatarPath=user.getAvatarPath();
        SuccessMessageLabel = new Label("", skin);
        SuccessMessageLabel.setColor(Color.GREEN);
        errorLabel = new Label("", skin);
        errorLabel.setColor(Color.RED);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        Texture backgroundTexture = new Texture(Gdx.files.internal("backgrounds/1.png"));
        Image backgroundImage = new Image(backgroundTexture);
        backgroundImage.setFillParent(true);
        stage.addActor(backgroundImage);

        Table root = new Table();
        Texture avatarTexture = new Texture(Gdx.files.internal(Main.getMain().getApp().getCurrentUser().getAvatarPath()));
        Image avatarImagex = new Image(new TextureRegionDrawable(new TextureRegion(avatarTexture)));
        avatarImagex.setSize(64, 64);
        avatarImagex.setScaling(Scaling.fit);


        Table topTable = new Table();
        topTable.add(avatarImagex).size(64).padRight(10);
        topTable.add(nameLabel).left().padRight(20);
        topTable.add(scoreLabel).left();
        topTable.top().left();
        root.add(topTable).expandX().left().padLeft(20).row();
        root.setFillParent(true);
        root.top();

        Label title = new Label("Select Your Avatar", skin);
        root.add(title).padTop(120).padBottom(30).row();

        Table avatarTable = new Table();
        for (Avatar avatar : Avatar.values()) {
            Texture texture = new Texture(Gdx.files.internal(avatar.getPath()));
            Image avatarImage = new Image(texture);
            avatarImage.setScaling(Scaling.fit);
            avatarImage.setSize(70, 70);

            avatarImage.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    setAvatar(avatar);
                    selectedFile = null;
                }
            });

            avatarTable.add(avatarImage).size(70, 70).pad(5).padBottom(70);
        }

        ScrollPane scrollPane = new ScrollPane(avatarTable, skin);
        scrollPane.setScrollingDisabled(false, true);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollbarsOnTop(true);

        root.add(scrollPane).height(80).expandX().fillX().padBottom(20).padTop(20).row();

        avatarPreview = new Image(new Texture(Gdx.files.internal(Main.getMain().getApp().getCurrentUser().getAvatarPath())));
        avatarPreview.setScaling(Scaling.fit);
        avatarPreview.setSize(60, 60);

        root.add(new Label("Preview:", skin)).padBottom(10).row();
        root.add(avatarPreview).size(60, 60).padBottom(20).row();

        root.add(selectFileBtn).padBottom(20).row();

        Table buttonsTable = new Table();

        buttonsTable.add(backBtn).padRight(20);
        buttonsTable.add(confirmBtn).padRight(20);

        confirmBtn.setVisible(true);

        root.add(buttonsTable).padTop(20).padBottom(30);
        root.row();


        root.add(errorLabel).colspan(2).center().row();
        root.add(SuccessMessageLabel).colspan(2).center().row();
        stage.addActor(root);
    }

    private void setAvatar(Avatar avatar) {
        if (selectedTexture != null) {
            selectedTexture.dispose();
            selectedTexture = null;
        }

        Texture tex = new Texture(Gdx.files.internal(avatar.getPath()));
        avatarPreview.setDrawable(new TextureRegionDrawable(new TextureRegion(tex)));
        selectedFile = null;
        avatarPath=avatar.getPath();
    }
    public void setErrorMessage(String message) {
        errorLabel.setText(message);
        if (clearErrorTask != null) {
            clearErrorTask.cancel();
        }
        clearErrorTask = new Timer.Task() {
            @Override
            public void run() {
                errorLabel.setText("");
            }
        };
        Timer.schedule(clearErrorTask, 5);
    }
    public void setSuccessMessage(String message) {
        SuccessMessageLabel.setText(message);
        if (clearErrorTask2 != null) {
            clearErrorTask2.cancel();
        }
        clearErrorTask2 = new Timer.Task() {
            @Override
            public void run() {
                SuccessMessageLabel.setText("");
            }
        };
        Timer.schedule(clearErrorTask2, 5);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(delta);
        stage.draw();
        controller.handleChangeAvatarMenu();
    }

    @Override public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override
    public void dispose() {
        stage.dispose();
        if (selectedTexture != null) selectedTexture.dispose();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public Image getAvatarPreview() {
        return avatarPreview;
    }

    public void setAvatarPreview(Image avatarPreview) {
        this.avatarPreview = avatarPreview;
    }

    public ChangeAvatarMenuController getChangeAvatarMenuController() {
        return controller;
    }

    public void setChangeAvatarMenuController(ChangeAvatarMenuController controller) {
        this.controller = controller;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public TextButton getBackBtn() {
        return backBtn;
    }

    public void setBackBtn(TextButton backBtn) {
        this.backBtn = backBtn;
    }

    public TextButton getConfirmBtn() {
        return confirmBtn;
    }

    public void setConfirmBtn(TextButton confirmBtn) {
        this.confirmBtn = confirmBtn;
    }

    public TextButton getSelectFileBtn() {
        return selectFileBtn;
    }

    public void setSelectFileBtn(TextButton selectFileBtn) {
        this.selectFileBtn = selectFileBtn;
    }

    public File getSelectedFile() {
        return selectedFile;
    }

    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }

    public Texture getSelectedTexture() {
        return selectedTexture;
    }

    public void setSelectedTexture(Texture selectedTexture) {
        this.selectedTexture = selectedTexture;
    }

    public ChangeAvatarMenuController getController() {
        return controller;
    }

    public void setController(ChangeAvatarMenuController controller) {
        this.controller = controller;
    }

    public Label getNameLabel() {
        return nameLabel;
    }

    public Label getScoreLabel() {
        return scoreLabel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public Label getSuccessMessageLabel() {
        return SuccessMessageLabel;
    }

    public Timer.Task getClearErrorTask() {
        return clearErrorTask;
    }

    public void setClearErrorTask(Timer.Task clearErrorTask) {
        this.clearErrorTask = clearErrorTask;
    }

    public Timer.Task getClearErrorTask2() {
        return clearErrorTask2;
    }

    public void setClearErrorTask2(Timer.Task clearErrorTask2) {
        this.clearErrorTask2 = clearErrorTask2;
    }
}
