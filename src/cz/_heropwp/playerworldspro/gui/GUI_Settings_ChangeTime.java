package cz._heropwp.playerworldspro.gui;

import cz._heropwp.playerworldspro.Main;
import cz._heropwp.playerworldspro.util.ConfigManager;
import cz._heropwp.playerworldspro.util.MaterialManager;
import java.util.ArrayList;
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

public class GUI_Settings_ChangeTime implements Listener {
   private final Main a;

   public GUI_Settings_ChangeTime(Main var1) {
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
      } else if (this.a.getConfig().getBoolean("Permissions.Change-Time") && !var1.hasPermission("PlayerWorldsPro.changeTime")) {
         var1.closeInventory();
         var1.sendMessage(
            this.a.getBasicManager().getMessagePrefix()
               + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Change-Time.Insufficient-Permission").replace("&", "§")
         );
      } else {
         Inventory var2 = Bukkit.createInventory(
            null, 27, this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Time.Title").replace("&", "§")
         );
         var1.openInventory(var2);
         var2.setItem(10, this.a());
         var2.setItem(11, this.b());
         var2.setItem(12, this.c());
         var2.setItem(13, this.d());
         var2.setItem(16, this.a(this.a.getBasicManager().getWorldPrefix() + this.a.j().b().get(var1.getName())));
      }
   }

   private ItemStack a() {
      ItemStack var1 = new ItemStack(this.a.getMaterialManager().get(MaterialManager.Type.CLOCK));
      ItemMeta var2 = var1.getItemMeta();
      var2.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Time.Items.Day.Displayname").replace("&", "§"));
      ArrayList var3 = new ArrayList();

      for (String var5 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Change-Time.Items.Day.Lore")) {
         var5 = var5.replace("&", "§");
         var3.add(var5);
      }

      var2.setLore(var3);
      var1.setItemMeta(var2);
      return var1;
   }

   private ItemStack b() {
      ItemStack var1 = new ItemStack(this.a.getMaterialManager().get(MaterialManager.Type.CLOCK));
      ItemMeta var2 = var1.getItemMeta();
      var2.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Time.Items.Sunset.Displayname").replace("&", "§"));
      ArrayList var3 = new ArrayList();

      for (String var5 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Change-Time.Items.Sunset.Lore")) {
         var5 = var5.replace("&", "§");
         var3.add(var5);
      }

      var2.setLore(var3);
      var1.setItemMeta(var2);
      return var1;
   }

   private ItemStack c() {
      ItemStack var1 = new ItemStack(this.a.getMaterialManager().get(MaterialManager.Type.CLOCK));
      ItemMeta var2 = var1.getItemMeta();
      var2.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Time.Items.Night.Displayname").replace("&", "§"));
      ArrayList var3 = new ArrayList();

      for (String var5 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Change-Time.Items.Night.Lore")) {
         var5 = var5.replace("&", "§");
         var3.add(var5);
      }

      var2.setLore(var3);
      var1.setItemMeta(var2);
      return var1;
   }

   private ItemStack d() {
      ItemStack var1 = new ItemStack(this.a.getMaterialManager().get(MaterialManager.Type.CLOCK));
      ItemMeta var2 = var1.getItemMeta();
      var2.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Time.Items.Sunrise.Displayname").replace("&", "§"));
      ArrayList var3 = new ArrayList();

      for (String var5 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Change-Time.Items.Sunrise.Lore")) {
         var5 = var5.replace("&", "§");
         var3.add(var5);
      }

      var2.setLore(var3);
      var1.setItemMeta(var2);
      return var1;
   }

   private ItemStack a(String var1) {
      World var2 = Bukkit.getWorld(var1);
      if (var2 == null) {
         return new ItemStack(Material.AIR);
      } else {
         String var3 = "Unlock";
         if (Boolean.parseBoolean(var2.getGameRuleValue("doDaylightCycle"))) {
            var3 = "Lock";
         }

         ItemStack var4 = new ItemStack(this.a.getMaterialManager().get(MaterialManager.Type.DEAD_BUSH));
         ItemMeta var5 = var4.getItemMeta();
         var5.setDisplayName(
            this.a
               .getConfigManager()
               .get(ConfigManager.Type.MESSAGES)
               .getString("GUI.Change-Time.Items.Daylight-Cycle-" + var3 + ".Displayname")
               .replace("&", "§")
         );
         ArrayList var6 = new ArrayList();

         for (String var8 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Change-Time.Items.Daylight-Cycle-" + var3 + ".Lore")) {
            var8 = var8.replace("&", "§");
            var6.add(var8);
         }

         var5.setLore(var6);
         var4.setItemMeta(var5);
         return var4;
      }
   }

   @EventHandler
   public void a(InventoryClickEvent var1) {
      Player var2 = (Player)var1.getWhoClicked();
      if (!var1.getView().getTitle().isEmpty()
         && var1.getView().getTitle().equals(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Time.Title").replace("&", "§"))) {
         if (var1.getCurrentItem() != null) {
            if (var1.getCurrentItem().hasItemMeta()) {
               if (var1.getCurrentItem().getItemMeta().hasDisplayName()) {
                  var1.setCancelled(true);
                  if (this.a.j().b().containsKey(var2.getName())
                     && Bukkit.getWorld(this.a.getBasicManager().getWorldPrefix() + this.a.j().b().get(var2.getName())) != null) {
                     String var3 = var1.getCurrentItem().getItemMeta().getDisplayName();
                     String var4 = this.a.j().b().get(var2.getName());
                     String var5 = this.a.getBasicManager().getWorldPrefix() + var4;
                     var2.closeInventory();
                     var2.updateInventory();
                     World var6 = Bukkit.getWorld(var5);
                     if (var3.equals(
                        this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Time.Items.Day.Displayname").replace("&", "§")
                     )) {
                        this.a(var5, 1000);
                        var2.sendMessage(
                           this.a.getBasicManager().getMessagePrefix()
                              + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Change-Time.Day").replace("&", "§")
                        );
                     } else if (var3.equals(
                        this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Time.Items.Sunset.Displayname").replace("&", "§")
                     )) {
                        this.a(var5, 12000);
                        var2.sendMessage(
                           this.a.getBasicManager().getMessagePrefix()
                              + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Change-Time.Sunset").replace("&", "§")
                        );
                     } else if (var3.equals(
                        this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Time.Items.Night.Displayname").replace("&", "§")
                     )) {
                        this.a(var5, 13000);
                        var2.sendMessage(
                           this.a.getBasicManager().getMessagePrefix()
                              + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Change-Time.Night").replace("&", "§")
                        );
                     } else if (var3.equals(
                        this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Time.Items.Sunrise.Displayname").replace("&", "§")
                     )) {
                        this.a(var5, 23000);
                        var2.sendMessage(
                           this.a.getBasicManager().getMessagePrefix()
                              + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Change-Time.Sunrise").replace("&", "§")
                        );
                     } else if (var3.equals(
                           this.a
                              .getConfigManager()
                              .get(ConfigManager.Type.MESSAGES)
                              .getString("GUI.Change-Time.Items.Daylight-Cycle-Lock.Displayname")
                              .replace("&", "§")
                        )
                        && var6 != null
                        && Boolean.parseBoolean(var6.getGameRuleValue("doDaylightCycle"))) {
                        this.a(var5, "false");
                        var2.sendMessage(
                           this.a.getBasicManager().getMessagePrefix()
                              + this.a
                                 .getConfigManager()
                                 .get(ConfigManager.Type.MESSAGES)
                                 .getString("Messages.Change-Time.Daylight-Cycle.Lock")
                                 .replace("&", "§")
                        );
                     } else if (var3.equals(
                           this.a
                              .getConfigManager()
                              .get(ConfigManager.Type.MESSAGES)
                              .getString("GUI.Change-Time.Items.Daylight-Cycle-Unlock.Displayname")
                              .replace("&", "§")
                        )
                        && var6 != null
                        && !Boolean.parseBoolean(var6.getGameRuleValue("doDaylightCycle"))) {
                        var2.closeInventory();
                        var2.updateInventory();
                        this.a(var5, "true");
                        var2.sendMessage(
                           this.a.getBasicManager().getMessagePrefix()
                              + this.a
                                 .getConfigManager()
                                 .get(ConfigManager.Type.MESSAGES)
                                 .getString("Messages.Change-Time.Daylight-Cycle.Unlock")
                                 .replace("&", "§")
                        );
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

   private void a(String var1, int var2) {
      for (String var4 : this.a.getWorldManager().getOwnWorlds(var1)) {
         World var5 = Bukkit.getWorld(var4);
         if (var5 != null) {
            var5.setTime((long)var2);
         }
      }
   }

   private void a(String var1, String var2) {
      for (String var4 : this.a.getWorldManager().getOwnWorlds(var1)) {
         World var5 = Bukkit.getWorld(var4);
         if (var5 != null) {
            var5.setGameRuleValue("doDaylightCycle", var2);
         }
      }
   }
}
