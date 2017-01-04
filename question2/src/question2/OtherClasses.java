package question2;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.image.BufferedImage;
import java.text.NumberFormat;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.EventObject;
import java.util.Hashtable;
import java.util.Vector;

import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ImageComponent;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.Link;
import javax.media.j3d.Material;
import javax.media.j3d.SharedGroup;
import javax.media.j3d.Switch;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;

public class OtherClasses {
}

class OffScreenCanvas3D extends Canvas3D {

  OffScreenCanvas3D(GraphicsConfiguration graphicsConfiguration,
      boolean offScreen) {

    super(graphicsConfiguration, offScreen);
  }

  private BufferedImage doRender(int width, int height) {

    BufferedImage bImage = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_RGB);

    ImageComponent2D buffer = new ImageComponent2D(
        ImageComponent.FORMAT_RGB, bImage);
    //buffer.setYUp(true);

    setOffScreenBuffer(buffer);
    renderOffScreenBuffer();
    waitForOffScreenRendering();
    bImage = getOffScreenBuffer().getImage();
    return bImage;
  }
}


interface Java3DExplorerConstants {

	  // colors
	  static Color3f black = new Color3f(0.0f, 0.0f, 0.0f);

	  static Color3f red = new Color3f(1.0f, 0.0f, 0.0f);

	  static Color3f green = new Color3f(0.0f, 1.0f, 0.0f);

	  static Color3f blue = new Color3f(0.0f, 0.0f, 1.0f);

	  static Color3f skyBlue = new Color3f(0.6f, 0.7f, 0.9f);

	  static Color3f cyan = new Color3f(0.0f, 1.0f, 1.0f);

	  static Color3f magenta = new Color3f(1.0f, 0.0f, 1.0f);

	  static Color3f yellow = new Color3f(1.0f, 1.0f, 0.0f);

	  static Color3f brightWhite = new Color3f(1.0f, 1.5f, 1.5f);

	  static Color3f white = new Color3f(1.0f, 1.0f, 1.0f);

	  static Color3f darkGrey = new Color3f(0.15f, 0.15f, 0.15f);

	  static Color3f medGrey = new Color3f(0.3f, 0.3f, 0.3f);

	  static Color3f grey = new Color3f(0.5f, 0.5f, 0.5f);

	  static Color3f lightGrey = new Color3f(0.75f, 0.75f, 0.75f);

	  // infinite bounding region, used to make env nodes active everywhere
	  BoundingSphere infiniteBounds = new BoundingSphere(new Point3d(),
	      Double.MAX_VALUE);

	  // common values
	  static final String nicestString = "NICEST";

	  static final String fastestString = "FASTEST";

	  static final String antiAliasString = "Anti-Aliasing";

	  static final String noneString = "NONE";

	  // light type constants
	  static int LIGHT_AMBIENT = 1;

	  static int LIGHT_DIRECTIONAL = 2;

	  static int LIGHT_POSITIONAL = 3;

	  static int LIGHT_SPOT = 4;

	  // screen capture constants
	  static final int USE_COLOR = 1;

	  static final int USE_BLACK_AND_WHITE = 2;

	  // number formatter
	  NumberFormat nf = NumberFormat.getInstance();

	}

class FloatLabelJSlider extends JPanel implements ChangeListener,
  Java3DExplorerConstants {

JSlider slider;

JLabel valueLabel;

Vector listeners = new Vector();

float min, max, resolution, current, scale;

int minInt, maxInt, curInt;;

int intDigits, fractDigits;

float minResolution = 0.001f;

// default slider with name, resolution = 0.1, min = 0.0, max = 1.0 inital
// 0.5
FloatLabelJSlider(String name) {
  this(name, 0.1f, 0.0f, 1.0f, 0.5f);
}

FloatLabelJSlider(String name, float resolution, float min, float max,
    float current) {

  this.resolution = resolution;
  this.min = min;
  this.max = max;
  this.current = current;

  if (resolution < minResolution) {
    resolution = minResolution;
  }

  // round scale to nearest integer fraction. i.e. 0.3 => 1/3 = 0.33
  scale = (float) Math.round(1.0f / resolution);
  resolution = 1.0f / scale;

  // get the integer versions of max, min, current
  minInt = Math.round(min * scale);
  maxInt = Math.round(max * scale);
  curInt = Math.round(current * scale);

  // sliders use integers, so scale our floating point value by "scale"
  // to make each slider "notch" be "resolution". We will scale the
  // value down by "scale" when we get the event.
  slider = new JSlider(JSlider.HORIZONTAL, minInt, maxInt, curInt);
  slider.addChangeListener(this);

  valueLabel = new JLabel(" ");

  // set the initial value label
  setLabelString();

  // add min and max labels to the slider
  Hashtable labelTable = new Hashtable();
  labelTable.put(new Integer(minInt), new JLabel(nf.format(min)));
  labelTable.put(new Integer(maxInt), new JLabel(nf.format(max)));
  slider.setLabelTable(labelTable);
  slider.setPaintLabels(true);

  /* layout to align left */
  setLayout(new BorderLayout());
  Box box = new Box(BoxLayout.X_AXIS);
  add(box, BorderLayout.WEST);

  box.add(new JLabel(name));
  box.add(slider);
  box.add(valueLabel);
}

public void setMinorTickSpacing(float spacing) {
  int intSpacing = Math.round(spacing * scale);
  slider.setMinorTickSpacing(intSpacing);
}

public void setMajorTickSpacing(float spacing) {
  int intSpacing = Math.round(spacing * scale);
  slider.setMajorTickSpacing(intSpacing);
}

public void setPaintTicks(boolean paint) {
  slider.setPaintTicks(paint);
}

public void addFloatListener(FloatListener listener) {
  listeners.add(listener);
}

public void removeFloatListener(FloatListener listener) {
  listeners.remove(listener);
}

public void stateChanged(ChangeEvent e) {
  JSlider source = (JSlider) e.getSource();
  // get the event type, set the corresponding value.
  // Sliders use integers, handle floating point values by scaling the
  // values by "scale" to allow settings at "resolution" intervals.
  // Divide by "scale" to get back to the real value.
  curInt = source.getValue();
  current = curInt / scale;

  valueChanged();
}

public void setValue(float newValue) {
  boolean changed = (newValue != current);
  current = newValue;
  if (changed) {
    valueChanged();
  }
}

private void valueChanged() {
  // update the label
  setLabelString();

  // notify the listeners
  FloatEvent event = new FloatEvent(this, current);
  for (Enumeration e = listeners.elements(); e.hasMoreElements();) {
    FloatListener listener = (FloatListener) e.nextElement();
    listener.floatChanged(event);
  }
}

void setLabelString() {
  // Need to muck around to try to make sure that the width of the label
  // is wide enough for the largest value. Pad the string
  // be large enough to hold the largest value.
  int pad = 5; // fudge to make up for variable width fonts
  float maxVal = Math.max(Math.abs(min), Math.abs(max));
  intDigits = Math.round((float) (Math.log(maxVal) / Math.log(10))) + pad;
  if (min < 0) {
    intDigits++; // add one for the '-'
  }
  // fractDigits is num digits of resolution for fraction. Use base 10 log
  // of scale, rounded up, + 2.
  fractDigits = (int) Math.ceil((Math.log(scale) / Math.log(10)));
  nf.setMinimumFractionDigits(fractDigits);
  nf.setMaximumFractionDigits(fractDigits);
  String value = nf.format(current);
  while (value.length() < (intDigits + fractDigits)) {
    value = value + "  ";
  }
  valueLabel.setText(value);
}

}

class FloatEvent extends EventObject {

float value;

FloatEvent(Object source, float newValue) {
  super(source);
  value = newValue;
}

float getValue() {
  return value;
}
}

interface FloatListener extends EventListener {
void floatChanged(FloatEvent e);
}

class LogFloatLabelJSlider extends JPanel implements ChangeListener,
  Java3DExplorerConstants {

JSlider slider;

JLabel valueLabel;

Vector listeners = new Vector();

float min, max, resolution, current, scale;

double minLog, maxLog, curLog;

int minInt, maxInt, curInt;;

int intDigits, fractDigits;

NumberFormat nf = NumberFormat.getInstance();

float minResolution = 0.001f;

double logBase = Math.log(10);

// default slider with name, resolution = 0.1, min = 0.0, max = 1.0 inital
// 0.5
LogFloatLabelJSlider(String name) {
  this(name, 0.1f, 100.0f, 10.0f);
}

LogFloatLabelJSlider(String name, float min, float max, float current) {

  this.min = min;
  this.max = max;
  this.current = current;

  if (resolution < minResolution) {
    resolution = minResolution;
  }

  minLog = log10(min);
  maxLog = log10(max);
  curLog = log10(current);

  // resolution is 100 steps from min to max
  scale = 100.0f;
  resolution = 1.0f / scale;

  // get the integer versions of max, min, current
  minInt = (int) Math.round(minLog * scale);
  maxInt = (int) Math.round(maxLog * scale);
  curInt = (int) Math.round(curLog * scale);

  slider = new JSlider(JSlider.HORIZONTAL, minInt, maxInt, curInt);
  slider.addChangeListener(this);

  valueLabel = new JLabel(" ");

  // Need to muck around to make sure that the width of the label
  // is wide enough for the largest value. Pad the initial string
  // be large enough to hold the largest value.
  int pad = 5; // fudge to make up for variable width fonts
  intDigits = (int) Math.ceil(maxLog) + pad;
  if (min < 0) {
    intDigits++; // add one for the '-'
  }
  if (minLog < 0) {
    fractDigits = (int) Math.ceil(-minLog);
  } else {
    fractDigits = 0;
  }
  nf.setMinimumFractionDigits(fractDigits);
  nf.setMaximumFractionDigits(fractDigits);
  String value = nf.format(current);
  while (value.length() < (intDigits + fractDigits)) {
    value = value + " ";
  }
  valueLabel.setText(value);

  // add min and max labels to the slider
  Hashtable labelTable = new Hashtable();
  labelTable.put(new Integer(minInt), new JLabel(nf.format(min)));
  labelTable.put(new Integer(maxInt), new JLabel(nf.format(max)));
  slider.setLabelTable(labelTable);
  slider.setPaintLabels(true);

  // layout to align left
  setLayout(new BorderLayout());
  Box box = new Box(BoxLayout.X_AXIS);
  add(box, BorderLayout.WEST);

  box.add(new JLabel(name));
  box.add(slider);
  box.add(valueLabel);
}

public void setMinorTickSpacing(float spacing) {
  int intSpacing = Math.round(spacing * scale);
  slider.setMinorTickSpacing(intSpacing);
}

public void setMajorTickSpacing(float spacing) {
  int intSpacing = Math.round(spacing * scale);
  slider.setMajorTickSpacing(intSpacing);
}

public void setPaintTicks(boolean paint) {
  slider.setPaintTicks(paint);
}

public void addFloatListener(FloatListener listener) {
  listeners.add(listener);
}

public void removeFloatListener(FloatListener listener) {
  listeners.remove(listener);
}

public void stateChanged(ChangeEvent e) {
  JSlider source = (JSlider) e.getSource();
  curInt = source.getValue();
  curLog = curInt / scale;
  current = (float) exp10(curLog);

  valueChanged();
}

public void setValue(float newValue) {
  boolean changed = (newValue != current);
  current = newValue;
  if (changed) {
    valueChanged();
  }
}

private void valueChanged() {
  String value = nf.format(current);
  valueLabel.setText(value);

  // notify the listeners
  FloatEvent event = new FloatEvent(this, current);
  for (Enumeration e = listeners.elements(); e.hasMoreElements();) {
    FloatListener listener = (FloatListener) e.nextElement();
    listener.floatChanged(event);
  }
}

double log10(double value) {
  return Math.log(value) / logBase;
}

double exp10(double value) {
  return Math.exp(value * logBase);
}

}

class RotAxis extends Switch implements Java3DExplorerConstants {


  // axis to align with 
  Vector3f    rotAxis = new Vector3f(1.0f, 0.0f, 0.0f); 
  // offset to ref point 
  Vector3f    refPt = new Vector3f(0.0f, 0.0f, 0.0f); 

  TransformGroup  axisTG; // the transform group used to align the axis

  // Temporaries that are reused
  Transform3D    tmpTrans = new Transform3D();
  Vector3f    tmpVector = new Vector3f();
  AxisAngle4f    tmpAxisAngle = new AxisAngle4f();

  // geometric constants
  Point3f    origin = new Point3f();
  Vector3f    yAxis = new Vector3f(0.0f, 1.0f, 0.0f);

  RotAxis(float axisLength) {
super(Switch.CHILD_NONE);
setCapability(Switch.ALLOW_SWITCH_READ);
setCapability(Switch.ALLOW_SWITCH_WRITE);

// set up the proportions for the arrow
float axisRadius = axisLength / 120.0f;
float arrowRadius = axisLength / 50.0f;
float arrowHeight = axisLength / 30.0f;


// create the TransformGroup which will be used to orient the axis
axisTG = new TransformGroup();
axisTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
axisTG.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
addChild(axisTG);

// Set up an appearance to make the Axis have 
// blue ambient, black emmissive, blue diffuse and white specular 
// coloring.  
Material material = new Material(red, black, red, white, 64);
Appearance appearance = new Appearance();
appearance.setMaterial(material);

// create a cylinder for the central line of the axis
Cylinder cylinder = new Cylinder(axisRadius, axisLength, appearance); 
// cylinder goes from -length/2 to length/2 in y
axisTG.addChild(cylinder);

// create a SharedGroup for the arrowHead
Cone arrowHead = new Cone(arrowRadius, arrowHeight, appearance); 
SharedGroup arrowHeadSG = new SharedGroup();
arrowHeadSG.addChild(arrowHead);

// Create a TransformGroup to move the cone to the top of the 
// cylinder
tmpVector.set(0.0f, axisLength/2 + arrowHeight / 2, 0.0f);
tmpTrans.set(tmpVector);
TransformGroup topTG = new TransformGroup();  
topTG.setTransform(tmpTrans);
topTG.addChild(new Link(arrowHeadSG));
axisTG.addChild(topTG);

// create the bottom of the arrow
// Create a TransformGroup to move the cone to the bottom of the 
// axis so that its pushes into the bottom of the cylinder
tmpVector.set(0.0f, -(axisLength / 2), 0.0f);
tmpTrans.set(tmpVector);
TransformGroup bottomTG = new TransformGroup();  
bottomTG.setTransform(tmpTrans);
bottomTG.addChild(new Link(arrowHeadSG));
axisTG.addChild(bottomTG);

updateAxisTransform();
  }

  public void setRotationAxis(Vector3f setRotAxis) {
rotAxis.set(setRotAxis);
float magSquared = rotAxis.lengthSquared();
if (magSquared > 0.0001) {
    rotAxis.scale((float)(1.0 / Math.sqrt(magSquared)));
} else {
    rotAxis.set(1.0f, 0.0f, 0.0f);
}
updateAxisTransform();
  }

  public void setRefPt(Vector3f setRefPt) {
refPt.set(setRefPt);
updateAxisTransform();
  }

  // set the transform on the axis so that it aligns with the rotation
  // axis and goes through the reference point
  private void updateAxisTransform() {
// We need to rotate the axis, which is defined along the y-axis,
// to the direction indicated by the rotAxis.
// We can do this using a neat trick.  To transform a vector to align
// with another vector (assuming both vectors have unit length), take 
// the cross product the the vectors.  The direction of the cross
// product is the axis, and the length of the cross product is the
// the sine of the angle, so the inverse sine of the length gives 
// us the angle
tmpVector.cross(yAxis, rotAxis);
float angle = (float)Math.asin(tmpVector.length());

tmpAxisAngle.set(tmpVector, angle);
tmpTrans.set(tmpAxisAngle);
tmpTrans.setTranslation(refPt);
axisTG.setTransform(tmpTrans);
  }
}
