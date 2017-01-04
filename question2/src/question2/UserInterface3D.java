package question2;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.text.NumberFormat;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Material;
import javax.media.j3d.Screen3D;
import javax.media.j3d.Switch;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class UserInterface3D extends Applet implements FloatListener{

	SimpleUniverse u;
	boolean isApplication;
	Canvas3D canvas;
	OffScreenCanvas3D offScreenCanvas;
	View view;

  // These names correspond to the H-Anim names
	TransformGroup Human_body;
	TransformGroup Human_r_shoulder;
	TransformGroup Human_r_elbow;
	TransformGroup Human_l_shoulder;
	TransformGroup Human_l_elbow;
	TransformGroup Human_skullbase;
	TransformGroup Human_r_hip;
	TransformGroup Human_r_knee;
	TransformGroup Human_l_hip;
	TransformGroup Human_l_knee;
  
	//rotational axis stuff
	Vector3f rShoulderRotateAxis =  new Vector3f(1.0f, 0.0f, 0.0f);
	Vector3f rShoulderRotateNAxis = new Vector3f(1.0f, 0.0f, 0.0f);
	float rShoulderRotateAngle = 0.0f;
	AxisAngle4f rShoulderRotateAxisAngle = new AxisAngle4f(rShoulderRotateAxis, rShoulderRotateAngle);
  
	RotAxis rShoulderRotAxis;
	float rShoulderRotAxisLength = 1.0f;
    
	FloatLabelJSlider rShoulderAxisXSlider;
	FloatLabelJSlider rShoulderAxisYSlider;
	FloatLabelJSlider rShoulderAxisZSlider;
	FloatLabelJSlider rShoulderRotateAxisAngleSlider;
  
	Vector3f rElbowRotateAxis =  new Vector3f(1.0f, 0.0f, 0.0f);
	Vector3f rElbowRotateNAxis = new Vector3f(1.0f, 0.0f, 0.0f);
	float rElbowRotateAngle = 0.0f;
	AxisAngle4f rElbowRotateAxisAngle = new AxisAngle4f(rElbowRotateAxis, rElbowRotateAngle);
  
	RotAxis rElbowRotAxis;
	float rElbowRotAxisLength = 1.0f;
    
	FloatLabelJSlider rElbowAxisXSlider;
	FloatLabelJSlider rElbowAxisYSlider;
	FloatLabelJSlider rElbowAxisZSlider;
	FloatLabelJSlider rElbowRotateAxisAngleSlider;
	
	Vector3f lShoulderRotateAxis =  new Vector3f(1.0f, 0.0f, 0.0f);
	Vector3f lShoulderRotateNAxis = new Vector3f(1.0f, 0.0f, 0.0f);
	float lShoulderRotateAngle = 0.0f;
	AxisAngle4f lShoulderRotateAxisAngle = new AxisAngle4f(lShoulderRotateAxis, lShoulderRotateAngle);
  
	RotAxis lShoulderRotAxis;
	float lShoulderRotAxisLength = 1.0f;
    
	FloatLabelJSlider lShoulderAxisXSlider;
	FloatLabelJSlider lShoulderAxisYSlider;
	FloatLabelJSlider lShoulderAxisZSlider;
	FloatLabelJSlider lShoulderRotateAxisAngleSlider;
  
	Vector3f lElbowRotateAxis =  new Vector3f(1.0f, 0.0f, 0.0f);
	Vector3f lElbowRotateNAxis = new Vector3f(1.0f, 0.0f, 0.0f);
	float lElbowRotateAngle = 0.0f;
	AxisAngle4f lElbowRotateAxisAngle = new AxisAngle4f(lElbowRotateAxis, lElbowRotateAngle);
  
	RotAxis lElbowRotAxis;
	float lElbowRotAxisLength = 1.0f;
    
	FloatLabelJSlider lElbowAxisXSlider;
	FloatLabelJSlider lElbowAxisYSlider;
	FloatLabelJSlider lElbowAxisZSlider;
	FloatLabelJSlider lElbowRotateAxisAngleSlider;
	
	Vector3f rHipRotateAxis =  new Vector3f(1.0f, 0.0f, 0.0f);
	Vector3f rHipRotateNAxis = new Vector3f(1.0f, 0.0f, 0.0f);
	float rHipRotateAngle = 0.0f;
	AxisAngle4f rHipRotateAxisAngle = new AxisAngle4f(rHipRotateAxis, rHipRotateAngle);
  
	RotAxis rHipRotAxis;
	float rHipRotAxisLength = 1.0f;
    
	FloatLabelJSlider rHipAxisXSlider;
	FloatLabelJSlider rHipAxisYSlider;
	FloatLabelJSlider rHipAxisZSlider;
	FloatLabelJSlider rHipRotateAxisAngleSlider;
  
	Vector3f rKneeRotateAxis =  new Vector3f(1.0f, 0.0f, 0.0f);
	Vector3f rKneeRotateNAxis = new Vector3f(1.0f, 0.0f, 0.0f);
	float rKneeRotateAngle = 0.0f;
	AxisAngle4f rKneeRotateAxisAngle = new AxisAngle4f(rKneeRotateAxis, rKneeRotateAngle);
  
	RotAxis rKneeRotAxis;
	float rKneeRotAxisLength = 1.0f;
    
	FloatLabelJSlider rKneeAxisXSlider;
	FloatLabelJSlider rKneeAxisYSlider;
	FloatLabelJSlider rKneeAxisZSlider;
	FloatLabelJSlider rKneeRotateAxisAngleSlider;
	
	Vector3f lHipRotateAxis =  new Vector3f(1.0f, 0.0f, 0.0f);
	Vector3f lHipRotateNAxis = new Vector3f(1.0f, 0.0f, 0.0f);
	float lHipRotateAngle = 0.0f;
	AxisAngle4f lHipRotateAxisAngle = new AxisAngle4f(lHipRotateAxis, lHipRotateAngle);
  
	RotAxis lHipRotAxis;
	float lHipRotAxisLength = 1.0f;
    
	FloatLabelJSlider lHipAxisXSlider;
	FloatLabelJSlider lHipAxisYSlider;
	FloatLabelJSlider lHipAxisZSlider;
	FloatLabelJSlider lHipRotateAxisAngleSlider;
  
	Vector3f lKneeRotateAxis =  new Vector3f(1.0f, 0.0f, 0.0f);
	Vector3f lKneeRotateNAxis = new Vector3f(1.0f, 0.0f, 0.0f);
	float lKneeRotateAngle = 0.0f;
	AxisAngle4f lKneeRotateAxisAngle = new AxisAngle4f(lKneeRotateAxis, lKneeRotateAngle);
  
	RotAxis lKneeRotAxis;
	float lKneeRotAxisLength = 1.0f;
    
	FloatLabelJSlider lKneeAxisXSlider;
	FloatLabelJSlider lKneeAxisYSlider;
	FloatLabelJSlider lKneeAxisZSlider;
	FloatLabelJSlider lKneeRotateAxisAngleSlider;
	
	//offscreen stuff
	float offScreenScale = 1.5f;

  // Temporaries that are reused
	Transform3D tmpTrans = new Transform3D();
	Vector3f tmpVector = new Vector3f();
	AxisAngle4f tmpAxisAngle = new AxisAngle4f();

  // These will be created, attached the scene graph and then the variable
  // will be reused to initialize other sections of the scene graph.
	Cylinder tmpCyl;
	Sphere tmpSphere;
	TransformGroup tmpTG;

  // colors for use in the bodyparts
	Color3f red = new Color3f(1.0f, 0.0f, 0.0f);
	Color3f blue = new Color3f(0.0f, 0.0f, 1.0f);
	Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
	Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
	Color3f skyBlue = new Color3f(0.6f, 0.7f, 0.9f);

  // NumberFormat to print out floats with only two digits
	NumberFormat nf;

	//create the whole body and adds each body part separately
	public void createHuman() {
		
	    // Set up an appearance to make the body with red ambient,
	    // black emmissive, red diffuse and white specular coloring
	    Material material = new Material(blue, black, blue, white, 64);
	    Appearance appearance = new Appearance();
	    appearance.setMaterial(material);
	    
	    Human_body = new TransformGroup();

    // center the body
		tmpVector.set(0.0f, -1.5f, 0.0f);
		tmpTrans.set(tmpVector);
		Human_body.setTransform(tmpTrans);
	
	    // offset and place the cylinder for the body
	    tmpTG = new TransformGroup();
	    // offset the shape
	    tmpVector.set(0.0f, 1.5f, 0.0f);
	    tmpTrans.set(tmpVector);
	    tmpTG.setTransform(tmpTrans);
	    tmpCyl = new Cylinder(0.30f, 3.0f, appearance);
	    tmpTG.addChild(tmpCyl);
	
	    // add the shape to the body
	    Human_body.addChild(tmpTG);
	
	    // create the r_shoulder TransformGroup
	    Human_r_shoulder = new TransformGroup();
	    Human_r_shoulder.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	    Human_r_shoulder.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    // translate from the waist
	    tmpVector.set(-0.95f, 2.9f, -0.2f);
	    tmpTrans.set(tmpVector);
	    Human_r_shoulder.setTransform(tmpTrans);
	
	    // place the sphere for the r_shoulder
	    tmpSphere = new Sphere(0.22f, appearance);
	    Human_r_shoulder.addChild(tmpSphere);

	    // offset and place the cylinder for the r_shoulder
	    tmpTG = new TransformGroup();
	    // offset the shape
	    tmpVector.set(0.0f, -0.5f, 0.0f);
	    tmpTrans.set(tmpVector);
	    tmpTG.setTransform(tmpTrans);
	    tmpCyl = new Cylinder(0.2f, 1.0f, appearance);
	    tmpTG.addChild(tmpCyl);
	    
	    // add the shape to the r_shoulder
	    Human_r_shoulder.addChild(tmpTG);
	   
	    //adding the rotational AXIS
	    rShoulderRotAxis = new RotAxis(rShoulderRotAxisLength); // the axis
	    Human_r_shoulder.addChild(rShoulderRotAxis);
	    rShoulderRotAxis.setWhichChild(Switch.CHILD_ALL);
	    
	    // add the shoulder to the body group
	    Human_body.addChild(Human_r_shoulder);
	    
	    // create the r_elbow TransformGroup
	    Human_r_elbow = new TransformGroup();
	    Human_r_elbow.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	    Human_r_elbow.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    tmpVector.set(0.0f, -1.054f, 0.0f);
	    tmpTrans.set(tmpVector);
	    Human_r_elbow.setTransform(tmpTrans);
	
	    // place the sphere for the r_elbow
	    tmpSphere = new Sphere(0.22f, appearance);
	    Human_r_elbow.addChild(tmpSphere);
	
	     //offset and place the cylinder for the r_shoulder
	    tmpTG = new TransformGroup();
	    // offset the shape
	    tmpVector.set(0.0f, -0.5f, 0.0f);
	    tmpTrans.set(tmpVector);
	    tmpTG.setTransform(tmpTrans);
	    tmpCyl = new Cylinder(0.2f, 1.0f, appearance);
	    tmpTG.addChild(tmpCyl);
	    // add the shape to the r_shoulder
	    Human_r_elbow.addChild(tmpTG);
	    
		//adding the rotational AXIS
	    rElbowRotAxis = new RotAxis(rElbowRotAxisLength); // the axis
	    Human_r_elbow.addChild(rElbowRotAxis);
	    rElbowRotAxis.setWhichChild(Switch.CHILD_ALL);
	
	    // add the elbow to the shoulder group
	    Human_r_shoulder.addChild(Human_r_elbow);
	
	    // create the l_shoulder TransformGroup
	    Human_l_shoulder = new TransformGroup();
	    Human_l_shoulder.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	    Human_l_shoulder.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    tmpVector.set(0.95f, 2.9f, -0.2f);
	    tmpTrans.set(tmpVector);
	    Human_l_shoulder.setTransform(tmpTrans);
	
	    // place the sphere for the l_shoulder
	    tmpSphere = new Sphere(0.22f, appearance);
	    Human_l_shoulder.addChild(tmpSphere);
	
	    // offset and place the cylinder for the l_shoulder
	    tmpTG = new TransformGroup();
	    // offset the shape
	    tmpVector.set(0.0f, -0.5f, 0.0f);
	    tmpTrans.set(tmpVector);
	    tmpTG.setTransform(tmpTrans);
	    tmpCyl = new Cylinder(0.2f, 1.0f, appearance);
	    tmpTG.addChild(tmpCyl);
	
	    // add the shape to the l_shoulder
	    Human_l_shoulder.addChild(tmpTG);
	    
	  //adding the rotational AXIS
	    lShoulderRotAxis = new RotAxis(lShoulderRotAxisLength); // the axis
	    Human_l_shoulder.addChild(lShoulderRotAxis);
	    lShoulderRotAxis.setWhichChild(Switch.CHILD_ALL);
	    
	    // add the shoulder to the body group
	    Human_body.addChild(Human_l_shoulder);
	
	    // create the r_elbow TransformGroup
	    Human_l_elbow = new TransformGroup();
	    Human_l_elbow.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	    Human_l_elbow.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    tmpVector.set(0.0f, -1.054f, 0.0f);
	    tmpTrans.set(tmpVector);
	    Human_l_elbow.setTransform(tmpTrans);
	
	    // place the sphere for the l_elbow
	    tmpSphere = new Sphere(0.22f, appearance);
	    Human_l_elbow.addChild(tmpSphere);
	    
	    // offset and place the cylinder for the l_elbow
	    tmpTG = new TransformGroup();
	    // offset the shape
	    tmpVector.set(0.0f, -0.5f, 0.0f);
	    tmpTrans.set(tmpVector);
	    tmpTG.setTransform(tmpTrans);
	    tmpCyl = new Cylinder(0.2f, 1.0f, appearance);
	    tmpTG.addChild(tmpCyl);
	
	    // add the shape to the l_elbow
	    Human_l_elbow.addChild(tmpTG);
	   
		  //adding the rotational AXIS
	    lElbowRotAxis = new RotAxis(lElbowRotAxisLength); // the axis
	    Human_l_elbow.addChild(lElbowRotAxis);
	    lElbowRotAxis.setWhichChild(Switch.CHILD_ALL);
	    
	    // add the shoulder to the body group
	    Human_l_shoulder.addChild(Human_l_elbow);
	
	    // create the skullbase TransformGroup
	    Human_skullbase = new TransformGroup();
	    tmpVector.set(0.0f, 3.632f, 0.0f);
	    tmpTrans.set(tmpVector);
	    Human_skullbase.setTransform(tmpTrans);
	
	    // offset and place the sphere for the skull
	    tmpSphere = new Sphere(0.5f, appearance);
	
	    // add the shape to the l_shoulder
	    Human_skullbase.addChild(tmpSphere);
	
	    // add the shoulder to the body group
	    Human_body.addChild(Human_skullbase);
	    
	    
	    
	  //-------------------legg stuff------------------------
	    
	 // create the r_shoulder TransformGroup
	    Human_r_hip = new TransformGroup();
	    Human_r_hip.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	    Human_r_hip.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    // translate from the waist
	    tmpVector.set(-0.6f, 0.5f, -0.2f);
	    tmpTrans.set(tmpVector);
	    Human_r_hip.setTransform(tmpTrans);
	
	    // place the sphere for the r_shoulder
	    tmpSphere = new Sphere(0.22f, appearance);
	    Human_r_hip.addChild(tmpSphere);

	    // offset and place the cylinder for the r_shoulder
	    tmpTG = new TransformGroup();
	    // offset the shape
	    tmpVector.set(0.0f, -0.5f, 0.0f);
	    tmpTrans.set(tmpVector);
	    tmpTG.setTransform(tmpTrans);
	    tmpCyl = new Cylinder(0.2f, 1.0f, appearance);
	    tmpTG.addChild(tmpCyl);
	    
	    // add the shape to the r_shoulder
	    Human_r_hip.addChild(tmpTG);
	    
	    //adding the rotational AXIS
	    rHipRotAxis = new RotAxis(rHipRotAxisLength); // the axis
	    Human_r_hip.addChild(rHipRotAxis);
	    rHipRotAxis.setWhichChild(Switch.CHILD_ALL);
	    
	    // add the shoulder to the body group
	    Human_body.addChild(Human_r_hip);
	    
	    // create the r_elbow TransformGroup
	    Human_r_knee = new TransformGroup();
	    Human_r_knee.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	    Human_r_knee.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    tmpVector.set(0.0f, -1.054f, 0.0f);
	    tmpTrans.set(tmpVector);
	    Human_r_knee.setTransform(tmpTrans);
	
	    // place the sphere for the r_elbow
	    tmpSphere = new Sphere(0.22f, appearance);
	    Human_r_knee.addChild(tmpSphere);
	
	     //offset and place the cylinder for the r_shoulder
	    tmpTG = new TransformGroup();
	    // offset the shape
	    tmpVector.set(0.0f, -0.5f, 0.0f);
	    tmpTrans.set(tmpVector);
	    tmpTG.setTransform(tmpTrans);
	    tmpCyl = new Cylinder(0.2f, 1.0f, appearance);
	    tmpTG.addChild(tmpCyl);
	    // add the shape to the r_shoulder
	    Human_r_knee.addChild(tmpTG);
	    
		//adding the rotational AXIS
	    rKneeRotAxis = new RotAxis(rKneeRotAxisLength); // the axis
	    Human_r_knee.addChild(rKneeRotAxis);
	    rKneeRotAxis.setWhichChild(Switch.CHILD_ALL);
	
	    // add the elbow to the shoulder group
	    Human_r_hip.addChild(Human_r_knee);
	
	    // create the l_shoulder TransformGroup
	    Human_l_hip = new TransformGroup();
	    Human_l_hip.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	    Human_l_hip.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    tmpVector.set(0.6f, 0.5f, -0.2f);
	    tmpTrans.set(tmpVector);
	    Human_l_hip.setTransform(tmpTrans);
	
	    // place the sphere for the l_shoulder
	    tmpSphere = new Sphere(0.22f, appearance);
	    Human_l_hip.addChild(tmpSphere);
	
	    // offset and place the cylinder for the l_shoulder
	    tmpTG = new TransformGroup();
	    // offset the shape
	    tmpVector.set(0.0f, -0.5f, 0.0f);
	    tmpTrans.set(tmpVector);
	    tmpTG.setTransform(tmpTrans);
	    tmpCyl = new Cylinder(0.2f, 1.0f, appearance);
	    tmpTG.addChild(tmpCyl);
	
	    // add the shape to the l_shoulder
	    Human_l_hip.addChild(tmpTG);
	    
	  //adding the rotational AXIS
	    lHipRotAxis = new RotAxis(lHipRotAxisLength); // the axis
	    Human_l_hip.addChild(lHipRotAxis);
	    lHipRotAxis.setWhichChild(Switch.CHILD_ALL);
	    
	    // add the shoulder to the body group
	    Human_body.addChild(Human_l_hip);
	
	    // create the r_elbow TransformGroup
	    Human_l_knee = new TransformGroup();
	    Human_l_knee.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	    Human_l_knee.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    tmpVector.set(0.0f, -1.054f, 0.0f);
	    tmpTrans.set(tmpVector);
	    Human_l_knee.setTransform(tmpTrans);
	
	    // place the sphere for the l_elbow
	    tmpSphere = new Sphere(0.22f, appearance);
	    Human_l_knee.addChild(tmpSphere);
	    
	    // offset and place the cylinder for the l_elbow
	    tmpTG = new TransformGroup();
	    // offset the shape
	    tmpVector.set(0.0f, -0.5f, 0.0f);
	    tmpTrans.set(tmpVector);
	    tmpTG.setTransform(tmpTrans);
	    tmpCyl = new Cylinder(0.2f, 1.0f, appearance);
	    tmpTG.addChild(tmpCyl);
	
	    // add the shape to the l_elbow
	    Human_l_knee.addChild(tmpTG);
	   
		  //adding the rotational AXIS
	    lKneeRotAxis = new RotAxis(lKneeRotAxisLength); // the axis
	    Human_l_knee.addChild(lKneeRotAxis);
	    lKneeRotAxis.setWhichChild(Switch.CHILD_ALL);
	    
	    // add the shoulder to the body group
	    Human_l_hip.addChild(Human_l_knee);
	}
	
	//functions for changing the rotation put it all in 1 function for easy bookkeeping
  	public void setRotation() {
	    Human_r_shoulder.getTransform(tmpTrans); // get the old transform
	    tmpTrans.setRotation(rShoulderRotateAxisAngle); // set only rotation
	    Human_r_shoulder.setTransform(tmpTrans); // set the new transform
	    
	    Human_r_elbow.getTransform(tmpTrans); // get the old transform
	    tmpTrans.setRotation(rElbowRotateAxisAngle); // set only rotation
	    Human_r_elbow.setTransform(tmpTrans); // set the new transform
	    
	    Human_l_shoulder.getTransform(tmpTrans); // get the old transform
	    tmpTrans.setRotation(lShoulderRotateAxisAngle); // set only rotation
	    Human_l_shoulder.setTransform(tmpTrans); // set the new transform
	    
	    Human_l_elbow.getTransform(tmpTrans); // get the old transform
	    tmpTrans.setRotation(lElbowRotateAxisAngle); // set only rotation
	    Human_l_elbow.setTransform(tmpTrans); // set the new transform
	    
	    Human_r_hip.getTransform(tmpTrans); // get the old transform
	    tmpTrans.setRotation(rHipRotateAxisAngle); // set only rotation
	    Human_r_hip.setTransform(tmpTrans); // set the new transform
	    
	    Human_r_knee.getTransform(tmpTrans); // get the old transform
	    tmpTrans.setRotation(rKneeRotateAxisAngle); // set only rotation
	    Human_r_knee.setTransform(tmpTrans); // set the new transform
	    
	    Human_l_hip.getTransform(tmpTrans); // get the old transform
	    tmpTrans.setRotation(lHipRotateAxisAngle); // set only rotation
	    Human_l_hip.setTransform(tmpTrans); // set the new transform
	    
	    Human_l_knee.getTransform(tmpTrans); // get the old transform
	    tmpTrans.setRotation(lKneeRotateAxisAngle); // set only rotation
	    Human_l_knee.setTransform(tmpTrans); // set the new transform
	}
  	
  	//update the rotate angles for each body part
    public void updateAxisAngle() {
    	rShoulderRotateAxisAngle.set(rShoulderRotateNAxis, (float) Math.toRadians(rShoulderRotateAngle));	
    	rElbowRotateAxisAngle.set(rElbowRotateNAxis, (float) Math.toRadians(rElbowRotateAngle));
    	
    	lShoulderRotateAxisAngle.set(lShoulderRotateNAxis, (float) Math.toRadians(lShoulderRotateAngle));	
    	lElbowRotateAxisAngle.set(lElbowRotateNAxis, (float) Math.toRadians(lElbowRotateAngle));
    	
    	rHipRotateAxisAngle.set(rHipRotateNAxis, (float) Math.toRadians(rHipRotateAngle));	
    	rKneeRotateAxisAngle.set(rKneeRotateNAxis, (float) Math.toRadians(rKneeRotateAngle));
    	
    	lHipRotateAxisAngle.set(lHipRotateNAxis, (float) Math.toRadians(lHipRotateAngle));	
    	lKneeRotateAxisAngle.set(lKneeRotateNAxis, (float) Math.toRadians(lKneeRotateAngle));
    }

    //make sure the ratate axis is a unit vector or it will mess up calculations
    public void normalizeRotateAxis() {
        float lengthSquared = rShoulderRotateAxis.lengthSquared();
        if (lengthSquared > 0.0001) {
        	rShoulderRotateNAxis.scale((float) (1.0 / Math.sqrt(lengthSquared)),rShoulderRotateAxis);
        } else {
        	rShoulderRotateNAxis.set(1.0f, 0.0f, 0.0f);
        }
        lengthSquared = rElbowRotateAxis.lengthSquared();
        if (lengthSquared > 0.0001) {
        	rElbowRotateNAxis.scale((float) (1.0 / Math.sqrt(lengthSquared)),rElbowRotateAxis);
        } else {
        	rElbowRotateNAxis.set(1.0f, 0.0f, 0.0f);
        }
        lengthSquared = lShoulderRotateAxis.lengthSquared();
        if (lengthSquared > 0.0001) {
        	lShoulderRotateNAxis.scale((float) (1.0 / Math.sqrt(lengthSquared)),lShoulderRotateAxis);
        } else {
        	lShoulderRotateNAxis.set(1.0f, 0.0f, 0.0f);
        }
        lengthSquared = lElbowRotateAxis.lengthSquared();
        if (lengthSquared > 0.0001) {
        	lElbowRotateNAxis.scale((float) (1.0 / Math.sqrt(lengthSquared)),lElbowRotateAxis);
        } else {
        	lElbowRotateNAxis.set(1.0f, 0.0f, 0.0f);
        }
        //foot stufff
        lengthSquared = rHipRotateAxis.lengthSquared();
        if (lengthSquared > 0.0001) {
        	rHipRotateNAxis.scale((float) (1.0 / Math.sqrt(lengthSquared)),rHipRotateAxis);
        } else {
        	rHipRotateNAxis.set(1.0f, 0.0f, 0.0f);
        }
        lengthSquared = rKneeRotateAxis.lengthSquared();
        if (lengthSquared > 0.0001) {
        	rKneeRotateNAxis.scale((float) (1.0 / Math.sqrt(lengthSquared)),rKneeRotateAxis);
        } else {
        	rKneeRotateNAxis.set(1.0f, 0.0f, 0.0f);
        }
        lengthSquared = lHipRotateAxis.lengthSquared();
        if (lengthSquared > 0.0001) {
        	lHipRotateNAxis.scale((float) (1.0 / Math.sqrt(lengthSquared)),lHipRotateAxis);
        } else {
        	lHipRotateNAxis.set(1.0f, 0.0f, 0.0f);
        }
        lengthSquared = lKneeRotateAxis.lengthSquared();
        if (lengthSquared > 0.0001) {
        	lKneeRotateNAxis.scale((float) (1.0 / Math.sqrt(lengthSquared)),lKneeRotateAxis);
        } else {
        	lKneeRotateNAxis.set(1.0f, 0.0f, 0.0f);
        }
    }

    //create the jpanel for controlling the upper body
    public JPanel RS(){
    	JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        panel.add(new JLabel("rShoulder Rotation Axis"));
        rShoulderAxisXSlider = new FloatLabelJSlider("X: ",0.01f, -1.0f, 1.0f, (float) rShoulderRotateAxis.x);
        rShoulderAxisXSlider.addFloatListener(this);
        panel.add(rShoulderAxisXSlider);
        
        rShoulderAxisYSlider = new FloatLabelJSlider("Y: ",0.01f, -1.0f, 1.0f, (float) rShoulderRotateAxis.y);
        rShoulderAxisYSlider.addFloatListener(this);
        panel.add(rShoulderAxisYSlider);
            
        rShoulderAxisZSlider = new FloatLabelJSlider("Z: ",0.01f, -1.0f, 1.0f, (float) rShoulderRotateAxis.z);
        rShoulderAxisZSlider.addFloatListener(this);
        panel.add(rShoulderAxisZSlider);
        
        normalizeRotateAxis();
        rShoulderRotateAxisAngleSlider = new FloatLabelJSlider("Angle: ", 1.0f, -180.0f, 180.0f, (float) rShoulderRotateAngle);
        rShoulderRotateAxisAngleSlider.addFloatListener(this);
        panel.add(rShoulderRotateAxisAngleSlider);
        
        return panel;
    }
    public JPanel RE(){
    	JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        
        panel.add(new JLabel("rElbow Rotation Axis"));
        rElbowAxisXSlider = new FloatLabelJSlider("X: ",0.01f, -1.0f, 1.0f, (float) rElbowRotateAxis.x);
        rElbowAxisXSlider.addFloatListener(this);
        panel.add(rElbowAxisXSlider);
        
        rElbowAxisYSlider = new FloatLabelJSlider("Y: ",0.01f, -1.0f, 1.0f, (float) rElbowRotateAxis.y);
        rElbowAxisYSlider.addFloatListener(this);
        panel.add(rElbowAxisYSlider);
            
        rElbowAxisZSlider = new FloatLabelJSlider("Z: ",0.01f, -1.0f, 1.0f, (float) rElbowRotateAxis.z);
        rElbowAxisZSlider.addFloatListener(this);
        panel.add(rElbowAxisZSlider);
        
        normalizeRotateAxis();
        rElbowRotateAxisAngleSlider = new FloatLabelJSlider("Angle: ", 1.0f, -180.0f, 180.0f, (float) rElbowRotateAngle);
        rElbowRotateAxisAngleSlider.addFloatListener(this);
        panel.add(rElbowRotateAxisAngleSlider);
        return panel;
    }
    public JPanel LS(){
    	JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        
        panel.add(new JLabel("lShoulder Rotation Axis"));
        lShoulderAxisXSlider = new FloatLabelJSlider("X: ",0.01f, -1.0f, 1.0f, (float) lShoulderRotateAxis.x);
        lShoulderAxisXSlider.addFloatListener(this);
        panel.add(lShoulderAxisXSlider);
        
        lShoulderAxisYSlider = new FloatLabelJSlider("Y: ",0.01f, -1.0f, 1.0f, (float) lShoulderRotateAxis.y);
        lShoulderAxisYSlider.addFloatListener(this);
        panel.add(lShoulderAxisYSlider);
            
        lShoulderAxisZSlider = new FloatLabelJSlider("Z: ",0.01f, -1.0f, 1.0f, (float) lShoulderRotateAxis.z);
        lShoulderAxisZSlider.addFloatListener(this);
        panel.add(lShoulderAxisZSlider);
        
        normalizeRotateAxis();
        lShoulderRotateAxisAngleSlider = new FloatLabelJSlider("Angle: ", 1.0f, -180.0f, 180.0f, (float) lShoulderRotateAngle);
        lShoulderRotateAxisAngleSlider.addFloatListener(this);
        panel.add(lShoulderRotateAxisAngleSlider);
        
        return panel;
    }
    public JPanel LE(){
    	JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        
        panel.add(new JLabel("lElbow Rotation Axis"));
        lElbowAxisXSlider = new FloatLabelJSlider("X: ",0.01f, -1.0f, 1.0f, (float) lElbowRotateAxis.x);
        lElbowAxisXSlider.addFloatListener(this);
        panel.add(lElbowAxisXSlider);
        
        lElbowAxisYSlider = new FloatLabelJSlider("Y: ",0.01f, -1.0f, 1.0f, (float) lElbowRotateAxis.y);
        lElbowAxisYSlider.addFloatListener(this);
        panel.add(lElbowAxisYSlider);
            
        lElbowAxisZSlider = new FloatLabelJSlider("Z: ",0.01f, -1.0f, 1.0f, (float) lElbowRotateAxis.z);
        lElbowAxisZSlider.addFloatListener(this);
        panel.add(lElbowAxisZSlider);
        
        normalizeRotateAxis();
        lElbowRotateAxisAngleSlider = new FloatLabelJSlider("Angle: ", 1.0f, -180.0f, 180.0f, (float) lElbowRotateAngle);
        lElbowRotateAxisAngleSlider.addFloatListener(this);
        panel.add(lElbowRotateAxisAngleSlider);

        return panel;
    }
    
    //creat the jpanel for controlling the lower body
    
    public JPanel RH(){
    	JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
    	
        panel.add(new JLabel("rHip Rotation Axis"));
        rHipAxisXSlider = new FloatLabelJSlider("X: ",0.01f, -1.0f, 1.0f, (float) rHipRotateAxis.x);
        rHipAxisXSlider.addFloatListener(this);
        panel.add(rHipAxisXSlider);
        
        rHipAxisYSlider = new FloatLabelJSlider("Y: ",0.01f, -1.0f, 1.0f, (float) rHipRotateAxis.y);
        rHipAxisYSlider.addFloatListener(this);
        panel.add(rHipAxisYSlider);
            
        rHipAxisZSlider = new FloatLabelJSlider("Z: ",0.01f, -1.0f, 1.0f, (float) rHipRotateAxis.z);
        rHipAxisZSlider.addFloatListener(this);
        panel.add(rHipAxisZSlider);
        
        normalizeRotateAxis();
        rHipRotateAxisAngleSlider = new FloatLabelJSlider("Angle: ", 1.0f, -180.0f, 180.0f, (float) rHipRotateAngle);
        rHipRotateAxisAngleSlider.addFloatListener(this);
        panel.add(rHipRotateAxisAngleSlider);
        
        return panel;
    }
    public JPanel RK(){
    	 JPanel panel = new JPanel();
         panel.setLayout(new GridLayout(0, 1));
         
         panel.add(new JLabel("rKnee Rotation Axis"));
         rKneeAxisXSlider = new FloatLabelJSlider("X: ",0.01f, -1.0f, 1.0f, (float) rKneeRotateAxis.x);
         rKneeAxisXSlider.addFloatListener(this);
         panel.add(rKneeAxisXSlider);
         
         rKneeAxisYSlider = new FloatLabelJSlider("Y: ",0.01f, -1.0f, 1.0f, (float) rKneeRotateAxis.y);
         rKneeAxisYSlider.addFloatListener(this);
         panel.add(rKneeAxisYSlider);
             
         rKneeAxisZSlider = new FloatLabelJSlider("Z: ",0.01f, -1.0f, 1.0f, (float) rKneeRotateAxis.z);
         rKneeAxisZSlider.addFloatListener(this);
         panel.add(rKneeAxisZSlider);
         
         normalizeRotateAxis();
         rKneeRotateAxisAngleSlider = new FloatLabelJSlider("Angle: ", 1.0f, -180.0f, 180.0f, (float) rKneeRotateAngle);
         rKneeRotateAxisAngleSlider.addFloatListener(this);
         panel.add(rKneeRotateAxisAngleSlider);
         
         return panel;
    }
    public JPanel LH(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        
        panel.add(new JLabel("lHip Rotation Axis"));
        lHipAxisXSlider = new FloatLabelJSlider("X: ",0.01f, -1.0f, 1.0f, (float) lHipRotateAxis.x);
        lHipAxisXSlider.addFloatListener(this);
        panel.add(lHipAxisXSlider);
        
        lHipAxisYSlider = new FloatLabelJSlider("Y: ",0.01f, -1.0f, 1.0f, (float) lHipRotateAxis.y);
        lHipAxisYSlider.addFloatListener(this);
        panel.add(lHipAxisYSlider);
            
        lHipAxisZSlider = new FloatLabelJSlider("Z: ",0.01f, -1.0f, 1.0f, (float) lHipRotateAxis.z);
        lHipAxisZSlider.addFloatListener(this);
        panel.add(lHipAxisZSlider);
        
        normalizeRotateAxis();
        lHipRotateAxisAngleSlider = new FloatLabelJSlider("Angle: ", 1.0f, -180.0f, 180.0f, (float) lHipRotateAngle);
        lHipRotateAxisAngleSlider.addFloatListener(this);
        panel.add(lHipRotateAxisAngleSlider);
        
        return panel;
    }
    public JPanel LK(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        
        panel.add(new JLabel("lKnee Rotation Axis"));
        lKneeAxisXSlider = new FloatLabelJSlider("X: ",0.01f, -1.0f, 1.0f, (float) lKneeRotateAxis.x);
        lKneeAxisXSlider.addFloatListener(this);
        panel.add(lKneeAxisXSlider);
        
        lKneeAxisYSlider = new FloatLabelJSlider("Y: ",0.01f, -1.0f, 1.0f, (float) lKneeRotateAxis.y);
        lKneeAxisYSlider.addFloatListener(this);
        panel.add(lKneeAxisYSlider);
            
        lKneeAxisZSlider = new FloatLabelJSlider("Z: ",0.01f, -1.0f, 1.0f, (float) lKneeRotateAxis.z);
        lKneeAxisZSlider.addFloatListener(this);
        panel.add(lKneeAxisZSlider);
        
        normalizeRotateAxis();
        lKneeRotateAxisAngleSlider = new FloatLabelJSlider("Angle: ", 1.0f, -180.0f, 180.0f, (float) lKneeRotateAngle);
        lKneeRotateAxisAngleSlider.addFloatListener(this);
        panel.add(lKneeRotateAxisAngleSlider);
        
        return panel;
    }
    
    //what the user sees of the 3d stuff
    //creates a new transform group over the human_body and acts as the master control
    BranchGroup createSceneGraph() {
	    // Create the root of the branch graph
	    BranchGroup objRoot = new BranchGroup();
	
	    // Create a TransformGroup to scale the scene down by 3.5x
	    // TODO: move view platform instead of scene using orbit behavior
	    TransformGroup objScale = new TransformGroup();
	    Transform3D scaleTrans = new Transform3D();
	    scaleTrans.set(1 / 3.5f); // scale down by 3.5x
	    objScale.setTransform(scaleTrans);
	    objRoot.addChild(objScale);
	
	    // Create a TransformGroup and initialize it to the
	    // identity. Enable the TRANSFORM_WRITE capability so that
	    // the mouse behaviors code can modify it at runtime. Add it to the
	    // root of the subgraph.
	    TransformGroup objTrans = new TransformGroup();
	    objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	    objScale.addChild(objTrans);
	
	    // Add the primitives to the scene
	    createHuman(); // the human
	    objTrans.addChild(Human_body);
	
	    BoundingSphere bounds = new BoundingSphere(new Point3d(), 100.0);
	
	    Background bg = new Background(new Color3f(1.0f, 1.0f, 1.0f));
	    bg.setApplicationBounds(bounds);
	    objTrans.addChild(bg);
	
	    // set up the mouse rotation behavior
	    MouseRotate mr = new MouseRotate();
	    mr.setTransformGroup(objTrans);
	    mr.setSchedulingBounds(bounds);
	    mr.setFactor(0.007);
	    objTrans.addChild(mr);
	
	    // Set up the ambient light
	    Color3f ambientColor = new Color3f(0.1f, 0.1f, 0.1f);
	    AmbientLight ambientLightNode = new AmbientLight(ambientColor);
	    ambientLightNode.setInfluencingBounds(bounds);
	    objRoot.addChild(ambientLightNode);
	
	    // Set up the directional lights
	    Color3f light1Color = new Color3f(1.0f, 1.0f, 1.0f);
	    Vector3f light1Direction = new Vector3f(0.0f, -0.2f, -1.0f);
	
	    DirectionalLight light1 = new DirectionalLight(light1Color,
	        light1Direction);
	    light1.setInfluencingBounds(bounds);
	    objRoot.addChild(light1);
	
	    return objRoot;
    }

    //controller specifying if it si an application 
    public UserInterface3D(boolean isApplication, float initOffScreenScale) {
    	this.isApplication = isApplication;
    	this.offScreenScale = initOffScreenScale;
    }

    //called when the applet is loaded. adds all the parts
    //the control for upp body
    //the control for lower body
    //the 3d canvas
    public void init() {
	    // set up a NumFormat object to print out float with only 3 fraction
	    // digits
	    nf = NumberFormat.getInstance();
	    nf.setMaximumFractionDigits(3);
	
	    setLayout(new BorderLayout());
	    GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
	
	    canvas = new Canvas3D(config);
	
	    add(BorderLayout.CENTER, canvas);
	
	    u = new SimpleUniverse(canvas);
	
	    if (isApplication) {
	    	offScreenCanvas = new OffScreenCanvas3D(config, true);
	    	// set the size of the off-screen canvas based on a scale
	    	// of the on-screen size
	    	Screen3D sOn = canvas.getScreen3D();
	    	Screen3D sOff = offScreenCanvas.getScreen3D();
	    	Dimension dim = sOn.getSize();
	      	dim.width *= offScreenScale;
	      	dim.height *= offScreenScale;
	      	sOff.setSize(dim);
	      	sOff.setPhysicalScreenWidth(sOn.getPhysicalScreenWidth() * offScreenScale);
	      	sOff.setPhysicalScreenHeight(sOn.getPhysicalScreenHeight() * offScreenScale);
	
	      	// attach the offscreen canvas to the view
	      	u.getViewer().getView().addCanvas3D(offScreenCanvas);
	    }

	    // Create a simple scene and attach it to the virtual universe
	    BranchGroup scene = createSceneGraph();

	    // This will move the ViewPlatform back a bit so the
	    // objects in the scene can be viewed.
	    u.getViewingPlatform().setNominalViewingTransform();
	    u.addBranchGraph(scene);

	    view = u.getViewer().getView();
//	    
//	    add(BorderLayout.WEST,upperBodyPanel());
//	    add(BorderLayout.EAST,lowerBodyPanel());
//	    
    	
    	//1. Create the frame.
    	JFrame frame = new JFrame("Joint Control");

    	JTabbedPane tabbedPane = new JTabbedPane();
    	
    	tabbedPane.add("Right Shoulder", RS());
    	tabbedPane.add("Right Elbow", RE());
    	tabbedPane.add("Left Shoulder", LS());
    	tabbedPane.add("Left Elbow", LE());
    	tabbedPane.add("Right Hip", RH());
    	tabbedPane.add("Right Knee", RK());
    	tabbedPane.add("Left Hip", LH());
    	tabbedPane.add("Left Knee", LK());
    	
//    	ChangeListener changeListener = new ChangeListener() {
//    	      public void stateChanged(ChangeEvent changeEvent) {
//    	        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
//    	        int index = sourceTabbedPane.getSelectedIndex();
//    	        System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
//    	      }
//    	};
//    	tabbedPane.addChangeListener(changeListener);

    	frame.getContentPane().add(tabbedPane);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.pack();
    	frame.setVisible(true);
    	frame.setLocation(800, 0);
    }

    //destructor
    public void destroy() {
    	u.removeAllLocales();
    }
    
    //this is what is called when you move one of the slidebars controlling the x,y,and z of the rotational axis
	@Override
	public void floatChanged(FloatEvent e) {
		if (e.getSource() == rShoulderAxisXSlider){
			rShoulderRotateAxis.x = e.getValue();
      	  	normalizeRotateAxis();
      	  	updateAxisAngle();
      	  	setRotation();
      	  	rShoulderRotAxis.setRotationAxis(rShoulderRotateAxis);
		}else if (e.getSource() == rShoulderAxisYSlider){
			rShoulderRotateAxis.y = e.getValue();
			normalizeRotateAxis();
			updateAxisAngle();
			setRotation();
			rShoulderRotAxis.setRotationAxis(rShoulderRotateAxis);
		}else if (e.getSource() == rShoulderAxisZSlider){
			rShoulderRotateAxis.z = e.getValue();
			normalizeRotateAxis();
			updateAxisAngle();
			setRotation();
			rShoulderRotAxis.setRotationAxis(rShoulderRotateAxis);
		}else if (e.getSource() == rShoulderRotateAxisAngleSlider){
			rShoulderRotateAngle = e.getValue();
			updateAxisAngle();
			setRotation();
		}else if (e.getSource() == rElbowAxisXSlider){
			rElbowRotateAxis.x = e.getValue();
      	  	normalizeRotateAxis();
      	  	updateAxisAngle();
      	  	setRotation();
      	  	rElbowRotAxis.setRotationAxis(rElbowRotateAxis);
		}else if (e.getSource() == rElbowAxisYSlider){
			rElbowRotateAxis.y = e.getValue();
			normalizeRotateAxis();
			updateAxisAngle();
			setRotation();
			rElbowRotAxis.setRotationAxis(rElbowRotateAxis);
		}else if (e.getSource() == rElbowAxisZSlider){
			rElbowRotateAxis.z = e.getValue();
			normalizeRotateAxis();
			updateAxisAngle();
			setRotation();
			rElbowRotAxis.setRotationAxis(rElbowRotateAxis);
		}else if (e.getSource() == rElbowRotateAxisAngleSlider){
			rElbowRotateAngle = e.getValue();
			updateAxisAngle();
			setRotation();
		}else if (e.getSource() == lShoulderAxisXSlider){
			lShoulderRotateAxis.x = e.getValue();
      	  	normalizeRotateAxis();
      	  	updateAxisAngle();
      	  	setRotation();
      	  	lShoulderRotAxis.setRotationAxis(lShoulderRotateAxis);
		}else if (e.getSource() == lShoulderAxisYSlider){
			lShoulderRotateAxis.y = e.getValue();
			normalizeRotateAxis();
			updateAxisAngle();
			setRotation();
			lShoulderRotAxis.setRotationAxis(lShoulderRotateAxis);
		}else if (e.getSource() == lShoulderAxisZSlider){
			lShoulderRotateAxis.z = e.getValue();
			normalizeRotateAxis();
			updateAxisAngle();
			setRotation();
			lShoulderRotAxis.setRotationAxis(lShoulderRotateAxis);
		}else if (e.getSource() == lShoulderRotateAxisAngleSlider){
			lShoulderRotateAngle = e.getValue();
			updateAxisAngle();
			setRotation();
		}else if (e.getSource() == lElbowAxisXSlider){
			lElbowRotateAxis.x = e.getValue();
      	  	normalizeRotateAxis();
      	  	updateAxisAngle();
      	  	setRotation();
      	  	lElbowRotAxis.setRotationAxis(lElbowRotateAxis);
		}else if (e.getSource() == lElbowAxisYSlider){
			lElbowRotateAxis.y = e.getValue();
			normalizeRotateAxis();
			updateAxisAngle();
			setRotation();
			lElbowRotAxis.setRotationAxis(lElbowRotateAxis);
		}else if (e.getSource() == lElbowAxisZSlider){
			lElbowRotateAxis.z = e.getValue();
			normalizeRotateAxis();
			updateAxisAngle();
			setRotation();
			lElbowRotAxis.setRotationAxis(lElbowRotateAxis);
		}else if (e.getSource() == lElbowRotateAxisAngleSlider){
			lElbowRotateAngle = e.getValue();
			updateAxisAngle();
			setRotation();
			
			///LEGS STUFF
		}else if (e.getSource() == rHipAxisXSlider){
			rHipRotateAxis.x = e.getValue();
      	  	normalizeRotateAxis();
      	  	updateAxisAngle();
      	  	setRotation();
      	  	rHipRotAxis.setRotationAxis(rHipRotateAxis);
		}else if (e.getSource() == rHipAxisYSlider){
			rHipRotateAxis.y = e.getValue();
			normalizeRotateAxis();
			updateAxisAngle();
			setRotation();
			rHipRotAxis.setRotationAxis(rHipRotateAxis);
		}else if (e.getSource() == rHipAxisZSlider){
			rHipRotateAxis.z = e.getValue();
			normalizeRotateAxis();
			updateAxisAngle();
			setRotation();
			rHipRotAxis.setRotationAxis(rHipRotateAxis);
		}else if (e.getSource() == rHipRotateAxisAngleSlider){
			rHipRotateAngle = e.getValue();
			updateAxisAngle();
			setRotation();
		}else if (e.getSource() == rKneeAxisXSlider){
			rKneeRotateAxis.x = e.getValue();
      	  	normalizeRotateAxis();
      	  	updateAxisAngle();
      	  	setRotation();
      	  	rKneeRotAxis.setRotationAxis(rKneeRotateAxis);
		}else if (e.getSource() == rKneeAxisYSlider){
			rKneeRotateAxis.y = e.getValue();
			normalizeRotateAxis();
			updateAxisAngle();
			setRotation();
			rKneeRotAxis.setRotationAxis(rKneeRotateAxis);
		}else if (e.getSource() == rKneeAxisZSlider){
			rKneeRotateAxis.z = e.getValue();
			normalizeRotateAxis();
			updateAxisAngle();
			setRotation();
			rKneeRotAxis.setRotationAxis(rKneeRotateAxis);
		}else if (e.getSource() == rKneeRotateAxisAngleSlider){
			rKneeRotateAngle = e.getValue();
			updateAxisAngle();
			setRotation();
		}else if (e.getSource() == lHipAxisXSlider){
			lHipRotateAxis.x = e.getValue();
      	  	normalizeRotateAxis();
      	  	updateAxisAngle();
      	  	setRotation();
      	  	lHipRotAxis.setRotationAxis(lHipRotateAxis);
		}else if (e.getSource() == lHipAxisYSlider){
			lHipRotateAxis.y = e.getValue();
			normalizeRotateAxis();
			updateAxisAngle();
			setRotation();
			lHipRotAxis.setRotationAxis(lHipRotateAxis);
		}else if (e.getSource() == lHipAxisZSlider){
			lHipRotateAxis.z = e.getValue();
			normalizeRotateAxis();
			updateAxisAngle();
			setRotation();
			lHipRotAxis.setRotationAxis(lHipRotateAxis);
		}else if (e.getSource() == lHipRotateAxisAngleSlider){
			lHipRotateAngle = e.getValue();
			updateAxisAngle();
			setRotation();
		}else if (e.getSource() == lKneeAxisXSlider){
			lKneeRotateAxis.x = e.getValue();
      	  	normalizeRotateAxis();
      	  	updateAxisAngle();
      	  	setRotation();
      	  	lKneeRotAxis.setRotationAxis(lKneeRotateAxis);
		}else if (e.getSource() == lKneeAxisYSlider){
			lKneeRotateAxis.y = e.getValue();
			normalizeRotateAxis();
			updateAxisAngle();
			setRotation();
			lKneeRotAxis.setRotationAxis(lKneeRotateAxis);
		}else if (e.getSource() == lKneeAxisZSlider){
			lKneeRotateAxis.z = e.getValue();
			normalizeRotateAxis();
			updateAxisAngle();
			setRotation();
			lKneeRotAxis.setRotationAxis(lKneeRotateAxis);
		}else if (e.getSource() == lKneeRotateAxisAngleSlider){
			lKneeRotateAngle = e.getValue();
			updateAxisAngle();
			setRotation();
		}
	}
	
	//starts the applet
    public static void main(String[] args) {
    	float initOffScreenScale = 2.5f;
    	new MainFrame(new UserInterface3D(true, initOffScreenScale), 800, 800);    	
    }
}
