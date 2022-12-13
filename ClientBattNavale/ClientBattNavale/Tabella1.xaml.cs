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
using System.Windows.Shapes;

namespace ClientBattNavale
{
    /// <summary>
    /// Logica di interazione per Tabella1.xaml
    /// </summary>
    public partial class Tabella1 : Window
    {
        public Tabella1()
        {
            InitializeComponent();
        }

        private void buttFine_Click(object sender, RoutedEventArgs e)
        {
            string messageBoxText = "Sicuro di voler salvare le modifiche?";
            string caption = "Salvataggio Navi";
            MessageBoxButton button = MessageBoxButton.YesNoCancel;         //messaggio per salvataggio delle modifiche
            MessageBoxImage icon = MessageBoxImage.Warning;
            MessageBoxResult result;

            result = MessageBox.Show(messageBoxText, caption, button, icon, MessageBoxResult.Yes);

            switch (result)
            {
                case MessageBoxResult.Cancel:
                    // torno alla pagina corrente
                    break;
                case MessageBoxResult.Yes:
                    // passaggio a finestra successiva
                   

                    var Attacco = new Attacco();
                    //Attacco.Owner = this;
                    
                    Attacco.Show();
                    this.Close();

                    





                    break;
                case MessageBoxResult.No:
                    // torno alla pagina corrente
                    break;
            }
        }
    }
}
