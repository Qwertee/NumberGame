import java.util.Comparator;

/**
 * Created by jon on 4/18/16.
 */
public class NumberGamePieceCompare implements Comparator<NumberGamePiece> {
    @Override
    public int compare(NumberGamePiece o1, NumberGamePiece o2) {
        return o1.compareTo(o2);
    }
}
