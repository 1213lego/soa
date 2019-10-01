using bike_project_net_client.vistas;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace bike_project_net_client
{
    public partial class GUIMain : Form
    {
        public GUIMain()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            BikeSoapService.BikeControllerClient d = new BikeSoapService.BikeControllerClient();
            BikeSoapService.bike [] bikes = d.getBikes();
            foreach(BikeSoapService.bike bike in bikes)
            {
                Console.WriteLine(bike.serial, bike.purchaseDate);
            }
        }

        private void GuiMain_Load(object sender, EventArgs e)
        {

        }

        private void tsMenuQuit_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void tsmMenuCreditos_Click(object sender, EventArgs e)
        {
            MessageBox.Show("Developed by Luis Granada and Gabriel Montalvo.");
        }

        private void menuAddBike_Click(object sender, EventArgs e)
        {
            GUIAddBike gui = new GUIAddBike();
            gui.Show();
        }

        private void menuDeleteBike_Click(object sender, EventArgs e)
        {
            GUIDeleteBike gui = new GUIDeleteBike();
            gui.Show();
        }

        private void menuUpdateBike_Click(object sender, EventArgs e)
        {

        }

        private void menuBikeList_Click(object sender, EventArgs e)
        {
            GuiBikeList gui = new GuiBikeList();
            gui.Show();
        }

        private void menuBikeTypeChart_Click(object sender, EventArgs e)
        {
            GuiPieChart gui = new GuiPieChart();
            gui.Show();
        }

        private void menuBike_Click(object sender, EventArgs e)
        {
            GUIBike gui = new GUIBike();
            gui.Show();
        }
    }
}
