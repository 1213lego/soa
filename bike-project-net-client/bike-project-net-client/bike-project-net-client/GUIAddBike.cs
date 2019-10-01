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
        public GUIAddBike()
        {
            InitializeComponent();
        }

        private void btnAdd_Click(object sender, EventArgs e)
        {
            if(emptyFields())
            {
                return;
            }

            try
            {
                double weightD = Double.Parse(txtWeight.Text);
                double priceD = Double.Parse(txtPrice.Text);
            }
            catch (Exception ex)
            {
                MessageBox.Show("The price or weight error: " + ex.Message);
                return;
            }

            BikeSoapService.BikeControllerClient bikeController = new BikeSoapService.BikeControllerClient();

            String serial = txtSerial.Text;
            String type = cbType.Text;
            String brand = txtBrand.Text;
            double weight = Double.Parse(txtWeight.Text);
            double price = Double.Parse(txtPrice.Text);
            DateTime purchaseDate = dtpPurchaseDate.Value;

            BikeSoapService.bike bike = new BikeSoapService.bike();
            bike.serial = serial;
            bike.brand = brand;
            bike.weight = weight;
            bike.price = price;
            bike.purchaseDate = purchaseDate;

            if(type.Equals("ROAD"))
            {
                bike.type = BikeSoapService.type.ROAD;
            }
            else if (type.Equals("GRAVEL"))
            {
                bike.type = BikeSoapService.type.GRAVEL;
            }
            else
            {
                bike.type = BikeSoapService.type.MOUNTAIN;
            }
            bike.typeSpecified = true;
            bike.purchaseDateSpecified = true;

            bikeController.saveBike(bike);
            clearFields();
        }

        private bool emptyFields()
        {
            String serial = txtSerial.Text;
            String brand = txtBrand.Text;
            String weight = txtWeight.Text;
            String price = txtPrice.Text;
            if(serial.Equals("") || brand.Equals("") || weight.Equals("") || price.Equals(""))
            {
                MessageBox.Show("Empty Fields");
                return true;
            }

            return false;
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
