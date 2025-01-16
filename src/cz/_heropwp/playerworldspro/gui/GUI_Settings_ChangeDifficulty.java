package cz._heropwp.playerworldspro.gui;

import cz._heropwp.playerworldspro.Main;
import cz._heropwp.playerworldspro.util.ConfigManager;
import cz._heropwp.playerworldspro.util.MaterialManager;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUI_Settings_ChangeDifficulty implements Listener {
   private final Main a;

   public GUI_Settings_ChangeDifficulty(Main var1) {
      this.a = var1;
   }

   public void a(Player var1) {
      if (!this.a.j().b().containsKey(var1.getName())) {
         var1.closeInventory();
         var1.updateInventory();
      } else if (this.a.getConfig().getBoolean("Permissions.Change-Difficulty") && !var1.hasPermission("PlayerWorldsPro.changeDifficulty")) {
         var1.closeInventory();
         var1.sendMessage(
            this.a.getBasicManager().getMessagePrefix()
               + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Change-Difficulty.Insufficient-Permission").replace("&", "§")
         );
      } else {
         Inventory var2 = Bukkit.createInventory(
            null, 27, this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Difficulty.Title").replace("&", "§")
         );
         var1.openInventory(var2);
         var2.setItem(10, this.a());
         var2.setItem(12, this.b());
         var2.setItem(14, this.c());
         var2.setItem(16, this.d());
      }
   }

   private ItemStack a() {
      ItemStack var1 = new ItemStack(Material.GOLDEN_APPLE);
      ItemMeta var2 = var1.getItemMeta();
      var2.setDisplayName(
         this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Difficulty.Items.Peaceful.Displayname").replace("&", "§")
      );
      ArrayList var3 = new ArrayList();

      for (String var5 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Change-Difficulty.Items.Peaceful.Lore")) {
         var5 = var5.replace("&", "§");
         var3.add(var5);
      }

      var2.setLore(var3);
      var1.setItemMeta(var2);
      return var1;
   }

   private ItemStack b() {
      ItemStack var1 = new ItemStack(this.a.getMaterialManager().get(MaterialManager.Type.WOODEN_SWORD));
      ItemMeta var2 = var1.getItemMeta();
      var2.setDisplayName(
         this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Difficulty.Items.Easy.Displayname").replace("&", "§")
      );
      ArrayList var3 = new ArrayList();

      for (String var5 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Change-Difficulty.Items.Easy.Lore")) {
         var5 = var5.replace("&", "§");
         var3.add(var5);
      }

      var2.setLore(var3);
      var1.setItemMeta(var2);
      return var1;
   }

   private ItemStack c() {
      ItemStack var1 = new ItemStack(Material.IRON_SWORD);
      ItemMeta var2 = var1.getItemMeta();
      var2.setDisplayName(
         this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Difficulty.Items.Normal.Displayname").replace("&", "§")
      );
      ArrayList var3 = new ArrayList();

      for (String var5 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Change-Difficulty.Items.Normal.Lore")) {
         var5 = var5.replace("&", "§");
         var3.add(var5);
      }

      var2.setLore(var3);
      var1.setItemMeta(var2);
      return var1;
   }

   private ItemStack d() {
      ItemStack var1 = new ItemStack(Material.DIAMOND_SWORD);
      ItemMeta var2 = var1.getItemMeta();
      var2.setDisplayName(
         this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Difficulty.Items.Hard.Displayname").replace("&", "§")
      );
      ArrayList var3 = new ArrayList();

      for (String var5 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Change-Difficulty.Items.Hard.Lore")) {
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
            .equals(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Difficulty.Title").replace("&", "§"))) {
         if (var1.getCurrentItem() != null) {
            if (var1.getCurrentItem().hasItemMeta()) {
               if (var1.getCurrentItem().getItemMeta().hasDisplayName()) {
                  var1.setCancelled(true);
                  var2.closeInventory();
                  var2.updateInventory();
                  if (this.a.j().b().containsKey(var2.getName())) {
                     String var3 = var1.getCurrentItem().getItemMeta().getDisplayName();
                     String var4 = this.a.j().b().get(var2.getName());
                     String var5 = this.a.getBasicManager().getWorldPrefix() + var4;
                     if (var3.equals(
                        this.a
                           .getConfigManager()
                           .get(ConfigManager.Type.MESSAGES)
                           .getString("GUI.Change-Difficulty.Items.Peaceful.Displayname")
                           .replace("&", "§")
                     )) {
                        this.a(var4, var5, Difficulty.PEACEFUL);
                        var2.sendMessage(
                           this.a.getBasicManager().getMessagePrefix()
                              + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Change-Difficulty.Peaceful").replace("&", "§")
                        );
                     } else if (var3.equals(
                        this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Difficulty.Items.Easy.Displayname").replace("&", "§")
                     )) {
                        this.a(var4, var5, Difficulty.EASY);
                        var2.sendMessage(
                           this.a.getBasicManager().getMessagePrefix()
                              + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Change-Difficulty.Easy").replace("&", "§")
                        );
                     } else if (var3.equals(
                        this.a
                           .getConfigManager()
                           .get(ConfigManager.Type.MESSAGES)
                           .getString("GUI.Change-Difficulty.Items.Normal.Displayname")
                           .replace("&", "§")
                     )) {
                        this.a(var4, var5, Difficulty.NORMAL);
                        var2.sendMessage(
                           this.a.getBasicManager().getMessagePrefix()
                              + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Change-Difficulty.Normal").replace("&", "§")
                        );
                     } else if (var3.equals(
                        this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Change-Difficulty.Items.Hard.Displayname").replace("&", "§")
                     )) {
                        this.a(var4, var5, Difficulty.HARD);
                        var2.sendMessage(
                           this.a.getBasicManager().getMessagePrefix()
                              + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Change-Difficulty.Hard").replace("&", "§")
                        );
                     }
                  }

                  this.a.j().b().remove(var2.getName());
               }
            }
         }
      }
   }

   private void a(String var1, String var2, Difficulty var3) {
      if (var3 != null) {
         this.a.getConfigManager().get(ConfigManager.Type.DATA).set("Worlds." + var1 + ".Difficulty", var3.toString());
         this.a.getConfigManager().save(ConfigManager.Type.DATA);
         this.a.getConfigManager().load(ConfigManager.Type.DATA);

         for (String var5 : this.a.getWorldManager().getOwnWorlds(var2)) {
            World var6 = Bukkit.getWorld(var5);
            if (var6 != null) {
               var6.setDifficulty(var3);
            }
         }
      }
   }
}
