package name.modid;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;
import name.modid.item.ObsidianArrowItem;

public class ModularArchery {
    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings){
        // Create the item key.
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(ModularModules.MOD_ID, name));

        // Create the item instance.
        Item item = itemFactory.apply(settings.registryKey(itemKey));

        // Register the item.
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }
//ArrowItem is used instead of Item so it is treated as ammunition via vanilla methods
    public static final Item OBSIDIAN_ARROW =
            register("obsidian_arrow", name.modid.item.ObsidianArrowItem::new, new Item.Settings());

    public static void initialize() {
        // Get the event for modifying entries in the ingredients group.
        // And register an event handler that adds our item to the ingredients group.
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                .register((itemGroup) -> itemGroup.add(ModularArchery.OBSIDIAN_ARROW));
    }
}
