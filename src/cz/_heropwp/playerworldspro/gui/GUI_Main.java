package cz._heropwp.playerworldspro.gui;

import cz._heropwp.playerworldspro.Main;
import cz._heropwp.playerworldspro.util.ConfigManager;
import cz._heropwp.playerworldspro.util.MaterialManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
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
import org.bukkit.scheduler.BukkitTask;

public class GUI_Main implements Listener {
   private final Main a;
   private final HashMap<String, BukkitTask> b;
   private final HashMap<String, Integer> c;
   private final LinkedHashSet<String> d;

   public GUI_Main(Main var1) {
      this.a = var1;
      this.b = new HashMap<>();
      this.c = new HashMap<>();
      this.d = new LinkedHashSet<>();
   }

   public void a(Player var1) {
      Inventory var2 = Bukkit.createInventory(
         null, 54, this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Main.Title").replace("&", "§")
      );
      var1.openInventory(var2);
      this.c.put(var1.getName(), 0);
      var2.setItem(6, this.b(var1));
      this.a(var1, var2);
   }

   private void a(Player var1, Inventory var2) {
      this.b.put(var1.getName(), Bukkit.getScheduler().runTaskTimerAsynchronously(this.a, () -> this.c(var1, var2), 0L, 10L));
   }

   private void b(Player var1, Inventory var2) {
      this.a(var1.getName(), false);
      this.a(9, var2);
      this.a(var1, var2);
   }

   private void c(Player var1, Inventory var2) {
      var2.setItem(0, this.a(var1.getName()));
      var2.setItem(8, this.b(var1.getName()));
      var2.setItem(2, this.e());
      var2.setItem(4, this.c(var1.getName()));
      int var3 = 9;
      int var4 = 1;
      if (this.a.getConfigManager().get(ConfigManager.Type.DATA).contains("Worlds")) {
         for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.DATA).getConfigurationSection("Worlds").getKeys(false)) {
            if (!this.c.containsKey(var1.getName())) {
               this.a(var1.getName(), true);
               return;
            }

            if (var3 >= var2.getSize()) {
               break;
            }

            if (var4 > this.c.get(var1.getName()) * 45) {
               ItemStack var7 = new ItemStack(
                  this.a.getMaterialManager().get(MaterialManager.Type.PLAYER_HEAD),
                  this.a.getWorldManager().MAYBE_GetPlayerNum(this.a.getBasicManager().getWorldPrefix() + var6, true),
                  (short)3
               );
               SkullMeta var8 = (SkullMeta)var7.getItemMeta();
               if (this.d.contains(var1.getName())) {
                  var8.setOwner(var6);
               }

               String var9 = "Player-World";
               if (this.a.getWorldManager().canVisitOp(var1, var6)) {
                  var9 = var9 + "-Own";
               }

               var8.setDisplayName(
                  this.a
                     .getConfigManager()
                     .get(ConfigManager.Type.MESSAGES)
                     .getString("GUI.Main.Items." + var9 + ".Displayname")
                     .replace("&", "§")
                     .replace("%player%", var6)
               );
               ArrayList var10 = new ArrayList();

               for (String var12 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Main.Items." + var9 + ".Lore")) {
                  if (this.a.getWorldManager().systemExipreEnabled() || !var12.contains("%expiration%")) {
                     var12 = var12.replace("&", "§");
                     var12 = var12.replace(
                        "%players%", String.valueOf(this.a.getWorldManager().MAYBE_GetPlayerNum(this.a.getBasicManager().getWorldPrefix() + var6, false))
                     );
                     var12 = var12.replace("%expiration%", this.a.getWorldManager().formatExpiration(var6));
                     var12 = var12.replace(
                        "%access%",
                        this.a
                           .getConfigManager()
                           .get(ConfigManager.Type.MESSAGES)
                           .getString("Variables." + this.a.getWorldManager().ruleAccessibility(var6))
                           .replace("&", "§")
                     );
                     var10.add(var12);
                  }
               }

               var8.setLore(var10);
               var7.setItemMeta(var8);
               var2.setItem(var3, var7);
               var3++;
            } else {
               var4++;
            }
         }
      }

      this.d.add(var1.getName());
      this.a(var3, var2);
   }

   public void a(int var1, Inventory var2) {
      Bukkit.getScheduler().runTask(this.a, () -> {
         for (int var2x = var1; var2x < var2.getSize(); var2x++) {
            var2.setItem(var2x, new ItemStack(Material.AIR));
         }
      });
   }

   private ItemStack a(String var1) {
      if (this.c.get(var1) > 0) {
         ItemStack var2 = new ItemStack(Material.ARROW);
         ItemMeta var3 = var2.getItemMeta();
         var3.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Main.Items.Previous").replace("&", "§"));
         var2.setItemMeta(var3);
         return var2;
      } else {
         return new ItemStack(Material.AIR);
      }
   }

   private ItemStack b(String var1) {
      int var2 = 0;
      if (this.a.getConfigManager().get(ConfigManager.Type.DATA).contains("Worlds")) {
         var2 = this.a.getConfigManager().get(ConfigManager.Type.DATA).getConfigurationSection("Worlds").getKeys(false).size();
      }

      if (var2 > (this.c.get(var1) + 1) * 45) {
         ItemStack var3 = new ItemStack(Material.ARROW);
         ItemMeta var4 = var3.getItemMeta();
         var4.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Main.Items.Next").replace("&", "§"));
         var3.setItemMeta(var4);
         return var3;
      } else {
         return new ItemStack(Material.AIR);
      }
   }

   private ItemStack e() {
      ItemStack var1 = new ItemStack(Material.EMERALD);
      ItemMeta var2 = var1.getItemMeta();
      var2.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Main.Items.Statistics.Displayname").replace("&", "§"));
      ArrayList var3 = new ArrayList();

      for (String var5 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Main.Items.Statistics.Lore")) {
         var5 = var5.replace("&", "§");
         var5 = var5.replace("%total_player_worlds%", String.valueOf(this.a.getWorldManager().worldCount()));
         var5 = var5.replace("%total_players%", String.valueOf(this.a.getWorldManager().getWorldPlayers()));
         var3.add(var5);
      }

      var2.setLore(var3);
      var1.setItemMeta(var2);
      return var1;
   }

   private ItemStack c(String var1) {
      ItemStack var2 = new ItemStack(
         this.a.getMaterialManager().get(MaterialManager.Type.PLAYER_HEAD),
         this.a.getWorldManager().MAYBE_GetPlayerNum(this.a.getBasicManager().getWorldPrefix() + var1, true),
         (short)SkullType.PLAYER.ordinal()
      );
      SkullMeta var3 = (SkullMeta)var2.getItemMeta();
      var3.setOwner(var1);
      String var4 = "My-World";
      if (!this.a.getWorldManager().worldExists(var1)) {
         var4 = var4 + "-Dont-Have";
      }

      var3.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Main.Items." + var4 + ".Displayname").replace("&", "§"));
      ArrayList var5 = new ArrayList();

      for (String var7 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Main.Items." + var4 + ".Lore")) {
         if (this.a.getWorldManager().systemExipreEnabled() || !var7.contains("%expiration%")) {
            var7 = var7.replace("&", "§");
            var7 = var7.replace(
               "%players%", String.valueOf(this.a.getWorldManager().MAYBE_GetPlayerNum(this.a.getBasicManager().getWorldPrefix() + var1, false))
            );
            var7 = var7.replace("%expiration%", this.a.getWorldManager().formatExpiration(var1));
            var5.add(var7);
         }
      }

      var3.setLore(var5);
      var2.setItemMeta(var3);
      return var2;
   }

   private ItemStack b(Player var1) {
      if (this.a.getConfig().getBoolean("Permissions.Create-World")
         && !var1.hasPermission("PlayerWorldsPro.createWorld")
         && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
         return new ItemStack(Material.AIR);
      } else {
         ItemStack var2 = new ItemStack(this.a.getMaterialManager().get(MaterialManager.Type.COMMAND_BLOCK));
         ItemMeta var3 = var2.getItemMeta();
         var3.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Main.Items.Create-World.Displayname").replace("&", "§"));
         ArrayList var4 = new ArrayList();

         for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Main.Items.Create-World.Lore")) {
            var6 = var6.replace("&", "§");
            var4.add(var6);
         }

         var3.setLore(var4);
         var2.setItemMeta(var3);
         return var2;
      }
   }

   @EventHandler
   public void a(InventoryClickEvent var1) {
      Player var2 = (Player)var1.getWhoClicked();
      if (!var1.getView().getTitle().isEmpty()
         && var1.getView().getTitle().equals(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Main.Title").replace("&", "§"))) {
         if (var1.getCurrentItem() != null) {
            if (var1.getCurrentItem().hasItemMeta()) {
               if (var1.getCurrentItem().getItemMeta().hasDisplayName()) {
                  var1.setCancelled(true);
                  String var3 = var1.getCurrentItem().getItemMeta().getDisplayName();
                  if (var1.getCurrentItem().getType() == Material.ARROW
                     && this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Main.Items.Previous").replace("&", "§").contains(var3)) {
                     this.c.put(var2.getName(), this.c.get(var2.getName()) - 1);
                     this.b(var2, var2.getOpenInventory().getTopInventory());
                  } else if (var1.getCurrentItem().getType() == Material.ARROW
                     && this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Main.Items.Next").replace("&", "§").contains(var3)) {
                     this.c.put(var2.getName(), this.c.get(var2.getName()) + 1);
                     this.b(var2, var2.getOpenInventory().getTopInventory());
                  } else if (var1.getCurrentItem().getType() == this.a.getMaterialManager().get(MaterialManager.Type.COMMAND_BLOCK)) {
                     this.a.e().a(var2);
                  } else if (var1.getCurrentItem().getType() == this.a.getMaterialManager().get(MaterialManager.Type.PLAYER_HEAD)) {
                     boolean var4 = this.a.getWorldManager().worldExists(var2.getName());
                     String var5 = "My-World";
                     if (!var4) {
                        var5 = var5 + "-Dont-Have";
                     }

                     String var6;
                     if (var3.equals(
                        this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Main.Items." + var5 + ".Displayname").replace("&", "§")
                     )) {
                        if (!var4) {
                           return;
                        }

                        var6 = var2.getName();
                     } else {
                        var5 = "GUI.Main.Items.Player-World.Displayname";
                        var6 = this.a(var5, var3);
                        if (!this.a.getWorldManager().worldExists(var6)) {
                           var5 = "GUI.Main.Items.Player-World-Own.Displayname";
                        }

                        var6 = this.a(var5, var3);
                     }

                     if (var1.isRightClick() && this.a.getWorldManager().canVisitOp(var2, var6)) {
                        this.a.j().a(var2, var6);
                        return;
                     }

                     var2.closeInventory();
                     this.a.getWorldManager().moveTo(var2, var6, true);
                  }
               }
            }
         }
      }
   }

   public String a(String var1, String var2) {
      String var3 = this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString(var1).replace("&", "§");
      String[] var4 = var3.split("%player%");
      String var5;
      if (var4.length > 0) {
         String var6 = var4[0];
         var5 = var2.replace(var6, "");
         if (var4.length == 2) {
            String var7 = var4[1];
            var5.replace(var7, "");
         }
      } else {
         var5 = var2;
      }

      return var5;
   }

   @EventHandler
   public void a(InventoryCloseEvent var1) {
      Player var2 = (Player)var1.getPlayer();
      if (!var1.getView().getTitle().isEmpty()
         && var1.getView().getTitle().equals(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Main.Title").replace("&", "§"))) {
         this.a(var2.getName(), true);
      }
   }

   public void a(String var1, boolean var2) {
      if (this.b.containsKey(var1)) {
         this.b.get(var1).cancel();
         this.d.remove(var1);
      }

      if (var2) {
         this.b.remove(var1);
         this.c.remove(var1);
      }
   }

   public Main a() {
      return this.a;
   }

   public HashMap<String, BukkitTask> b() {
      return this.b;
   }

   public HashMap<String, Integer> c() {
      return this.c;
   }

   public LinkedHashSet<String> d() {
      return this.d;
   }
}
