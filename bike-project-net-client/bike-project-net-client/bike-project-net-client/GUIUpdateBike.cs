using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
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
    public partial class GUIUpdateBike : Form
    {
        private Bike findBike;
        private WebClient request;
        public GUIUpdateBike()
        {
            InitializeComponent();
            findBike = null;
            request = new WebClient();
        }

        private void btnFindBike_Click(object sender, EventArgs e)
        {
            if(txtFindSerial.Text == "")
            {
                MessageBox.Show("The serial can't be empty.");
                return;
            }

            try
            {
                string response = request.DownloadString("http://localhost:8080/api/bike/" + txtFindSerial.Text);
                Bike findBike = JsonConvert.DeserializeObject<Bike>(response);
                txtSerial.Text = findBike.serial;
                txtBrand.Text = findBike.brand;
                txtWeight.Text = Convert.ToString(findBike.weight);
                txtPrice.Text = Convert.ToString(findBike.price);
                dtpPurchaseDate.Value = findBike.purchaseDate;

                if (findBike.type.Equals(Bike.types.ROAD))
                {
                    cbType.SelectedIndex = 0;
                }
                else if (findBike.type.Equals(Bike.types.MOUNTAIN))
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

        private void btnUpdate_Click(object sender, EventArgs e)
        {
            findBike = getBike();
            if (findBike != null)
            {
                HttpWebRequest request = (HttpWebRequest)WebRequest.Create("http://localhost:8080/api/bike/" + findBike.serial);
                request.ContentType = "application/json; charset=utf-8";
                request.Method = "PUT";

                using (var streamWriter = new StreamWriter(request.GetRequestStream()))
                {
                    var json = JsonConvert.SerializeObject(findBike, new JsonSerializerSettings() { DateFormatString = "yyyy-MM-dd" });
                    streamWriter.Write(json);
                    streamWriter.Flush();
                    streamWriter.Close();
                }

                HttpWebResponse response = null;

                try
                {
                    response = (HttpWebResponse)request.GetResponse();
                    if (response.StatusCode == HttpStatusCode.Created)
                    {
                        MessageBox.Show("The bike has been updated");
                    }

                }
                catch (WebException we)
                {
                    HttpWebResponse webResp = (HttpWebResponse)we.Response;
                    using (var streamReader = new StreamReader(webResp.GetResponseStream()))
                    {
                        var content = streamReader.ReadToEnd();
                        if (((HttpWebResponse)we.Response).StatusCode == HttpStatusCode.InternalServerError)
                        {
                            Dictionary<String, Object> hash = JsonConvert.DeserializeObject<Dictionary<String, Object>>(content);
                            String message = (String)hash["message"];
                            String specifications = (String)hash["specifications"];
                            MessageBox.Show(message + Environment.NewLine + specifications);
                        }
                        else if (((HttpWebResponse)we.Response).StatusCode == HttpStatusCode.BadRequest)
                        {
                            JObject result = JObject.Parse(content);
                            var clientarray = result["errores"].Value<JArray>();
                            List<String> errores = clientarray.ToObject<List<String>>();
                            String message = "";
                            foreach (String error in errores)
                            {
                                message += (error + Environment.NewLine);
                            }
                            MessageBox.Show(message);

                        }
                        else if(((HttpWebResponse)we.Response).StatusCode == HttpStatusCode.NotFound)
                        {
                            Dictionary<String, String> hash = JsonConvert.DeserializeObject<Dictionary<String, String>>(content);
                            String message = (String)hash["message"];
                            MessageBox.Show(message);
                        }

                        streamReader.Close();
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
            if (serial.Equals("") || brand.Equals("") || weight.Equals("") || price.Equals(""))
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
