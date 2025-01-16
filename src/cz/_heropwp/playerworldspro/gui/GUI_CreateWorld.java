package cz._heropwp.playerworldspro.gui;

import cz._heropwp.playerworldspro.Main;
import cz._heropwp.playerworldspro.util.ConfigManager;
import cz._heropwp.playerworldspro.util.MaterialManager;
import cz._heropwp.playerworldspro.util.WorldManager;
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

public class GUI_CreateWorld implements Listener {
   private final Main a;

   public GUI_CreateWorld(Main var1) {
      this.a = var1;
   }

   public void a(Player var1) {
      if (this.a.getWorldManager().worldExists(var1.getName())) {
         var1.closeInventory();
         var1.sendMessage(
            this.a.getBasicManager().getMessagePrefix()
               + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Already-Have").replace("&", "§")
         );
      } else if (this.a.getConfig().getBoolean("Permissions.Create-World") && !var1.hasPermission("PlayerWorldsPro.createWorld")) {
         var1.closeInventory();
         var1.sendMessage(
            this.a.getBasicManager().getMessagePrefix()
               + this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("Messages.Insufficient-Permission-Create-World").replace("&", "§")
         );
      } else {
         Inventory var2 = Bukkit.createInventory(
            null, 27, this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Create-World.Title").replace("&", "§")
         );
         var1.openInventory(var2);
         var2.setItem(10, this.b(var1));
         var2.setItem(11, this.c(var1));
         var2.setItem(12, this.d(var1));
         var2.setItem(15, this.e(var1));
         var2.setItem(16, this.f(var1));
      }
   }

   private ItemStack b(Player var1) {
      if (this.a.getConfig().getBoolean("Permissions.Create-World-Type.Normal")
         && !var1.hasPermission("PlayerWorldsPro.createWorld.normal")
         && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
         return new ItemStack(Material.AIR);
      } else {
         ItemStack var2 = new ItemStack(this.a.getMaterialManager().get(MaterialManager.Type.OAK_SAPLING));
         ItemMeta var3 = var2.getItemMeta();
         var3.setDisplayName(
            this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Create-World.Items.Normal.Displayname").replace("&", "§")
         );
         ArrayList var4 = new ArrayList();

         for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Create-World.Items.Normal.Lore")) {
            var6 = var6.replace("&", "§");
            var4.add(var6);
         }

         var3.setLore(var4);
         var2.setItemMeta(var3);
         return var2;
      }
   }

   private ItemStack c(Player var1) {
      if (this.a.getConfig().getBoolean("Permissions.Create-World-Type.Flat")
         && !var1.hasPermission("PlayerWorldsPro.createWorld.flat")
         && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
         return new ItemStack(Material.AIR);
      } else {
         ItemStack var2 = new ItemStack(Material.GRASS);
         ItemMeta var3 = var2.getItemMeta();
         var3.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Create-World.Items.Flat.Displayname").replace("&", "§"));
         ArrayList var4 = new ArrayList();

         for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Create-World.Items.Flat.Lore")) {
            var6 = var6.replace("&", "§");
            var4.add(var6);
         }

         var3.setLore(var4);
         var2.setItemMeta(var3);
         return var2;
      }
   }

   private ItemStack d(Player var1) {
      if (this.a.getConfig().getBoolean("Permissions.Create-World-Type.Empty")
         && !var1.hasPermission("PlayerWorldsPro.createWorld.empty")
         && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
         return new ItemStack(Material.AIR);
      } else {
         ItemStack var2 = new ItemStack(Material.BARRIER);
         ItemMeta var3 = var2.getItemMeta();
         var3.setDisplayName(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Create-World.Items.Empty.Displayname").replace("&", "§"));
         ArrayList var4 = new ArrayList();

         for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Create-World.Items.Empty.Lore")) {
            var6 = var6.replace("&", "§");
            var4.add(var6);
         }

         var3.setLore(var4);
         var2.setItemMeta(var3);
         return var2;
      }
   }

   private ItemStack e(Player var1) {
      if (this.a.getConfig().getBoolean("Permissions.Create-World-Type.Pre-Built-Maps")
         && !var1.hasPermission("PlayerWorldsPro.createWorld.preBuiltMaps")
         && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
         return new ItemStack(Material.AIR);
      } else {
         ItemStack var2 = new ItemStack(Material.BRICK);
         ItemMeta var3 = var2.getItemMeta();
         var3.setDisplayName(
            this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Create-World.Items.Pre-Built-Maps.Displayname").replace("&", "§")
         );
         ArrayList var4 = new ArrayList();

         for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Create-World.Items.Pre-Built-Maps.Lore")) {
            var6 = var6.replace("&", "§");
            var4.add(var6);
         }

         var3.setLore(var4);
         var2.setItemMeta(var3);
         return var2;
      }
   }

   private ItemStack f(Player var1) {
      if (this.a.getConfig().getBoolean("Permissions.Create-World-Type.Custom-Generators")
         && !var1.hasPermission("PlayerWorldsPro.createWorld.customGenerators")
         && this.a.getConfig().getBoolean("GUI.Basic.Hide-Without-Permission")) {
         return new ItemStack(Material.AIR);
      } else {
         ItemStack var2 = new ItemStack(this.a.getMaterialManager().get(MaterialManager.Type.COMMAND_BLOCK));
         ItemMeta var3 = var2.getItemMeta();
         var3.setDisplayName(
            this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Create-World.Items.Custom-Generators.Displayname").replace("&", "§")
         );
         ArrayList var4 = new ArrayList();

         for (String var6 : this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getStringList("GUI.Create-World.Items.Custom-Generators.Lore")) {
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
         && var1.getView().getTitle().equals(this.a.getConfigManager().get(ConfigManager.Type.MESSAGES).getString("GUI.Create-World.Title").replace("&", "§"))) {
         if (var1.getCurrentItem() != null) {
            if (var1.getCurrentItem().hasItemMeta()) {
               if (var1.getCurrentItem().getItemMeta().hasDisplayName()) {
                  var1.setCancelled(true);
                  if (var1.getCurrentItem().getType() == this.a.getMaterialManager().get(MaterialManager.Type.OAK_SAPLING)) {
                     if (this.a.getConfig().getBoolean("Permissions.Create-World-Type.Normal") && !var2.hasPermission("PlayerWorldsPro.createWorld.normal")) {
                        var2.closeInventory();
                        var2.updateInventory();
                        var2.sendMessage(
                           this.a.getBasicManager().getMessagePrefix()
                              + this.a
                                 .getConfigManager()
                                 .get(ConfigManager.Type.MESSAGES)
                                 .getString("Messages.Insufficient-Permission-Create-World.Type")
                                 .replace("&", "§")
                        );
                        return;
                     }

                     if (this.a.getWorldManager().systemExipreEnabled()) {
                        if (this.a.getConfig().getBoolean("Claim.Enabled")
                           && !this.a.getConfigManager().get(ConfigManager.Type.PLAYERS).contains("Claim." + var2.getName())) {
                           var2.closeInventory();
                           var2.updateInventory();
                           this.a
                              .getWorldManager()
                              .createWorld(var2, WorldManager.Generator.NORMAL, null, null, this.a.getConfig().getInt("Claim.Length"), null, true);
                        } else {
                           this.a.d().a(var2, WorldManager.Generator.NORMAL, null, null);
                        }
                     } else {
                        var2.closeInventory();
                        var2.updateInventory();
                        this.a.getWorldManager().createWorld(var2, WorldManager.Generator.NORMAL, null, null, null, null, false);
                     }
                  } else if (var1.getCurrentItem().getType() == Material.GRASS) {
                     if (this.a.getConfig().getBoolean("Permissions.Create-World-Type.Flat") && !var2.hasPermission("PlayerWorldsPro.createWorld.flat")) {
                        var2.closeInventory();
                        var2.updateInventory();
                        var2.sendMessage(
                           this.a.getBasicManager().getMessagePrefix()
                              + this.a
                                 .getConfigManager()
                                 .get(ConfigManager.Type.MESSAGES)
                                 .getString("Messages.Insufficient-Permission-Create-World.Type")
                                 .replace("&", "§")
                        );
                        return;
                     }

                     if (this.a.getWorldManager().systemExipreEnabled()) {
                        if (this.a.getConfig().getBoolean("Claim.Enabled")
                           && !this.a.getConfigManager().get(ConfigManager.Type.PLAYERS).contains("Claim." + var2.getName())) {
                           var2.closeInventory();
                           var2.updateInventory();
                           this.a
                              .getWorldManager()
                              .createWorld(var2, WorldManager.Generator.FLAT, null, null, this.a.getConfig().getInt("Claim.Length"), null, true);
                        } else {
                           this.a.d().a(var2, WorldManager.Generator.FLAT, null, null);
                        }
                     } else {
                        var2.closeInventory();
                        var2.updateInventory();
                        this.a.getWorldManager().createWorld(var2, WorldManager.Generator.FLAT, null, null, null, null, false);
                     }
                  } else if (var1.getCurrentItem().getType() == Material.BARRIER) {
                     if (this.a.getConfig().getBoolean("Permissions.Create-World-Type.Empty") && !var2.hasPermission("PlayerWorldsPro.createWorld.empty")) {
                        var2.closeInventory();
                        var2.updateInventory();
                        var2.sendMessage(
                           this.a.getBasicManager().getMessagePrefix()
                              + this.a
                                 .getConfigManager()
                                 .get(ConfigManager.Type.MESSAGES)
                                 .getString("Messages.Insufficient-Permission-Create-World.Type")
                                 .replace("&", "§")
                        );
                        return;
                     }

                     if (this.a.getWorldManager().systemExipreEnabled()) {
                        if (this.a.getConfig().getBoolean("Claim.Enabled")
                           && !this.a.getConfigManager().get(ConfigManager.Type.PLAYERS).contains("Claim." + var2.getName())) {
                           var2.closeInventory();
                           var2.updateInventory();
                           this.a
                              .getWorldManager()
                              .createWorld(var2, WorldManager.Generator.EMPTY, null, null, this.a.getConfig().getInt("Claim.Length"), null, true);
                        } else {
                           this.a.d().a(var2, WorldManager.Generator.EMPTY, null, null);
                        }
                     } else {
                        var2.closeInventory();
                        var2.updateInventory();
                        this.a.getWorldManager().createWorld(var2, WorldManager.Generator.EMPTY, null, null, null, null, false);
                     }
                  } else if (var1.getCurrentItem().getType() == Material.BRICK) {
                     if (this.a.getConfig().getBoolean("Permissions.Create-World-Type.Pre-Built-Maps")
                        && !var2.hasPermission("PlayerWorldsPro.createWorld.preBuiltMaps")) {
                        var2.closeInventory();
                        var2.updateInventory();
                        var2.sendMessage(
                           this.a.getBasicManager().getMessagePrefix()
                              + this.a
                                 .getConfigManager()
                                 .get(ConfigManager.Type.MESSAGES)
                                 .getString("Messages.Insufficient-Permission-Create-World.Type")
                                 .replace("&", "§")
                        );
                        return;
                     }

                     this.a.i().a(var2);
                  } else if (var1.getCurrentItem().getType() == this.a.getMaterialManager().get(MaterialManager.Type.COMMAND_BLOCK)) {
                     if (this.a.getConfig().getBoolean("Permissions.Create-World-Type.Custom-Generators")
                        && !var2.hasPermission("PlayerWorldsPro.createWorld.customGenerators")) {
                        var2.closeInventory();
                        var2.updateInventory();
                        var2.sendMessage(
                           this.a.getBasicManager().getMessagePrefix()
                              + this.a
                                 .getConfigManager()
                                 .get(ConfigManager.Type.MESSAGES)
                                 .getString("Messages.Insufficient-Permission-Create-World.Type")
                                 .replace("&", "§")
                        );
                        return;
                     }

                     this.a.f().a(var2);
                  }
               }
            }
         }
      }
   }
}
