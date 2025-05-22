package com.tildawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tildawn.Controller.LoginMenuController;
import com.tildawn.Main;

public class LoginMenuView implements Screen {
    private Stage stage;
    private final TextButton signupButton;
    private final TextButton RecoveryPassword;
    private final TextButton loginButton;
    private final Label gameTitle;
    private final TextField username;
    private final TextField password;
    private final LoginMenuController controller;
    public Table table;
    private final Skin skin;

    private final Label errorLabel;
    private Timer.Task clearErrorTask;

    public LoginMenuView(LoginMenuController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);
        this.skin = skin;

        this.signupButton = new TextButton(com.tildawn.Enums.Label.SIGN_UP.getText(), skin);
        this.RecoveryPassword = new TextButton(com.tildawn.Enums.Label.RECOVERY_PASSWORD.getText(), skin);
        this.RecoveryPassword.getLabel().setFontScale(0.4f);
        this.RecoveryPassword.getLabel().setWrap(true);
        this.loginButton = new TextButton(com.tildawn.Enums.Label.LOGIN.getText(), skin);
        this.gameTitle = new Label(com.tildawn.Enums.Label.LOGIN_MENU.getText(), skin);
        this.gameTitle.setColor(Color.GOLD);
        this.username = new TextField("", skin);
        this.password = new TextField("", skin);

        password.setPasswordCharacter('*');
        password.setPasswordMode(true);

        errorLabel = new Label("", skin);
        errorLabel.setColor(Color.RED);
        errorLabel.setWrap(true);

        this.table = new Table();
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Texture backgroundTexture = new Texture(Gdx.files.internal("backgrounds/29.png"));
        Image backgroundImage = new Image(backgroundTexture);

        backgroundImage.setFillParent(true);

        stage.addActor(backgroundImage);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();

        table.add(gameTitle).colspan(2).pad(10, 200 , 10 , 0);
        table.row().pad(10, 0 , 10 , 0);

        table.add(new Label(com.tildawn.Enums.Label.USERNAME.getText(), skin)).left();
        table.add(username).width(600);
        table.row().pad(10, 0 , 10 , 0);

        table.add(new Label(com.tildawn.Enums.Label.PASSWORD.getText(), skin)).left();
        table.add(password).width(600);
        table.row().pad(10, 0 , 10 , 0);

        Table buttonRow = new Table();
        buttonRow.add(signupButton).width(150).padRight(10);
        buttonRow.add(loginButton).width(150).padRight(10);
        buttonRow.add(RecoveryPassword).width(150);

        table.row().pad(0, 270, 30, 0);
        table.add(buttonRow).colspan(2).center();
        table.row().pad(10, 370, 0, 0);
        table.add(errorLabel).colspan(2).left().width(600);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
       controller.handleButtonClicked();
    }

    public void setErrorMessage(String error) {
        errorLabel.setText(error);

        if (clearErrorTask != null) {
            clearErrorTask.cancel();
        }

        if (!error.isEmpty()) {
            clearErrorTask = new Timer.Task() {
                @Override
                public void run() {
                    errorLabel.setText("");
                }
            };
            Timer.schedule(clearErrorTask, 2);
        }
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override
    public void dispose() {
        stage.dispose();
        if (clearErrorTask != null) clearErrorTask.cancel();
    }




    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public TextButton getSignupButton() {
        return signupButton;
    }

    public TextButton getRecoveryPasswordButton() {
        return RecoveryPassword;
    }

    public TextButton getLoginButton() {
        return loginButton;
    }

    public Label getGameTitle() {
        return gameTitle;
    }

    public TextField getUsername() {
        return username;
    }

    public TextField getPassword() {
        return password;
    }


    public LoginMenuController getController() {
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

    public Label getErrorLabel() {
        return errorLabel;
    }

    public Timer.Task getClearErrorTask() {
        return clearErrorTask;
    }

    public void setClearErrorTask(Timer.Task clearErrorTask) {
        this.clearErrorTask = clearErrorTask;
    }
}
