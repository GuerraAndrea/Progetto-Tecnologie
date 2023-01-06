using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ClientBattNavale.Logica
{
    class Coordinate
    {
        public char Colonna;
        public int Riga;
        public Coordinate(string csv)
        {
            string[] split = csv.Split(';');
            Colonna = split[0][0];
            Riga = int.Parse(split[1]);
        }
        public Coordinate(char colonna, int riga)
        {
            Colonna = colonna;
            Riga = riga;
        }
        public int getNumeroColonna()
        {
            return Colonna - 'A';
        }
        public string toCSV()
        {
            return Riga + ";" + Colonna;
        }
    }
}
