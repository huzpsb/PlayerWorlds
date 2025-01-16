package cz._heropwp.playerworldspro.gui;

import cz._heropwp.playerworldspro.Main;
import cz._heropwp.playerworldspro.util.ConfigManager;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUI_Settings_ChangeGameMode implements Listener {
   private final Main a;

   public GUI_Settings_ChangeGameMode(Main var1) {
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
      } else if (this.a.getConfig().getBoolean("Permissions.Change-GameMode") && !var1.hasPermission("PlayerWorldsPro.changeGameMode")) {
         var1.closeInventory();
         var1.sendMessage(
            this.a.getBasicManager().getMessagePrefix()
               + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Change-GameMode.Insufficient-Permission").replace("&", "§")
         );
      } else {
         Inventory var2 = Bukkit.createInventory(
            null, 27, this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-GameMode.Title").replace("&", "§")
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
         this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-GameMode.Items.Survival.Displayname").replace("&", "§")
      );
      ArrayList var3 = new ArrayList();

      for (String var5 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Change-GameMode.Items.Survival.Lore")) {
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
         this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-GameMode.Items.Creative.Displayname").replace("&", "§")
      );
      ArrayList var3 = new ArrayList();

      for (String var5 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Change-GameMode.Items.Creative.Lore")) {
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
         this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-GameMode.Items.Adventure.Displayname").replace("&", "§")
      );
      ArrayList var3 = new ArrayList();

      for (String var5 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Change-GameMode.Items.Adventure.Lore")) {
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
         this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-GameMode.Items.Spectator.Displayname").replace("&", "§")
      );
      ArrayList var3 = new ArrayList();

      for (String var5 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Change-GameMode.Items.Spectator.Lore")) {
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
            .equals(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-GameMode.Title").replace("&", "§"))) {
         if (var1.getCurrentItem() != null) {
            if (var1.getCurrentItem().hasItemMeta()) {
               if (var1.getCurrentItem().getItemMeta().hasDisplayName()) {
                  var1.setCancelled(true);
                  if (this.a.j().b().containsKey(var2.getName())
                     && Bukkit.getWorld(this.a.getBasicManager().getWorldPrefix() + this.a.j().b().get(var2.getName())) != null) {
                     String var3 = var1.getCurrentItem().getItemMeta().getDisplayName();
                     if (var3.equals(
                        this.a
                           .getConfigManager()
                           .get(ConfigManager.Type.MESSAGES)
                           .getString("GUI.Change-GameMode.Items.Survival.Displayname")
                           .replace("&", "§")
                     )) {
                        this.a(var2, var1, GameMode.SURVIVAL, "Survival");
                     } else if (var3.equals(
                        this.a
                           .getConfigManager()
                           .get(ConfigManager.Type.MESSAGES)
                           .getString("GUI.Change-GameMode.Items.Creative.Displayname")
                           .replace("&", "§")
                     )) {
                        this.a(var2, var1, GameMode.CREATIVE, "Creative");
                     } else if (var3.equals(
                        this.a
                           .getConfigManager()
                           .get(ConfigManager.Type.MESSAGES)
                           .getString("GUI.Change-GameMode.Items.Adventure.Displayname")
                           .replace("&", "§")
                     )) {
                        this.a(var2, var1, GameMode.ADVENTURE, "Adventure");
                     } else if (var3.equals(
                        this.a
                           .getConfigManager()
                           .get(ConfigManager.Type.MESSAGES)
                           .getString("GUI.Change-GameMode.Items.Spectator.Displayname")
                           .replace("&", "§")
                     )) {
                        this.a(var2, var1, GameMode.SPECTATOR, "Spectator");
                     }
                  } else {
                     var2.closeInventory();
                     var2.updateInventory();
                     this.a.j().b().remove(var2.getName());
                  }
               }
            }
         }
      }
   }

   private void a(Player var1, InventoryClickEvent var2, GameMode var3, String var4) {
      if (var2.isLeftClick()) {
         if (var1.getWorld().getName().equals(this.a.getBasicManager().getWorldPrefix() + this.a.j().b().get(var1.getName()))) {
            var1.closeInventory();
            var1.updateInventory();
            var1.setGameMode(var3);
            var1.sendMessage(
               this.a.getBasicManager().getMessagePrefix()
                  + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Change-GameMode." + var4).replace("&", "§")
            );
         } else {
            var1.closeInventory();
            var1.updateInventory();
            var1.sendMessage(
               this.a.getBasicManager().getMessagePrefix()
                  + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Same-World").replace("&", "§")
            );
         }

         this.a.j().b().remove(var1.getName());
      } else if (var2.isRightClick()) {
         this.a.p().a(var1, var3);
      }
   }
}
