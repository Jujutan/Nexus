package Nexus.Core;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import Nexus.Config.ConfigurationManager;
import Nexus.Util.Util;

public class NexusCore extends JavaPlugin {

	public Logger log;
	public static NexusCore plugin;
	public static String PluginName = "Nexus";
	ConfigurationManager cfg;
	private static ConfigurationManager msg;

	public void onEnable() {
		plugin = this;
		this.log = this.getLogger();
		cfg = new ConfigurationManager(this);
		msg = new ConfigurationManager(this, "messages.yml");
		plugin.saveConfig();
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
	public String getMessage(String par1Key) {
		return msg.getString(par1Key);
	}

	/**
	 * コマンドのPrefixを返す
	 * @return PrefixをマスキングリプレースしたString
	 */
	public String getPrefix(){
		return Util.maskedStringReplace(cfg.getString("Prefix"),null);
	}
	public void onDisable() {
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
			par1Sender.sendMessage(Util.maskedStringReplace(par2String, null));
		}else{
			plugin.log.info(Util.maskedStringReplace(par2String, null));
		}
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
}