package com.io.project;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.io.furiousveggies.model.Game;

import static org.junit.jupiter.api.Assertions.*;

import com.io.furiousveggies.model.Block;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class TestBlock {
	final float screenWidth = 1024;
	final float scale = Game.width / screenWidth;
	final float bodyHalfSize = 1;
	float center, size, bottom;
	Body body;
	Block block;
	
	@BeforeAll
	void prepareBody() {
		Vector2 gravity = new Vector2(0, -10);
		World world = new World(gravity, false);
		
		//body
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(0, 10);
		
		body = world.createBody(bodyDef);
		
		
		PolygonShape box = new PolygonShape();
		box.setAsBox(bodyHalfSize, bodyHalfSize);
		
		Fixture fixture = body.createFixture(box, 1.0f);
		fixture.setRestitution(0);
		fixture.setFriction(1);
		
		box.dispose();
		
		//ground
		bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(0, -0.5f);
		
		Body groundbody = world.createBody(bodyDef);
		
		box = new PolygonShape();
		box.setAsBox(1000, 0.5f);
		
		fixture = groundbody.createFixture(box, 0.0f);
		fixture.setRestitution(0);
		fixture.setFriction(1);
		
		box.dispose();
		
		block = new Block(body, bodyHalfSize * 2, (float) (1024/20.0));
		
		for(int i = 0; i < 60 * 100; i++) {
			world.step(1.0f/60.0f, 8, 6);
			block.act(1.0f/60.0f);
		}
		
		center = scale * block.getY(Align.center);
		size = scale * block.getHeight();
		bottom = scale * block.getY();
	}
	
	@Test
	void testCenterAndBottomInRightProximity() {
		assertTrue((center - size / 2.0) - bottom < 0.01);
	}
	
	@Test
	void testDidItFall() {
		assertTrue(bottom < 0.02);
	}
	
	@Test
	void testDoTheBottomVerticesHaveTheSameY() {
		assertEquals(block.getY(Align.bottomLeft), block.getY(Align.bottomRight));
	}
	
	@Test
	void testDoesItCalculateVerticesAccurately() {
		assertEquals(bottom, scale * block.getY(Align.bottomLeft));
		assertEquals(bottom, scale * block.getY(Align.bottomRight));
	}
}
