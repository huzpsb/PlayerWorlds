package cz._heropwp.playerworldspro.gui;

import cz._heropwp.playerworldspro.Main;
import cz._heropwp.playerworldspro.util.ConfigManager;
import cz._heropwp.playerworldspro.util.WorldManager;
import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUI_Buy_PlayerWorld implements Listener {
   private final Main a;
   private final HashMap<String, WorldManager.Generator> b;
   private final HashMap<String, String> c;
   private final HashMap<String, String> d;

   public GUI_Buy_PlayerWorld(Main var1) {
      this.a = var1;
      this.b = new HashMap<>();
      this.c = new HashMap<>();
      this.d = new HashMap<>();
   }

   public void a(Player var1, WorldManager.Generator var2, String var3, String var4) {
      this.b.put(var1.getName(), var2);
      this.c.put(var1.getName(), var3);
      this.d.put(var1.getName(), var4);
      Inventory var5 = Bukkit.createInventory(
         null, 27, this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Buy-Player-World.Title").replace("&", "§")
      );
      var1.openInventory(var5);
      var5.setItem(11, this.a("First"));
      var5.setItem(13, this.a("Second"));
      var5.setItem(15, this.a("Third"));
   }

   private ItemStack a(String var1) {
      ItemStack var2 = new ItemStack(Material.GOLD_INGOT);
      ItemMeta var3 = var2.getItemMeta();
      var3.setDisplayName(
         this.a
            .getConfigManager()
            .get(ConfigManager.Type.MESSAGES)
            .getString("GUI.Buy-Player-World.Items." + var1 + ".Displayname")
            .replace("&", "§")
            .replace("%length%", this.a.getConfig().getString("GUI.Buy-Player-World.Items." + var1 + ".Length"))
      );
      ArrayList var4 = new ArrayList();

      for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Buy-Player-World.Items." + var1 + ".Lore")) {
         var6 = var6.replace("&", "§");
         var6 = var6.replace("%price%", this.a.getConfig().getString("GUI.Buy-Player-World.Items." + var1 + ".Price"));
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
            .equals(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Buy-Player-World.Title").replace("&", "§"))) {
         if (var1.getCurrentItem() != null) {
            if (var1.getCurrentItem().hasItemMeta()) {
               if (var1.getCurrentItem().getItemMeta().hasDisplayName()) {
                  var1.setCancelled(true);
                  if (this.b.containsKey(var2.getName())) {
                     if (var1.getSlot() == 11) {
                        this.a(var2, "First");
                     } else if (var1.getSlot() == 13) {
                        this.a(var2, "Second");
                     } else if (var1.getSlot() == 15) {
                        this.a(var2, "Third");
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
         && var1.getView()
            .getTitle()
            .equals(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Buy-Player-World.Title").replace("&", "§"))) {
         this.b.remove(var2.getName());
         this.c.remove(var2.getName());
         this.d.remove(var2.getName());
      }
   }

   private void a(Player var1, String var2) {
      int var3 = this.a.getConfig().getInt("GUI.Buy-Player-World.Items." + var2 + ".Length");
      int var4 = this.a.getConfig().getInt("GUI.Buy-Player-World.Items." + var2 + ".Price");
      this.a.getWorldManager().createWorld(var1, this.b.get(var1.getName()), this.c.get(var1.getName()), this.d.get(var1.getName()), var3, var4, false);
      var1.closeInventory();
      var1.updateInventory();
   }
}
