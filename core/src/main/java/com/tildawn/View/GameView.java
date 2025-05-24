package com.tildawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tildawn.Controller.GameController;
import com.tildawn.Main;
import com.tildawn.Model.Character;

public class GameView implements Screen, InputProcessor {

    private Stage stage;
    private final GameController controller;
    private Table tableTop;
    private Table tableBottom;
    private Label messageLabel;
    private Label healthLabel;
    private Label ammoLabel;
    private Label killsLabel;
    private Label timeLabel;
    private Label levelLabel;
    private ProgressBar xpProgressBar;
    private Character player;
    private OrthographicCamera camera;
    private Texture bgTexture;
    private Texture playerBgTexture;
    private Sprite lightSprite;
    private Timer.Task clearErrorTask;

    private long startTimeMillis;

    public GameView(GameController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);
        player = controller.getPlayerController().getPlayer();
        Main.getMain().getApp().getCurrentGame().setGameView(this);
    }

    @Override
    public void show() {

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage = new Stage(new ScreenViewport());
        playerBgTexture = new Texture(Gdx.files.internal("Texture2D/lightmask.png"));
        bgTexture = new Texture(Gdx.files.internal("backgrounds/Mainbackground.png"));
        bgTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        lightSprite = new Sprite(playerBgTexture);
        lightSprite.setSize(30f, 30f);
        lightSprite.setOriginCenter();
        BitmapFont font = new BitmapFont();
        healthLabel = new Label("Health: 100", new Label.LabelStyle(font, Color.RED));
        ammoLabel = new Label("Ammo:", new Label.LabelStyle(font, Color.WHITE));
        killsLabel = new Label("Kills:", new Label.LabelStyle(font, Color.WHITE));
        timeLabel = new Label("Time:", new Label.LabelStyle(font, Color.WHITE));
        levelLabel = new Label("Level: 1", new Label.LabelStyle(font, Color.GOLD));
        messageLabel=new Label("",new Label.LabelStyle(font, Color.GREEN));


        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle();

        Pixmap bgPixmap = new Pixmap(200, 20, Pixmap.Format.RGBA8888);
        bgPixmap.setColor(Color.DARK_GRAY);
        bgPixmap.fill();
        progressBarStyle.background = new TextureRegionDrawable(new Texture(bgPixmap));

        Pixmap knobPixmap = new Pixmap(1, 20, Pixmap.Format.RGBA8888);
        knobPixmap.setColor(Color.GREEN);
        knobPixmap.fill();
        progressBarStyle.knobBefore = new TextureRegionDrawable(new Texture(knobPixmap));

        xpProgressBar = new ProgressBar(0f, (float) player.xpToNextLevel(player.getLevel()), 1f, false, progressBarStyle);

        tableTop = new Table();
        tableTop.top().left();
        tableTop.setFillParent(true);
        tableTop.add(healthLabel).pad(10).padRight(25).left();
        healthLabel.setText("HP: " + player.getHp());
        tableTop.add(ammoLabel).pad(10).padRight(50).left();
        tableTop.add(killsLabel).pad(10).padRight(50).left();
        tableTop.add(levelLabel).pad(10).padRight(50).left();
        tableTop.add(xpProgressBar).pad(10).padRight(50).left();
        tableTop.add(messageLabel).pad(10).padRight(50).left();
        tableTop.add(timeLabel).pad(10).expandX().right();

        stage.addActor(tableTop);

        ammoLabel.setText("Ammo: " + player.getWeapon().getAmmo());
        killsLabel.setText("Kills: " + player.getEliminations());
        levelLabel.setText("Level: " + player.getLevel());
        xpProgressBar.setValue(player.getXp() - player.maxLevelXp());

        startTimeMillis = System.currentTimeMillis();

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(this);
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        System.out.println(player.getPosX());
        System.out.println(player.getPosY());
        ScreenUtils.clear(0, 0, 0, 1);
        lightSprite.setCenter(player.getPosX(), player.getPosY());
        ammoLabel.setText(com.tildawn.Enums.Label.AMMO.getText() + player.getWeapon().getAmmo());
        killsLabel.setText(com.tildawn.Enums.Label.KILLS.getText() + player.getEliminations());
        healthLabel.setText(com.tildawn.Enums.Label.HP.getText() + player.getHp());
        levelLabel.setText(com.tildawn.Enums.Label.LEVEL.getText() + player.getLevel());
        xpProgressBar.setRange(0, player.xpToNextLevel(player.getLevel()));
        xpProgressBar.setValue(player.getXp() - player.maxLevelXp());
        camera.update();

        Main.getBatch().setProjectionMatrix(camera.combined);
      Main.getBatch().begin();

        float camX = camera.position.x;
        float camY = camera.position.y;

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        Main.getBatch().draw(
            bgTexture,
            camX - width / 2f, camY - height / 2f,
            width, height,
            0, 0,
            width / bgTexture.getWidth(), height / bgTexture.getHeight()
        );
        lightSprite.draw(Main.getBatch());

        controller.updateGame();
        lightSprite.setCenter(player.getPosX(), player.getPosY());
        lightSprite.draw(Main.getBatch());
        Main.getBatch().end();


        int totalGameTimeInSeconds = Main.getMain().getApp().getCurrentGame().getGameTime() * 60;
        int elapsedSeconds = (int)((System.currentTimeMillis() - startTimeMillis) / 1000);
        int remainingSeconds = Math.max(0, totalGameTimeInSeconds - elapsedSeconds);

        int minutes = remainingSeconds / 60;
        int seconds = remainingSeconds % 60;

        timeLabel.setText(String.format(com.tildawn.Enums.Label.TIME +" %02d:%02d", minutes, seconds));

        if (remainingSeconds == 0) {
            //controller.endGame();
        }
        stage.act(Math.min(delta, 1 / 30f));
        stage.draw();
    }
    public void setErrorMessage(String error) {
        messageLabel.setText(error);

        if (clearErrorTask != null) {
            clearErrorTask.cancel();
        }

        if (!error.isEmpty()) {
            clearErrorTask = new Timer.Task() {
                @Override
                public void run() {
                    messageLabel.setText("");
                }
            };
            Timer.schedule(clearErrorTask, 2);
        }
    }
    @Override public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }
    public void increaseStartTimeMillis() {

        startTimeMillis-=60000;

    }
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        bgTexture.dispose();
    }

    @Override public boolean keyDown(int keycode) { return false; }
    @Override public boolean keyUp(int keycode) { return false; }
    @Override public boolean keyTyped(char character) { return false; }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        controller.getWeaponController().handleWeaponShoot(screenX, screenY);
        return false;
    }

    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }
    @Override public boolean touchCancelled(int screenX, int screenY, int pointer, int button) { return false; }
    @Override public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        controller.getWeaponController().handleWeaponRotation(screenX, screenY);
        return false;
    }

    @Override public boolean scrolled(float amountX, float amountY) { return false; }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public GameController getController() {
        return controller;
    }

    public Table getTableTop() {
        return tableTop;
    }

    public void setTableTop(Table tableTop) {
        this.tableTop = tableTop;
    }

    public Table getTableBottom() {
        return tableBottom;
    }

    public void setTableBottom(Table tableBottom) {
        this.tableBottom = tableBottom;
    }

    public Label getMessageLabel() {
        return messageLabel;
    }

    public void setMessageLabel(Label messageLabel) {
        this.messageLabel = messageLabel;
    }

    public Label getHealthLabel() {
        return healthLabel;
    }

    public void setHealthLabel(Label healthLabel) {
        this.healthLabel = healthLabel;
    }

    public Label getAmmoLabel() {
        return ammoLabel;
    }

    public void setAmmoLabel(Label ammoLabel) {
        this.ammoLabel = ammoLabel;
    }

    public Label getKillsLabel() {
        return killsLabel;
    }

    public void setKillsLabel(Label killsLabel) {
        this.killsLabel = killsLabel;
    }

    public Label getTimeLabel() {
        return timeLabel;
    }

    public void setTimeLabel(Label timeLabel) {
        this.timeLabel = timeLabel;
    }

    public Label getLevelLabel() {
        return levelLabel;
    }

    public void setLevelLabel(Label levelLabel) {
        this.levelLabel = levelLabel;
    }

    public ProgressBar getXpProgressBar() {
        return xpProgressBar;
    }

    public void setXpProgressBar(ProgressBar xpProgressBar) {
        this.xpProgressBar = xpProgressBar;
    }

    public Character getPlayer() {
        return player;
    }

    public void setPlayer(Character player) {
        this.player = player;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public Texture getBgTexture() {
        return bgTexture;
    }

    public void setBgTexture(Texture bgTexture) {
        this.bgTexture = bgTexture;
    }

    public Texture getPlayerBgTexture() {
        return playerBgTexture;
    }

    public void setPlayerBgTexture(Texture playerBgTexture) {
        this.playerBgTexture = playerBgTexture;
    }

    public Sprite getLightSprite() {
        return lightSprite;
    }

    public void setLightSprite(Sprite lightSprite) {
        this.lightSprite = lightSprite;
    }

    public Timer.Task getClearErrorTask() {
        return clearErrorTask;
    }

    public void setClearErrorTask(Timer.Task clearErrorTask) {
        this.clearErrorTask = clearErrorTask;
    }

    public long getStartTimeMillis() {
        return startTimeMillis;
    }

    public void setStartTimeMillis(long startTimeMillis) {
        this.startTimeMillis = startTimeMillis;
    }
}
