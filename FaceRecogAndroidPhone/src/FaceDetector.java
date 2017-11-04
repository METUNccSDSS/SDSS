import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.imgproc.Imgproc;

public class FaceDetector {

public static int callFaceDetector() {
    // Load the native library.
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

    // Create a face detector from the cascade file in the resources directory.
   CascadeClassifier faceDetector = new CascadeClassifier("C:/Users/alica/Desktop/gallery/haarcascade_frontalface_alt.xml");
     Mat image = Imgcodecs.imread("C:/Users/alica/Desktop/FromServer/Randomly_Selected.png");
	 Size sz = new Size(125,150);
	 int count = 0;
     
if (faceDetector.empty()){
     System.out.println("faceless");
	 return 0;
    }
    if(image.empty()){
     System.out.println("imageless");

	 return 0;
    }
    
 // Rotate the picture from the camera
    Core.transpose(image, image);
    Core.flip(image, image, 0);

    // Detect faces in the image.
    // MatOfRect is a special container class for Rect.
    MatOfRect faceDetections = new MatOfRect();
    faceDetector.detectMultiScale(image, faceDetections);

    System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
   
    for (Rect rect : faceDetections.toArray()) {
    	   Mat faceImage = image.submat(rect);
    	   Imgproc.cvtColor(faceImage,faceImage,Imgproc.COLOR_BGR2GRAY);
    	   Imgproc.resize( faceImage, faceImage, sz );
    	   if (count != 0)
    	   Imgcodecs.imwrite("Randomly_Selected_" + count + ".png", faceImage);
    	   else
    	   Imgcodecs.imwrite("C:/Users/alica/Desktop/FromServer/Randomly_Selected.png", faceImage);
    	   count++;    	   
    	}
    if (faceDetections.toArray().length <1)
    	return 0;
    else 
    	return 1;
  }
}