package use_case.Login;

public interface LoginOutputBoundary {
    void prepareSuccessView(LoginOutputData player);

    void prepareFailView(String error);
}