package com.tildawn.Controller;

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
                view.getBackButton().setChecked(false);
                Main.getMain().setScreen(new LoginMenuView(new LoginMenuController()
                    , GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getSubmitButton().isChecked()) {
                view.getSubmitButton().setChecked(false);
                if(password.isEmpty() || confirmPassword.isEmpty() || securityAnswer.isEmpty()) {
                    view.setErrorMessage("please fill all the fields");
                    return;
                }
                if(!user.getSecurityAnswer().equals(securityAnswer)) {
                    view.setErrorMessage("security answer doesn't match");
                    return;
                }
                if(!password.equals(confirmPassword)) {
                    view.setErrorMessage("Passwords do not match");
                    return;
                }
                if(!SignupMenuController.isStrongPassword(password)) {
                    view.setErrorMessage("please enter a strong password");
                    return;
                }
                user.setPassword(password);
                Main.getMain().getApp().getSaving().saveUserToJson(user);
                Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(), GameAssetManager.getGameAssetManager().getSkin()));

            }
        }
    }
}
