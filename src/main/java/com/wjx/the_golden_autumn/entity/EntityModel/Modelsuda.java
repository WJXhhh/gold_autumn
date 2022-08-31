package com.wjx.the_golden_autumn.entity.EntityModel;// Made with Blockbench 4.3.1
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class Modelsuda extends ModelBase {
	private final ModelRenderer head;
	private final ModelRenderer hair;
	private final ModelRenderer h8;
	private final ModelRenderer h7;
	private final ModelRenderer h6;
	private final ModelRenderer h5;
	private final ModelRenderer h4;
	private final ModelRenderer h3;
	private final ModelRenderer h2;
	private final ModelRenderer h1;
	private final ModelRenderer headdressLeftTop;
	private final ModelRenderer headdressLeftMiddle;
	private final ModelRenderer headdressRightTop;
	private final ModelRenderer headdressRightMiddle;
	private final ModelRenderer headdressLeftBottom;
	private final ModelRenderer headdressRightBottom;
	private final ModelRenderer headdressMiddle;
	private final ModelRenderer armRight;
	private final ModelRenderer armLeft;
	private final ModelRenderer body;
	private final ModelRenderer legLeft;
	private final ModelRenderer legRight;

	public Modelsuda() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 6.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, false));

		hair = new ModelRenderer(this);
		hair.setRotationPoint(0.0F, 0.0F, 4.0F);
		head.addChild(hair);
		setRotationAngle(hair, 0.0873F, 0.0F, 0.0F);
		

		h8 = new ModelRenderer(this);
		h8.setRotationPoint(0.0F, 0.0F, 0.0F);
		hair.addChild(h8);
		h8.cubeList.add(new ModelBox(h8, 4, 47, -4.0F, 0.0F, 0.0F, 1, 12, 0, 0.0F, false));

		h7 = new ModelRenderer(this);
		h7.setRotationPoint(0.0F, 0.0F, 0.0F);
		hair.addChild(h7);
		setRotationAngle(h7, 0.0087F, 0.0F, 0.0F);
		h7.cubeList.add(new ModelBox(h7, 2, 47, -3.0F, 0.0F, 0.0F, 1, 12, 0, 0.0F, false));

		h6 = new ModelRenderer(this);
		h6.setRotationPoint(0.0F, 0.0F, 0.0F);
		hair.addChild(h6);
		setRotationAngle(h6, 0.0175F, 0.0F, 0.0F);
		h6.cubeList.add(new ModelBox(h6, 0, 47, -2.0F, 0.0F, 0.0F, 1, 12, 0, 0.0F, false));

		h5 = new ModelRenderer(this);
		h5.setRotationPoint(0.0F, 0.0F, 0.0F);
		hair.addChild(h5);
		setRotationAngle(h5, 0.0524F, 0.0F, 0.0F);
		h5.cubeList.add(new ModelBox(h5, 40, 45, -1.0F, 0.0F, 0.0F, 1, 12, 0, 0.0F, false));

		h4 = new ModelRenderer(this);
		h4.setRotationPoint(0.0F, 0.0F, 0.0F);
		hair.addChild(h4);
		setRotationAngle(h4, 0.0262F, 0.0F, 0.0F);
		h4.cubeList.add(new ModelBox(h4, 38, 45, 0.0F, 0.0F, 0.0F, 1, 12, 0, 0.0F, false));

		h3 = new ModelRenderer(this);
		h3.setRotationPoint(0.0F, 0.0F, 0.0F);
		hair.addChild(h3);
		setRotationAngle(h3, 0.0349F, 0.0F, 0.0F);
		h3.cubeList.add(new ModelBox(h3, 36, 45, 1.0F, 0.0F, 0.0F, 1, 12, 0, 0.0F, false));

		h2 = new ModelRenderer(this);
		h2.setRotationPoint(0.0F, 0.0F, 0.0F);
		hair.addChild(h2);
		setRotationAngle(h2, 0.0175F, 0.0F, 0.0F);
		h2.cubeList.add(new ModelBox(h2, 34, 45, 2.0F, 0.0F, 0.0F, 1, 12, 0, 0.0F, false));

		h1 = new ModelRenderer(this);
		h1.setRotationPoint(0.0F, 0.0F, 0.0F);
		hair.addChild(h1);
		h1.cubeList.add(new ModelBox(h1, 32, 45, 3.0F, 0.0F, 0.0F, 1, 12, 0, 0.0F, false));

		headdressLeftTop = new ModelRenderer(this);
		headdressLeftTop.setRotationPoint(-1.0F, -6.5F, 4.5F);
		head.addChild(headdressLeftTop);
		setRotationAngle(headdressLeftTop, 0.0F, 0.1745F, 0.4363F);
		headdressLeftTop.cubeList.add(new ModelBox(headdressLeftTop, 44, 13, -5.0F, -2.5F, -2.0F, 5, 2, 1, 0.0F, false));

		headdressLeftMiddle = new ModelRenderer(this);
		headdressLeftMiddle.setRotationPoint(-1.0F, -6.5F, 4.5F);
		head.addChild(headdressLeftMiddle);
		setRotationAngle(headdressLeftMiddle, 0.0F, 0.0873F, 0.2618F);
		headdressLeftMiddle.cubeList.add(new ModelBox(headdressLeftMiddle, 44, 10, -5.0F, -2.5F, -2.0F, 5, 2, 1, 0.0F, false));

		headdressRightTop = new ModelRenderer(this);
		headdressRightTop.setRotationPoint(1.0F, -6.5F, 4.5F);
		head.addChild(headdressRightTop);
		setRotationAngle(headdressRightTop, 0.0F, -0.1745F, -0.4363F);
		headdressRightTop.cubeList.add(new ModelBox(headdressRightTop, 43, 44, 0.0F, -2.5F, -2.0F, 5, 2, 1, 0.0F, false));

		headdressRightMiddle = new ModelRenderer(this);
		headdressRightMiddle.setRotationPoint(1.0F, -6.5F, 4.5F);
		head.addChild(headdressRightMiddle);
		setRotationAngle(headdressRightMiddle, 0.0F, -0.0873F, -0.2618F);
		headdressRightMiddle.cubeList.add(new ModelBox(headdressRightMiddle, 41, 33, 0.0F, -2.5F, -2.0F, 5, 2, 1, 0.0F, false));

		headdressLeftBottom = new ModelRenderer(this);
		headdressLeftBottom.setRotationPoint(0.0F, -6.0F, 4.5F);
		head.addChild(headdressLeftBottom);
		setRotationAngle(headdressLeftBottom, 0.0F, -0.0873F, 0.0873F);
		headdressLeftBottom.cubeList.add(new ModelBox(headdressLeftBottom, 40, 23, -6.0F, -3.0F, -2.0F, 5, 2, 1, 0.0F, false));

		headdressRightBottom = new ModelRenderer(this);
		headdressRightBottom.setRotationPoint(0.0F, -6.0F, 4.5F);
		head.addChild(headdressRightBottom);
		setRotationAngle(headdressRightBottom, 0.0F, 0.0873F, -0.0873F);
		headdressRightBottom.cubeList.add(new ModelBox(headdressRightBottom, 40, 20, 1.0F, -3.0F, -2.0F, 5, 2, 1, 0.0F, false));

		headdressMiddle = new ModelRenderer(this);
		headdressMiddle.setRotationPoint(0.0F, -7.0F, 4.5F);
		head.addChild(headdressMiddle);
		headdressMiddle.cubeList.add(new ModelBox(headdressMiddle, 22, 16, -1.0F, -2.5F, -2.5F, 2, 3, 1, 0.0F, false));
		headdressMiddle.cubeList.add(new ModelBox(headdressMiddle, 0, 16, -1.0F, -3.0F, -1.5F, 2, 4, 1, 0.0F, false));
		headdressMiddle.cubeList.add(new ModelBox(headdressMiddle, 0, 0, -1.0F, -3.5F, -1.0F, 2, 4, 1, 0.0F, false));

		armRight = new ModelRenderer(this);
		armRight.setRotationPoint(-3.0F, 6.5F, 0.0F);
		setRotationAngle(armRight, 0.0F, 0.0F, 0.4363F);
		armRight.cubeList.add(new ModelBox(armRight, 41, 0, -1.5F, 0.5F, -1.0F, 2, 8, 2, 0.0F, false));
		armRight.cubeList.add(new ModelBox(armRight, 0, 37, -2.0F, 0.0F, -1.5F, 3, 7, 3, 0.0F, false));

		armLeft = new ModelRenderer(this);
		armLeft.setRotationPoint(3.0F, 6.5F, 0.0F);
		setRotationAngle(armLeft, 0.0F, 0.0F, -0.4363F);
		armLeft.cubeList.add(new ModelBox(armLeft, 24, 41, -0.5F, 0.5F, -1.0F, 2, 8, 2, 0.0F, false));
		armLeft.cubeList.add(new ModelBox(armLeft, 12, 41, -1.0F, 0.0F, -1.5F, 3, 7, 3, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 13.5F, 0.0F);
		body.cubeList.add(new ModelBox(body, 22, 22, -3.0F, -7.499F, -3.0F, 6, 5, 6, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 26, -2.5F, -7.499F, -2.5F, 5, 6, 5, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 16, -4.0F, -1.5F, -3.0F, 8, 4, 6, 0.0F, false));

		legLeft = new ModelRenderer(this);
		legLeft.setRotationPoint(2.0F, 15.0F, 0.0F);
		legLeft.cubeList.add(new ModelBox(legLeft, 32, 33, -1.25F, 0.0F, -1.5F, 3, 9, 3, 0.0F, false));
		legLeft.cubeList.add(new ModelBox(legLeft, 16, 33, -1.75F, -1.0F, -2.0F, 4, 4, 4, 0.0F, false));

		legRight = new ModelRenderer(this);
		legRight.setRotationPoint(-2.0F, 15.0F, 0.0F);
		legRight.cubeList.add(new ModelBox(legRight, 32, 8, -1.75F, 0.0F, -1.5F, 3, 9, 3, 0.0F, false));
		legRight.cubeList.add(new ModelBox(legRight, 24, 0, -2.25F, -1.0F, -2.0F, 4, 4, 4, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		head.render(f5);
		armRight.render(f5);
		armLeft.render(f5);
		body.render(f5);
		legLeft.render(f5);
		legRight.render(f5);
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