package ch.gyselanimatioon;

import java.sql.SQLException;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ReportCommand extends Command {

	public ReportCommand() {
		super("report");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender commandSender, String[] strings) {
		if (strings.length > 1) {
			try {
				ProxiedPlayer sender = (ProxiedPlayer) commandSender;
				ProxiedPlayer reported = ProxyServer.getInstance().getPlayer(strings[0]);
				if (reported == null) {
					sender.sendMessage("Spieler nicht online.");
				} else {
					String sender_username = sender.getName();
					String sender_uuid = sender.getUUID();
					String reported_username = reported.getName();
					String reported_uuid = reported.getUUID();
					String grund = "";
					for (int i = 1; i < strings.length; i++) {
						grund = grund + strings[i] + " ";
					}
					MiepcraftBungee.connection.createStatement().execute(
							"INSERT INTO miepcraft_reports (sender_username,sender_uuid,reported_username,reported_uuid,grund) VALUES ('"
									+ sender_username + "','" + sender_uuid + "','" + reported_username + "','" + reported_uuid + "','" + grund
									+ "');");
					sender.sendMessage("§7Report gespeichert.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			commandSender.sendMessage("/report <username> <grund>");
		}
	}
}