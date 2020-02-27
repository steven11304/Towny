package com.palmergames.bukkit.towny.database;

import com.palmergames.bukkit.towny.TownyAdapter;
import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.database.results.CreateResult;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownBlock;
import com.palmergames.bukkit.towny.object.TownyWorld;
import org.bukkit.Bukkit;
import org.bukkit.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

/**
 * Database helper class for Object Creation, Merging, Renaming and similiar processes.
 *
 * @author Lukas Mansour (ArticDive)
 * @author Suneet Tipirneni (Siris)
 */
public final class TownyDatabaseHelper {
	/**
	 * Creates a new {@link TownyWorld} and adds it to the database.
	 *
	 * @param world the Bukkit world to add.
	 * @return {@link CreateResult}, enum containing the result of the world creation.
	 */
	@Nonnull
	public static CreateResult newWorld(World world) {
		
		// Convert world to towny world.
		TownyWorld townyWorld = TownyAdapter.wrapBukkitWorld(world);

		// Don't create a new world for temporary DungeonsXL instanced worlds.
		if (Bukkit.getServer().getPluginManager().getPlugin("DungeonsXL") != null && world.getName().startsWith("DXL_")) {
			townyWorld.setUsingTowny(false);
			return CreateResult.UNEXPECTED;
		}

		// Save changes.
		TownyUniverse.getInstance().addWorld(townyWorld);
		return (TownyUniverse.getInstance().save(townyWorld)) ? CreateResult.SUCCESSFUL : CreateResult.UNEXPECTED;
	}
	
	/**
	 * Creates a new {@link Nation} and adds it to the database.
	 *
	 * @param identifier {@link UUID} of the new nation.
	 * @param name {@link String} containing the nation name.
	 * @param capital {@link Town} the capital of the nation.   
	 * @return {@link CreateResult}, enum containing the result of the nation creation.
	 */
	@Nonnull
	public static CreateResult newNation(UUID identifier, String name, @Nullable Town capital) {
		return null;
	}
	
	/**
	 * Creates a new {@link Town} and adds it to the database.
	 *
	 * @param identifier {@link UUID} of the new town.
	 * @param name {@link String} containing the town name.
	 * @param mayor {@link Resident} the mayor of the town.   
	 * @return {@link CreateResult}, enum containing the result of the town creation.
	 */
	@Nonnull
	public static CreateResult newTown(UUID identifier, String name, @Nullable Resident mayor) {
		return null;
	}
	
	/**
	 * Creates a new {@link Resident} and adds it to the database.
	 * Please note this is also used for NPCs.
	 *
	 * @param identifier - {@link UUID} of the Resident (Player's identifier or NPC's identifier)
	 * @param name - {@link String} containing the resident's name.
	 * @return {@link CreateResult}, enum containing the result of the resident creation.
	 */
	@Nonnull
	public static CreateResult newResident(UUID identifier, String name) {
		
		// Create Resident
		Resident resident = new Resident(identifier);
		resident.setName(name);
		
		// Add to towny Universe.
		TownyUniverse.getInstance().addResident(resident);
		
		// Save changes.
		TownyUniverse.getInstance().save(resident);
		
		return CreateResult.SUCCESSFUL;
	}
	
	/**
	 * Creates a new {@link TownBlock} and adds it to the database.
	 *
	 * @param identifier - {@link UUID} of the TownBlock
	 * @param x {@link Integer} of the townblock's x-coordinate 
	 * @param y {@link Integer} of the townblock's y-coordinate 
	 * @param world {@link TownyWorld} in which the townblock is located.   
	 * @return {@link CreateResult}, enum containing the result of the townblock creation.
	 */
	@Nonnull
	public static CreateResult newTownBlock(UUID identifier, int x, int y, TownyWorld world) {
		return null;
	}
}