package com.tildawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tildawn.Controller.EndGameController;
import com.tildawn.Enums.SFX;
import com.tildawn.Main;
import com.tildawn.Model.Character;
import com.tildawn.Model.Game;

public class EndGameView implements Screen {
    private Stage stage;
    private Skin skin;
    private float timeSurvived;
    private EndGameController controller;
    private TextButton backButton ;

    public EndGameView(EndGameController controller, Skin skin, float timeSurvived) {
        this.timeSurvived = timeSurvived;
        this.controller = controller;
        this.skin = skin;
        controller.setView(this);
        backButton = new TextButton(com.tildawn.Enums.Label.BACK_TO_MAIN_MENU.getText(), skin);
    }

    @Override
    public void show() {
        Game game = Main.getMain().getApp().getCurrentGame();

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);


        Texture backgroundTexture = new Texture(Gdx.files.internal(game.getLost() ?
            "backgrounds/pastel-pink.jpg" :
            "backgrounds/vintage-textured-paper-background-vector.jpg"));
        Image backgroundImage = new Image(backgroundTexture);
        backgroundImage.setFillParent(true);
        stage.addActor(backgroundImage);

         game.setInPause(false);
        if (game.getLost()) {
            SFX.LOSING.play();
        } else {
            SFX.WINNING.play();
        }


        Table table = new Table();
        table.setFillParent(true);
        table.top().padTop(80);

        String status = game.getLost() ? com.tildawn.Enums.Label.YOU_LOSE.toString() : com.tildawn.Enums.Label.YOU_WIN.toString();
        Label titleLabel = new Label(status, skin, "title");
        titleLabel.setColor(game.getLost() ? Color.RED : Color.CYAN);
        titleLabel.setFontScale(2f);
        titleLabel.setAlignment(Align.center);

        Character character = game.getCharacter();
        Label usernameLabel = new Label(com.tildawn.Enums.Label.USERNAME + character.getUsername(), skin);
        Label scoreLabel = new Label(com.tildawn.Enums.Label.SCORE.getText() + (int)(timeSurvived * character.getEliminations()), skin);
        Label killsLabel = new Label(com.tildawn.Enums.Label.KILL_COUNT.getText() + character.getEliminations(), skin);
        Label timeLabel = new Label(String.format(com.tildawn.Enums.Label.TIME_SURVIVED.toString() +" %.1f"+" seconds", timeSurvived), skin);



        table.add(titleLabel).colspan(2).center().padBottom(40);
        table.row();
        table.add(usernameLabel).left().padBottom(10);
        table.row();
        table.add(scoreLabel).left().padBottom(10);
        table.row();
        table.add(killsLabel).left().padBottom(10);
        table.row();
        table.add(timeLabel).left().padBottom(30);
        table.row();
        table.add(backButton).center().padTop(30).width(250).height(70);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1); // Clear black
        stage.act(Math.min(delta, 1 / 30f));
        stage.draw();
        controller.handleInput();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
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

    public float getTimeSurvived() {
        return timeSurvived;
    }

    public void setTimeSurvived(float timeSurvived) {
        this.timeSurvived = timeSurvived;
    }

    public EndGameController getController() {
        return controller;
    }

    public void setController(EndGameController controller) {
        this.controller = controller;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public void setBackButton(TextButton backButton) {
        this.backButton = backButton;
    }
}
