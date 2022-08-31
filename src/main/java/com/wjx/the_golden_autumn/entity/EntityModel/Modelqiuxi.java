package com.wjx.the_golden_autumn.entity.EntityModel;
// Made with Blockbench 4.3.1
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class Modelqiuxi extends ModelBase {
	private final ModelRenderer zhuti;
	private final ModelRenderer legRight;
	private final ModelRenderer legLeft;
	private final ModelRenderer body;
	private final ModelRenderer armLeft;
	private final ModelRenderer armRight;
	private final ModelRenderer head;
	private final ModelRenderer hair;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;

	public Modelqiuxi() {
		textureWidth = 64;
		textureHeight = 64;

		zhuti = new ModelRenderer(this);
		zhuti.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		legRight = new ModelRenderer(this);
		legRight.setRotationPoint(-2.0F, -9.0F, 0.0F);
		zhuti.addChild(legRight);
		legRight.cubeList.add(new ModelBox(legRight, 0, 29, -1.5F, 0.0F, -1.5F, 3, 9, 3, 0.0F, false));

		legLeft = new ModelRenderer(this);
		legLeft.setRotationPoint(2.0F, -9.0F, 0.0F);
		zhuti.addChild(legLeft);
		legLeft.cubeList.add(new ModelBox(legLeft, 12, 29, -1.5F, 0.0F, -1.5F, 3, 9, 3, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -10.5F, 0.0F);
		zhuti.addChild(body);
		body.cubeList.add(new ModelBox(body, 26, 10, -3.0F, -7.5F, -3.0F, 6, 4, 6, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 25, 25, -3.5F, -3.5F, -3.5F, 7, 4, 7, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 16, -4.0F, 0.5F, -4.0F, 8, 5, 8, 0.0F, false));

		armLeft = new ModelRenderer(this);
		armLeft.setRotationPoint(3.0F, -17.5F, 0.0F);
		zhuti.addChild(armLeft);
		setRotationAngle(armLeft, 0.0F, 0.0F, -0.4363F);
		armLeft.cubeList.add(new ModelBox(armLeft, 32, 0, 0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F, false));

		armRight = new ModelRenderer(this);
		armRight.setRotationPoint(-3.0F, -17.5F, 0.0F);
		zhuti.addChild(armRight);
		setRotationAngle(armRight, 0.0F, 0.0F, 0.4363F);
		armRight.cubeList.add(new ModelBox(armRight, 24, 36, -2.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -18.0F, 0.0F);
		zhuti.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, false));

		hair = new ModelRenderer(this);
		hair.setRotationPoint(0.0F, 0.0F, 4.0F);
		head.addChild(hair);
		setRotationAngle(hair, 0.1745F, 0.0F, 0.0F);
		hair.cubeList.add(new ModelBox(hair, 16, 41, 3.0F, 0.0F, -1.0F, 1, 12, 1, 0.0F, false));
		hair.cubeList.add(new ModelBox(hair, 12, 41, -1.0F, 0.0F, -1.0F, 1, 12, 1, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 0.0F, 0.0F);
		hair.addChild(bone);
		setRotationAngle(bone, 0.0873F, 0.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 8, 41, 2.0F, 0.0F, -1.0F, 1, 12, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 4, 41, 0.0F, 0.0F, -1.0F, 1, 12, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 41, -2.0F, 0.0F, -1.0F, 1, 12, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 40, 40, -4.0F, 0.0F, -1.0F, 1, 12, 1, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
		hair.addChild(bone2);
		setRotationAngle(bone2, 0.1745F, 0.0F, 0.0F);
		bone2.cubeList.add(new ModelBox(bone2, 36, 36, 1.0F, 0.0F, -1.0F, 1, 12, 1, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 32, 36, -3.0F, 0.0F, -1.0F, 1, 12, 1, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(-3.75F, 2.5F, -3.25F);
		head.addChild(bone3);
		setRotationAngle(bone3, -0.0873F, 0.0F, 0.0F);
		bone3.cubeList.add(new ModelBox(bone3, 4, 0, -0.25F, -2.4251F, -0.965F, 1, 7, 1, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(3.75F, 2.5F, -3.25F);
		head.addChild(bone4);
		setRotationAngle(bone4, -0.0873F, 0.0F, 0.0F);
		bone4.cubeList.add(new ModelBox(bone4, 0, 0, -0.75F, -2.4251F, -0.965F, 1, 7, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		zhuti.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
		head.rotateAngleY = netHeadYaw / 57.295776F;
		head.rotateAngleX = headPitch / 57.295776F;
		legRight.rotateAngleX = MathHelper.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		armLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;
		legLeft.rotateAngleX = MathHelper.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		armRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * limbSwingAmount;
	}

}