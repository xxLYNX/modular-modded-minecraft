package name.modid.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ObsidianArrowItem extends ArrowItem {
    public ObsidianArrowItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter, @Nullable ItemStack shotFrom) {
        return new SpectralArrowEntity(world, shooter, stack.copyWithCount(1), shotFrom);
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        SpectralArrowEntity spectralArrowEntity = new SpectralArrowEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack.copyWithCount(1), (ItemStack)null);
        spectralArrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
        return spectralArrowEntity;
    }

}
