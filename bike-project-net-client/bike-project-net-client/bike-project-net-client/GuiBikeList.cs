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
    public partial class GuiBikeList : Form
    {
        BikeSoapService.BikeControllerClient service;
        public GuiBikeList()
        {
            InitializeComponent();
            service = new BikeSoapService.BikeControllerClient();
            loadBikes();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            loadBikes();
        }
        private void loadBikes()
        {
            BikeSoapService.bike[] bikes = service.getBikes();
            gridViewBikes.RowCount = 1;
            for(int i = 0; i < bikes.Length; i++)
            {
                object[] fields = { bikes[i].serial, bikes[i].type,
                    bikes[i].brand, bikes[i].weight, bikes[i].price, bikes[i].purchaseDate};
                gridViewBikes.Rows.Add(fields);
            }
        }
    }
}
