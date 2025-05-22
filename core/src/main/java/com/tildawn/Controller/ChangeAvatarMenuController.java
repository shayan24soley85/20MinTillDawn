package com.tildawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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
        enableDragAndDrop();
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

    // فعال کردن Drag and Drop روی پنجره LibGDX
    private void enableDragAndDrop() {
        SwingUtilities.invokeLater(() -> {
            java.awt.Window[] windows = java.awt.Window.getWindows();
            for (java.awt.Window window : windows) {
                if (window.isVisible() && window instanceof java.awt.Frame) {
                    try {
                        // اعمال DropTarget به پنجره بازی
                        window.setDropTarget(new DropTarget() {
                            @Override
                            public synchronized void drop(DropTargetDropEvent dtde) {
                                try {
                                    dtde.acceptDrop(DnDConstants.ACTION_COPY);
                                    java.util.List<File> droppedFiles = (java.util.List<File>)
                                        dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);

                                    if (!droppedFiles.isEmpty()) {
                                        File file = droppedFiles.get(0);
                                        String fileName = file.getName().toLowerCase();
                                        if (fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
                                            Gdx.app.postRunnable(() -> loadAvatarFromFile(file));
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Only image files are supported (.png, .jpg, .jpeg)");
                                        }
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        });
    }

    // بارگذاری عکس آواتار از فایل
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

    // مدیریت رویدادهای مختلف در منوی تغییر آواتار
    public void handleChangeAvatarMenu() {
        if (view != null) {
            if (view.getBackBtn().isChecked()) {
                view.getBackBtn().setChecked(false);
                Main.getMain().setScreen(new ProfileMenuView(
                    new ProfileMenuController(),
                    GameAssetManager.getGameAssetManager().getSkin()
                ));
                return;
            } else if (view.getConfirmBtn().isChecked()) {
                view.getConfirmBtn().setChecked(false);
                if (view.getAvatarPath().equals(view.getUser().getAvatarPath())) {
                    view.setErrorMessage("you have not choose an avatar yet");
                    return;
                }
                view.setSuccessMessage("you have successfully changed your avatar");
                view.getUser().setAvatarPath(view.getAvatarPath());
                Main.getMain().getApp().getSaving().saveUserToJson(view.getUser());
            } else if (view.getSelectFileBtn().isChecked()) {
                view.getSelectFileBtn().setChecked(false);
                openFileChooser();
            }
        }
    }
}
