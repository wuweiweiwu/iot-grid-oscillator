# Question #2: 3D interactive modeling of Human Body

##Coding process

This problem was probably one of the most complicated self projects ever. 

I decided to code this problem in Java because I have done past user interfaces in Java and I am most familiar with it.

Since I decided to code this in Java, I immediately started looking into 3D modeling in Java and I found a library called Java3D that was created by 
Oracle for 3D modeling. However, it was discontinued a couple of years ago and taken up by other people so it was pretty hard to find new documentation on it. 
After a lot of research I found some .jar libraries compatible with Java 8 and copied the jars into my eclipse environment and ran my first 3d application.

Then I started creating the articulated human body by using balls and cylinders offset at different distances from the origin.
That part was pretty easy and so was doing rotations along 1 axis. However, when I tried to do rotations with multiple axis or with rotational axis that is differnt from the x y or z axis it wouldnt work. 
It always reset when I added a new rotation which made the animation kind of glitchy.

Then I found some tips on 3D transformations combining different transformation such as translations, scale, and rotation.

http://www.java2s.com/Code/Java/3D/TransformExplorer.htm

And I realized that I was completely doing rotations wrong with only 1 Transform3D object for each piece (i.e: shoulder, elbow, etc) and I implemeted rotations based on 3D vectors which made it a lot easier.
And then I also realized that when you mess with 3D rotations, the vectors need to be unit vectors so the transformation wouldnt be scaled wrong. 
So, thus, I found a floating point version of JSlider and added that into my program (otherClasses.java) since for unit vectors, the x, y and z values all have to be between 0 and 1. And JSlider in javax only works with integers.

I also implemented a visible (red) rotational axis for each of the articulated joints so you can see where the joint will move when you change the rotational vector's x, y and z. And the limb will rotate along that axis when you change the Angle value.
That was a class I found called RotAxis that is pretty much a red arrow indicating the positive direction of the axis (otherClasses) that I modified and added to my project.

I was doing a lot of coding in the beginning getting the axis to rotate correctly and getting the body part to rotate around the axis. However, after I finished the rotational computations for rShoulder, it was a lot of copy and paste for each of the 7 other joints and some minor refactoring. I made my code as compact as possible but there is a lot of replication since each joints basically behaves in the same way

Here is a screenshot of the 3D user interface in action

![alt text](https://github.com/hungweiwu/iot-grid-oscillator/blob/master/question2/Screen%20Shot%202016-09-05%20at%209.39.38%20PM.png "Screen Shot")

##Execution and testing

the source code is located in this eclipse project directory. the jar file is also located in this directory. it can be executed in command line by the command java -jar question2executable.jar

If you are running in Eclipse the main class is UserInterface3D

As you adjust the sliders for each articulated joint, you can see how the red arrow representing the rotational axis changes. And when you change the angle of rotation, the body part will rotate the specified amount around the custom axis
