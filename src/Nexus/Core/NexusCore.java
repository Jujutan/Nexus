package Nexus.Core;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import Nexus.Commands.CommandNexus;
import Nexus.Config.ConfigurationManager;
import Nexus.Listener.NexusEventListener;
import Nexus.Util.Util;

public class NexusCore extends JavaPlugin {

	public Logger log;
	public static NexusCore plugin;
	public static String PluginName = "Nexus";
	public static ConfigurationManager cfg;
	private static ConfigurationManager msg;
	public static ConfigurationManager Team;
	public static boolean isNexusSetmode;
	public static List<String> TeamList;
	private static NexusTeamManager manager;

	public void onEnable() {
		plugin = this;
		this.log = this.getLogger();
		manager = new NexusTeamManager();
		cfg = new ConfigurationManager(this);
		TeamList = cfg.getStringList("Teams");
		msg = new ConfigurationManager(this, "messages.yml");
		msg.isnull("Initialize");
		Team = new ConfigurationManager(this, "Team.yml");
		Team.isnull("Initialize");
		initNexusTeam();
		this.getServer().getPluginManager().registerEvents(new NexusEventListener(), this);
		this.getCommand("nexus").setExecutor(new CommandNexus());
		this.log.info(PluginName + " has been enabled!");
	}

	/**
	 * プラグインのJARファイルのFileを返す
	 * @return JARのFileインスタンス
	 */
	public File getPluginJarFile() {
		return plugin.getFile();
	}

	/**
	 * メッセージをコンフィグから読み取って返す
	 * @param par1Key コンフィグのキー
	 * @return メッセージ
	 */
	public static String getMessage(String par1Key) {
		return msg.getString("en." + par1Key);
	}

	/**
	 * コマンドのPrefixを返す
	 * @return PrefixをマスキングリプレースしたString
	 */
	public static String getPrefix(){
		return Util.maskedStringReplace(cfg.getString("Prefix"),null);
	}
	public void onDisable() {
		//manager.clear();
		cfg.save();
		msg.save();
		Team.save();
		this.log.info(PluginName + " has been disabled.");
	}
	/**
	 * メッセージの送信を行う
	 * @param par1Sender メッセージの送信先
	 * @param par2String メッセージ(カラーコード可)
	 */
	public static void Message(
			CommandSender par1Sender,
			String par2String){
		if(par1Sender != null){
			par1Sender.sendMessage(getPrefix() + Util.maskedStringReplace(par2String, null));
		}else{
			plugin.log.info(getPrefix() + Util.maskedStringReplace(par2String, null));
		}
	}
	/**
	 * サーバー一斉通知を行う
	 * @param par1String 送信するメッセージ(カラーコード可)
	 */
	public static void broadCastMessage(
			String par1String){
			broadCastMessage(par1String, null);
	}
	public static void broadCastMessage(
			String par1String, String[][] Args){
			plugin.getServer().broadcastMessage(getPrefix() +
					Util.maskedStringReplace(par1String, Args));
	}
	/**
	 * マスク付きメッセージの送信を行う
	 * @param par1Sender メッセージの送信先
	 * @param par2String メッセージ(カラーコード/マスク可)
	 * @param par3Masks  マスクの指定
	 */
	public static void Message(
			CommandSender par1Sender,
			String par2String,
			String[][] par3Masks){
			Message(par1Sender,Util.maskedStringReplace(par2String, par3Masks));
	}
	/**
	 * メインコンフィグを取得する
	 * @return コンフィグのインスタンス
	 */
	public ConfigurationManager getcfg(){
		return cfg;
	}

	protected void initNexusTeam(){
		for(int i = 0; i < TeamList.size(); i++){
			manager.addTeam(i, TeamList.get(i));
		}
	}
	public static NexusTeamManager getTeamManager(){
		return manager;
	}

	public static void initCfg() {
		cfg.initialize();
		msg.initialize();
		Team.initialize();
	}
}