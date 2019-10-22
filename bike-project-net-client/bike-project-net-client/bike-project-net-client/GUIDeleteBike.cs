using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace bike_project_net_client
{
    public partial class GUIDeleteBike : Form
    {
        WebClient request;

        public GUIDeleteBike()
        {
            InitializeComponent();
            request = new WebClient();
        }

        private void btnFindBike_Click(object sender, EventArgs e)
        {
            try
            {
                string response = request.DownloadString("http://localhost:8080/api/bike/" + txtFindSerial.Text);
                Bike findBike = JsonConvert.DeserializeObject<Bike>(response);
                txtSerial.Text = findBike.serial;
                txtBrand.Text = findBike.brand;
                txtWeight.Text = Convert.ToString(findBike.weight);
                txtPrice.Text = Convert.ToString(findBike.price);
                dtpPurchaseDate.Value = findBike.purchaseDate;

                if (findBike.type.Equals(BikeSoapService.type.ROAD))
                {
                    cbType.SelectedIndex = 0;
                }
                else if (findBike.type.Equals(BikeSoapService.type.MOUNTAIN))
                {
                    cbType.SelectedIndex = 1;
                }
                else
                {
                    cbType.SelectedIndex = 2;
                }
            }
            catch (WebException we)
            {
                HttpWebResponse webResp = (HttpWebResponse)we.Response;
                var stream = we.Response.GetResponseStream();
                var sr = new StreamReader(stream);
                var content = sr.ReadToEnd();
                Dictionary<String, String> hash = JsonConvert.DeserializeObject<Dictionary<String, String>>(content);
                if (((HttpWebResponse)we.Response).StatusCode == HttpStatusCode.NotFound)
                {
                    String message = hash["message"];
                    MessageBox.Show(message);
                }
                else if (((HttpWebResponse)we.Response).StatusCode == HttpStatusCode.InternalServerError)
                {
                    String message = hash["message"];
                    String specifications = hash["specifications"];
                    MessageBox.Show(message + "/n" + specifications);
                }
            }
        }

        private void btnDelete_Click(object sender, EventArgs e)
        {
            WebRequest request = WebRequest.Create("http://localhost:8080/api/bike/" + txtFindSerial.Text);
            request.Method = "DELETE";
            HttpWebResponse response = null;
            try
            {
                response = (HttpWebResponse)request.GetResponse();
                if (response.StatusCode == HttpStatusCode.OK)
                {
                    MessageBox.Show("Bike has been deleted.");
                    clearFields();
                }
            }
            catch (WebException we)
            {
                HttpWebResponse webResp = (HttpWebResponse)we.Response;
                var stream = we.Response.GetResponseStream();
                var sr = new StreamReader(stream);
                var content = sr.ReadToEnd();
                Dictionary<String, String> hash = JsonConvert.DeserializeObject<Dictionary<String, String>>(content);
                if (((HttpWebResponse)we.Response).StatusCode == HttpStatusCode.InternalServerError)
                {
                    String message = hash["message"];
                    String specifications = hash["specifications"];
                    MessageBox.Show(message + "/n" + specifications);
                }
            }
            
        }

        private void btnQuit_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void btnClearFields_Click(object sender, EventArgs e)
        {
            clearFields();
        }

        private void clearFields()
        {
            txtFindSerial.Clear();
            txtSerial.Clear();
            cbType.SelectedIndex = 0;
            txtBrand.Clear();
            txtWeight.Clear();
            txtPrice.Clear();
            dtpPurchaseDate.Value = DateTime.Today;
        }
    }
}
