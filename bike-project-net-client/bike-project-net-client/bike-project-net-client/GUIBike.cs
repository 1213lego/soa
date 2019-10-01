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
    public partial class GUIBike : Form
    {
        public GUIBike()
        {
            InitializeComponent();
        }

        private void btnQuit_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void btnFindBike_Click(object sender, EventArgs e)
        {
            BikeSoapService.BikeControllerClient bikeController = new BikeSoapService.BikeControllerClient();
            BikeSoapService.bike findBike = bikeController.findBikeBySerial(txtFindSerial.Text);

            if (findBike != null)
            {
                txtSerial.Text = findBike.serial;
                txtBrand.Text = findBike.brand;
                txtWeight.Text = Convert.ToString(findBike.weight);
                txtPrice.Text = Convert.ToString(findBike.price);
                dtpPurchaseDate.Value = findBike.purchaseDate;

                if (findBike.type.Equals(BikeSoapService.type.ROAD))
                {
                    cbType.SelectedIndex = 0;
                }
                else if (findBike.Equals(BikeSoapService.type.MOUNTAIN))
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
        }
    }
}
