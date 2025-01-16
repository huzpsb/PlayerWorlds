package cz._heropwp.playerworldspro.event;

import cz._heropwp.playerworldspro.Main;
import cz._heropwp.playerworldspro.util.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.event.world.WorldInitEvent;

public class BasicEvents implements Listener {
   private final Main a;

   public BasicEvents(Main var1) {
      this.a = var1;
   }

   @EventHandler
   public void a(PlayerJoinEvent var1) {
      Player var2 = var1.getPlayer();
      String var3 = var2.getWorld().getName();
      String var4 = this.a.getBasicManager().getOwnerFromWorldName(var3);
      this.a.getBasicManager().cancelUnload(var3);
      if (this.a.getWorldManager().worldExists(var4)) {
         var2.setGameMode(GameMode.valueOf(this.a.getWorldManager().ruleDefaultGamemode(var4)));
      }
   }

   @EventHandler
   public void a(PlayerQuitEvent var1) {
      Player var2 = var1.getPlayer();
      this.a.h().a(var2.getName(), true);
      this.a.j().b().remove(var2.getName());
      World var3 = var2.getWorld();
      String var4 = this.a.getBasicManager().getOwnerFromWorldName(var3.getName());
      this.a.getBasicManager().unloadIfNecessary(var3, var4);
   }

   @EventHandler(
      priority = EventPriority.HIGHEST
   )
   public void a(PlayerRespawnEvent var1) {
      Player var2 = var1.getPlayer();
      if (this.a.getConfig().getBoolean("Basic.Respawn")) {
         String var3 = var2.getWorld().getName();
         String var4 = this.a.getBasicManager().getOwnerFromWorldName(var3);
         if (this.a.getWorldManager().worldExists(var4)) {
            this.a.getWorldManager().d(var4, false);
            if (this.a.getConfigManager().get(ConfigManager.Type.DATA).getStringList("Worlds." + var4 + ".Banned").contains(var2.getName())) {
               var2.sendMessage(
                  this.a.getBasicManager().getMessagePrefix()
                     + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Ban.Teleport").replace("&", "§")
               );
               return;
            }

            if (this.a.getWorldManager().ruleAccessibility(var4).equals("PRIVATE") && !this.a.getWorldManager().canVisit(var2, var4)) {
               var2.sendMessage(
                  this.a.getBasicManager().getMessagePrefix()
                     + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Access.Only-For-Members").replace("&", "§")
               );
               return;
            }

            Location var5 = this.a.getWorldManager().getSpawn(var4);
            if (!this.a(var5)) {
               var1.setRespawnLocation(var5);
            }
         }
      }
   }

   private boolean a(Location var1) {
      WorldBorder var2 = var1.getWorld().getWorldBorder();
      double var3 = var2.getSize() / 2.0;
      Location var5 = var2.getCenter();
      double var6 = var1.getX() - var5.getX();
      double var8 = var1.getZ() - var5.getZ();
      return var6 > var3 || -var6 > var3 || var8 > var3 || -var8 > var3;
   }

   @EventHandler
   public void a(WorldInitEvent var1) {
      World var2 = var1.getWorld();
      String var3 = this.a.getBasicManager().getOwnerFromWorldName(var2.getName());
      if (this.a.getWorldManager().worldExists(var3)) {
         var2.setKeepSpawnInMemory(this.a.getConfig().getBoolean("Basic.Optimization.Keep-Spawn-In-Memory"));
         var2.setDifficulty(Difficulty.valueOf(this.a.getWorldManager().ruleDifficulty(var3)));
      }
   }

   @EventHandler
   public void a(PlayerTeleportEvent var1) {
      World var2 = var1.getFrom().getWorld();
      World var3 = var1.getTo().getWorld();
      String var4 = this.a.getBasicManager().getOwnerFromWorldName(var3.getName());
      if (var1.getCause() == TeleportCause.SPECTATE) {
         String var5 = this.a.getBasicManager().getOwnerFromWorldName(var2.getName());
         if (var5 != null) {
            var1.setCancelled(!var5.equals(var4));
            return;
         }
      }

      if (this.a.getWorldManager().worldExists(var4)) {
         Player var6 = var1.getPlayer();
         if (this.a.getWorldManager().d(var6, var4)) {
            var6.sendMessage(
               this.a.getBasicManager().getMessagePrefix()
                  + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Ban.Teleport").replace("&", "§")
            );
            var1.setCancelled(true);
            if (var2 == var3) {
               Bukkit.getScheduler().runTaskLater(this.a, () -> this.a.getBasicManager().teleport(var6, this.a.getBasicManager().getLobby(), false), 1L);
            }
         } else if (this.a.getWorldManager().ruleAccessibility(var4).equals("PRIVATE") && !this.a.getWorldManager().canVisit(var6, var4)) {
            var6.sendMessage(
               this.a.getBasicManager().getMessagePrefix()
                  + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Access.Only-For-Members").replace("&", "§")
            );
            var1.setCancelled(true);
            if (var2 == var3) {
               Bukkit.getScheduler().runTaskLater(this.a, () -> this.a.getBasicManager().teleport(var6, this.a.getBasicManager().getLobby(), false), 1L);
            }
         }
      }
   }

   @EventHandler
   public void a(PlayerPortalEvent var1) {
      if (this.a.getConfig().getBoolean("Basic.Portals")) {
         Player var2 = var1.getPlayer();
         String var3 = var2.getWorld().getName();
         String var4 = this.a.getBasicManager().getOwnerFromWorldName(var3);
         if (this.a.getWorldManager().worldExists(var4)) {
            if (var1.getCause() == TeleportCause.NETHER_PORTAL) {
               if (var3.endsWith("_nether")) {
                  this.a.getWorldManager().netherToWorld(var2, var1);
               } else {
                  this.a.getWorldManager().worldToNether(var2, var1);
               }
            } else if (var1.getCause() == TeleportCause.END_PORTAL) {
               var1.setCancelled(true);
               if (var3.endsWith("_the_end")) {
                  this.a.getWorldManager().moveTo(var2, var4, false);
               } else {
                  this.a.getWorldManager().worldToEnd(var2);
               }
            }
         }
      }
   }

   @EventHandler
   public void a(PlayerChangedWorldEvent var1) {
      Player var2 = var1.getPlayer();
      World var3 = var2.getWorld();
      String var4 = this.a.getBasicManager().getOwnerFromWorldName(var3.getName());
      World var5 = var1.getFrom();
      String var6 = this.a.getBasicManager().getOwnerFromWorldName(var5.getName());
      this.a.getBasicManager().cancelUnload(var3.getName());
      if (this.a.getConfig().getBoolean("JoinAndLeaveMSG.Enabled")) {
         if (this.a.getConfig().getBoolean("JoinAndLeaveMSG.Bypass") && var2.hasPermission("PlayerWorldsPro.bypass.JoinAndLeaveMSG")) {
            return;
         }

         if (var4 != null && !var4.equals(var6)) {
            for (String var8 : this.a.getWorldManager().getOwnWorlds(this.a.getBasicManager().getWorldPrefix() + var4)) {
               World var9 = Bukkit.getWorld(var8);
               if (var9 != null) {
                  for (Player var11 : var9.getPlayers()) {
                     if (var11 != var2) {
                        var11.sendMessage(
                           this.a
                              .getConfigManager()
                              .get(ConfigManager.Type.MESSAGES)
                              .getString("JoinAndLeaveMSG.Join")
                              .replace("&", "§")
                              .replace("%player%", var2.getName())
                        );
                     }
                  }
               }
            }
         }

         if (var6 != null && !var6.equals(var4)) {
            for (String var13 : this.a.getWorldManager().getOwnWorlds(this.a.getBasicManager().getWorldPrefix() + var6)) {
               World var14 = Bukkit.getWorld(var13);
               if (var14 != null) {
                  for (Player var16 : var14.getPlayers()) {
                     var16.sendMessage(
                        this.a
                           .getConfigManager()
                           .get(ConfigManager.Type.MESSAGES)
                           .getString("JoinAndLeaveMSG.Leave")
                           .replace("&", "§")
                           .replace("%player%", var2.getName())
                     );
                  }
               }
            }
         }
      }

      if (var4 != null) {
         var2.setGameMode(GameMode.valueOf(this.a.getWorldManager().ruleDefaultGamemode(var4)));
      }

      this.a.getBasicManager().unloadIfNecessary(var5, var6);
   }
}
