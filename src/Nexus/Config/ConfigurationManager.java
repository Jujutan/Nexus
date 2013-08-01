package Nexus.Config;

import Nexus.Core.NexusCore;


public class ConfigurationManager extends FileSetConfiguration {
	FileSetConfiguration cfg;
	public ConfigurationManager(NexusCore par1Plugin){
		super(par1Plugin, "config.yml");
		cfg = super.Instance;
	}
	public ConfigurationManager(NexusCore par1Plugin, String par2Fname){
		super(par1Plugin, par2Fname);
		cfg = super.Instance;
	}
}
