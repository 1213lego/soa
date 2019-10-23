using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Net;
using System.Windows.Forms;

namespace bike_project_net_client
{
    public partial class GuiBikeList : Form
    {
        WebClient webCliente;
        public GuiBikeList()
        {
            InitializeComponent();
            webCliente = new WebClient();
            loadBikes();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            loadBikes();
        }
        private void loadBikes()
        {
            String content = webCliente.DownloadString(Bike.API_BIKE);
            List<Bike> bikes = JsonConvert.DeserializeObject<List<Bike>>(content);

            gridViewBikes.RowCount = 1;
            foreach(Bike bike in bikes)
            {
                object[] fields = { bike.serial, bike.type,
                    bike.brand, bike.weight, bike.price, bike.purchaseDate.ToShortDateString()};
                gridViewBikes.Rows.Add(fields);
            }
        }
    }
}