package Scripts.Interfaces;

public interface IPoolable {
    void reset();
    boolean isActive();
    void setActive(boolean active);
}
