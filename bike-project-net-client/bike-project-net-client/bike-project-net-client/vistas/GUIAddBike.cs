using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace bike_project_net_client.vistas
{
    public partial class GUIAddBike : Form
    {
        BikeSoapService.BikeControllerClient bikeController;

        public GUIAddBike()
        {
            InitializeComponent();
            bikeController = new BikeSoapService.BikeControllerClient();
        }

        private void btnAdd_Click(object sender, EventArgs e)
        {
            String serial = txtSerial.Text;
            String type = cbType.Text;
            String brand = txtBrand.Text;
            double weight = Double.Parse(txtWeight.Text);
            double price = Double.Parse(txtPrice.Text);
            DateTime purchaseDate = dtpPurchaseDate.Value;

            BikeSoapService.bike bike = new BikeSoapService.bike(serial, type, brand, weight, price, purchaseDate);
            bikeController.saveBike(bike);
            clearFields();
        }

        private void clearFields()
        {
            txtSerial.Clear();
            cbType.SelectedIndex = 0;
            txtBrand.Clear();
            txtWeight.Clear();
            txtPrice.Clear();
            dtpPurchaseDate.Value = DateTime.Today;
        }

        private void btnQuit_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
