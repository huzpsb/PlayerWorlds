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

public class GUI_Extend_PlayerWorld implements Listener {
   private final Main a;

   public GUI_Extend_PlayerWorld(Main var1) {
      this.a = var1;
   }

   public void a(Player var1) {
      if (!this.a.j().b().containsKey(var1.getName())) {
         var1.closeInventory();
         var1.updateInventory();
      } else {
         Inventory var2 = Bukkit.createInventory(
            null, 27, this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Extend-Player-World.Title").replace("&", "§")
         );
         var1.openInventory(var2);
         var2.setItem(11, this.a("First"));
         var2.setItem(13, this.a("Second"));
         var2.setItem(15, this.a("Third"));
      }
   }

   private ItemStack a(String var1) {
      ItemStack var2 = new ItemStack(Material.GOLD_INGOT);
      ItemMeta var3 = var2.getItemMeta();
      var3.setDisplayName(
         this.a
            .getConfigManager()
            .get(ConfigManager.Type.MESSAGES)
            .getString("GUI.Extend-Player-World.Items." + var1 + ".Displayname")
            .replace("&", "§")
            .replace("%length%", this.a.getConfig().getString("GUI.Extend-Player-World.Items." + var1 + ".Length"))
      );
      ArrayList var4 = new ArrayList();

      for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Extend-Player-World.Items." + var1 + ".Lore")) {
         var6 = var6.replace("&", "§");
         var6 = var6.replace("%price%", this.a.getConfig().getString("GUI.Extend-Player-World.Items." + var1 + ".Price"));
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
            .equals(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Extend-Player-World.Title").replace("&", "§"))) {
         if (var1.getCurrentItem() != null) {
            if (var1.getCurrentItem().hasItemMeta()) {
               if (var1.getCurrentItem().getItemMeta().hasDisplayName()) {
                  var1.setCancelled(true);
                  var2.closeInventory();
                  var2.updateInventory();
                  if (this.a.j().b().containsKey(var2.getName())) {
                     switch (var1.getSlot()) {
                        case 11:
                           this.a.getWorldManager().cmdHandleExtend(var2, this.a.j().b().get(var2.getName()), "First", true);
                        case 12:
                        case 14:
                        default:
                           break;
                        case 13:
                           this.a.getWorldManager().cmdHandleExtend(var2, this.a.j().b().get(var2.getName()), "Second", true);
                           break;
                        case 15:
                           this.a.getWorldManager().cmdHandleExtend(var2, this.a.j().b().get(var2.getName()), "Third", true);
                     }
                  }

                  this.a.j().b().remove(var2.getName());
               }
            }
         }
      }
   }
}
