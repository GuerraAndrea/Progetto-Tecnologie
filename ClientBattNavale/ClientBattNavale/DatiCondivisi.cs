using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ClientBattNavale
{
    public class DatiCondivisi
    {
        public Uri sourceOfTheImage { get; set; }
        public bool aCaso { get; set; }
        public string Utente { get; set; }


        public DatiCondivisi() 
        {
            Utente = "";
            aCaso= true;
        }

    }
}
