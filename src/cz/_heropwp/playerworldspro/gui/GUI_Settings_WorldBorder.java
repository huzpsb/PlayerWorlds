package cz._heropwp.playerworldspro.gui;

import cz._heropwp.playerworldspro.Main;
import cz._heropwp.playerworldspro.util.ConfigManager;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUI_Settings_WorldBorder implements Listener {
   private final Main a;

   public GUI_Settings_WorldBorder(Main var1) {
      this.a = var1;
   }

   public void a(Player var1) {
      if (!this.a.j().b().containsKey(var1.getName())
         || Bukkit.getWorld(this.a.getBasicManager().getWorldPrefix() + this.a.j().b().get(var1.getName())) == null) {
         var1.closeInventory();
         var1.sendMessage(
            this.a.getBasicManager().getMessagePrefix()
               + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Unloaded-World").replace("&", "§")
         );
      } else if (this.a.getConfig().getBoolean("Permissions.World-Border") && !var1.hasPermission("PlayerWorldsPro.worldBorder")) {
         var1.closeInventory();
         var1.sendMessage(
            this.a.getBasicManager().getMessagePrefix()
               + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.World-Border.Insufficient-Permission").replace("&", "§")
         );
      } else {
         Inventory var2 = Bukkit.createInventory(
            null, 27, this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.World-Border.Title").replace("&", "§")
         );
         World var3 = Bukkit.getWorld(this.a.getBasicManager().getWorldPrefix() + this.a.j().b().get(var1.getName()));
         double var4 = var3.getWorldBorder().getSize();
         var1.openInventory(var2);
         var2.setItem(10, this.a(var4));
         var2.setItem(12, this.a());
         var2.setItem(14, this.b());
         var2.setItem(16, this.c());
      }
   }

   private ItemStack a(double var1) {
      ItemStack var3 = new ItemStack(Material.ARROW);
      ItemMeta var4 = var3.getItemMeta();
      var4.setDisplayName(
         this.a
            .getConfigManager()
            .get(ConfigManager.Type.MESSAGES)
            .getString("GUI.World-Border.Items.Change.Displayname")
            .replace("&", "§")
            .replace("%size%", String.valueOf(var1))
      );
      ArrayList var5 = new ArrayList();

      for (String var7 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.World-Border.Items.Change.Lore")) {
         var7 = var7.replace("&", "§");
         var5.add(var7);
      }

      var4.setLore(var5);
      var3.setItemMeta(var4);
      return var3;
   }

   private ItemStack a() {
      ItemStack var1 = new ItemStack(Material.COMPASS);
      ItemMeta var2 = var1.getItemMeta();
      var2.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.World-Border.Items.Center.Displayname").replace("&", "§"));
      ArrayList var3 = new ArrayList();

      for (String var5 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.World-Border.Items.Center.Lore")) {
         var5 = var5.replace("&", "§");
         var3.add(var5);
      }

      var2.setLore(var3);
      var1.setItemMeta(var2);
      return var1;
   }

   private ItemStack b() {
      ItemStack var1 = new ItemStack(Material.PAPER);
      ItemMeta var2 = var1.getItemMeta();
      var2.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.World-Border.Items.Set.Displayname").replace("&", "§"));
      ArrayList var3 = new ArrayList();

      for (String var5 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.World-Border.Items.Set.Lore")) {
         var5 = var5.replace("&", "§");
         var3.add(var5);
      }

      var2.setLore(var3);
      var1.setItemMeta(var2);
      return var1;
   }

   private ItemStack c() {
      ItemStack var1 = new ItemStack(Material.BARRIER);
      ItemMeta var2 = var1.getItemMeta();
      var2.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.World-Border.Items.Reset.Displayname").replace("&", "§"));
      ArrayList var3 = new ArrayList();

      for (String var5 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.World-Border.Items.Reset.Lore")) {
         var5 = var5.replace("&", "§");
         var3.add(var5);
      }

      var2.setLore(var3);
      var1.setItemMeta(var2);
      return var1;
   }

   @EventHandler
   public void a(InventoryClickEvent var1) {
      Player var2 = (Player)var1.getWhoClicked();
      if (!var1.getView().getTitle().isEmpty()
         && var1.getView().getTitle().equals(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.World-Border.Title").replace("&", "§"))) {
         if (var1.getCurrentItem() != null) {
            if (var1.getCurrentItem().hasItemMeta()) {
               if (var1.getCurrentItem().getItemMeta().hasDisplayName()) {
                  var1.setCancelled(true);
                  if (this.a.j().b().containsKey(var2.getName())
                     && Bukkit.getWorld(this.a.getBasicManager().getWorldPrefix() + this.a.j().b().get(var2.getName())) != null) {
                     String var3 = var1.getCurrentItem().getItemMeta().getDisplayName();
                     String var4 = this.a.j().b().get(var2.getName());
                     World var5 = Bukkit.getWorld(this.a.getBasicManager().getWorldPrefix() + var4);
                     WorldBorder var6 = var5.getWorldBorder();
                     double var7 = var6.getSize();
                     if (var3.equals(
                        this.a
                           .getConfigManager()
                           .get(ConfigManager.Type.MESSAGES)
                           .getString("GUI.World-Border.Items.Change.Displayname")
                           .replace("&", "§")
                           .replace("%size%", String.valueOf(var7))
                     )) {
                        if (var1.isLeftClick()) {
                           if (var7 > 100.0) {
                              var6.setSize(var7 - 100.0);
                           } else {
                              var6.setSize(1.0);
                           }
                        } else if (var1.isRightClick()) {
                           var6.setSize(var7 + 100.0);
                        }

                        var1.getView().getTopInventory().setItem(var1.getSlot(), this.a(var6.getSize()));
                        return;
                     }

                     if (var3.equals(
                        this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.World-Border.Items.Center.Displayname").replace("&", "§")
                     )) {
                        var2.closeInventory();
                        var2.updateInventory();
                        if (var5.getName().equals(var2.getWorld().getName())) {
                           var6.setCenter(var2.getLocation());
                           var2.sendMessage(
                              this.a.getBasicManager().getMessagePrefix()
                                 + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.World-Border.Center").replace("&", "§")
                           );
                        } else {
                           var2.sendMessage(
                              this.a.getBasicManager().getMessagePrefix()
                                 + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Same-World").replace("&", "§")
                           );
                        }
                     } else if (var3.equals(
                        this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.World-Border.Items.Set.Displayname").replace("&", "§")
                     )) {
                        var6.setSize(100.0);
                        var2.sendMessage(
                           this.a.getBasicManager().getMessagePrefix()
                              + this.a
                                 .getConfigManager()
                                 .get(ConfigManager.Type.MESSAGES)
                                 .getString("Messages.World-Border.Set")
                                 .replace("&", "§")
                                 .replace("%size%", "100")
                        );
                     } else if (var3.equals(
                        this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.World-Border.Items.Reset.Displayname").replace("&", "§")
                     )) {
                        var6.setSize(2.9999984E7);
                        var2.sendMessage(
                           this.a.getBasicManager().getMessagePrefix()
                              + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.World-Border.Reset").replace("&", "§")
                        );
                     }
                  }

                  var2.closeInventory();
                  var2.updateInventory();
                  this.a.j().b().remove(var2.getName());
               }
            }
         }
      }
   }
}
