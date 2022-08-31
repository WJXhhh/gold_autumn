package com.wjx.the_golden_autumn.entity.EntityModel;// Made with Blockbench 4.3.1
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class Modeljaoboss extends ModelBase {
	private final ModelRenderer Head;
	private final ModelRenderer Body;
	private final ModelRenderer RightArm;
	private final ModelRenderer LeftArm;
	private final ModelRenderer RightLeg;
	private final ModelRenderer LeftLeg;

	public Modeljaoboss() {
		textureWidth = 256;
		textureHeight = 256;

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.cubeList.add(new ModelBox(Head, 0, 0, -16.7F, -104.0F, -16.0F, 32, 32, 32, 0.0F, false));

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.cubeList.add(new ModelBox(Body, 64, 64, -16.7F, -72.0F, -8.0F, 32, 48, 16, 0.0F, false));

		RightArm = new ModelRenderer(this);
		RightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		RightArm.cubeList.add(new ModelBox(RightArm, 160, 64, -27.7F, -74.0F, -8.0F, 16, 48, 16, 0.0F, false));

		LeftArm = new ModelRenderer(this);
		LeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
		LeftArm.cubeList.add(new ModelBox(LeftArm, 160, 64, 10.3F, -74.0F, -8.0F, 16, 48, 16, 0.0F, false));

		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
		RightLeg.cubeList.add(new ModelBox(RightLeg, 0, 64, -14.4F, -36.0F, -8.0F, 16, 48, 16, 0.0F, false));

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
		LeftLeg.cubeList.add(new ModelBox(LeftLeg, 64, 192, -3.0F, -36.0F, -8.0F, 16, 48, 16, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Head.render(f5);
		Body.render(f5);
		RightArm.render(f5);
		LeftArm.render(f5);
		RightLeg.render(f5);
		LeftLeg.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
		Head.rotateAngleY = netHeadYaw / 57.295776F;
		Head.rotateAngleX = headPitch / 57.295776F;
		RightLeg.rotateAngleX = MathHelper.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		LeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;
		LeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		RightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * limbSwingAmount;
	}
}