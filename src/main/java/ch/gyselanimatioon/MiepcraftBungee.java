package ch.gyselanimatioon;

import java.sql.Connection;

import net.md_5.bungee.api.plugin.Plugin;

public class MiepcraftBungee extends Plugin {
	
	public static Connection connection;
	
	public void onEnable() {

		Database db = new Database("jdbc:mysql://server.mineunity.eu/Mineunity88", "Mineunity88", "pw");

		connection = db.getConnection();
		
		
		getProxy().getPluginManager().registerCommand(this, new ReportCommand());
	}
}