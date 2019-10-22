using Newtonsoft.Json;
using Newtonsoft.Json.Converters;
using System;

namespace bike_project_net_client
{
    public class Bike
    {
        [JsonConverter(typeof(StringEnumConverter))]
        public enum types
        {
            ROAD,
            MOUNTAIN,
            GRAVEL,
        }

        private string serialField;
        private types typeField;
        private string brandField;
        private double priceField;
        private double weightField;
        private DateTime purchaseDateField;

        public Bike(string serialField, string brandField, double priceField, double weightField, DateTime purchaseDateField)
        {
            this.serialField = serialField;
            this.brandField = brandField;
            this.priceField = priceField;
            this.weightField = weightField;
            this.purchaseDateField = purchaseDateField;
        }

        public string brand
        {
            get
            {
                return this.brandField;
            }
            set
            {
                this.brandField = value;
            }
        }

       public double price
        {
            get
            {
                return this.priceField;
            }
            set
            {
                this.priceField = value;
            }
        }

        public DateTime purchaseDate
        {
            get
            {
                return this.purchaseDateField;
            }
            set
            {
                this.purchaseDateField = value;
            }
        }

        public string serial
        {
            get
            {
                return this.serialField;
            }
            set
            {
                this.serialField = value;
            }
        }

        public types type
        {
            get
            {
                return this.typeField;
            }
            set
            {
                this.typeField = value;
            }
        }

        public double weight
        {
            get
            {
                return this.weightField;
            }
            set
            {
                this.weightField = value;
            }
        }
    }
}
