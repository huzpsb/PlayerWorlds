package cz._heropwp.playerworldspro.util;

import com.google.common.base.Charsets;
import cz._heropwp.playerworldspro.Main;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {
   private final Main instance;
   private final HashMap<ConfigManager.Type, File> t2f;
   private final HashMap<ConfigManager.Type, FileConfiguration> t2c;

   public ConfigManager(Main var1) {
      this.instance = var1;
      this.t2f = new HashMap<>();
      this.t2c = new HashMap<>();
   }

   private String getFileName(ConfigManager.Type var1) {
      return var1.toString().toLowerCase() + ".yml";
   }

   public void init() {
      if (!this.instance.getDataFolder().exists()) {
         this.instance.getDataFolder().mkdir();
      }

      File var1 = new File(this.instance.getDataFolder(), "maps");
      if (!var1.exists()) {
         var1.mkdir();
      }

      for (ConfigManager.Type var5 : ConfigManager.Type.values()) {
         File var6 = new File(this.instance.getDataFolder(), this.getFileName(var5));
         if (!var6.exists()) {
            this.instance.saveResource(this.getFileName(var5), false);
         }

         this.t2f.put(var5, var6);
         this.load(var5);
      }
   }

   public FileConfiguration get(ConfigManager.Type var1) {
      return this.t2c.get(var1);
   }

   public void save(ConfigManager.Type var1) {
      try {
         this.t2c.get(var1).save(this.t2f.get(var1));
      } catch (IOException var3) {
         var3.printStackTrace();
      }
   }

   public void load(ConfigManager.Type var1) {
      this.t2c.put(var1, YamlConfiguration.loadConfiguration(this.t2f.get(var1)));
      InputStream var2 = this.instance.getResource(this.getFileName(var1));
      if (var2 != null) {
         this.t2c.get(var1).setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(var2, Charsets.UTF_8)));
      }
   }

   public static enum Type {
      DATA,
      MESSAGES,
      PLAYERS;
   }
}
