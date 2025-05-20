package com.tildawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tildawn.Controller.SignupMenuController;
import com.tildawn.Main;

public class SignupMenuView implements Screen {
    private Stage stage;
    private final TextButton signupButton;
    private final TextButton playAsGuessButton;
    private final TextButton loginButton;
    private final Label gameTitle;
    private final TextField username;
    private final TextField password;
    private final TextField confirmPassword;
    private final SelectBox<String> securityQuestion;
    private final TextField securityAnswer;
    private final SignupMenuController controller;
    public Table table;
    private final Skin skin;

    private final Label errorLabel;
    private Timer.Task clearErrorTask;

    public SignupMenuView(SignupMenuController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);
        this.skin = skin;

        this.signupButton = new TextButton("SignUp", skin);
        this.playAsGuessButton = new TextButton("Guest", skin);
        this.loginButton = new TextButton("Login", skin);
        this.gameTitle = new Label("SIGNUP MENU", skin);
        this.gameTitle.setColor(Color.GOLD);
        this.username = new TextField("", skin);
        this.password = new TextField("", skin);
        this.confirmPassword = new TextField("", skin);

        this.securityQuestion = new SelectBox<>(skin);
        securityQuestion.setItems("whats your favorite food?", "whats your best friend's name?","whats your father's name?","whats your favorite color?");

        this.securityAnswer = new TextField("", skin);

        password.setPasswordCharacter('*');
        password.setPasswordMode(true);
        confirmPassword.setPasswordCharacter('*');
        confirmPassword.setPasswordMode(true);

        errorLabel = new Label("", skin);
        errorLabel.setColor(Color.RED);
        errorLabel.setWrap(true);

        this.table = new Table();
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Texture backgroundTexture = new Texture(Gdx.files.internal("backgrounds/25.png"));
        Image backgroundImage = new Image(backgroundTexture);

        backgroundImage.setFillParent(true);

        stage.addActor(backgroundImage);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();

        table.add(gameTitle).colspan(2).pad(10, 200 , 10 , 0);
        table.row().pad(10, 0 , 10 , 0);

        table.add(new Label("Username:", skin)).left();
        table.add(username).width(600);
        table.row().pad(10, 0 , 10 , 0);

        table.add(new Label("Password:", skin)).left();
        table.add(password).width(600);
        table.row().pad(10, 0 , 10 , 0);

        table.add(new Label("Confirm Password:", skin)).left();
        table.add(confirmPassword).width(600);
        table.row().pad(10, 0 , 10 , 0);

        table.add(new Label("Security Question:", skin)).left();
        table.add(securityQuestion).width(600);
        table.row().pad(10, 0 , 10 , 0);

        table.add(new Label("Security Answer:", skin)).left();
        table.add(securityAnswer).width(600);
        table.row().pad(20, 0, 0, 0);

        Table buttonRow = new Table();
        buttonRow.add(signupButton).width(150).padRight(10);
        buttonRow.add(loginButton).width(150).padRight(10);
        buttonRow.add(playAsGuessButton).width(150);

        table.row().pad(0, 350, 30, 0);
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
        controller.handleSignupMenuButtonClicked();
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


    public String getSelectedSecurityQuestion() {
        return securityQuestion.getSelected();
    }

    public String getSecurityAnswer() {
        return securityAnswer.getText();
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

    public TextButton getPlayAsGuessButton() {
        return playAsGuessButton;
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

    public TextField getConfirmPassword() {
        return confirmPassword;
    }

    public SelectBox<String> getSecurityQuestion() {
        return securityQuestion;
    }

    public SignupMenuController getController() {
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
