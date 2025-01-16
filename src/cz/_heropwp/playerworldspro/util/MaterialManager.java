package cz._heropwp.playerworldspro.util;

import org.bukkit.Material;

public class MaterialManager {
   private final boolean modern = Material.getMaterial("RED_WOOL") != null;

   private String toLegacy(MaterialManager.Type var1) {
      switch (var1) {
         case OAK_SAPLING:
            return "SAPLING";
         case PLAYER_HEAD:
            return "SKULL_ITEM";
         case COMMAND_BLOCK:
            return "COMMAND";
         case CLOCK:
            return "WATCH";
         case RED_BED:
            return "BED";
         case WRITABLE_BOOK:
            return "BOOK_AND_QUILL";
         case WOODEN_SWORD:
            return "WOOD_SWORD";
         case DEAD_BUSH:
            return "DOUBLE_PLANT";
         default:
            return "BEDROCK";
      }
   }

   public Material get(MaterialManager.Type var1) {
      return this.modern ? Material.getMaterial(String.valueOf(var1)) : Material.getMaterial(this.toLegacy(var1));
   }

   public static enum Type {
      OAK_SAPLING,
      PLAYER_HEAD,
      COMMAND_BLOCK,
      CLOCK,
      RED_BED,
      WRITABLE_BOOK,
      WOODEN_SWORD,
      DEAD_BUSH;
   }
}
