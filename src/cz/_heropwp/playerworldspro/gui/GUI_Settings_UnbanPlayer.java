package cz._heropwp.playerworldspro.gui;

import cz._heropwp.playerworldspro.Main;
import cz._heropwp.playerworldspro.util.ConfigManager;
import cz._heropwp.playerworldspro.util.MaterialManager;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class GUI_Settings_UnbanPlayer implements Listener {
   private final Main a;

   public GUI_Settings_UnbanPlayer(Main var1) {
      this.a = var1;
   }

   public void a(Player var1) {
      if (!this.a.j().b().containsKey(var1.getName())) {
         var1.closeInventory();
         var1.updateInventory();
      } else if (this.a.getConfig().getBoolean("Permissions.Ban") && !var1.hasPermission("PlayerWorldsPro.ban")) {
         var1.closeInventory();
         var1.sendMessage(
            this.a.getBasicManager().getMessagePrefix()
               + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Ban.Insufficient-Permission").replace("&", "§")
         );
      } else {
         Inventory var2 = Bukkit.createInventory(
            null, 54, this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Unban-Player.Title").replace("&", "§")
         );
         var1.openInventory(var2);
         this.a.h().c().put(var1.getName(), 0);
         this.a(var1, var2);
      }
   }

   private void a(Player var1, Inventory var2) {
      this.a.h().b().put(var1.getName(), Bukkit.getScheduler().runTaskTimerAsynchronously(this.a, () -> this.c(var1, var2), 0L, 10L));
   }

   private void b(Player var1, Inventory var2) {
      this.a.h().a(var1.getName(), false);
      this.a.h().a(9, var2);
      this.a(var1, var2);
   }

   private void c(Player var1, Inventory var2) {
      var2.setItem(0, this.a(var1.getName()));
      var2.setItem(8, this.b(var1.getName()));
      int var3 = 9;
      int var4 = 1;
      if (this.a.j().b().containsKey(var1.getName()) && this.a.h().c().containsKey(var1.getName())) {
         String var5 = this.a.j().b().get(var1.getName());
         if (this.a.getConfigManager().get(ConfigManager.Type.DATA).contains("Worlds." + var5 + ".Banned")) {
            for (String var7 : this.a.getConfigManager().get(ConfigManager.Type.DATA).getStringList("Worlds." + var5 + ".Banned")) {
               if (var3 >= var2.getSize()) {
                  break;
               }

               if (!this.a.h().c().containsKey(var1.getName())) {
                  this.a.h().a(var1.getName(), true);
                  return;
               }

               if (var4 > this.a.h().c().get(var1.getName()) * 45) {
                  ItemStack var8 = new ItemStack(this.a.getMaterialManager().get(MaterialManager.Type.PLAYER_HEAD), 1, (short)SkullType.PLAYER.ordinal());
                  SkullMeta var9 = (SkullMeta)var8.getItemMeta();
                  if (this.a.h().d().contains(var1.getName())) {
                     var9.setOwner(var7);
                  }

                  var9.setDisplayName(
                     this.a
                        .getConfigManager()
                        .get(ConfigManager.Type.MESSAGES)
                        .getString("GUI.Unban-Player.Items.Player.Displayname")
                        .replace("&", "§")
                        .replace("%player%", var7)
                  );
                  ArrayList var10 = new ArrayList();

                  for (String var12 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Unban-Player.Items.Player.Lore")) {
                     var12 = var12.replace("&", "§");
                     var10.add(var12);
                  }

                  var9.setLore(var10);
                  var8.setItemMeta(var9);
                  var2.setItem(var3, var8);
                  var3++;
               }

               var4++;
            }
         }
      }

      this.a.h().d().add(var1.getName());
      this.a.h().a(var3, var2);
   }

   private ItemStack a(String var1) {
      if (this.a.h().c().get(var1) > 0) {
         ItemStack var2 = new ItemStack(Material.ARROW);
         ItemMeta var3 = var2.getItemMeta();
         var3.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Unban-Player.Items.Previous").replace("&", "§"));
         var2.setItemMeta(var3);
         return var2;
      } else {
         return new ItemStack(Material.AIR);
      }
   }

   private ItemStack b(String var1) {
      int var2 = 0;
      if (this.a.j().b().containsKey(var1)) {
         String var3 = this.a.j().b().get(var1);
         if (this.a.getConfigManager().get(ConfigManager.Type.DATA).contains("Worlds." + var3 + ".Banned")) {
            var2 = this.a.getConfigManager().get(ConfigManager.Type.DATA).getStringList("Worlds." + var3 + ".Banned").size();
         }
      }

      if (var2 > (this.a.h().c().get(var1) + 1) * 45) {
         ItemStack var5 = new ItemStack(Material.ARROW);
         ItemMeta var4 = var5.getItemMeta();
         var4.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Unban-Player.Items.Next").replace("&", "§"));
         var5.setItemMeta(var4);
         return var5;
      } else {
         return new ItemStack(Material.AIR);
      }
   }

   @EventHandler
   public void a(InventoryClickEvent var1) {
      Player var2 = (Player)var1.getWhoClicked();
      if (!var1.getView().getTitle().isEmpty()
         && var1.getView().getTitle().equals(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Unban-Player.Title").replace("&", "§"))) {
         if (var1.getCurrentItem() != null) {
            if (var1.getCurrentItem().hasItemMeta()) {
               if (var1.getCurrentItem().getItemMeta().hasDisplayName()) {
                  var1.setCancelled(true);
                  if (!this.a.j().b().containsKey(var2.getName())) {
                     var2.closeInventory();
                     var2.updateInventory();
                  } else {
                     String var3 = var1.getCurrentItem().getItemMeta().getDisplayName();
                     if (var1.getCurrentItem().getType() == Material.ARROW
                        && this.a
                           .getConfigManager()
                           .get(ConfigManager.Type.MESSAGES)
                           .getString("GUI.Unban-Player.Items.Previous")
                           .replace("&", "§")
                           .contains(var3)) {
                        this.a.h().c().put(var2.getName(), this.a.h().c().get(var2.getName()) - 1);
                        this.b(var2, var2.getOpenInventory().getTopInventory());
                     } else if (var1.getCurrentItem().getType() == Material.ARROW
                        && this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Unban-Player.Items.Next").replace("&", "§").contains(var3)
                        )
                      {
                        this.a.h().c().put(var2.getName(), this.a.h().c().get(var2.getName()) + 1);
                        this.b(var2, var2.getOpenInventory().getTopInventory());
                     } else if (var1.getCurrentItem().getType() == this.a.getMaterialManager().get(MaterialManager.Type.PLAYER_HEAD)) {
                        String var4 = this.a.h().a("GUI.Unban-Player.Items.Player.Displayname", var3);
                        String var5 = this.a.j().b().get(var2.getName());
                        var2.closeInventory();
                        var2.updateInventory();
                        List var6 = this.a.getConfigManager().get(ConfigManager.Type.DATA).getStringList("Worlds." + var5 + ".Banned");
                        var6.remove(var4);
                        this.a.getConfigManager().get(ConfigManager.Type.DATA).set("Worlds." + var5 + ".Banned", var6);
                        this.a.getConfigManager().save(ConfigManager.Type.DATA);
                        this.a.getConfigManager().load(ConfigManager.Type.DATA);

                        for (Player var8 : Bukkit.getOnlinePlayers()) {
                           String var9 = this.a.getBasicManager().getOwnerFromWorldName(var8.getWorld().getName());
                           if (var9 != null && var9.equals(var5)) {
                              String var10 = this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Broadcast.Unban");
                              var10 = var10.replace("&", "§");
                              var10 = var10.replace("%player%", var4);
                              var10 = var10.replace("%executor%", var2.getName());
                              var8.sendMessage(this.a.getBasicManager().getMessagePrefix() + var10);
                           }
                        }

                        var2.sendMessage(
                           this.a.getBasicManager().getMessagePrefix()
                              + this.a
                                 .getConfigManager()
                                 .get(ConfigManager.Type.MESSAGES)
                                 .getString("Messages.Ban.Unban-Player")
                                 .replace("&", "§")
                                 .replace("%player%", var4)
                        );
                        this.a.j().b().remove(var2.getName());
                     }
                  }
               }
            }
         }
      }
   }

   @EventHandler
   public void a(InventoryCloseEvent var1) {
      Player var2 = (Player)var1.getPlayer();
      if (!var1.getView().getTitle().isEmpty()
         && var1.getView().getTitle().equals(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Unban-Player.Title").replace("&", "§"))) {
         this.a.h().a(var2.getName(), true);
      }
   }
}
