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
    public partial class GUIUpdateBike : Form
    {
        private BikeSoapService.bike findBike;
        private BikeSoapService.BikeControllerClient bikeController;
        public GUIUpdateBike()
        {
            InitializeComponent();
            findBike = null;
            bikeController = new BikeSoapService.BikeControllerClient();
        }

        private void btnFindBike_Click(object sender, EventArgs e)
        {
            findBike = bikeController.findBikeBySerial(txtFindSerial.Text);

            if (findBike != null)
            {
                txtSerial.Text = findBike.serial;
                txtBrand.Text = findBike.brand;
                txtWeight.Text = Convert.ToString(findBike.weight);
                txtPrice.Text = Convert.ToString(findBike.price);
                dtpPurchaseDate.Value = findBike.purchaseDate;

                if (findBike.type.Equals("ROAD"))
                {
                    cbType.SelectedIndex = 0;
                }
                else if (findBike.Equals("MOUNTAIN"))
                {
                    cbType.SelectedIndex = 1;
                }
                else
                {
                    cbType.SelectedIndex = 2;
                }
            }
            else
            {
                MessageBox.Show("Bike not found");
            }
        }

        private void btnUpdate_Click(object sender, EventArgs e)
        {
            if (findBike != null)
            {
                findBike.brand = txtBrand.Text;
                findBike.weight = Double.Parse(txtWeight.Text);
                findBike.price = Double.Parse(txtPrice.Text);
                findBike.purchaseDate = dtpPurchaseDate.Value;

                if (cbType.Text.Equals("ROAD"))
                {
                    findBike.type = BikeSoapService.type.ROAD;
                }
                else if (cbType.Text.Equals("GRAVEL"))
                {
                    findBike.type = BikeSoapService.type.GRAVEL;
                }
                else
                {
                    findBike.type = BikeSoapService.type.MOUNTAIN;
                }

                try
                {
                    bikeController.updateBike(findBike);
                    clearFields();
                    MessageBox.Show("Updated bike");
                }
                catch(Exception ex)
                {
                    MessageBox.Show("Error: " + ex.Message);
                }
            }
            else
            {
                MessageBox.Show("Bike not found");
            }
        }

        private void btnClearFields_Click(object sender, EventArgs e)
        {
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
            findBike = null;
        }

        private void btnQuit_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
