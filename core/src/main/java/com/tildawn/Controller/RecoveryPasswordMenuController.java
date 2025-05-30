package com.tildawn.Controller;

import com.tildawn.Enums.Message;
import com.tildawn.Enums.SFX;
import com.tildawn.Main;
import com.tildawn.Model.GameAssetManager;
import com.tildawn.Model.User;
import com.tildawn.View.LoginMenuView;
import com.tildawn.View.RecoveryPasswordMenu;

public class RecoveryPasswordMenuController {
    private RecoveryPasswordMenu view;

    public void setView(RecoveryPasswordMenu view) {
        this.view = view;
    }

    public RecoveryPasswordMenu getView() {
        return view;
    }
    public void handleButtonClicked(User user) {
        if(view!=null) {
            String password=view.getPassword().getText();
            String confirmPassword=view.getConfirmPassword().getText();
            String securityAnswer=view.getSecurityAnswer();
            if(view.getBackButton().isChecked()){
                SFX.CLICK_BUTTON.play();
                view.getBackButton().setChecked(false);
                Main.getMain().setScreen(new LoginMenuView(new LoginMenuController()
                    , GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getSubmitButton().isChecked()) {
                SFX.CLICK_BUTTON.play();
                view.getSubmitButton().setChecked(false);
                if(password.isEmpty() || confirmPassword.isEmpty() || securityAnswer.isEmpty()) {
                    view.setErrorMessage(Message.PLEASE_FILL_ALL_FIELDS.toString());
                    return;
                }
                if(!user.getSecurityAnswer().equals(securityAnswer)) {
                    view.setErrorMessage(Message.SECURITY_ANSWER_WRONG.toString());
                    return;
                }
                if(!password.equals(confirmPassword)) {
                    view.setErrorMessage(Message.PASSWORDS_DO_NOT_MATCH.toString());
                    return;
                }
                if(!SignupMenuController.isStrongPassword(password)) {
                    view.setErrorMessage(Message.WEAK_PASSWORD.toString());
                    return;
                }
                user.setPassword(password);
                Main.getMain().getApp().getSaving().saveUserToJson(user);

                Main.getMain().getApp().getDatabaseManager().updateUser(user);

                Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(), GameAssetManager.getGameAssetManager().getSkin()));

            }
        }
    }
}
