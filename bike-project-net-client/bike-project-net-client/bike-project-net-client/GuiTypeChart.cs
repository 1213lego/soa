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
    public partial class GuiTypeChart : Form
    {
        BikeSoapService.BikeControllerClient service;
        public GuiTypeChart()
        {
            InitializeComponent();
            service = new BikeSoapService.BikeControllerClient();
            loadChart();
        }

        private void loadChart()
        {
            string[] types = {BikeSoapService.type.GRAVEL.ToString(),
                BikeSoapService.type.MOUNTAIN.ToString(),
                BikeSoapService.type.ROAD.ToString()};
            int[] values = new int[types.Length];
            BikeSoapService.bike[] bikes = service.getBikes();
            foreach (BikeSoapService.bike bike in bikes)
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
