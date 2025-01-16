package cz._heropwp.playerworldspro.event;

import cz._heropwp.playerworldspro.Main;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class SettingsEvents implements Listener {
   private final Main a;

   public SettingsEvents(Main var1) {
      this.a = var1;
   }

   @EventHandler
   public void a(WeatherChangeEvent var1) {
      String var2 = var1.getWorld().getName();
      String var3 = this.a.getBasicManager().getOwnerFromWorldName(var2);
      if (this.a.getWorldManager().worldExists(var3) && !this.a.getWorldManager().ruleWeather(var3) && !this.a.r().a().contains(var3)) {
         var1.setCancelled(true);
      }
   }

   @EventHandler
   public void a(BlockBreakEvent var1) {
      Player var2 = var1.getPlayer();
      String var3 = var2.getWorld().getName();
      String var4 = this.a.getBasicManager().getOwnerFromWorldName(var3);
      if (this.a.getWorldManager().worldExists(var4) && !this.a.getWorldManager().canVisitOp(var2, var4) && !this.a.getWorldManager().ruleBlockBreaking(var4)) {
         var1.setCancelled(true);
      }
   }

   @EventHandler
   public void a(BlockPlaceEvent var1) {
      Player var2 = var1.getPlayer();
      String var3 = var2.getWorld().getName();
      String var4 = this.a.getBasicManager().getOwnerFromWorldName(var3);
      if (this.a.getWorldManager().worldExists(var4) && !this.a.getWorldManager().canVisitOp(var2, var4) && !this.a.getWorldManager().ruleBlockPlacing(var4)) {
         var1.setCancelled(true);
      }
   }

   @EventHandler
   public void a(EntityDamageByEntityEvent var1) {
      if (var1.getEntity() instanceof Player) {
         Player var2 = (Player)var1.getEntity();
         if (var1.getDamager() instanceof Player || var1.getDamager() instanceof Projectile && ((Projectile)var1.getDamager()).getShooter() instanceof Player) {
            String var3 = var2.getWorld().getName();
            String var4 = this.a.getBasicManager().getOwnerFromWorldName(var3);
            if (this.a.getWorldManager().worldExists(var4) && !this.a.getWorldManager().rulePvP(var4)) {
               var1.setCancelled(true);
            }
         }
      }
   }

   @EventHandler
   public void a(PlayerPickupItemEvent var1) {
      Player var2 = var1.getPlayer();
      String var3 = var2.getWorld().getName();
      String var4 = this.a.getBasicManager().getOwnerFromWorldName(var3);
      if (this.a.getWorldManager().worldExists(var4) && !this.a.getWorldManager().canVisitOp(var2, var4) && !this.a.getWorldManager().rulePickup(var4)) {
         var1.setCancelled(true);
      }
   }

   @EventHandler
   public void a(PlayerDropItemEvent var1) {
      Player var2 = var1.getPlayer();
      String var3 = var2.getWorld().getName();
      String var4 = this.a.getBasicManager().getOwnerFromWorldName(var3);
      if (this.a.getWorldManager().worldExists(var4) && !this.a.getWorldManager().canVisitOp(var2, var4) && !this.a.getWorldManager().ruleDrop(var4)) {
         var1.setCancelled(true);
      }
   }

   @EventHandler
   public void a(EntityDamageEvent var1) {
      if (var1.getEntity() instanceof Player) {
         Player var2 = (Player)var1.getEntity();
         String var3 = var2.getWorld().getName();
         String var4 = this.a.getBasicManager().getOwnerFromWorldName(var3);
         if (this.a.getWorldManager().worldExists(var4) && !this.a.getWorldManager().ruleDamage(var4)) {
            var1.setCancelled(true);
            if (var1.getCause() == DamageCause.VOID) {
               this.a.getBasicManager().teleport(var2, this.a.getWorldManager().getSpawn(var4), false);
            }
         }
      }
   }

   @EventHandler
   public void a(FoodLevelChangeEvent var1) {
      if (var1.getEntity() instanceof Player) {
         Player var2 = (Player)var1.getEntity();
         String var3 = var2.getWorld().getName();
         String var4 = this.a.getBasicManager().getOwnerFromWorldName(var3);
         if (this.a.getWorldManager().worldExists(var4) && !this.a.getWorldManager().ruleHunger(var4)) {
            var1.setCancelled(true);
            if (var2.getFoodLevel() < 19) {
               var2.setFoodLevel(20);
            }
         }
      }
   }

   @EventHandler
   public void a(PlayerBucketFillEvent var1) {
      Player var2 = var1.getPlayer();
      String var3 = var2.getWorld().getName();
      String var4 = this.a.getBasicManager().getOwnerFromWorldName(var3);
      if (this.a.getWorldManager().worldExists(var4) && !this.a.getWorldManager().canVisitOp(var2, var4) && !this.a.getWorldManager().ruleBucket(var4)) {
         var1.setCancelled(true);
      }
   }

   @EventHandler
   public void a(PlayerBucketEmptyEvent var1) {
      Player var2 = var1.getPlayer();
      String var3 = var2.getWorld().getName();
      String var4 = this.a.getBasicManager().getOwnerFromWorldName(var3);
      if (this.a.getWorldManager().worldExists(var4) && !this.a.getWorldManager().canVisitOp(var2, var4) && !this.a.getWorldManager().ruleBucket(var4)) {
         var1.setCancelled(true);
      }
   }
}
