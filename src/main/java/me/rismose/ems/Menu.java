package me.rismose.ems;

import me.mattstudios.mfgui.gui.components.ItemBuilder;
import me.mattstudios.mfgui.gui.guis.Gui;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Menu implements Listener, CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // MAIN GUI
        Gui gui = new Gui(3,"Main Menu");
        gui.setDefaultClickAction(event -> {
            event.setCancelled(true);
        });
        // ZOMBIE GUI
        Gui zombiegui = new Gui(4, "Zombie Menu");
        zombiegui.setDefaultClickAction(event -> {
            event.setCancelled(true);
        });
        // ZOMBIE ARMOR GUI
        Gui zombieArmorGUI = new Gui(3, "Zombie Armortype Menu");
        zombieArmorGUI.setDefaultClickAction(event -> {
            event.setCancelled(true);
        });
        // OTHER GUI'S

        // MAIN GUI ITEMS
        GuiItem ZHead = ItemBuilder.from(Material.ZOMBIE_HEAD).setName(ChatColor.YELLOW + "Zombie Menu").asGuiItem(event -> {

            Player player = (Player) event.getWhoClicked();
            zombiegui.open(player);

        });
        GuiItem Close = ItemBuilder.from(Material.RED_WOOL).setName(ChatColor.RED + "Close").asGuiItem(event -> {

            Player player = (Player) event.getWhoClicked();
            gui.close(player);

        });
        GuiItem Back = ItemBuilder.from(Material.GRAY_WOOL).setName(ChatColor.RED + "Back").asGuiItem(event -> {

            Player player = (Player) event.getWhoClicked();
            gui.open(player);

        });
        // ZOMBIE GUI ITEMS
        GuiItem ZSpawn = ItemBuilder.from(Material.ZOMBIE_HEAD).setName(ChatColor.YELLOW + "Default Zombie").asGuiItem(event -> {

            Player player = (Player) event.getWhoClicked();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',EMS.ZombieNormalSpawn));
            spawnZombie(player.getLocation(), EntityType.ZOMBIE);

        });
        GuiItem ZPoppy = ItemBuilder.from(Material.POPPY).setName(ChatColor.YELLOW + "NOTE: Spawns a default Zombie").asGuiItem();
        GuiItem ZHeadArmor = ItemBuilder.from(Material.ZOMBIE_HEAD).setName(ChatColor.YELLOW + "Armored Zombie").asGuiItem(event -> {

            Player player = (Player) event.getWhoClicked();
            zombieArmorGUI.open(player);

        });
        GuiItem ZArmorChest = ItemBuilder.from(Material.IRON_CHESTPLATE).setName(ChatColor.YELLOW + "NOTE: Armored Zombie GUI").asGuiItem();
        // OTHER GUI ITEMS

        // MAIN MENU SET ITEMS
        gui.setItem(13, ZHead);
        gui.setItem(26, Close);
        gui.getFiller().fill(ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).setName(" ").asGuiItem());
        // ZOMBIE MENU SET ITEMS
        zombiegui.setItem(12, ZSpawn);
        zombiegui.setItem(21, ZPoppy);
        zombiegui.setItem(14, ZHeadArmor);
        zombiegui.setItem(23, ZArmorChest);
        zombiegui.setItem(35, Back);
        zombiegui.getFiller().fill(ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).setName(" ").asGuiItem());
        // OTHER MENU SET ITEMS

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("EMS.Menu")) {
                if (command.getName().equalsIgnoreCase("ems")) {
                    gui.open(player);
                }
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', EMS.Permission));
            }
        } else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', EMS.NonPlayer));
        }


        return true;
    }

    public void spawnZombie(Location loc, EntityType zombie) {
        loc.getWorld().spawnEntity(loc, zombie);
    }
}
