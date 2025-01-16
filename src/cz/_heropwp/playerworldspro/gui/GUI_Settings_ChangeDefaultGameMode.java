package cz._heropwp.playerworldspro.gui;

import cz._heropwp.playerworldspro.Main;
import cz._heropwp.playerworldspro.util.ConfigManager;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUI_Settings_ChangeDefaultGameMode implements Listener {
   private final Main a;

   public GUI_Settings_ChangeDefaultGameMode(Main var1) {
      this.a = var1;
   }

   public void a(Player var1) {
      if (!this.a.j().b().containsKey(var1.getName())) {
         var1.closeInventory();
         var1.updateInventory();
      } else if (this.a.getConfig().getBoolean("Permissions.Change-Default-GameMode") && !var1.hasPermission("PlayerWorldsPro.changeGameMode.default")) {
         var1.closeInventory();
         var1.sendMessage(
            this.a.getBasicManager().getMessagePrefix()
               + this.a
                  .getConfigManager()
                  .get(ConfigManager.Type.MESSAGES)
                  .getString("Messages.Change-Default-GameMode.Insufficient-Permission")
                  .replace("&", "§")
         );
      } else {
         Inventory var2 = Bukkit.createInventory(
            null, 27, this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Default-GameMode.Title").replace("&", "§")
         );
         var1.openInventory(var2);
         var2.setItem(10, this.a());
         var2.setItem(12, this.b());
         var2.setItem(14, this.c());
         var2.setItem(16, this.d());
      }
   }

   private ItemStack a() {
      ItemStack var1 = new ItemStack(Material.STONE_PICKAXE);
      ItemMeta var2 = var1.getItemMeta();
      var2.setDisplayName(
         this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Default-GameMode.Items.Survival.Displayname").replace("&", "§")
      );
      ArrayList var3 = new ArrayList();

      for (String var5 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Change-Default-GameMode.Items.Survival.Lore")) {
         var5 = var5.replace("&", "§");
         var3.add(var5);
      }

      var2.setLore(var3);
      var1.setItemMeta(var2);
      return var1;
   }

   private ItemStack b() {
      ItemStack var1 = new ItemStack(Material.BRICK);
      ItemMeta var2 = var1.getItemMeta();
      var2.setDisplayName(
         this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Default-GameMode.Items.Creative.Displayname").replace("&", "§")
      );
      ArrayList var3 = new ArrayList();

      for (String var5 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Change-Default-GameMode.Items.Creative.Lore")) {
         var5 = var5.replace("&", "§");
         var3.add(var5);
      }

      var2.setLore(var3);
      var1.setItemMeta(var2);
      return var1;
   }

   private ItemStack c() {
      ItemStack var1 = new ItemStack(Material.LEATHER_BOOTS);
      ItemMeta var2 = var1.getItemMeta();
      var2.setDisplayName(
         this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Default-GameMode.Items.Adventure.Displayname").replace("&", "§")
      );
      ArrayList var3 = new ArrayList();

      for (String var5 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Change-Default-GameMode.Items.Adventure.Lore")) {
         var5 = var5.replace("&", "§");
         var3.add(var5);
      }

      var2.setLore(var3);
      var1.setItemMeta(var2);
      return var1;
   }

   private ItemStack d() {
      ItemStack var1 = new ItemStack(Material.BARRIER);
      ItemMeta var2 = var1.getItemMeta();
      var2.setDisplayName(
         this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Default-GameMode.Items.Spectator.Displayname").replace("&", "§")
      );
      ArrayList var3 = new ArrayList();

      for (String var5 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Change-Default-GameMode.Items.Spectator.Lore")) {
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
         && var1.getView()
            .getTitle()
            .equals(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Default-GameMode.Title").replace("&", "§"))) {
         if (var1.getCurrentItem() != null) {
            if (var1.getCurrentItem().hasItemMeta()) {
               if (var1.getCurrentItem().getItemMeta().hasDisplayName()) {
                  var1.setCancelled(true);
                  if (this.a.j().b().containsKey(var2.getName())) {
                     String var3 = var1.getCurrentItem().getItemMeta().getDisplayName();
                     String var4 = this.a.j().b().get(var2.getName());
                     if (var3.equals(
                        this.a
                           .getConfigManager()
                           .get(ConfigManager.Type.MESSAGES)
                           .getString("GUI.Change-Default-GameMode.Items.Survival.Displayname")
                           .replace("&", "§")
                     )) {
                        this.a(var2, var4, "Survival");
                     } else if (var3.equals(
                        this.a
                           .getConfigManager()
                           .get(ConfigManager.Type.MESSAGES)
                           .getString("GUI.Change-Default-GameMode.Items.Creative.Displayname")
                           .replace("&", "§")
                     )) {
                        this.a(var2, var4, "Creative");
                     } else if (var3.equals(
                        this.a
                           .getConfigManager()
                           .get(ConfigManager.Type.MESSAGES)
                           .getString("GUI.Change-Default-GameMode.Items.Adventure.Displayname")
                           .replace("&", "§")
                     )) {
                        this.a(var2, var4, "Adventure");
                     } else if (var3.equals(
                        this.a
                           .getConfigManager()
                           .get(ConfigManager.Type.MESSAGES)
                           .getString("GUI.Change-Default-GameMode.Items.Spectator.Displayname")
                           .replace("&", "§")
                     )) {
                        this.a(var2, var4, "Spectator");
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

   private void a(Player var1, String var2, String var3) {
      this.a.getConfigManager().get(ConfigManager.Type.DATA).set("Worlds." + var2 + ".GameMode", var3.toUpperCase());
      this.a.getConfigManager().save(ConfigManager.Type.DATA);
      this.a.getConfigManager().load(ConfigManager.Type.DATA);
      var1.sendMessage(
         this.a.getBasicManager().getMessagePrefix()
            + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Change-Default-GameMode." + var3).replace("&", "§")
      );
   }
}
