import java.util.Objects;

public class Ball {

    private String type;

    private String colour;

    private String description;

    public Ball(String type, String colour, String description) {
        this.type = type;
        this.colour = colour;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getColour() {
        return colour;
    }

    public String getDescription() {
        return description;
    }

    public void stringWhenDrawn() {
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Ball ball = (Ball) other;
        return Objects.equals(type, ball.type) && Objects.equals(colour, ball.colour)
                && Objects.equals(description, ball.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, colour, description);
    }
}
