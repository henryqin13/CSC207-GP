package use_case.Game;

public class GameOutputData {

    private final String username;
    private boolean useCaseFailed;

    public GameOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

}
