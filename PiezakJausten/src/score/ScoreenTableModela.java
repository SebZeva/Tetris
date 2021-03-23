/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package score;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author SebZeva
 */
public class ScoreenTableModela extends AbstractTableModel
{
    private ArrayList<Score> alscr = null;

    @Override
    public int getRowCount()
    {
        if (alscr == null)
        {
            alscr = SQLiteKudeatu.irakurri();
            if (alscr == null)
            {
                return 0;
            }
        }
        return alscr.size();
    }

    @Override
    public int getColumnCount()
    {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        if (alscr == null)
        {
            alscr = SQLiteKudeatu.irakurri();
            if (alscr == null)
            {
                return null;
            }
        }
        Score scr = alscr.get(rowIndex);
        if (columnIndex == 1)
        {
            return scr.izena;
        }
        else
        {
            return scr.puntuak;
        }
    }
    
    @Override
    public String getColumnName(int column)
    {
        if (column == 1)
        {
            return "Izenak";
        }
        else
        {
            return "Puntuak";
        }
    }
}
