using ClientBattNavale.Logica;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace ClientBattNavale
{
    /// <summary>
    /// Logica di interazione per Client.xaml
    /// </summary>
    public partial class Client : Window
    {
        public Button[,] buttons = new Button[10, 10], attacchi = new Button[10, 10];
        static int[] navi = { 4, 3, 3, 2, 2, 2, 1, 1, 1, 1 };
        int naviInserite;
        public Client()
        {
            InitializeComponent();
            DatiCondivisi.Init().main = this;
        }
        public void InitPosizioneNavi()
        {

            naviInserite = 0;
            TipoNave.Content = "Inserire una nave di " + navi[naviInserite] + " celle";
            for (int i = 0; i < 10; i++)
            {
                for (int j = 0; j < 10; j++)
                {
                    Button b = new Button();
                    b.Width = InitGrigliaNavi.Width / 10;
                    b.Height = InitGrigliaNavi.Height / 10;
                    b.Margin = new Thickness(j * InitGrigliaNavi.Width / 10, i * InitGrigliaNavi.Height / 10, (9 - j) * InitGrigliaNavi.Width / 10, (9 - i) * InitGrigliaNavi.Width / 10);
                    b.Click += B_Click;
                    b.Name = "B" + (i + 1) + "_" + j;
                    buttons[i, j] = b;
                    InitGrigliaNavi.Children.Add(b);
                }
            }
        }
        private void B_Click(object sender, RoutedEventArgs e)
        {
            string[] numero = ((Button)e.Source).Name.Substring(1).Split('_');
            if (txtLettera.Text == "")
            {
                txtLettera.Text = (char)(int.Parse(numero[1]) + 'A') + "";
                txtNumero.Text = int.Parse(numero[0]) + "";
            }
            else
            {
                txtLetteraTermine.Text = (char)(int.Parse(numero[1]) + 'A') + "";
                txtNumeroTermine.Text = int.Parse(numero[0]) + "";
            }
        }
        private void Button_Click(object sender, RoutedEventArgs e)
        {
            if (txtLettera.Text != "" && txtLetteraTermine.Text != "")
            {
                if (DatiCondivisi.Init().mappa.aggiungiNave(new Coordinate(txtLettera.Text[0], int.Parse(txtNumero.Text)), new Coordinate(txtLetteraTermine.Text[0], int.Parse(txtNumeroTermine.Text)), navi[naviInserite]))
                {
                    naviInserite++;
                    if (naviInserite < navi.Length)
                        TipoNave.Content = "Inserire una nave di " + navi[naviInserite] + " celle";
                    else
                        MostraGioco();
                    txtLettera.Text = "";
                    txtLetteraTermine.Text = "";
                    txtNumero.Text = "";
                    txtNumeroTermine.Text = "";
                    AggiornaMappa();
                }
                else
                    MessageBox.Show("Errore");
            }
        }
        public void MostraInitGioco()
        {
            Dispatcher.Invoke(delegate
            {
                InitGioco.Visibility = Visibility.Visible;
                GridGioco.Visibility = Visibility.Hidden;
                GridConnessione.Visibility = Visibility.Hidden;
                InitPosizioneNavi();
            });
        }
        public void MostraConnessione()
        {
            Dispatcher.Invoke(delegate
            {
                InitGioco.Visibility = Visibility.Hidden;
                GridGioco.Visibility = Visibility.Hidden;
                GridConnessione.Visibility = Visibility.Visible;
            });
        }

        public void MostraGioco()
        {
            Dispatcher.Invoke(delegate
            {
                InitGioco.Visibility = Visibility.Hidden;
                GridGioco.Visibility = Visibility.Visible;
                GridConnessione.Visibility = Visibility.Hidden;
                MostraMappaGioco();
            });
        }
        public void AggiornaMappa()
        {
            Dispatcher.Invoke(delegate
            {
                for (int i = 0; i < 10; i++)
                    for (int j = 0; j < 10; j++)
                        if (DatiCondivisi.Init().mappa.mappa[i, j] < 0)
                            buttons[i, j].Background = Brushes.Red;
                        else if (DatiCondivisi.Init().mappa.mappa[i, j] > 0)
                            buttons[i, j].Background = Brushes.Black;
            });
        }
        public void AggiornaAttacchi()
        {
            Dispatcher.Invoke(delegate
            {
                for (int i = 0; i < 10; i++)
                    for (int j = 0; j < 10; j++)
                        if (DatiCondivisi.Init().mappa.attacchi[i, j] < 0)
                            attacchi[i, j].Background = Brushes.Red;
                        else if (DatiCondivisi.Init().mappa.attacchi[i, j] > 0)
                            attacchi[i, j].Background = Brushes.Black;
            });
        }
        public void MostraMappaGioco()
        {
            Dispatcher.Invoke(delegate
            {
                for (int i = 0; i < 10; i++)
                    for (int j = 0; j < 10; j++)
                    {
                        InitGrigliaNavi.Children.Remove(buttons[i, j]);
                        MiaMappa.Children.Add(buttons[i, j]);
                        Button b = new Button();
                        b.Width = InitGrigliaNavi.Width / 10;
                        b.Height = InitGrigliaNavi.Height / 10;
                        b.Margin = new Thickness(j * InitGrigliaNavi.Width / 10, i * InitGrigliaNavi.Height / 10, (9 - j) * InitGrigliaNavi.Width / 10, (9 - i) * InitGrigliaNavi.Width / 10);
                        b.Click += BAttacchi_Click;
                        b.Name = "B" + (i + 1) + "_" + j;
                        attacchi[i, j] = b;
                        MappaAttacchi.Children.Add(b);
                    }
            });
        }

    }
}
