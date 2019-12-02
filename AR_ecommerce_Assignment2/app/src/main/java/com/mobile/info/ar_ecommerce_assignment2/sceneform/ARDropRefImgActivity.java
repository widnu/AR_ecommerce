package com.mobile.info.ar_ecommerce_assignment2.sceneform;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.ar.core.Anchor;
import com.google.ar.core.AugmentedImage;
import com.google.ar.core.AugmentedImageDatabase;
import com.google.ar.core.Config;
import com.google.ar.core.Frame;
import com.google.ar.core.Session;
import com.google.ar.core.TrackingState;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
import com.mobile.info.ar_ecommerce_assignment2.Constants;
import com.mobile.info.ar_ecommerce_assignment2.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

public class ARDropRefImgActivity extends AppCompatActivity {

    private String tryOnItemName = "";

    ArFragment arFragment;
    boolean shouldAddModel = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ardroprefimg);

        // get parameters
        tryOnItemName = "Ring";

        // manage AR Fragment
        arFragment = (CustomArFragment) getSupportFragmentManager().findFragmentById(R.id.sceneform_fragment_refimg);
        arFragment.getPlaneDiscoveryController().hide();
        arFragment.getArSceneView().getScene().addOnUpdateListener(this::onUpdateFrame);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void placeObject(ArFragment arFragment, Anchor anchor, Uri uri) {
        ModelRenderable.builder()
                .setSource(arFragment.getContext(), uri)
                .build()
                .thenAccept(modelRenderable -> addNodeToScene(arFragment, anchor, modelRenderable))
                .exceptionally(throwable -> {
                            Toast.makeText(arFragment.getContext(), "Error:" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                            return null;
                        }
                );
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void onUpdateFrame(FrameTime frameTime) {
        Frame frame = arFragment.getArSceneView().getArFrame();
        Collection<AugmentedImage> augmentedImages = frame.getUpdatedTrackables(AugmentedImage.class);
        for (AugmentedImage augmentedImage : augmentedImages) {
            if (augmentedImage.getTrackingState() == TrackingState.TRACKING) {
                if (Constants.REF_IMG.equals(augmentedImage.getName()) && shouldAddModel) {
                    placeObject(arFragment, augmentedImage.createAnchor(augmentedImage.getCenterPose()), getARObject(tryOnItemName));
                    shouldAddModel = false;
                }
            }
        }
    }
    public boolean setupAugmentedImagesDb(Config config, Session session) {
        AugmentedImageDatabase augmentedImageDatabase;
        Bitmap bitmap = loadAugmentedImage();
        if (bitmap == null) {
            return false;
        }
        augmentedImageDatabase = new AugmentedImageDatabase(session);
        augmentedImageDatabase.addImage(Constants.REF_IMG, bitmap);
        config.setAugmentedImageDatabase(augmentedImageDatabase);
        return true;
    }
    private Bitmap loadAugmentedImage() {
        try  {
            InputStream is = getAssets().open("world.jpg");
            return BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            Log.e("ImageLoad", "IO Exception", e);
        }
        return null;
    }

    public void addNodeToScene(ArFragment arFragment, Anchor anchor, Renderable renderable) {
        AnchorNode anchorNode = new AnchorNode(anchor);
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
        node.setRenderable(renderable);
        node.setParent(anchorNode);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
        node.select();
    }

    private Uri getARObject(String itemName){
        String arObjectName = "";
        if(itemName.equals("andy_dance")){
            arObjectName = "andy_dance.sfb";
        }else if(itemName.equals("Ring")){
            arObjectName = "Ring.sfb";
        }


        return Uri.parse(arObjectName);
    }
}