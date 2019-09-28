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
    public partial class GuiMain : Form
    {
        public GuiMain()
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
    }
}
