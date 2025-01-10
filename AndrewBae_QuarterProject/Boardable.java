/**
 * Write a description of interface Boardable here.
 *
 * @author (Andrew Bae)
 * @version (9/27/24)
 */
public interface Boardable {
    void setBoardStart(int month, int day, int year) throws IllegalDateException;
    
    void setBoardEnd(int month, int day, int year) throws IllegalDateException;
    
    boolean boarding(int month, int day, int year);
}


