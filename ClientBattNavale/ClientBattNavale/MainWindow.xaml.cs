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
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        DatiCondivisi condi;
        Client c;
        public int valueImage { get; set; }
        public Uri sourceOfTheImage { get; set; }
        Random rand = new Random();
        public MainWindow(DatiCondivisi condi,Client c)
        {
            sourceOfTheImage = new Uri(".jpg", UriKind.Relative);
            InitializeComponent();
            valueImage = 1;
            this.condi = condi;
            this.c = c;
        }
        public void closing()
        {
            Dispatcher.Invoke(() =>
            {
                if (txtNome.Text != "" && txtNome.Text != null)
                {
                    condi.Utente = txtNome.Text;
                    condi.sourceOfTheImage = sourceOfTheImage;
                    this.Close();
                }
                else
                {
                    MessageBox.Show("Invalid username", "BATTAGLIA NAVALE");
                    condi.aCaso = false;
                }
            });

        }

        private void btnPartita_Click(object sender, RoutedEventArgs e)
        {
            if (txtNome.Text == "" && txtNome.Text != null)
            {
                MessageBox.Show("Invalid username", "BATTAGLIA NAVALE");
            }

           

            
        }
    }
}
