using ClientBattNavale.Comunicazione;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ClientBattNavale.Logica
{
    class Mappa
    {
        public int[,] attacchi;
        public int[,] mappa;
        public bool turno;
        public Mappa()
        {
            mappa = new int[10, 10];
            attacchi = new int[10, 10];
        }
        public bool aggiungiNave(Coordinate inizio, Coordinate fine, int celle)
        {
            if (inizio.Riga == fine.Riga)
            {
                if (Math.Abs(inizio.getNumeroColonna() - fine.getNumeroColonna()) != celle - 1)
                    return false;
                for (int i = Math.Min(inizio.getNumeroColonna(), fine.getNumeroColonna()); i < Math.Max(inizio.getNumeroColonna(), fine.getNumeroColonna()); i++)
                    if (mappa[inizio.Riga - 1, i] != 0)
                        return false;
                for (int i = Math.Min(inizio.getNumeroColonna(), fine.getNumeroColonna()); i <= Math.Max(inizio.getNumeroColonna(), fine.getNumeroColonna()); i++)
                    mappa[inizio.Riga - 1, i] = 1;
            }
            else if (inizio.Colonna == fine.Colonna)
            {
                if (Math.Abs(inizio.Riga - fine.Riga) != celle - 1)
                    return false;
                for (int i = Math.Min(inizio.Riga, fine.Riga) - 1; i < Math.Max(inizio.Riga, fine.Riga); i++)
                    if (mappa[i, inizio.getNumeroColonna()] != 0)
                        return false;
                for (int i = Math.Min(inizio.Riga, fine.Riga) - 1; i < Math.Max(inizio.Riga, fine.Riga); i++)
                    mappa[i, inizio.getNumeroColonna()] = 1;
            }
            DatiCondivisi.Init().avversario.Invia(new Messaggio("P", inizio.toCSV() + ";" + fine.toCSV()));
            return true;
        }
    }
}
