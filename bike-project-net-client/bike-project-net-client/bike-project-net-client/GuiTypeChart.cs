using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace bike_project_net_client
{
    public partial class GuiTypeChart : Form
    {
        WebClient webCliente;
        public GuiTypeChart()
        {
            InitializeComponent();
            webCliente = new WebClient();
            loadChart();
        }

        private void loadChart()
        {
            String content = webCliente.DownloadString(Bike.API_BIKE);
            List<Bike> bikes = JsonConvert.DeserializeObject<List<Bike>>(content);

            string[] types = {Bike.types.GRAVEL.ToString(),
                Bike.types.MOUNTAIN.ToString(),
                Bike.types.ROAD.ToString()};
            int[] values = new int[types.Length];

            foreach (Bike bike in bikes)
            {
                for (int i = 0; i < types.Length; i++)
                {
                    if (String.Equals(bike.type.ToString(), types[i]))
                    {
                        values[i] += 1;
                    }
                }
            }
            pieChart.Series[0].ChartType = System.Windows.Forms.DataVisualization.Charting.SeriesChartType.Pie;
            pieChart.Series[0].Points.DataBindXY(types, values);
            pieChart.Legends[0].Enabled = true;
            pieChart.ChartAreas[0].Area3DStyle.Enable3D = true;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            loadChart();
        }
    }
}
