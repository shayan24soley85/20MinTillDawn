package com.tildawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tildawn.Controller.changeAvatarMenuController;
import com.tildawn.Enums.Avatar;

public class ChangeAvatarMenuView implements Screen {
    private Stage stage;
    private Skin skin;
    private Image avatarPreview;
    private changeAvatarMenuController changeAvatarMenuController;

    public ChangeAvatarMenuView(changeAvatarMenuController controller,Skin skin) {
        this.skin = skin;
        this.changeAvatarMenuController = controller;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table root = new Table();
        root.setFillParent(true);

        Label title = new Label("Select Your Avatar", skin);
        root.top().add(title).pad(10).row();

        HorizontalGroup avatarGroup = new HorizontalGroup();
        avatarGroup.space(10);

        // برای اسکرول لیست آواتارها
        for (Avatar avatar : Avatar.values()) {
            Texture tex = new Texture(Gdx.files.internal(avatar.getPath()));
            Image avatarImage = new Image(tex);
            avatarImage.setSize(64, 64);
            avatarImage.setScaling(com.badlogic.gdx.utils.Scaling.fit);

            avatarImage.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    setAvatar(avatar);
                }
            });

            avatarGroup.addActor(avatarImage);
        }

        ScrollPane scrollPane = new ScrollPane(avatarGroup, skin);
        scrollPane.setScrollingDisabled(false, true);
        scrollPane.setFadeScrollBars(false);
        root.add(scrollPane).height(80).expandX().fillX().pad(10).row();

        // پیش نمایش آواتار انتخاب شده
        avatarPreview = new Image(new Texture(Gdx.files.internal(Avatar.AVATAR_1.getPath())));
        avatarPreview.setSize(128, 128);
        avatarPreview.setScaling(com.badlogic.gdx.utils.Scaling.fit);

        root.add(new Label("Preview:", skin)).padTop(20).row();
        root.add(avatarPreview).size(128, 128).padBottom(20);

        stage.addActor(root);
    }

    private void setAvatar(Avatar avatar) {
        // تغییر تصویر پیش نمایش
        avatarPreview.setDrawable(new com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable(
            new com.badlogic.gdx.graphics.g2d.TextureRegion(new Texture(Gdx.files.internal(avatar.getPath())))
        ));

        // اینجا می‌تونی id آواتار انتخاب شده رو ذخیره کنی، مثلاً:
        // currentUser.setAvatarId(avatar.getId());
        System.out.println("Selected Avatar ID: " + avatar.getId());
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
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
    }
}
