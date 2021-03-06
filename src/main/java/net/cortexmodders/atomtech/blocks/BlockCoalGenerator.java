package net.cortexmodders.atomtech.blocks;

import net.cortexmodders.atomtech.AtomTech;
import net.cortexmodders.atomtech.lib.BlockHelper;
import net.cortexmodders.atomtech.tileentity.TileEntityCoalGenerator;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockCoalGenerator extends BlockContainer
{
    
    private static Icon back;
    private static Icon bottom;
    private static Icon left;
    private static Icon right;
    private static Icon top_on;
    private static Icon top_off;
    private static Icon front_on;
    private static Icon front_off;
    
    protected BlockCoalGenerator(int par1)
    {
        super(par1, Material.iron);
        this.setCreativeTab(AtomTech.atomTab);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileEntityCoalGenerator();
    }
    
    @Override
    public Icon getIcon(int side, int meta)
    {
        if (meta == 0 || meta == 4)
            switch (side)
            {
                case 0:
                    return bottom;
                case 1:
                    return meta == 4 ? top_on : top_off;
                case 2:
                    return meta == 4 ? front_on : front_off;
                case 3:
                    return back;
                case 4:
                    return right;
                case 5:
                    return left;
            }
        else if (meta == 1 || meta == 5)
            switch (side)
            {
                case 0:
                    return bottom;
                case 1:
                    return meta == 5 ? top_on : top_off;
                case 2:
                    return right;
                case 3:
                    return left;
                case 4:
                    return back;
                case 5:
                    return meta == 5 ? front_on : front_off;
            }
        else if (meta == 2 || meta == 6)
            switch (side)
            {
                case 0:
                    return bottom;
                case 1:
                    return meta == 6 ? top_on : top_off;
                case 2:
                    return back;
                case 3:
                    return meta == 6 ? front_on : front_off;
                case 4:
                    return left;
                case 5:
                    return right;
            }
        else if (meta == 3 || meta == 7)
            switch (side)
            {
                case 0:
                    return bottom;
                case 1:
                    return meta == 7 ? top_on : top_off;
                case 2:
                    return left;
                case 3:
                    return right;
                case 4:
                    return meta == 7 ? front_on : front_off;
                case 5:
                    return back;
            }
        return back;
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote && world.getBlockTileEntity(x, y, z) instanceof TileEntityCoalGenerator && player != null)
        {
            return ((TileEntityCoalGenerator)world.getBlockTileEntity(x, y, z)).onBlockActivated(player, side, hitX, hitY, hitZ);
        }
        return false;
    }
    
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
    {
        super.onBlockPlacedBy(world, x, y, z, entity, stack);
        int meta = BlockHelper.getBlockOrientation(entity.rotationYaw) & 3;
        
        world.setBlockMetadataWithNotify(x, y, z, meta, 2);
    }
    
    @Override
    public void registerIcons(IconRegister register)
    {
        back = register.registerIcon("atomtech:generator_back");
        bottom = register.registerIcon("atomtech:generator_bottom");
        left = register.registerIcon("atomtech:generator_left");
        right = register.registerIcon("atomtech:generator_right");
        top_on = register.registerIcon("atomtech:generator_top_on");
        top_off = register.registerIcon("atomtech:generator_top_off");
        front_on = register.registerIcon("atomtech:generator_front_on");
        front_off = register.registerIcon("atomtech:generator_front_off");
    }
}
