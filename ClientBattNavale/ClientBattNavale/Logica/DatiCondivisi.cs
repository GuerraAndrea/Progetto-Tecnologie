using ClientBattNavale.Comunicazione;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;

namespace ClientBattNavale.Logica
{
    class DatiCondivisi
    {
        public static DatiCondivisi instance = null;
        public GestioneConnessione avversario;
        public Client main;
        public Mappa mappa;
        private DatiCondivisi()
        {
            TcpClient c = new TcpClient("192.168.1.2", 12345);
            avversario = new GestioneConnessione(c);
            mappa = new Mappa();
        }
        public static DatiCondivisi Init()
        {
            if (instance == null)
                instance = new DatiCondivisi();
            return instance;
        }
    }
}
