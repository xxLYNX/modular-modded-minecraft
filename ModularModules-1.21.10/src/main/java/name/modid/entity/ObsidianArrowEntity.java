package name.modid.entity;

import name.modid.ModularArchery;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.EffectParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ObsidianArrowEntity extends PersistentProjectileEntity {
    private int duration = 200;

    // Registry Constructor
    public ObsidianArrowEntity(EntityType<? extends ObsidianArrowEntity> type, World world) {
        super(type, world);
    }

    // Fired by LivingEntity Constructor
    public ObsidianArrowEntity(World world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(ModularArchery.OBSIDIAN_ARROW_ENTITY, owner, world, stack, shotFrom);
    }

    // Spawned in World Constructor (dispensers etc.)
    public ObsidianArrowEntity(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(EntityType.SPECTRAL_ARROW, x, y, z, world, stack, shotFrom);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getEntityWorld().isClient() && !this.isInGround()) {
            this.getEntityWorld().addParticleClient(EffectParticleEffect.of(ParticleTypes.EFFECT, -1, 1.0F), this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
        StatusEffectInstance statusEffectInstance = new StatusEffectInstance(StatusEffects.GLOWING, this.duration, 0);
        target.addStatusEffect(statusEffectInstance, this.getEffectCause());
    }

    @Override
    protected void readCustomData(ReadView view) {
        super.readCustomData(view);
        this.duration = view.getInt("Duration", 200);
    }

    @Override
    protected void writeCustomData(WriteView view) {
        super.writeCustomData(view);
        view.putInt("Duration", this.duration);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(Items.SPECTRAL_ARROW);
    }
}
