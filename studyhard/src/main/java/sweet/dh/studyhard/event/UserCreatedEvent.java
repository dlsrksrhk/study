package sweet.dh.studyhard.event;

public class UserCreatedEvent {
    private final Long userId;
    private final String userName;

    public UserCreatedEvent(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
}