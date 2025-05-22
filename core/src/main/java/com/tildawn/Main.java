package com.tildawn;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tildawn.Controller.MainMenuController;
import com.tildawn.Controller.PreGameMenuController;
import com.tildawn.Controller.SignupMenuController;
import com.tildawn.Model.App;
import com.tildawn.Model.GameAssetManager;
import com.tildawn.Model.Language.LanguageSetting;
import com.tildawn.View.MainMenuView;
import com.tildawn.View.PreGameMenuView;
import com.tildawn.View.SignupMenuView;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static Main main;
    private static SpriteBatch batch;
    private Texture image;
    private  App app;

    @Override
    public void create() {
        main=this;
        batch = new SpriteBatch();
        app = new App();
        app.setCurrentLanguage(new LanguageSetting());
        app.run();
        Main.getMain().setScreen(new SignupMenuView(new SignupMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }

    public static Main getMain() {
        return main;
    }

    public static void setMain(Main main) {
        Main.main = main;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    public static void setBatch(SpriteBatch batch) {
        Main.batch = batch;
    }

    public Texture getImage() {
        return image;
    }

    public void setImage(Texture image) {
        this.image = image;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }
}
