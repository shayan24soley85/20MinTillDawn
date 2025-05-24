package com.tildawn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.tildawn.Controller.SignupMenuController;
import com.tildawn.Enums.SFX;
import com.tildawn.Model.App;
import com.tildawn.Model.GameAssetManager;
import com.tildawn.Model.Language.LanguageSetting;
import com.tildawn.View.SignupMenuView;

/** Main game class shared across all platforms. */
public class Main extends Game {
    private static Main main;
    private static SpriteBatch batch;
    private App app;
    private ShaderProgram grayscaleShader;

    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();


        ShaderProgram.pedantic = false;
        grayscaleShader = new ShaderProgram(
            Gdx.files.internal("default.vert.txt"),
            Gdx.files.internal("grayscale.frag.txt")
        );
//        if (!grayscaleShader.isCompiled()) {
//            Gdx.app.error("Shader", "Shader compilation failed:\n" + grayscaleShader.getLog());
//        }


        app = new App();
        app.setCurrentLanguage(new LanguageSetting());
        app.run();


        Pixmap pixmap = new Pixmap(Gdx.files.internal("corsur.png"));
        Cursor customCursor = Gdx.graphics.newCursor(pixmap, 0, 0);
        Gdx.graphics.setCursor(customCursor);
        pixmap.dispose();


        for (SFX sfx : SFX.values()) {
            sfx.load();
        }


        setScreen(new SignupMenuView(new SignupMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
    }

    @Override
    public void dispose() {
        batch.dispose();
        if (grayscaleShader != null) grayscaleShader.dispose();
    }



    public static Main getMain() {
        return main;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    public App getApp() {
        return app;
    }

    public ShaderProgram getGrayscaleShader() {
        return grayscaleShader;
    }
}
