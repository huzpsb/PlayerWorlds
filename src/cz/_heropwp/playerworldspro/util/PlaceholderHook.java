package cz._heropwp.playerworldspro.util;

import cz._heropwp.playerworldspro.Main;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

public class PlaceholderHook extends PlaceholderExpansion {
   private final Main instance;

   public PlaceholderHook(Main var1) {
      this.instance = var1;
   }

   public boolean persist() {
      return true;
   }

   public boolean canRegister() {
      return true;
   }

   public String getIdentifier() {
      return "pwp";
   }

   public String getAuthor() {
      return this.instance.getDescription().getAuthors().toString();
   }

   public String getVersion() {
      return this.instance.getDescription().getVersion();
   }

   public String onRequest(OfflinePlayer var1, String var2) {
      if (var1 == null) {
         return "";
      } else if (var2.equals("worlds")) {
         return String.valueOf(this.instance.getWorldManager().worldCount());
      } else if (var2.equals("online_players")) {
         return String.valueOf(this.instance.getWorldManager().getWorldPlayers());
      } else if (var2.equals("has_world")) {
         return Boolean.toString(this.instance.getWorldManager().worldExists(var1.getName()));
      } else if (!var2.equals("in_world")) {
         return null;
      } else {
         if (var1.isOnline() && this.instance.getWorldManager().worldExists(var1.getName())) {
            for (String var4 : this.instance.getWorldManager().getOwnWorlds(this.instance.getBasicManager().getWorldPrefix() + var1.getName())) {
               if (var1.getPlayer().getWorld().getName().equals(var4)) {
                  return "true";
               }
            }
         }

         return "false";
      }
   }
}
