package cz._heropwp.playerworldspro.gui;

import cz._heropwp.playerworldspro.Main;
import cz._heropwp.playerworldspro.util.ConfigManager;
import cz._heropwp.playerworldspro.util.WorldManager;
import java.io.File;
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

public class GUI_PreBuiltMaps implements Listener {
   private final Main a;

   public GUI_PreBuiltMaps(Main var1) {
      this.a = var1;
   }

   public void a(Player var1) {
      if (this.a.getWorldManager().worldExists(var1.getName())) {
         var1.closeInventory();
         var1.sendMessage(
            this.a.getBasicManager().getMessagePrefix()
               + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Already-Have").replace("&", "§")
         );
      } else {
         Inventory var2 = Bukkit.createInventory(
            null, 54, this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Pre-Built-Maps.Title").replace("&", "§")
         );
         var1.openInventory(var2);
         int var3 = 0;
         File var4 = new File(this.a.getDataFolder(), "maps");
         if (var4.exists()) {
            for (File var8 : var4.listFiles()) {
               if (var3 >= var2.getSize()) {
                  break;
               }

               if (var8.isDirectory()) {
                  var2.setItem(var3, this.a(var8.getName()));
                  var3++;
               }
            }
         }
      }
   }

   private ItemStack a(String var1) {
      ItemStack var2 = new ItemStack(Material.PAPER);
      ItemMeta var3 = var2.getItemMeta();
      var3.setDisplayName(
         this.a
            .getConfigManager()
            .get(ConfigManager.Type.MESSAGES)
            .getString("GUI.Pre-Built-Maps.Items.Map.Displayname")
            .replace("&", "§")
            .replace("%name%", var1)
      );
      ArrayList var4 = new ArrayList();

      for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Pre-Built-Maps.Items.Map.Lore")) {
         var6 = var6.replace("&", "§");
         var4.add(var6);
      }

      var3.setLore(var4);
      var2.setItemMeta(var3);
      return var2;
   }

   @EventHandler
   public void a(InventoryClickEvent var1) {
      Player var2 = (Player)var1.getWhoClicked();
      if (!var1.getView().getTitle().isEmpty()
         && var1.getView()
            .getTitle()
            .equals(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Pre-Built-Maps.Title").replace("&", "§"))) {
         if (var1.getCurrentItem() != null) {
            if (var1.getCurrentItem().hasItemMeta()) {
               if (var1.getCurrentItem().getItemMeta().hasDisplayName()) {
                  var1.setCancelled(true);
                  if (var1.getCurrentItem().getType() == Material.PAPER) {
                     File var3 = new File(this.a.getDataFolder(), "maps");
                     if (var3.exists()) {
                        for (File var7 : var3.listFiles()) {
                           if (var7.isDirectory()
                              && var1.getCurrentItem()
                                 .getItemMeta()
                                 .getDisplayName()
                                 .equals(
                                    this.a
                                       .getConfigManager()
                                       .get(ConfigManager.Type.MESSAGES)
                                       .getString("GUI.Pre-Built-Maps.Items.Map.Displayname")
                                       .replace("&", "§")
                                       .replace("%name%", var7.getName())
                                 )) {
                              if (this.a.getWorldManager().systemExipreEnabled()) {
                                 if (this.a.getConfig().getBoolean("Claim.Enabled")
                                    && !this.a.getConfigManager().get(ConfigManager.Type.PLAYERS).contains("Claim." + var2.getName())) {
                                    var2.closeInventory();
                                    var2.updateInventory();
                                    this.a
                                       .getWorldManager()
                                       .createWorld(
                                          var2, WorldManager.Generator.EMPTY, var7.getName(), null, this.a.getConfig().getInt("Claim.Length"), null, true
                                       );
                                 } else {
                                    this.a.d().a(var2, WorldManager.Generator.EMPTY, var7.getName(), null);
                                 }
                              } else {
                                 var2.closeInventory();
                                 var2.updateInventory();
                                 this.a.getWorldManager().createWorld(var2, WorldManager.Generator.EMPTY, var7.getName(), null, null, null, false);
                              }
                              break;
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }
}
