package interface_adapter.Guest;


public class GuestState {
    private String guestName = "Guest";

    public GuestState(GuestState guestName) {
        guestName = guestName;
    }
    public GuestState() {}

        // Because of the previous copy constructor, the default constructor must be explicit.

    }


