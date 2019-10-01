namespace bike_project_net_client
{
    partial class GUIMain
    {
        /// <summary>
        /// Variable del diseñador necesaria.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Limpiar los recursos que se estén usando.
        /// </summary>
        /// <param name="disposing">true si los recursos administrados se deben desechar; false en caso contrario.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Código generado por el Diseñador de Windows Forms

        /// <summary>
        /// Método necesario para admitir el Diseñador. No se puede modificar
        /// el contenido de este método con el editor de código.
        /// </summary>
        private void InitializeComponent()
        {
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.menuFile = new System.Windows.Forms.ToolStripMenuItem();
            this.menuQuit = new System.Windows.Forms.ToolStripMenuItem();
            this.menuBikes = new System.Windows.Forms.ToolStripMenuItem();
            this.menuAddBike = new System.Windows.Forms.ToolStripMenuItem();
            this.menuDeleteBike = new System.Windows.Forms.ToolStripMenuItem();
            this.menuUpdateBike = new System.Windows.Forms.ToolStripMenuItem();
            this.menuBikeList = new System.Windows.Forms.ToolStripMenuItem();
            this.menuBikeTypeChart = new System.Windows.Forms.ToolStripMenuItem();
            this.menuHelp = new System.Windows.Forms.ToolStripMenuItem();
            this.menuAbout = new System.Windows.Forms.ToolStripMenuItem();
            this.button1 = new System.Windows.Forms.Button();
            this.menuBike = new System.Windows.Forms.ToolStripMenuItem();
            this.menuStrip1.SuspendLayout();
            this.SuspendLayout();
            // 
            // menuStrip1
            // 
            this.menuStrip1.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.menuFile,
            this.menuBikes,
            this.menuHelp});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(800, 28);
            this.menuStrip1.TabIndex = 1;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // menuFile
            // 
            this.menuFile.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.menuQuit});
            this.menuFile.Name = "menuFile";
            this.menuFile.Size = new System.Drawing.Size(44, 24);
            this.menuFile.Text = "File";
            // 
            // menuQuit
            // 
            this.menuQuit.Name = "menuQuit";
            this.menuQuit.Size = new System.Drawing.Size(112, 26);
            this.menuQuit.Text = "Quit";
            this.menuQuit.Click += new System.EventHandler(this.tsMenuQuit_Click);
            // 
            // menuBikes
            // 
            this.menuBikes.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.menuAddBike,
            this.menuDeleteBike,
            this.menuBike,
            this.menuUpdateBike,
            this.menuBikeList,
            this.menuBikeTypeChart});
            this.menuBikes.Name = "menuBikes";
            this.menuBikes.Size = new System.Drawing.Size(55, 24);
            this.menuBikes.Text = "Bikes";
            // 
            // menuAddBike
            // 
            this.menuAddBike.Name = "menuAddBike";
            this.menuAddBike.Size = new System.Drawing.Size(182, 26);
            this.menuAddBike.Text = "Add bike";
            this.menuAddBike.Click += new System.EventHandler(this.menuAddBike_Click);
            // 
            // menuDeleteBike
            // 
            this.menuDeleteBike.Name = "menuDeleteBike";
            this.menuDeleteBike.Size = new System.Drawing.Size(182, 26);
            this.menuDeleteBike.Text = "Delete bike";
            this.menuDeleteBike.Click += new System.EventHandler(this.menuDeleteBike_Click);
            // 
            // menuUpdateBike
            // 
            this.menuUpdateBike.Name = "menuUpdateBike";
            this.menuUpdateBike.Size = new System.Drawing.Size(182, 26);
            this.menuUpdateBike.Text = "Update bike";
            this.menuUpdateBike.Click += new System.EventHandler(this.menuUpdateBike_Click);
            // 
            // menuBikeList
            // 
            this.menuBikeList.Name = "menuBikeList";
            this.menuBikeList.Size = new System.Drawing.Size(182, 26);
            this.menuBikeList.Text = "Bike list";
            this.menuBikeList.Click += new System.EventHandler(this.menuBikeList_Click);
            // 
            // menuBikeTypeChart
            // 
            this.menuBikeTypeChart.Name = "menuBikeTypeChart";
            this.menuBikeTypeChart.Size = new System.Drawing.Size(182, 26);
            this.menuBikeTypeChart.Text = "Bike type chart";
            this.menuBikeTypeChart.Click += new System.EventHandler(this.menuBikeTypeChart_Click);
            // 
            // menuHelp
            // 
            this.menuHelp.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.menuAbout});
            this.menuHelp.Name = "menuHelp";
            this.menuHelp.Size = new System.Drawing.Size(53, 24);
            this.menuHelp.Text = "Help";
            // 
            // menuAbout
            // 
            this.menuAbout.Name = "menuAbout";
            this.menuAbout.Size = new System.Drawing.Size(187, 26);
            this.menuAbout.Text = "About bike app";
            this.menuAbout.Click += new System.EventHandler(this.tsmMenuCreditos_Click);
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(343, 215);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(75, 23);
            this.button1.TabIndex = 2;
            this.button1.Text = "button1";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // menuBike
            // 
            this.menuBike.Name = "menuBike";
            this.menuBike.Size = new System.Drawing.Size(182, 26);
            this.menuBike.Text = "Find bike";
            this.menuBike.Click += new System.EventHandler(this.menuBike_Click);
            // 
            // GUIMain
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.menuStrip1);
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "GUIMain";
            this.Text = "Bikes";
            this.Load += new System.EventHandler(this.GuiMain_Load);
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem menuFile;
        private System.Windows.Forms.ToolStripMenuItem menuBikes;
        private System.Windows.Forms.ToolStripMenuItem menuHelp;
        private System.Windows.Forms.ToolStripMenuItem menuAbout;
        private System.Windows.Forms.ToolStripMenuItem menuQuit;
        private System.Windows.Forms.ToolStripMenuItem menuAddBike;
        private System.Windows.Forms.ToolStripMenuItem menuDeleteBike;
        private System.Windows.Forms.ToolStripMenuItem menuUpdateBike;
        private System.Windows.Forms.ToolStripMenuItem menuBikeList;
        private System.Windows.Forms.ToolStripMenuItem menuBikeTypeChart;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.ToolStripMenuItem menuBike;
    }
}

