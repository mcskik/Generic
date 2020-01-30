        private CloneDroid moCloneDroid;

        private void cmdCloneDroid_Click(object sender, EventArgs e)
        {
            if (cboMode.Text.StartsWith("CloneDroid"))
            {
                _resultsDisplayLimitReached = false;
                if (System.IO.Directory.Exists(txtPath.Text))
                {
                    SaveParameters();
                    txtResults.Text = string.Empty;
                    moCloneDroid.Action = "CloneDroid";
                    moCloneDroid.LoadConfiguration(cboMode.Text);
                    moCloneDroid.Run();
                }
                else
                {
                    string sMessage = @"Directory """ + txtPath.Text + @""" does not exist.";
                    staStatusStrip.Text = sMessage;
                    toolStripStatusLabel.Text = sMessage;
                }
            }
            else
            {
                string sMessage = @"Cannot clone droid unless mode is set to ""CloneDroid*""";
                staStatusStrip.Text = sMessage;
                toolStripStatusLabel.Text = sMessage;
            }
        }

        private void LoadParameters()
        {
            moCloneDroid = new CloneDroid();
		}

        private void SaveParameters()
        {
            moCloneDroid.Mode = cboMode.Text;
        }