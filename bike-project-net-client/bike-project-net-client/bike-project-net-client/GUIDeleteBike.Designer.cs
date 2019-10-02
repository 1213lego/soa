using System;

namespace bike_project_net_client
{
    partial class GUIDeleteBike
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
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.txtSerial = new System.Windows.Forms.TextBox();
            this.txtBrand = new System.Windows.Forms.TextBox();
            this.txtWeight = new System.Windows.Forms.TextBox();
            this.txtPrice = new System.Windows.Forms.TextBox();
            this.dtpPurchaseDate = new System.Windows.Forms.DateTimePicker();
            this.cbType = new System.Windows.Forms.ComboBox();
            this.btnDelete = new System.Windows.Forms.Button();
            this.btnQuit = new System.Windows.Forms.Button();
            this.label8 = new System.Windows.Forms.Label();
            this.txtFindSerial = new System.Windows.Forms.TextBox();
            this.btnFindBike = new System.Windows.Forms.Button();
            this.btnClearFields = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 36F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(95, 116);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(477, 69);
            this.label1.TabIndex = 0;
            this.label1.Text = "Bike Information";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.Location = new System.Drawing.Point(210, 212);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(69, 24);
            this.label2.TabIndex = 2;
            this.label2.Text = "Serial:";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label3.Location = new System.Drawing.Point(216, 252);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(63, 24);
            this.label3.TabIndex = 3;
            this.label3.Text = "Type:";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label4.Location = new System.Drawing.Point(208, 293);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(71, 24);
            this.label4.TabIndex = 4;
            this.label4.Text = "Brand:";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label5.Location = new System.Drawing.Point(198, 336);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(81, 24);
            this.label5.TabIndex = 5;
            this.label5.Text = "Weight:";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label6.Location = new System.Drawing.Point(215, 379);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(64, 24);
            this.label6.TabIndex = 6;
            this.label6.Text = "Price:";
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label7.Location = new System.Drawing.Point(127, 419);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(152, 24);
            this.label7.TabIndex = 7;
            this.label7.Text = "Purchase Date:";
            // 
            // txtSerial
            // 
            this.txtSerial.Enabled = false;
            this.txtSerial.Location = new System.Drawing.Point(286, 212);
            this.txtSerial.Name = "txtSerial";
            this.txtSerial.Size = new System.Drawing.Size(244, 22);
            this.txtSerial.TabIndex = 8;
            // 
            // txtBrand
            // 
            this.txtBrand.Enabled = false;
            this.txtBrand.Location = new System.Drawing.Point(286, 293);
            this.txtBrand.Name = "txtBrand";
            this.txtBrand.Size = new System.Drawing.Size(244, 22);
            this.txtBrand.TabIndex = 9;
            // 
            // txtWeight
            // 
            this.txtWeight.Enabled = false;
            this.txtWeight.Location = new System.Drawing.Point(286, 336);
            this.txtWeight.Name = "txtWeight";
            this.txtWeight.Size = new System.Drawing.Size(244, 22);
            this.txtWeight.TabIndex = 10;
            // 
            // txtPrice
            // 
            this.txtPrice.Enabled = false;
            this.txtPrice.Location = new System.Drawing.Point(286, 379);
            this.txtPrice.Name = "txtPrice";
            this.txtPrice.Size = new System.Drawing.Size(244, 22);
            this.txtPrice.TabIndex = 11;
            // 
            // dtpPurchaseDate
            // 
            this.dtpPurchaseDate.Enabled = false;
            this.dtpPurchaseDate.Location = new System.Drawing.Point(286, 419);
            this.dtpPurchaseDate.Name = "dtpPurchaseDate";
            this.dtpPurchaseDate.Size = new System.Drawing.Size(286, 22);
            this.dtpPurchaseDate.TabIndex = 12;
            // 
            // cbType
            // 
            this.cbType.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbType.Enabled = false;
            this.cbType.FormattingEnabled = true;
            this.cbType.Items.AddRange(new object[] {
            "ROAD",
            "MOUNTAIN",
            "GRAVEL"});
            this.cbType.Location = new System.Drawing.Point(286, 252);
            this.cbType.Name = "cbType";
            this.cbType.Size = new System.Drawing.Size(244, 24);
            this.cbType.TabIndex = 0;
            // 
            // btnDelete
            // 
            this.btnDelete.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnDelete.Location = new System.Drawing.Point(81, 476);
            this.btnDelete.Name = "btnDelete";
            this.btnDelete.Size = new System.Drawing.Size(117, 42);
            this.btnDelete.TabIndex = 14;
            this.btnDelete.Text = "Delete";
            this.btnDelete.UseVisualStyleBackColor = true;
            this.btnDelete.Click += new System.EventHandler(this.btnDelete_Click);
            // 
            // btnQuit
            // 
            this.btnQuit.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnQuit.Location = new System.Drawing.Point(495, 476);
            this.btnQuit.Name = "btnQuit";
            this.btnQuit.Size = new System.Drawing.Size(77, 42);
            this.btnQuit.TabIndex = 15;
            this.btnQuit.Text = "Quit";
            this.btnQuit.UseVisualStyleBackColor = true;
            this.btnQuit.Click += new System.EventHandler(this.btnQuit_Click);
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label8.Location = new System.Drawing.Point(127, 45);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(69, 24);
            this.label8.TabIndex = 16;
            this.label8.Text = "Serial:";
            // 
            // txtFindSerial
            // 
            this.txtFindSerial.Location = new System.Drawing.Point(214, 47);
            this.txtFindSerial.Name = "txtFindSerial";
            this.txtFindSerial.Size = new System.Drawing.Size(244, 22);
            this.txtFindSerial.TabIndex = 17;
            // 
            // btnFindBike
            // 
            this.btnFindBike.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnFindBike.Location = new System.Drawing.Point(481, 36);
            this.btnFindBike.Name = "btnFindBike";
            this.btnFindBike.Size = new System.Drawing.Size(106, 38);
            this.btnFindBike.TabIndex = 18;
            this.btnFindBike.Text = "Search";
            this.btnFindBike.UseVisualStyleBackColor = true;
            this.btnFindBike.Click += new System.EventHandler(this.btnFindBike_Click);
            // 
            // btnClearFields
            // 
            this.btnClearFields.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnClearFields.Location = new System.Drawing.Point(275, 476);
            this.btnClearFields.Name = "btnClearFields";
            this.btnClearFields.Size = new System.Drawing.Size(152, 42);
            this.btnClearFields.TabIndex = 19;
            this.btnClearFields.Text = "Clear Fields";
            this.btnClearFields.UseVisualStyleBackColor = true;
            this.btnClearFields.Click += new System.EventHandler(this.btnClearFields_Click);
            // 
            // GUIDeleteBike
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(647, 535);
            this.Controls.Add(this.btnClearFields);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.btnFindBike);
            this.Controls.Add(this.txtFindSerial);
            this.Controls.Add(this.label8);
            this.Controls.Add(this.btnQuit);
            this.Controls.Add(this.btnDelete);
            this.Controls.Add(this.cbType);
            this.Controls.Add(this.dtpPurchaseDate);
            this.Controls.Add(this.txtPrice);
            this.Controls.Add(this.txtWeight);
            this.Controls.Add(this.txtBrand);
            this.Controls.Add(this.txtSerial);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Name = "GUIDeleteBike";
            this.Text = "Delete bike";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.TextBox txtSerial;
        private System.Windows.Forms.TextBox txtBrand;
        private System.Windows.Forms.TextBox txtWeight;
        private System.Windows.Forms.TextBox txtPrice;
        private System.Windows.Forms.DateTimePicker dtpPurchaseDate;
        private System.Windows.Forms.ComboBox cbType;
        private System.Windows.Forms.Button btnDelete;
        private System.Windows.Forms.Button btnQuit;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.TextBox txtFindSerial;
        private System.Windows.Forms.Button btnFindBike;
        private System.Windows.Forms.Button btnClearFields;
    }
}