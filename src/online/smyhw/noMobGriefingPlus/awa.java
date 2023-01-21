package online.smyhw.noMobGriefingPlus;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.java.JavaPlugin;

public class awa extends JavaPlugin{
    @Override
    public void onEnable() {
        getLogger().info("加载noMobGriefingPlus...");
        getLogger().info("加载配置...");
        saveDefaultConfig();
        config.readConfig(this);
        config.checkConfig(getLogger());
        getLogger().info("注册监听器...");
        Bukkit.getPluginManager().registerEvents(new listener(),this);
        getLogger().info("noMobGriefingPlus加载完成,生效中...");
    }

    @Override
    public void onDisable() {
        getLogger().info("卸载noMobGriefingPlus...");
    }
}
