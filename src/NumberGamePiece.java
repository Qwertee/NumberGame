/**
 * Created by jon on 4/18/16.
 */
public class NumberGamePiece implements Comparable<NumberGamePiece>{
    private int weight, value;
    private int x, y;

    public NumberGamePiece(int weight, int value, int x, int y) {
        this.weight = weight;
        this.value = value;
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(NumberGamePiece p) {
        if (this.getWeight() < p.getWeight()) {
            return 1;
        } else if (this.getWeight() > p.getWeight()) {
            return -1;
        } else {
            return 0;
        }
    }

    public int getWeight() {
        return this.weight;
    }
    public void setWeight(int newWeight) {
        this.weight = newWeight;
    }

    public int getValue() {
        return this.value;
    }
    public void setValue(int newValue)  { this.value = newValue; }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
