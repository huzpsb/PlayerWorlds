package cz._heropwp.playerworldspro.util;

import cz._heropwp.playerworldspro.Main;
import io.papermc.lib.PaperLib;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class BasicManager {
   private final Main instance;
   private String worldPrefix;
   private String messagePrefix;
   private final String logPrefix;
   private final HashMap<String, BukkitTask> unloadTimers;

   public BasicManager(Main var1) {
      this.instance = var1;
      this.loadWorldPrefix();
      this.loadMessagePrefix();
      this.logPrefix = "§8[§6PlayerWorldsPro§8] §r";
      this.unloadTimers = new HashMap<>();
   }

   public void loadWorldPrefix() {
      this.worldPrefix = this.instance.getConfig().getString("Basic.World-Prefix");
   }

   public void loadMessagePrefix() {
      this.messagePrefix = this.instance.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Prefix").replace("&", "§");
   }

   public boolean hasLobby() {
      return this.instance.getConfigManager().get(ConfigManager.Type.DATA).contains("Lobby");
   }

   public Location getLobby() {
      if (this.hasLobby()) {
         String var1 = this.instance.getConfigManager().get(ConfigManager.Type.DATA).getString("Lobby");
         String[] var2 = var1.split(";");
         World var3 = Bukkit.getWorld(var2[0]);
         double var4 = Double.parseDouble(var2[1]);
         double var6 = Double.parseDouble(var2[2]);
         double var8 = Double.parseDouble(var2[3]);
         float var10 = Float.parseFloat(var2[4]);
         float var11 = Float.parseFloat(var2[5]);
         return new Location(var3, var4, var6, var8, var10, var11);
      } else {
         return null;
      }
   }

   public void teleport(Player var1, Location var2, boolean var3) {
      if (!var3 && this.instance.getConfig().getBoolean("Basic.Optimization.Teleport")) {
         PaperLib.teleportAsync(var1, var2);
      } else {
         var1.teleport(var2);
      }
   }

   public String getOwnerFromWorldName(String var1) {
      if (var1.startsWith(this.worldPrefix)) {
         String[] var3 = var1.split(this.worldPrefix);
         String var2 = var3[1];
         if (var1.endsWith("_nether")) {
            var3 = var2.split("_nether");
         } else if (var1.endsWith("_the_end")) {
            var3 = var2.split("_the_end");
         } else {
            var3 = null;
         }

         if (var3 != null) {
            var2 = var3[var3.length - 1];
         }

         return var2;
      } else {
         return null;
      }
   }

   public void unloadIfNecessary(World var1, String var2) {
      if (this.instance.getConfig().getBoolean("Basic.Optimization.Unload-Empty-Worlds.Enabled") && var2 != null && var1.getPlayers().size() == 0) {
         String var3 = var1.getName();
         if (!this.unloadTimers.containsKey(var3)) {
            long var4 = this.instance.getConfig().getLong("Basic.Optimization.Unload-Empty-Worlds.Delay");
            if (var4 < 0L) {
               var4 = 0L;
            }

            this.unloadTimers.put(var3, Bukkit.getScheduler().runTaskLater(this.instance, () -> {
               Bukkit.unloadWorld(var1, true);
               this.unloadTimers.remove(var3);
            }, 20L * var4));
         }
      }
   }

   public void cancelUnload(String var1) {
      if (this.unloadTimers.containsKey(var1)) {
         this.unloadTimers.get(var1).cancel();
         this.unloadTimers.remove(var1);
      }
   }

   public Main getInstance() {
      return this.instance;
   }

   public String getWorldPrefix() {
      return this.worldPrefix;
   }

   public String getMessagePrefix() {
      return this.messagePrefix;
   }

   public String getLogPrefix() {
      return this.logPrefix;
   }

   public HashMap<String, BukkitTask> unused() {
      return this.unloadTimers;
   }
}
