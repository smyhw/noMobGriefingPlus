package online.smyhw.noMobGriefingPlus;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

/**
 * 将全部配置项目预载为简单的变量
 * 这样做是为了发生事件时能更快获取到配置值
 */
public class config {
    /**
     * 读取/重载配置文件
     */
    public static void readConfig(JavaPlugin jp){
        FileConfiguration config = jp.getConfig();
        enableWorldsCreeper = config.getStringList("Creeper.enableWorlds");
        preventCreeperExplode = config.getBoolean("Creeper.preventExplode",false);
        CreeperExplodeGroundBreaking = config.getBoolean("Creeper.blockbreak",true);
        CreeperExplodePower = config.getInt("Creeper.explodePower",6);
        CreeperExplodeSetFire = config.getBoolean("Creeper.setFire",false);

        enableWorldsEnderman = config.getStringList("Enderman.enableWorlds");
        preventEndermanChangeBlock = config.getBoolean("Enderman.preventEndermanChangeBlock",true);
    }

    public static void checkConfig(Logger logger){

        logger.info("检查有效配置...");

        String re  = "";
        if (preventCreeperExplode){re="是";}else{re="否";}
        logger.info("完全禁止苦力怕爆炸    -> "+re);
        if (CreeperExplodeGroundBreaking){re="是";}else{re="否";}
        if (preventCreeperExplode){re = re+" (不生效,因为preventExplode为true)";}
        logger.info("自定义爆炸破坏方块    -> "+re);
        re = CreeperExplodePower+"";
        if (preventCreeperExplode){re = re+" (不生效,因为preventExplode为true)";}
        logger.info("自定义爆炸威力       -> "+re);
        if (CreeperExplodeSetFire){re="是";}else{re="否";}
        if (preventCreeperExplode){re = re+" (不生效,因为preventExplode为true)";}
        logger.info("自定义爆炸着火       -> "+re);


        re = "";
        if (enableWorldsCreeper.size()<=0){
            re = "!!!空!!!";
            logger.warning("警告: 没有设置苦力怕限制生效的世界,苦力怕限制将不会生效");
        }else{
            for (String one: enableWorldsCreeper){
                if (Bukkit.getWorld(one)==null){
                    logger.warning("警告: 在服务器中未找到世界<"+one+">,请确认世界存在,继续尝试加载...");
                    re = re + " " + one + "(可能无效)";
                    continue;
                }
                re = re + " " + one;
            }
        }
        logger.info("苦力怕限制运行世界    -> "+re);

        //========================================================
        if (preventEndermanChangeBlock){re="是";}else{re="否";}
        logger.info("禁止末影人操作方块    -> "+re);

        re = "";
        if (enableWorldsEnderman.size()<=0){
            re = "!!!空!!!";
            logger.warning("警告: 没有设置末影人限制生效的世界,末影人限制将不会生效");
        }else{
            for (String one: enableWorldsEnderman){
                if (Bukkit.getWorld(one)==null){
                    logger.warning("警告: 在服务器中未找到世界<"+one+">,请确认世界存在,继续尝试加载...");
                    re = re + " " + one + "(可能无效)";
                    continue;
                }
                re = re + " " + one;
            }
        }
        logger.info("末影人限制运行世界    -> "+re);

        logger.info("配置检查完成...");
    }

    /**
     * =======================苦力怕相关=======================
     */

    //Creeper.enableWorlds
    public static List<String> enableWorldsCreeper = new CopyOnWriteArrayList<String>();

    //Creeper.preventExplode
    public static boolean preventCreeperExplode = false;

    //Creeper.CreeperExplodeGroundBreaking
    public static boolean CreeperExplodeGroundBreaking = true;

    //Creeper.explodePower
    public static int CreeperExplodePower = 6;

    //Creeper.setFire
    public static boolean CreeperExplodeSetFire = true;

    /**
     * =======================末影人相关=======================
     */

    //Enderman.enableWorlds
    public static List<String> enableWorldsEnderman = new CopyOnWriteArrayList<String>();

    //Enderman.preventChangeBlock
    public static boolean preventEndermanChangeBlock = true;

}
