# Introduction
The goal of the project is to create an AR Jewellery Android App for object visualisation such as s watch, ring, bracelet and necklace that gives our potential customers the new unique user experience and allows them to look at the item more precisely and in more detail.
Using augmented reality allows us to bring virtual objects in the real world. In this way, an augmented reality app for smartphone owners can turn all items of catalogs into 3D animated models. In jewellery or watch it’s all about presentation and showcases. People only need to point their mobile devices onto the showcases.

# Implementation
The following programs are used to implement the AR Jewellery application. The 3D models are provided by Google Poly which is allowed the developer to use them under the Creative Commons licenses [6].

<i>1. Android Studio:</i> Android Studio provides the fastest tools for building apps on every type of Android device.
<i>2. AR Core (Sceneform):</i> To enable the Augmented reality feature in the android application. Sceneform makes it easy to make 3D scenes in AR and non-AR applications in practical [7].

The development tasks are divided into 2 subtasks. The first subtask is to develop an android application for displaying all the products, and the second subtask is to develop an AR camera for trying a specific product. There are some difficulties in the development process which are:
1.	The android emulator does not work properly for testing the AR camera. The emulator works very slow when it renders the virtual scene.
2.	The reference image for placing a 3D model cannot be detected when running the application in the emulator.
3.	The real device has the supported android API version for testing AR Core, but there is a critical bug in AR Core which happens in the developer’s device. This bug is fixed in the higher android API version.
4.	From the application testing, there are some concerns about the constraint of the reference image that it may need to be a flat image like a printout picture rather than a picture of real-world objects like a hand or the real ring.
