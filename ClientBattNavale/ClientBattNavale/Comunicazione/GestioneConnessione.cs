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

        private void Elabora(Messaggio m)
        {
            string[] split;
            switch (m.scelta)
            {
                case "c":
                    DatiCondivisi.Init().main.MostraInitGioco();
                    break;
                case "A":
                    split = m.dati.Split(';');
                    DatiCondivisi.Init().mappa.SubisciAttaccoNave(new Coordinate(split[1][0], int.Parse(split[0])));
                    DatiCondivisi.Init().main.AggiornaMappa();
                    break;
                case "RA":
                    split = m.dati.Split(';');
                    switch (split[0])
                    {
                        case "0":
                            DatiCondivisi.Init().mappa.SubisciAttaccoNave(new Coordinate(split[1][0], int.Parse(split[0])));
                            break;
                        case "1":
                            DatiCondivisi.Init().mappa.SubisciAttaccoNave(new Coordinate(split[1][0], int.Parse(split[0])));
                            MessageBox.Show("Hai colpito una nave");
                            break;
                        case "2":
                            DatiCondivisi.Init().mappa.NaveAbbattuta(new Coordinate(split[2][0], int.Parse(split[1])), new Coordinate(split[4][0], int.Parse(split[3])));
                            MessageBox.Show("Hai abbattuto una nave");
                            break;
                        case "3":
                            MessageBox.Show("Hai vinto!!");
                            DatiCondivisi.Init().main.MostraConnessione();
                            break;
                    }
                    DatiCondivisi.Init().main.AggiornaAttacchi();
                    Coordinate c = new Coordinate(split[1][0], int.Parse(split[0]));
                    DatiCondivisi.Init().mappa.SubisciAttaccoNave(c);
                    break;
                case "t":
                    DatiCondivisi.Init().mappa.turno = !DatiCondivisi.Init().mappa.turno;
                    break;
            }
        }


    }
}
