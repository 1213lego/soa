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
            Bike bike = getBike();
            if(bike != null)
            {
                var request = (HttpWebRequest) WebRequest.Create("http://localhost:8080/api/bike/");
                request.Accept = "application/json";
                request.ContentType = "application/json";
                request.Method = "POST";

                using (var streamWriter = new StreamWriter(request.GetRequestStream()))
                {
                    string json = JsonConvert.SerializeObject(bike);
                    streamWriter.Write(json);
                }

                HttpWebResponse response = null;

                try
                {
                    response = (HttpWebResponse)request.GetResponse();
                    if (response.StatusCode == HttpStatusCode.Created)
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
        }

        private Bike getBike()
        {
            if (emptyFields() || doubleFieldsValidate())
            {
                return null;
            }

            String serial = txtSerial.Text;
            String type = cbType.Text;
            String brand = txtBrand.Text;
            double weight = Double.Parse(txtWeight.Text);
            double price = Double.Parse(txtPrice.Text);
            DateTime purchaseDate = dtpPurchaseDate.Value;

            Bike bike = new Bike(serial, brand, weight, price, purchaseDate);
            bike.serial = serial;
            bike.brand = brand;
            bike.weight = weight;
            bike.price = price;
            bike.purchaseDate = purchaseDate;

            if (type.Equals("ROAD"))
            {
                bike.type = Bike.types.ROAD;
            }
            else if (type.Equals("GRAVEL"))
            {
                bike.type = Bike.types.GRAVEL;
            }
            else
            {
                bike.type = Bike.types.MOUNTAIN;
            }

            return bike;

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
        
        private bool doubleFieldsValidate()
        {
            try
            {
                double weightD = Double.Parse(txtWeight.Text);
                double priceD = Double.Parse(txtPrice.Text);
            }
            catch (Exception ex)
            {
                MessageBox.Show("The price or weight error: " + ex.Message);
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
