package cz._heropwp.playerworldspro.gui;

import cz._heropwp.playerworldspro.Main;
import cz._heropwp.playerworldspro.util.ConfigManager;
import cz._heropwp.playerworldspro.util.MaterialManager;
import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUI_Settings implements Listener {
   private final Main a;
   private final HashMap<String, String> b;

   public GUI_Settings(Main var1) {
      this.a = var1;
      this.b = new HashMap<>();
   }

   public void a(Player var1, String var2) {
      this.b.put(var1.getName(), var2);
      Inventory var3 = Bukkit.createInventory(
         null, 54, this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Settings.Title").replace("&", "§")
      );
      var1.openInventory(var3);
      var3.setItem(0, this.a(var1));
      var3.setItem(1, this.b(var1));
      var3.setItem(4, this.d(var1));
      var3.setItem(7, this.e(var1));
      var3.setItem(8, this.f(var1));
      var3.setItem(9, this.c(var1));
      var3.setItem(10, this.h(var1));
      var3.setItem(12, this.n(var1));
      var3.setItem(13, this.o(var1));
      var3.setItem(14, this.p(var1));
      var3.setItem(16, this.g(var1));
      var3.setItem(17, this.k(var1));
      var3.setItem(25, this.i(var1));
      var3.setItem(26, this.j(var1));
      var3.setItem(30, this.q(var1));
      var3.setItem(31, this.r(var1));
      var3.setItem(32, this.s(var1));
      var3.setItem(34, this.l(var1));
      var3.setItem(35, this.m(var1));
      if (this.a.getWorldManager().systemExipreEnabled()) {
         var3.setItem(46, this.c());
      }

      var3.setItem(49, this.d());
      var3.setItem(51, this.t(var1));
      var3.setItem(52, this.u(var1));
   }

   private ItemStack a(Player var1) {
      if (this.a.getConfig().getBoolean("Permissions.Change-Time")
         && !var1.hasPermission("PlayerWorldsPro.changeTime")
         && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
         return new ItemStack(Material.AIR);
      } else {
         ItemStack var2 = new ItemStack(this.a.getMaterialManager().get(MaterialManager.Type.CLOCK));
         ItemMeta var3 = var2.getItemMeta();
         var3.setDisplayName(
            this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Settings.Items.Change-Time.Displayname").replace("&", "§")
         );
         ArrayList var4 = new ArrayList();

         for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Settings.Items.Change-Time.Lore")) {
            var6 = var6.replace("&", "§");
            var4.add(var6);
         }

         var3.setLore(var4);
         var2.setItemMeta(var3);
         return var2;
      }
   }

   private ItemStack b(Player var1) {
      if (this.a.getConfig().getBoolean("Permissions.Change-Weather")
         && !var1.hasPermission("PlayerWorldsPro.changeWeather")
         && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
         return new ItemStack(Material.AIR);
      } else {
         ItemStack var2 = new ItemStack(Material.WATER_BUCKET);
         ItemMeta var3 = var2.getItemMeta();
         var3.setDisplayName(
            this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Settings.Items.Change-Weather.Displayname").replace("&", "§")
         );
         ArrayList var4 = new ArrayList();

         for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Settings.Items.Change-Weather.Lore")) {
            var6 = var6.replace("&", "§");
            var4.add(var6);
         }

         var3.setLore(var4);
         var2.setItemMeta(var3);
         return var2;
      }
   }

   private ItemStack c(Player var1) {
      if (this.a.getConfig().getBoolean("Permissions.Change-Difficulty")
         && !var1.hasPermission("PlayerWorldsPro.changeDifficulty")
         && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
         return new ItemStack(Material.AIR);
      } else {
         ItemStack var2 = new ItemStack(Material.BONE);
         ItemMeta var3 = var2.getItemMeta();
         var3.setDisplayName(
            this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Settings.Items.Change-Difficulty.Displayname").replace("&", "§")
         );
         ArrayList var4 = new ArrayList();

         for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Settings.Items.Change-Difficulty.Lore")) {
            var6 = var6.replace("&", "§");
            var4.add(var6);
         }

         var3.setLore(var4);
         var2.setItemMeta(var3);
         return var2;
      }
   }

   private ItemStack d(Player var1) {
      if (this.a.getConfig().getBoolean("Permissions.Set-Spawn-Location")
         && !var1.hasPermission("PlayerWorldsPro.setSpawn")
         && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
         return new ItemStack(Material.AIR);
      } else {
         ItemStack var2 = new ItemStack(this.a.getMaterialManager().get(MaterialManager.Type.RED_BED));
         ItemMeta var3 = var2.getItemMeta();
         var3.setDisplayName(
            this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Settings.Items.Spawn-Location.Displayname").replace("&", "§")
         );
         ArrayList var4 = new ArrayList();

         for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Settings.Items.Spawn-Location.Lore")) {
            var6 = var6.replace("&", "§");
            var4.add(var6);
         }

         var3.setLore(var4);
         var2.setItemMeta(var3);
         return var2;
      }
   }

   private String a(boolean var1) {
      String var2 = "Variables.";
      if (var1) {
         var2 = var2 + "Enabled";
      } else {
         var2 = var2 + "Disabled";
      }

      return this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString(var2);
   }

   private ItemStack e(Player var1) {
      if (this.a.getConfig().getBoolean("Permissions.Block-Breaking")
         && !var1.hasPermission("PlayerWorldsPro.editBlockBreaking")
         && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
         return new ItemStack(Material.AIR);
      } else {
         ItemStack var2 = new ItemStack(Material.IRON_PICKAXE);
         ItemMeta var3 = var2.getItemMeta();
         var3.setDisplayName(
            this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Settings.Items.Block-Breaking.Displayname").replace("&", "§")
         );
         ArrayList var4 = new ArrayList();

         for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Settings.Items.Block-Breaking.Lore")) {
            var6 = var6.replace("%status%", this.a(this.a.getWorldManager().ruleBlockBreaking(this.b.get(var1.getName()))));
            var6 = var6.replace("&", "§");
            var4.add(var6);
         }

         var3.setLore(var4);
         var2.setItemMeta(var3);
         return var2;
      }
   }

   private ItemStack f(Player var1) {
      if (this.a.getConfig().getBoolean("Permissions.Block-Placing")
         && !var1.hasPermission("PlayerWorldsPro.editBlockPlacing")
         && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
         return new ItemStack(Material.AIR);
      } else {
         ItemStack var2 = new ItemStack(Material.BRICK);
         ItemMeta var3 = var2.getItemMeta();
         var3.setDisplayName(
            this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Settings.Items.Block-Placing.Displayname").replace("&", "§")
         );
         ArrayList var4 = new ArrayList();

         for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Settings.Items.Block-Placing.Lore")) {
            var6 = var6.replace("%status%", this.a(this.a.getWorldManager().ruleBlockPlacing(this.b.get(var1.getName()))));
            var6 = var6.replace("&", "§");
            var4.add(var6);
         }

         var3.setLore(var4);
         var2.setItemMeta(var3);
         return var2;
      }
   }

   private ItemStack g(Player var1) {
      if (this.a.getConfig().getBoolean("Permissions.PvP")
         && !var1.hasPermission("PlayerWorldsPro.editPvP")
         && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
         return new ItemStack(Material.AIR);
      } else {
         ItemStack var2 = new ItemStack(Material.IRON_SWORD);
         ItemMeta var3 = var2.getItemMeta();
         var3.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Settings.Items.PvP.Displayname").replace("&", "§"));
         ArrayList var4 = new ArrayList();

         for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Settings.Items.PvP.Lore")) {
            var6 = var6.replace("%status%", this.a(this.a.getWorldManager().rulePvP(this.b.get(var1.getName()))));
            var6 = var6.replace("&", "§");
            var4.add(var6);
         }

         var3.setLore(var4);
         var2.setItemMeta(var3);
         return var2;
      }
   }

   private ItemStack h(Player var1) {
      if (this.a.getConfig().getBoolean("Permissions.World-Border")
         && !var1.hasPermission("PlayerWorldsPro.worldBorder")
         && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
         return new ItemStack(Material.AIR);
      } else {
         ItemStack var2 = new ItemStack(Material.BARRIER);
         ItemMeta var3 = var2.getItemMeta();
         var3.setDisplayName(
            this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Settings.Items.World-Border.Displayname").replace("&", "§")
         );
         ArrayList var4 = new ArrayList();

         for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Settings.Items.World-Border.Lore")) {
            var6 = var6.replace("&", "§");
            var4.add(var6);
         }

         var3.setLore(var4);
         var2.setItemMeta(var3);
         return var2;
      }
   }

   private ItemStack i(Player var1) {
      if (this.a.getConfig().getBoolean("Permissions.Item-Pickup")
         && !var1.hasPermission("PlayerWorldsPro.editItemPickup")
         && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
         return new ItemStack(Material.AIR);
      } else {
         ItemStack var2 = new ItemStack(Material.GOLDEN_APPLE);
         ItemMeta var3 = var2.getItemMeta();
         var3.setDisplayName(
            this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Settings.Items.Item-Pickup.Displayname").replace("&", "§")
         );
         ArrayList var4 = new ArrayList();

         for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Settings.Items.Item-Pickup.Lore")) {
            var6 = var6.replace("%status%", this.a(this.a.getWorldManager().rulePickup(this.b.get(var1.getName()))));
            var6 = var6.replace("&", "§");
            var4.add(var6);
         }

         var3.setLore(var4);
         var2.setItemMeta(var3);
         return var2;
      }
   }

   private ItemStack j(Player var1) {
      if (this.a.getConfig().getBoolean("Permissions.Drop-Item")
         && !var1.hasPermission("PlayerWorldsPro.editDropItem")
         && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
         return new ItemStack(Material.AIR);
      } else {
         ItemStack var2 = new ItemStack(Material.ROTTEN_FLESH);
         ItemMeta var3 = var2.getItemMeta();
         var3.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Settings.Items.Drop-Item.Displayname").replace("&", "§"));
         ArrayList var4 = new ArrayList();

         for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Settings.Items.Drop-Item.Lore")) {
            var6 = var6.replace("%status%", this.a(this.a.getWorldManager().ruleDrop(this.b.get(var1.getName()))));
            var6 = var6.replace("&", "§");
            var4.add(var6);
         }

         var3.setLore(var4);
         var2.setItemMeta(var3);
         return var2;
      }
   }

   private ItemStack k(Player var1) {
      if (this.a.getConfig().getBoolean("Permissions.Player-Damage")
         && !var1.hasPermission("PlayerWorldsPro.editPlayerDamage")
         && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
         return new ItemStack(Material.AIR);
      } else {
         ItemStack var2 = new ItemStack(Material.LEATHER_CHESTPLATE);
         ItemMeta var3 = var2.getItemMeta();
         var3.setDisplayName(
            this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Settings.Items.Player-Damage.Displayname").replace("&", "§")
         );
         ArrayList var4 = new ArrayList();

         for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Settings.Items.Player-Damage.Lore")) {
            var6 = var6.replace("%status%", this.a(this.a.getWorldManager().ruleDamage(this.b.get(var1.getName()))));
            var6 = var6.replace("&", "§");
            var4.add(var6);
         }

         var3.setLore(var4);
         var2.setItemMeta(var3);
         return var2;
      }
   }

   private ItemStack l(Player var1) {
      if (this.a.getConfig().getBoolean("Permissions.Hunger")
         && !var1.hasPermission("PlayerWorldsPro.editHunger")
         && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
         return new ItemStack(Material.AIR);
      } else {
         ItemStack var2 = new ItemStack(Material.APPLE);
         ItemMeta var3 = var2.getItemMeta();
         var3.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Settings.Items.Hunger.Displayname").replace("&", "§"));
         ArrayList var4 = new ArrayList();

         for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Settings.Items.Hunger.Lore")) {
            var6 = var6.replace("%status%", this.a(this.a.getWorldManager().ruleHunger(this.b.get(var1.getName()))));
            var6 = var6.replace("&", "§");
            var4.add(var6);
         }

         var3.setLore(var4);
         var2.setItemMeta(var3);
         return var2;
      }
   }

   private ItemStack m(Player var1) {
      if (this.a.getConfig().getBoolean("Permissions.Bucket")
         && !var1.hasPermission("PlayerWorldsPro.editBucket")
         && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
         return new ItemStack(Material.AIR);
      } else {
         ItemStack var2 = new ItemStack(Material.BUCKET);
         ItemMeta var3 = var2.getItemMeta();
         var3.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Settings.Items.Bucket.Displayname").replace("&", "§"));
         ArrayList var4 = new ArrayList();

         for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Settings.Items.Bucket.Lore")) {
            var6 = var6.replace("%status%", this.a(this.a.getWorldManager().ruleBucket(this.b.get(var1.getName()))));
            var6 = var6.replace("&", "§");
            var4.add(var6);
         }

         var3.setLore(var4);
         var2.setItemMeta(var3);
         return var2;
      }
   }

   private ItemStack n(Player var1) {
      if (this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")
         && this.a.getConfig().getBoolean("Permissions.Change-GameMode")
         && !var1.hasPermission("PlayerWorldsPro.changeGameMode")
         && this.a.getConfig().getBoolean("Permissions.Change-Default-GameMode")
         && !var1.hasPermission("PlayerWorldsPro.changeGameMode.default")) {
         return new ItemStack(Material.AIR);
      } else {
         ItemStack var2 = new ItemStack(Material.DIAMOND);
         ItemMeta var3 = var2.getItemMeta();
         var3.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Settings.Items.GameMode.Displayname").replace("&", "§"));
         ArrayList var4 = new ArrayList();

         for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Settings.Items.GameMode.Lore")) {
            var6 = var6.replace("&", "§");
            var4.add(var6);
         }

         var3.setLore(var4);
         var2.setItemMeta(var3);
         return var2;
      }
   }

   private ItemStack o(Player var1) {
      if (this.a.getConfig().getBoolean("Permissions.Teleport")
         && !var1.hasPermission("PlayerWorldsPro.teleport")
         && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
         return new ItemStack(Material.AIR);
      } else {
         ItemStack var2 = new ItemStack(this.a.getMaterialManager().get(MaterialManager.Type.PLAYER_HEAD), 1, (short)SkullType.PLAYER.ordinal());
         ItemMeta var3 = var2.getItemMeta();
         var3.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Settings.Items.Teleport.Displayname").replace("&", "§"));
         ArrayList var4 = new ArrayList();

         for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Settings.Items.Teleport.Lore")) {
            var6 = var6.replace("&", "§");
            var4.add(var6);
         }

         var3.setLore(var4);
         var2.setItemMeta(var3);
         return var2;
      }
   }

   private ItemStack p(Player var1) {
      if (this.a.getConfig().getBoolean("Permissions.Fly")
         && !var1.hasPermission("PlayerWorldsPro.fly")
         && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
         return new ItemStack(Material.AIR);
      } else {
         ItemStack var2 = new ItemStack(Material.FEATHER, 1);
         ItemMeta var3 = var2.getItemMeta();
         var3.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Settings.Items.Fly.Displayname").replace("&", "§"));
         ArrayList var4 = new ArrayList();

         for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Settings.Items.Fly.Lore")) {
            var6 = var6.replace("&", "§");
            var4.add(var6);
         }

         var3.setLore(var4);
         var2.setItemMeta(var3);
         return var2;
      }
   }

   private ItemStack q(Player var1) {
      if (this.a.getConfig().getBoolean("Permissions.Access")
         && !var1.hasPermission("PlayerWorldsPro.access")
         && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
         return new ItemStack(Material.AIR);
      } else {
         ItemStack var2 = new ItemStack(this.a.getMaterialManager().get(MaterialManager.Type.WRITABLE_BOOK));
         ItemMeta var3 = var2.getItemMeta();
         var3.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Settings.Items.Access.Displayname").replace("&", "§"));
         ArrayList var4 = new ArrayList();

         for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Settings.Items.Access.Lore")) {
            String var7 = "Variables." + this.a.getWorldManager().ruleAccessibility(this.b.get(var1.getName()));
            var6 = var6.replace("%status%", this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString(var7));
            var6 = var6.replace("&", "§");
            var4.add(var6);
         }

         var3.setLore(var4);
         var2.setItemMeta(var3);
         return var2;
      }
   }

   private ItemStack r(Player var1) {
      if (this.a.getConfig().getBoolean("Permissions.Access")
         && !var1.hasPermission("PlayerWorldsPro.access")
         && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
         return new ItemStack(Material.AIR);
      } else {
         ItemStack var2 = new ItemStack(this.a.getMaterialManager().get(MaterialManager.Type.PLAYER_HEAD), 1, (short)SkullType.PLAYER.ordinal());
         ItemMeta var3 = var2.getItemMeta();
         var3.setDisplayName(
            this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Settings.Items.Add-Member.Displayname").replace("&", "§")
         );
         ArrayList var4 = new ArrayList();

         for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Settings.Items.Add-Member.Lore")) {
            var6 = var6.replace("&", "§");
            var4.add(var6);
         }

         var3.setLore(var4);
         var2.setItemMeta(var3);
         return var2;
      }
   }

   private ItemStack s(Player var1) {
      if (this.a.getConfig().getBoolean("Permissions.Access")
         && !var1.hasPermission("PlayerWorldsPro.access")
         && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
         return new ItemStack(Material.AIR);
      } else {
         ItemStack var2 = new ItemStack(this.a.getMaterialManager().get(MaterialManager.Type.PLAYER_HEAD), 1, (short)SkullType.PLAYER.ordinal());
         ItemMeta var3 = var2.getItemMeta();
         var3.setDisplayName(
            this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Settings.Items.Remove-Member.Displayname").replace("&", "§")
         );
         ArrayList var4 = new ArrayList();

         for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Settings.Items.Remove-Member.Lore")) {
            var6 = var6.replace("&", "§");
            var4.add(var6);
         }

         var3.setLore(var4);
         var2.setItemMeta(var3);
         return var2;
      }
   }

   private ItemStack c() {
      ItemStack var1 = new ItemStack(Material.GOLD_INGOT);
      ItemMeta var2 = var1.getItemMeta();
      var2.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Settings.Items.Extend.Displayname").replace("&", "§"));
      ArrayList var3 = new ArrayList();

      for (String var5 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Settings.Items.Extend.Lore")) {
         var5 = var5.replace("&", "§");
         var3.add(var5);
      }

      var2.setLore(var3);
      var1.setItemMeta(var2);
      return var1;
   }

   private ItemStack d() {
      ItemStack var1 = new ItemStack(Material.TNT);
      ItemMeta var2 = var1.getItemMeta();
      var2.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Settings.Items.Delete.Displayname").replace("&", "§"));
      ArrayList var3 = new ArrayList();

      for (String var5 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Settings.Items.Delete.Lore")) {
         var5 = var5.replace("&", "§");
         var3.add(var5);
      }

      var2.setLore(var3);
      var1.setItemMeta(var2);
      return var1;
   }

   private ItemStack t(Player var1) {
      if (this.a.getConfig().getBoolean("Permissions.Kick")
         && !var1.hasPermission("PlayerWorldsPro.kick")
         && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
         return new ItemStack(Material.AIR);
      } else {
         ItemStack var2 = new ItemStack(Material.LEATHER_BOOTS);
         ItemMeta var3 = var2.getItemMeta();
         var3.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Settings.Items.Kick.Displayname").replace("&", "§"));
         ArrayList var4 = new ArrayList();

         for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Settings.Items.Kick.Lore")) {
            var6 = var6.replace("&", "§");
            var4.add(var6);
         }

         var3.setLore(var4);
         var2.setItemMeta(var3);
         return var2;
      }
   }

   private ItemStack u(Player var1) {
      if (this.a.getConfig().getBoolean("Permissions.Ban")
         && !var1.hasPermission("PlayerWorldsPro.ban")
         && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
         return new ItemStack(Material.AIR);
      } else {
         ItemStack var2 = new ItemStack(Material.BARRIER);
         ItemMeta var3 = var2.getItemMeta();
         var3.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Settings.Items.Ban.Displayname").replace("&", "§"));
         ArrayList var4 = new ArrayList();

         for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Settings.Items.Ban.Lore")) {
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
         && var1.getView().getTitle().equals(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Settings.Title").replace("&", "§"))) {
         if (var1.getCurrentItem() != null) {
            if (var1.getCurrentItem().hasItemMeta()) {
               if (var1.getCurrentItem().getItemMeta().hasDisplayName()) {
                  var1.setCancelled(true);
                  if (!this.b.containsKey(var2.getName())) {
                     var2.closeInventory();
                     var2.updateInventory();
                  } else {
                     String var3 = var1.getCurrentItem().getItemMeta().getDisplayName();
                     String var4 = this.b.get(var2.getName());
                     if (var1.getCurrentItem().getType() == this.a.getMaterialManager().get(MaterialManager.Type.CLOCK)) {
                        this.a.q().a(var2);
                     } else if (var1.getCurrentItem().getType() == Material.WATER_BUCKET) {
                        this.a.r().a(var2);
                     } else if (var1.getCurrentItem().getType() == Material.BONE) {
                        this.a.n().a(var2);
                     } else if (var1.getCurrentItem().getType().toString().contains(this.a.getMaterialManager().get(MaterialManager.Type.RED_BED).toString())) {
                        this.b(var2, var4);
                     } else if (var1.getCurrentItem().getType() == Material.IRON_PICKAXE) {
                        this.a(var2, var1, var4, "Block-Breaking", "editBlockBreaking");
                     } else if (var1.getCurrentItem().getType() == Material.BRICK) {
                        this.a(var2, var1, var4, "Block-Placing", "editBlockPlacing");
                     } else if (var1.getCurrentItem().getType() == Material.IRON_SWORD) {
                        this.a(var2, var1, var4, "PvP", "editPvP");
                     } else if (var1.getCurrentItem().getType() == Material.BARRIER
                        && var3.equals(
                           this.a
                              .getConfigManager()
                              .get(ConfigManager.Type.MESSAGES)
                              .getString("GUI.Settings.Items.World-Border.Displayname")
                              .replace("&", "§")
                        )) {
                        this.a.y().a(var2);
                     } else if (var1.getCurrentItem().getType() == Material.GOLDEN_APPLE) {
                        this.a(var2, var1, var4, "Item-Pickup", "editItemPickup");
                     } else if (var1.getCurrentItem().getType() == Material.ROTTEN_FLESH) {
                        this.a(var2, var1, var4, "Drop-Item", "editDropItem");
                     } else if (var1.getCurrentItem().getType() == Material.LEATHER_CHESTPLATE) {
                        this.a(var2, var1, var4, "Player-Damage", "editPlayerDamage");
                     } else if (var1.getCurrentItem().getType() == Material.APPLE) {
                        this.a(var2, var1, var4, "Hunger", "editHunger");
                     } else if (var1.getCurrentItem().getType() == Material.BUCKET) {
                        this.a(var2, var1, var4, "Bucket", "editBucket");
                     } else if (var1.getCurrentItem().getType() == Material.DIAMOND) {
                        this.a(var2, var1);
                     } else if (var1.getCurrentItem().getType() == this.a.getMaterialManager().get(MaterialManager.Type.PLAYER_HEAD)
                        && var3.equals(
                           this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Settings.Items.Teleport.Displayname").replace("&", "§")
                        )) {
                        this.b(var2, var1);
                     } else if (var1.getCurrentItem().getType() == Material.FEATHER) {
                        this.a(var2, var1, var4);
                     } else if (var1.getCurrentItem().getType() == this.a.getMaterialManager().get(MaterialManager.Type.WRITABLE_BOOK)) {
                        this.b(var2, var1, var4);
                     } else if (var1.getCurrentItem().getType() == this.a.getMaterialManager().get(MaterialManager.Type.PLAYER_HEAD)
                        && var3.equals(
                           this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Settings.Items.Add-Member.Displayname").replace("&", "§")
                        )) {
                        this.a.k().a(var2);
                     } else if (var1.getCurrentItem().getType() == this.a.getMaterialManager().get(MaterialManager.Type.PLAYER_HEAD)
                        && var3.equals(
                           this.a
                              .getConfigManager()
                              .get(ConfigManager.Type.MESSAGES)
                              .getString("GUI.Settings.Items.Remove-Member.Displayname")
                              .replace("&", "§")
                        )) {
                        this.a.u().a(var2);
                     } else if (var1.getCurrentItem().getType() == Material.GOLD_INGOT) {
                        this.a.g().a(var2);
                     } else if (var1.getCurrentItem().getType() == Material.TNT && var1.isShiftClick()) {
                        var2.closeInventory();
                        this.a.getWorldManager().cmdHandleDelete(var2, this.b.get(var2.getName()));
                     } else if (var1.getCurrentItem().getType() == Material.LEATHER_BOOTS) {
                        this.a.t().a(var2);
                     } else if (var1.getCurrentItem().getType() == Material.BARRIER) {
                        this.c(var2, var1);
                     }
                  }
               }
            }
         }
      }
   }

   private void a(Player var1, InventoryClickEvent var2, String var3, String var4, String var5) {
      var1.closeInventory();
      var1.updateInventory();
      if (this.a.getConfig().getBoolean("Permissions." + var4) && !var1.hasPermission("PlayerWorldsPro." + var5)) {
         var1.sendMessage(
            this.a.getBasicManager().getMessagePrefix()
               + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages." + var4 + ".Insufficient-Permission").replace("&", "§")
         );
      } else if (var2.isLeftClick()) {
         this.a.getConfigManager().get(ConfigManager.Type.DATA).set("Worlds." + var3 + "." + var4, true);
         this.a.getConfigManager().save(ConfigManager.Type.DATA);
         this.a.getConfigManager().load(ConfigManager.Type.DATA);
         var1.sendMessage(
            this.a.getBasicManager().getMessagePrefix()
               + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages." + var4 + ".Allow").replace("&", "§")
         );
      } else if (var2.isRightClick()) {
         this.a.getConfigManager().get(ConfigManager.Type.DATA).set("Worlds." + var3 + "." + var4, false);
         this.a.getConfigManager().save(ConfigManager.Type.DATA);
         this.a.getConfigManager().load(ConfigManager.Type.DATA);
         var1.sendMessage(
            this.a.getBasicManager().getMessagePrefix()
               + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages." + var4 + ".Deny").replace("&", "§")
         );
      }

      this.a.j().b().remove(var1.getName());
   }

   private void b(Player var1, String var2) {
      if (this.a.getConfig().getBoolean("Permissions.Set-Spawn-Location") && !var1.hasPermission("PlayerWorldsPro.setSpawn")) {
         var1.sendMessage(
            this.a.getBasicManager().getMessagePrefix()
               + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Insufficient-Permission").replace("&", "§")
         );
      } else {
         this.a.getWorldManager().setSpawn(var1, var2);
      }

      var1.closeInventory();
      var1.updateInventory();
      this.a.j().b().remove(var1.getName());
   }

   private void a(Player var1, InventoryClickEvent var2) {
      if (var2.isLeftClick()) {
         this.a.o().a(var1);
      } else if (var2.isRightClick()) {
         this.a.m().a(var1);
      }
   }

   private void b(Player var1, InventoryClickEvent var2) {
      if (var2.isLeftClick()) {
         this.a.v().a(var1);
      } else if (var2.isRightClick()) {
         this.a.w().a(var1);
      }
   }

   private void a(Player var1, InventoryClickEvent var2, String var3) {
      if (this.a.getConfig().getBoolean("Permissions.Fly") && !var1.hasPermission("PlayerWorldsPro.fly")) {
         var1.sendMessage(
            this.a.getBasicManager().getMessagePrefix()
               + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Fly.Insufficient-Permission").replace("&", "§")
         );
      } else {
         String var4 = this.a.getBasicManager().getOwnerFromWorldName(var1.getWorld().getName());
         if (var4 == null || !var4.equals(var3)) {
            var1.closeInventory();
            var1.updateInventory();
            var1.sendMessage(
               this.a.getBasicManager().getMessagePrefix()
                  + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Same-World").replace("&", "§")
            );
            return;
         }

         if (var2.isLeftClick()) {
            if (var1.getAllowFlight()) {
               var1.setAllowFlight(false);
               var1.setFlying(false);
            } else {
               var1.setAllowFlight(true);
               var1.setFlying(true);
            }

            var1.sendMessage(
               this.a.getBasicManager().getMessagePrefix()
                  + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Fly.Change").replace("&", "§")
            );
            var1.closeInventory();
            var1.updateInventory();
            this.a.j().b().remove(var1.getName());
         } else if (var2.isRightClick()) {
            this.a.s().a(var1);
         }
      }
   }

   private void b(Player var1, InventoryClickEvent var2, String var3) {
      if (this.a.getConfig().getBoolean("Permissions.Access") && !var1.hasPermission("PlayerWorldsPro.access")) {
         var1.sendMessage(
            this.a.getBasicManager().getMessagePrefix()
               + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Access.Insufficient-Permission").replace("&", "§")
         );
      } else if (var2.isLeftClick()) {
         this.a.getConfigManager().get(ConfigManager.Type.DATA).set("Worlds." + var3 + ".Access", "PUBLIC");
         this.a.getConfigManager().save(ConfigManager.Type.DATA);
         this.a.getConfigManager().load(ConfigManager.Type.DATA);
         var1.sendMessage(
            this.a.getBasicManager().getMessagePrefix()
               + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Access.Public").replace("&", "§")
         );
      } else if (var2.isRightClick()) {
         this.a.getConfigManager().get(ConfigManager.Type.DATA).set("Worlds." + var3 + ".Access", "PRIVATE");
         this.a.getConfigManager().save(ConfigManager.Type.DATA);
         this.a.getConfigManager().load(ConfigManager.Type.DATA);
         var1.sendMessage(
            this.a.getBasicManager().getMessagePrefix()
               + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Access.Private").replace("&", "§")
         );

         for (String var5 : this.a.getWorldManager().getOwnWorlds(this.a.getBasicManager().getWorldPrefix() + var3)) {
            World var6 = Bukkit.getWorld(var5);
            if (var6 != null) {
               for (Player var8 : var6.getPlayers()) {
                  if (!this.a.getWorldManager().canVisit(var8, var3)) {
                     if (this.a.getBasicManager().hasLobby()) {
                        this.a.getBasicManager().teleport(var8, this.a.getBasicManager().getLobby(), false);
                     } else {
                        var8.kickPlayer(
                           this.a.getBasicManager().getMessagePrefix()
                              + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Lobby-Is-Not-Configured").replace("&", "§")
                        );
                     }
                  }
               }
            }
         }
      }

      var1.closeInventory();
      var1.updateInventory();
      this.a.j().b().remove(var1.getName());
   }

   private void c(Player var1, InventoryClickEvent var2) {
      if (var2.isLeftClick()) {
         this.a.l().a(var1);
      } else if (var2.isRightClick()) {
         this.a.x().a(var1);
      }
   }

   public Main a() {
      return this.a;
   }

   public HashMap<String, String> b() {
      return this.b;
   }
}
