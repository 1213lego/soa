namespace bike_project_net_client
{
    partial class GuiBikeList
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.getBikesResponseBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.bikeBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.button1 = new System.Windows.Forms.Button();
            this.gridViewBikes = new System.Windows.Forms.DataGridView();
            this.serial = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.type = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.brand = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.weight = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.price = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.purchaseDate = new System.Windows.Forms.DataGridViewTextBoxColumn();
            ((System.ComponentModel.ISupportInitialize)(this.getBikesResponseBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.bikeBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridViewBikes)).BeginInit();
            this.SuspendLayout();
            // 
            // getBikesResponseBindingSource
            // 
            this.getBikesResponseBindingSource.DataSource = typeof(bike_project_net_client.BikeSoapService.getBikesResponse);
            // 
            // bikeBindingSource
            // 
            this.bikeBindingSource.DataSource = typeof(bike_project_net_client.BikeSoapService.bike);
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(343, 397);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(75, 23);
            this.button1.TabIndex = 1;
            this.button1.Text = "Refresh";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // gridViewBikes
            // 
            this.gridViewBikes.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.gridViewBikes.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.serial,
            this.type,
            this.brand,
            this.weight,
            this.price,
            this.purchaseDate});
            this.gridViewBikes.Location = new System.Drawing.Point(12, 12);
            this.gridViewBikes.Name = "gridViewBikes";
            this.gridViewBikes.RowTemplate.Height = 24;
            this.gridViewBikes.Size = new System.Drawing.Size(971, 366);
            this.gridViewBikes.TabIndex = 2;
            // 
            // serial
            // 
            this.serial.HeaderText = "Serial";
            this.serial.Name = "serial";
            // 
            // type
            // 
            this.type.HeaderText = "Type";
            this.type.Name = "type";
            // 
            // brand
            // 
            this.brand.HeaderText = "Brand";
            this.brand.Name = "brand";
            // 
            // weight
            // 
            this.weight.HeaderText = "Weight";
            this.weight.Name = "weight";
            // 
            // price
            // 
            this.price.HeaderText = "Price";
            this.price.Name = "price";
            // 
            // purchaseDate
            // 
            this.purchaseDate.HeaderText = "Purchase date";
            this.purchaseDate.Name = "purchaseDate";
            // 
            // GuiBikeList
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1032, 450);
            this.Controls.Add(this.gridViewBikes);
            this.Controls.Add(this.button1);
            this.Name = "GuiBikeList";
            this.Text = "GuiBikeList";
            ((System.ComponentModel.ISupportInitialize)(this.getBikesResponseBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.bikeBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridViewBikes)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion
        private System.Windows.Forms.BindingSource getBikesResponseBindingSource;
        private System.Windows.Forms.BindingSource bikeBindingSource;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.DataGridView gridViewBikes;
        private System.Windows.Forms.DataGridViewTextBoxColumn serial;
        private System.Windows.Forms.DataGridViewTextBoxColumn type;
        private System.Windows.Forms.DataGridViewTextBoxColumn brand;
        private System.Windows.Forms.DataGridViewTextBoxColumn weight;
        private System.Windows.Forms.DataGridViewTextBoxColumn price;
        private System.Windows.Forms.DataGridViewTextBoxColumn purchaseDate;
    }
}