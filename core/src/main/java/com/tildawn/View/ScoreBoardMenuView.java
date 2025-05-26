package com.tildawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tildawn.Controller.ScoreBoardMenuController;
import com.tildawn.Enums.SortBy;
import com.tildawn.Main;
import com.tildawn.Model.User;

import java.util.ArrayList;

public class ScoreBoardMenuView implements Screen {
    private Stage stage;
    private Skin skin;
    private Table table;
    private ArrayList<User> users;
    private ScoreBoardMenuController controller;
    private SelectBox<SortBy> sortSelectBox;
    TextButton confirmButton;
    TextButton backButton;

    public ScoreBoardMenuView(Skin skin, ArrayList<User> users,ScoreBoardMenuController controller) {
        this.skin = skin;
        this.users =users;
        this.controller = controller;
        controller.setView(this);
         confirmButton = new TextButton("Confirm", skin);
         backButton = new TextButton("Back to Main Menu", skin);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        Texture backgroundTexture = new Texture(Gdx.files.internal("backgrounds/14.png"));
        Image backgroundImage = new Image(backgroundTexture);

        backgroundImage.setFillParent(true);

        stage.addActor(backgroundImage);

        table = new Table();
        table.setFillParent(true);
        table.top().pad(20);
        stage.addActor(table);

        sortSelectBox = new SelectBox<>(skin);
        sortSelectBox.setItems(SortBy.score, SortBy.username, SortBy.TimeSurvived, SortBy.killCount);
        sortSelectBox.setSelected(Main.getMain().getApp().getLastSortBy());
        Table topBar = new Table();
        topBar.add(sortSelectBox).padRight(10);
        topBar.add(confirmButton).padRight(10);
        topBar.add(backButton);

        table.add(topBar).colspan(4).padBottom(20);
        table.row();
        Label header = new Label("Username        Kills     Score     Time Survived", skin);
        table.add(header).left().padBottom(10);
        table.row();

        for (int i = 0; i < users.size(); i++) {
            User player = users.get(i);
            String row = String.format("%-15s  %-5d  %-6d  %-6d",
                    player.getUsername(),
                    player.getTotalEliminations(),
                    player.getScore(),
                    (int) player.getMostTimeAlive());

            Label label = new Label(row, skin);

            if (player.getUsername().equals(Main.getMain().getApp().getCurrentUser().getUsername())) {
                label.setColor(Color.GREEN);
            } else if (i == 0) {
                label.setColor(Color.GOLD);
            } else if (i == 1) {
                label.setColor(Color.GRAY);
            } else if (i == 2) {
                label.setColor(new Color(0.8f, 0.5f, 0.2f, 1));
            } else {
                label.setColor(Color.WHITE);
            }

            table.add(label).left().pad(5);
            table.row();
        }
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

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ScoreBoardMenuController getController() {
        return controller;
    }

    public void setController(ScoreBoardMenuController controller) {
        this.controller = controller;
    }

    public SelectBox<SortBy> getSortSelectBox() {
        return sortSelectBox;
    }

    public void setSortSelectBox(SelectBox<SortBy> sortSelectBox) {
        this.sortSelectBox = sortSelectBox;
    }

    public TextButton getConfirmButton() {
        return confirmButton;
    }

    public void setConfirmButton(TextButton confirmButton) {
        this.confirmButton = confirmButton;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public void setBackButton(TextButton backButton) {
        this.backButton = backButton;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
        controller.handleButtonClick();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
