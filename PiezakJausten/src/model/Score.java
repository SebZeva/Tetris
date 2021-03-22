package model;


import java.time.LocalDateTime;
import java.sql.Timestamp;


/**
 *
 * @author SebZeva
 */
public class Score
        implements Comparable<Score>
{

    public final int puntuak;
    public final String izena;
    public final LocalDateTime denbora;

    public Score(int puntuak, String izena)
    {
        this.puntuak = puntuak;
        this.izena = izena;
        denbora = LocalDateTime.now();
    }

    @Override
    public String toString()
    {
        return String.format("%20s%5d", izena, puntuak);
    }

    @Override
    public int compareTo(Score o)
    {
        if (puntuak > o.puntuak)
        {
            return 1;
        }
        else if (puntuak == o.puntuak)
        {
            return o.denbora.compareTo(denbora);
        }
        else
        {
            return -1;
        }
    }

}
