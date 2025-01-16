package cz._heropwp.playerworldspro.gui;

import cz._heropwp.playerworldspro.Main;
import cz._heropwp.playerworldspro.util.ConfigManager;
import cz._heropwp.playerworldspro.util.MaterialManager;
import java.util.ArrayList;
import java.util.HashSet;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUI_Settings_ChangeWeather implements Listener {
   private final Main a;
   private final HashSet<String> b;

   public GUI_Settings_ChangeWeather(Main var1) {
      this.a = var1;
      this.b = new HashSet<>();
   }

   public void a(Player var1) {
      if (!this.a.j().b().containsKey(var1.getName())
         || Bukkit.getWorld(this.a.getBasicManager().getWorldPrefix() + this.a.j().b().get(var1.getName())) == null) {
         var1.closeInventory();
         var1.sendMessage(
            this.a.getBasicManager().getMessagePrefix()
               + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Unloaded-World").replace("&", "§")
         );
      } else if (this.a.getConfig().getBoolean("Permissions.Change-Weather") && !var1.hasPermission("PlayerWorldsPro.changeWeather")) {
         var1.closeInventory();
         var1.sendMessage(
            this.a.getBasicManager().getMessagePrefix()
               + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Change-Weather.Insufficient-Permission").replace("&", "§")
         );
      } else {
         Inventory var2 = Bukkit.createInventory(
            null, 27, this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Weather.Title").replace("&", "§")
         );
         var1.openInventory(var2);
         var2.setItem(10, this.b());
         var2.setItem(11, this.c());
         var2.setItem(12, this.d());
         var2.setItem(16, this.a(var1.getName()));
      }
   }

   private ItemStack b() {
      ItemStack var1 = new ItemStack(Material.DEAD_BUSH);
      ItemMeta var2 = var1.getItemMeta();
      var2.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Weather.Items.Clear.Displayname").replace("&", "§"));
      ArrayList var3 = new ArrayList();

      for (String var5 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Change-Weather.Items.Clear.Lore")) {
         var5 = var5.replace("&", "§");
         var3.add(var5);
      }

      var2.setLore(var3);
      var1.setItemMeta(var2);
      return var1;
   }

   private ItemStack c() {
      ItemStack var1 = new ItemStack(Material.WATER_BUCKET);
      ItemMeta var2 = var1.getItemMeta();
      var2.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Weather.Items.Rain.Displayname").replace("&", "§"));
      ArrayList var3 = new ArrayList();

      for (String var5 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Change-Weather.Items.Rain.Lore")) {
         var5 = var5.replace("&", "§");
         var3.add(var5);
      }

      var2.setLore(var3);
      var1.setItemMeta(var2);
      return var1;
   }

   private ItemStack d() {
      ItemStack var1 = new ItemStack(Material.WATER_BUCKET);
      ItemMeta var2 = var1.getItemMeta();
      var2.setDisplayName(
         this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Weather.Items.Thunder.Displayname").replace("&", "§")
      );
      ArrayList var3 = new ArrayList();

      for (String var5 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Change-Weather.Items.Thunder.Lore")) {
         var5 = var5.replace("&", "§");
         var3.add(var5);
      }

      var2.setLore(var3);
      var1.setItemMeta(var2);
      return var1;
   }

   private ItemStack a(String var1) {
      String var2 = "Unlock";
      if (this.a.getConfigManager().get(ConfigManager.Type.DATA).getBoolean("Worlds." + var1 + ".WeatherCycle")) {
         var2 = "Lock";
      }

      ItemStack var3 = new ItemStack(this.a.getMaterialManager().get(MaterialManager.Type.DEAD_BUSH));
      ItemMeta var4 = var3.getItemMeta();
      var4.setDisplayName(
         this.a
            .getConfigManager()
            .get(ConfigManager.Type.MESSAGES)
            .getString("GUI.Change-Weather.Items.Weather-Cycle-" + var2 + ".Displayname")
            .replace("&", "§")
      );
      ArrayList var5 = new ArrayList();

      for (String var7 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Change-Weather.Items.Weather-Cycle-" + var2 + ".Lore")) {
         var7 = var7.replace("&", "§");
         var5.add(var7);
      }

      var4.setLore(var5);
      var3.setItemMeta(var4);
      return var3;
   }

   @EventHandler
   public void a(InventoryClickEvent var1) {
      Player var2 = (Player)var1.getWhoClicked();
      if (!var1.getView().getTitle().isEmpty()
         && var1.getView()
            .getTitle()
            .equals(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Weather.Title").replace("&", "§"))) {
         if (var1.getCurrentItem() != null) {
            if (var1.getCurrentItem().hasItemMeta()) {
               if (var1.getCurrentItem().getItemMeta().hasDisplayName()) {
                  var1.setCancelled(true);
                  if (this.a.j().b().containsKey(var2.getName())
                     && Bukkit.getWorld(this.a.getBasicManager().getWorldPrefix() + this.a.j().b().get(var2.getName())) != null) {
                     String var3 = var1.getCurrentItem().getItemMeta().getDisplayName();
                     String var4 = this.a.j().b().get(var2.getName());
                     World var5 = Bukkit.getWorld(this.a.getBasicManager().getWorldPrefix() + var4);
                     var2.closeInventory();
                     var2.updateInventory();
                     if (var5 != null) {
                        if (var3.equals(
                           this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Weather.Items.Clear.Displayname").replace("&", "§")
                        )) {
                           this.a(var5, var4, false, false);
                           var2.sendMessage(
                              this.a.getBasicManager().getMessagePrefix()
                                 + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Change-Weather.Clear").replace("&", "§")
                           );
                        } else if (var3.equals(
                           this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Weather.Items.Rain.Displayname").replace("&", "§")
                        )) {
                           this.a(var5, var4, false, true);
                           var2.sendMessage(
                              this.a.getBasicManager().getMessagePrefix()
                                 + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Change-Weather.Rain").replace("&", "§")
                           );
                        } else if (var3.equals(
                           this.a
                              .getConfigManager()
                              .get(ConfigManager.Type.MESSAGES)
                              .getString("GUI.Change-Weather.Items.Thunder.Displayname")
                              .replace("&", "§")
                        )) {
                           this.a(var5, var4, true, true);
                           var2.sendMessage(
                              this.a.getBasicManager().getMessagePrefix()
                                 + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Change-Weather.Thunder").replace("&", "§")
                           );
                        } else if (var3.equals(
                              this.a
                                 .getConfigManager()
                                 .get(ConfigManager.Type.MESSAGES)
                                 .getString("GUI.Change-Weather.Items.Weather-Cycle-Lock.Displayname")
                                 .replace("&", "§")
                           )
                           && this.a.getWorldManager().ruleWeather(var4)) {
                           this.a(var4, false);
                           var2.sendMessage(
                              this.a.getBasicManager().getMessagePrefix()
                                 + this.a
                                    .getConfigManager()
                                    .get(ConfigManager.Type.MESSAGES)
                                    .getString("Messages.Change-Weather.Weather-Cycle.Lock")
                                    .replace("&", "§")
                           );
                        } else if (var3.equals(
                              this.a
                                 .getConfigManager()
                                 .get(ConfigManager.Type.MESSAGES)
                                 .getString("GUI.Change-Weather.Items.Weather-Cycle-Unlock.Displayname")
                                 .replace("&", "§")
                           )
                           && !this.a.getWorldManager().ruleWeather(var4)) {
                           this.a(var4, true);
                           var2.sendMessage(
                              this.a.getBasicManager().getMessagePrefix()
                                 + this.a
                                    .getConfigManager()
                                    .get(ConfigManager.Type.MESSAGES)
                                    .getString("Messages.Change-Weather.Weather-Cycle.Unlock")
                                    .replace("&", "§")
                           );
                        }
                     }
                  } else {
                     var2.closeInventory();
                     var2.updateInventory();
                  }

                  this.a.j().b().remove(var2.getName());
               }
            }
         }
      }
   }

   private void a(World var1, String var2, boolean var3, boolean var4) {
      this.b.add(var2);
      var1.setThundering(var3);
      var1.setStorm(var4);
      this.b.remove(var2);
   }

   private void a(String var1, boolean var2) {
      this.a.getConfigManager().get(ConfigManager.Type.DATA).set("Worlds." + var1 + ".WeatherCycle", var2);
      this.a.getConfigManager().save(ConfigManager.Type.DATA);
      this.a.getConfigManager().load(ConfigManager.Type.DATA);
   }

   public HashSet<String> a() {
      return this.b;
   }
}
