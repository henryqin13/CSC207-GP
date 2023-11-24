package interface_adapter.Login;

import use_case.Login.LoginInputData;
import use_case.Login.LoginInputBoundary;
import use_case.Signup.SignupInputBoundary;
import use_case.Signup.SignupInputData;

public class LoginController {

    final LoginInputBoundary loginUseCaseInteractor;
    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }


    public void execute(String username, String password) {
        LoginInputData loginInputData = new LoginInputData(username, password);

        loginUseCaseInteractor.execute(loginInputData);
    }
}
