package com.tildawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.tildawn.Enums.Message;
import com.tildawn.Enums.SFX;
import com.tildawn.Main;
import com.tildawn.Model.GameAssetManager;
import com.tildawn.View.ChangeAvatarMenuView;
import com.tildawn.View.ProfileMenuView;

import javax.swing.*;
import java.awt.dnd.*;
import java.awt.datatransfer.DataFlavor;
import java.io.File;

public class ChangeAvatarMenuController {
    private ChangeAvatarMenuView view;

    public void setView(ChangeAvatarMenuView view) {
        this.view = view;
    }

    public ChangeAvatarMenuView getView() {
        return view;
    }

    private void openFileChooser() {
        SwingUtilities.invokeLater(() -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choosing avatar");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("pictures", "png", "jpg", "jpeg"));

            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                view.setAvatarPath(file.getAbsolutePath());
                Gdx.app.postRunnable(() -> loadAvatarFromFile(file));
            }
        });
    }



    public void loadAvatarFromFile(File file) {
        try {
            if (view.getSelectedTexture() != null) view.getSelectedTexture().dispose();

            view.setSelectedFile(file);
            view.setSelectedTexture(new Texture(Gdx.files.absolute(file.getAbsolutePath())));
            view.getAvatarPreview().setDrawable(new TextureRegionDrawable(new TextureRegion(view.getSelectedTexture())));

            for (Actor actor : view.getStage().getActors()) {
                if (actor instanceof Table) {
                    Table root = (Table) actor;
                    Table buttonsTable = (Table) root.getChildren().get(root.getChildren().size - 1);
                    for (Actor btn : buttonsTable.getChildren()) {
                        if (btn instanceof TextButton) {
                            TextButton tb = (TextButton) btn;
                            if (tb.getText().toString().equals("Accept") || tb.getText().toString().equals("Cancel")) {
                                tb.setVisible(true);
                                if (tb.getText().equals("Accept")) {
                                    view.setAvatarPath(file.getAbsolutePath());
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            Gdx.app.error("ChangeAvatarMenuView", "can not upload the picture", e);
        }
    }

    public void handleChangeAvatarMenu() {
        if (view != null) {
            if (view.getBackBtn().isChecked()) {
                SFX.CLICK_BUTTON.play();
                view.getBackBtn().setChecked(false);
                Main.getMain().setScreen(new ProfileMenuView(
                    new ProfileMenuController(),
                    GameAssetManager.getGameAssetManager().getSkin()
                ));
                return;
            } else if (view.getConfirmBtn().isChecked()) {
                SFX.CLICK_BUTTON.play();

                view.getConfirmBtn().setChecked(false);
                if (view.getAvatarPath().equals(view.getUser().getAvatarPath())) {
                    view.setErrorMessage(Message.AVATAR_NOT_CHOSEN.toString());
                    return;
                }
                view.setSuccessMessage(Message.AVATAR_CHANGED.toString());
                view.getUser().setAvatarPath(view.getAvatarPath());
                Main.getMain().getApp().getSaving().saveUserToJson(view.getUser());

                Main.getMain().getApp().getDatabaseManager().updateUser(view.getUser());

            } else if (view.getSelectFileBtn().isChecked()) {
                SFX.CLICK_BUTTON.play();
                view.getSelectFileBtn().setChecked(false);
                openFileChooser();
            } else if (view.getDragedBtn().isChecked()) {
                SFX.CLICK_BUTTON.play();
                view.getDragedBtn().setChecked(false);
                if (view.getAvatarPath().equals(view.getUser().getAvatarPath())) {
                    view.setErrorMessage(Message.AVATAR_NOT_CHOSEN.toString());
                    return;
                }
                view.setAvatarPath(view.getUser().getAvatarPath());
                view.setSuccessMessage(Message.AVATAR_CHANGED.toString());
                Main.getMain().getApp().getSaving().saveUserToJson(view.getUser());

                Main.getMain().getApp().getDatabaseManager().updateUser(view.getUser());

            }
        }
    }
}
