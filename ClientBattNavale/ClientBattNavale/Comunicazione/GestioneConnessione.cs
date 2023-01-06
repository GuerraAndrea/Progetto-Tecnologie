using ClientBattNavale.Logica;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows;

namespace ClientBattNavale.Comunicazione
{
    public class GestioneConnessione
    {
        private StreamWriter sw;
        private StreamReader sr;
        private bool termina;
        private Queue<Messaggio> DaInviare;
        private Queue<Messaggio> DaElaborare;
        private object synElabora, synInvia;
        private object synTerm;

        public bool Termina { get { lock (synTerm) { return termina; } } set { lock (synTerm) { termina = value; } } }
        public GestioneConnessione(TcpClient c)
        {
            synInvia = new object();
            synElabora = new object();
            synTerm = new object();
            sw = new StreamWriter(c.GetStream());
            sr = new StreamReader(c.GetStream());
            termina = false;
            DaInviare = new Queue<Messaggio>();
            DaElaborare = new Queue<Messaggio>();
            Thread client = new Thread(GClient);
            Thread Server = new Thread(GServer);
            Thread Logica = new Thread(GLogica);
            client.Start();
            Server.Start();
            Logica.Start();
        }
       
        


    }
}
