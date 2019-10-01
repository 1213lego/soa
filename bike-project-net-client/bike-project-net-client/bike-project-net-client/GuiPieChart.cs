using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Windows.Forms.DataVisualization.Charting;

namespace bike_project_net_client
{
    public partial class GuiPieChart : Form
    {
        BikeSoapService.BikeControllerClient service;
    
        public GuiPieChart()
        {
            InitializeComponent();
            service = new BikeSoapService.BikeControllerClient();
            loadChart();
        }

        private void button1_Click(object sender, EventArgs e)
        {
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
                for(int i = 0; i < types.Length; i++)
                {
                    if (String.Equals(bike.type.ToString(), types[i]))
                    {
                        values[i] += 1;
                    }
                }
            }
            pieChart.Series[0].ChartType = SeriesChartType.Pie;
            pieChart.Series[0].Points.DataBindXY(types, values);
            pieChart.Legends[0].Enabled = true;
            pieChart.ChartAreas[0].Area3DStyle.Enable3D = true;
        }
    }
}
