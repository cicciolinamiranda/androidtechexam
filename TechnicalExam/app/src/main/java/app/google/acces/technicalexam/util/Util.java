package app.google.acces.technicalexam.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by cicciolina on 2/1/18.
 */

public class Util {

    private static Util instance;


    public static Util getInstance(){
        if(instance == null){
            instance = new Util();
        }
        return instance;
    }


    private Util(){

    }

    public Bitmap resizeBitmap(String photoPath, int targetW, int targetH) {
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = 1;
        if ((targetW > 0) || (targetH > 0)) {
            scaleFactor = Math.min(photoW/targetW, photoH/targetH);
        }

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true; //Deprecated API 21

        return BitmapFactory.decodeFile(photoPath, bmOptions);
    }
}
