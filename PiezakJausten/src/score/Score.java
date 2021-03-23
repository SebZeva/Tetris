package score;


import java.time.Instant;


/**
 *
 * @author SebZeva
 */
public final class Score
        implements Comparable<Score>
{

    public final long puntuak;
    public final String izena;
    public final long denbora;

    public Score(String izena, long puntuak)
    {
        this.puntuak = puntuak;
        this.izena = izena;
        denbora = Instant.now().getEpochSecond();
    }

    public Score(String izena, long puntuak, long denbora)
    {
        this.puntuak = puntuak;
        this.izena = izena;
        this.denbora = denbora;
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
            return -1;
        }
        else if (puntuak == o.puntuak)
        {
            if (o.denbora > denbora)
            {
                return -1;
            }
            else if (o.denbora == denbora)
            {
                return 0;
            }
            else
            {
                return 1;
            }
        }
        else
        {
            return 1;
        }
    }

}
