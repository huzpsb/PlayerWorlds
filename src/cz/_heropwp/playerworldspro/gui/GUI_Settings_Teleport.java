package cz._heropwp.playerworldspro.gui;

import cz._heropwp.playerworldspro.Main;
import cz._heropwp.playerworldspro.util.ConfigManager;
import cz._heropwp.playerworldspro.util.MaterialManager;
import java.util.ArrayList;
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

public class GUI_Settings_Teleport implements Listener {
   private final Main a;

   public GUI_Settings_Teleport(Main var1) {
      this.a = var1;
   }

   public void a(Player var1) {
      if (this.a.getConfig().getBoolean("Permissions.Teleport") && !var1.hasPermission("PlayerWorldsPro.teleport")) {
         var1.closeInventory();
         var1.sendMessage(
            this.a.getBasicManager().getMessagePrefix()
               + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Insufficient-Permission").replace("&", "§")
         );
      } else {
         Inventory var2 = Bukkit.createInventory(
            null, 54, this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Teleport.Title").replace("&", "§")
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
         String var5 = this.a.getBasicManager().getWorldPrefix();
         String var6 = this.a.j().b().get(var1.getName());

         for (Player var8 : Bukkit.getOnlinePlayers()) {
            if (!var8.getName().equals(var1.getName())) {
               for (String var10 : this.a.getWorldManager().getOwnWorlds(var5 + var6)) {
                  if (var8.getWorld().getName().equals(var10)) {
                     if (var3 >= var2.getSize()) {
                        break;
                     }

                     if (!this.a.h().c().containsKey(var1.getName())) {
                        this.a.h().a(var1.getName(), true);
                        return;
                     }

                     if (var4 > this.a.h().c().get(var1.getName()) * 45) {
                        ItemStack var11 = new ItemStack(this.a.getMaterialManager().get(MaterialManager.Type.PLAYER_HEAD), 1, (short)SkullType.PLAYER.ordinal());
                        SkullMeta var12 = (SkullMeta)var11.getItemMeta();
                        if (this.a.h().d().contains(var1.getName())) {
                           var12.setOwner(var8.getName());
                        }

                        var12.setDisplayName(
                           this.a
                              .getConfigManager()
                              .get(ConfigManager.Type.MESSAGES)
                              .getString("GUI.Teleport.Items.Player.Displayname")
                              .replace("&", "§")
                              .replace("%player%", var8.getName())
                        );
                        ArrayList var13 = new ArrayList();

                        for (String var15 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Teleport.Items.Player.Lore")) {
                           var15 = var15.replace("&", "§");
                           var13.add(var15);
                        }

                        var12.setLore(var13);
                        var11.setItemMeta(var12);
                        var2.setItem(var3, var11);
                        var3++;
                     }

                     var4++;
                  }
               }
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
         var3.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Teleport.Items.Previous").replace("&", "§"));
         var2.setItemMeta(var3);
         return var2;
      } else {
         return new ItemStack(Material.AIR);
      }
   }

   private ItemStack b(String var1) {
      int var2 = 0;
      String var3 = this.a.getBasicManager().getWorldPrefix();
      String var4 = this.a.j().b().get(var1);

      for (Player var6 : Bukkit.getOnlinePlayers()) {
         if (!var6.getName().equals(var1) && var6.getWorld().getName().equals(var3 + var4)) {
            var2++;
         }
      }

      if (var2 > (this.a.h().c().get(var1) + 1) * 45) {
         ItemStack var7 = new ItemStack(Material.ARROW);
         ItemMeta var8 = var7.getItemMeta();
         var8.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Teleport.Items.Next").replace("&", "§"));
         var7.setItemMeta(var8);
         return var7;
      } else {
         return new ItemStack(Material.AIR);
      }
   }

   @EventHandler
   public void a(InventoryClickEvent var1) {
      Player var2 = (Player)var1.getWhoClicked();
      if (!var1.getView().getTitle().isEmpty()
         && var1.getView().getTitle().equals(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Teleport.Title").replace("&", "§"))) {
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
                        && this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Teleport.Items.Previous").replace("&", "§").contains(var3)
                        )
                      {
                        this.a.h().c().put(var2.getName(), this.a.h().c().get(var2.getName()) - 1);
                        this.b(var2, var2.getOpenInventory().getTopInventory());
                     } else if (var1.getCurrentItem().getType() == Material.ARROW
                        && this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Teleport.Items.Next").replace("&", "§").contains(var3)) {
                        this.a.h().c().put(var2.getName(), this.a.h().c().get(var2.getName()) + 1);
                        this.b(var2, var2.getOpenInventory().getTopInventory());
                     } else if (var1.getCurrentItem().getType() == this.a.getMaterialManager().get(MaterialManager.Type.PLAYER_HEAD)) {
                        Player var4 = Bukkit.getPlayer(this.a.h().a("GUI.Teleport.Items.Player.Displayname", var3));
                        var2.closeInventory();
                        var2.updateInventory();
                        if (var4 == null || !var4.isOnline()) {
                           return;
                        }

                        this.a.getBasicManager().teleport(var2, var4.getLocation(), false);
                        var2.sendMessage(
                           this.a.getBasicManager().getMessagePrefix()
                              + this.a
                                 .getConfigManager()
                                 .get(ConfigManager.Type.MESSAGES)
                                 .getString("Messages.Teleport.To")
                                 .replace("&", "§")
                                 .replace("%player%", var4.getName())
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
         && var1.getView().getTitle().equals(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Teleport.Title").replace("&", "§"))) {
         this.a.h().a(var2.getName(), true);
      }
   }
}
