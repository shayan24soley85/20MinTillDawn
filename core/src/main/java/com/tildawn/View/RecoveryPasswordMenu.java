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
import com.tildawn.Controller.LoginMenuController;
import com.tildawn.Controller.RecoveryPasswordMenuController;
import com.tildawn.Controller.SignupMenuController;
import com.tildawn.Enums.Avatar;
import com.tildawn.Main;
import com.tildawn.Model.User;

public class RecoveryPasswordMenu implements Screen {
    private Stage stage;
    private final TextButton BackButton;
    private final TextButton submitButton;
    private final Label gameTitle;
    private final Label question;
    private final TextField password;
    private final TextField confirmPassword;
    private final TextField securityAnswer;
    private final RecoveryPasswordMenuController controller;
    public Table table;
    private final Skin skin;
    private final User user;
    private final Label errorLabel;
    private Timer.Task clearErrorTask;

    public RecoveryPasswordMenu(RecoveryPasswordMenuController controller, Skin skin,User user) {
        this.controller = controller;
        controller.setView(this);
        this.skin = skin;

        this.BackButton = new TextButton("Back", skin);
        this.submitButton = new TextButton("Submit", skin);
        this.gameTitle = new Label("Recovery password", skin);
        this.question = new Label(user.getSecurityQuestion(), skin);
        this.question.setColor(Color.BLACK);
        this.gameTitle.setColor(Color.GOLD);
        this.password = new TextField("", skin);
        this.confirmPassword = new TextField("", skin);
        this.securityAnswer = new TextField("", skin);
        this.user=user;
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

        Texture backgroundTexture = new Texture(Gdx.files.internal("backgrounds/41.png"));
        Image backgroundImage = new Image(backgroundTexture);

        backgroundImage.setFillParent(true);

        stage.addActor(backgroundImage);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();

        table.add(gameTitle).colspan(2).pad(10, 200 , 10 , 0);
        table.row().pad(10, 0 , 10 , 0);
        table.row();
        table.add(question).colspan(2).pad(10, 200 , 10 , 0);
        table.row().pad(10, 0 , 10 , 0);
        table.row();
        table.add(new Label("New Password:", skin)).left();
        table.add(password).width(600);
        table.row().pad(10, 0 , 10 , 0);

        table.add(new Label("Confirm Password:", skin)).left();
        table.add(confirmPassword).width(600);
        table.row().pad(10, 0 , 10 , 0);


        table.add(new Label("Security Answer:", skin)).left();
        table.add(securityAnswer).width(600);
        table.row().pad(20, 0, 0, 0);

        Table buttonRow = new Table();
        buttonRow.add(BackButton).width(150).padRight(10);
        buttonRow.add(submitButton).width(150).padRight(10);

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
        controller.handleButtonClicked(user);
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



    public String getSecurityAnswer() {
        return securityAnswer.getText();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public TextButton getBackButton() {
        return BackButton;
    }


    public TextButton getSubmitButton() {
        return submitButton;
    }

    public Label getGameTitle() {
        return gameTitle;
    }


    public TextField getPassword() {
        return password;
    }

    public TextField getConfirmPassword() {
        return confirmPassword;
    }


    public RecoveryPasswordMenuController getController() {
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
