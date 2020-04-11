package com.mobile.info.ar_ecommerce_assignment2.sceneform;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.google.ar.sceneform.Camera;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.Sun;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
import com.mobile.info.ar_ecommerce_assignment2.Constants;
import com.mobile.info.ar_ecommerce_assignment2.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static android.widget.LinearLayout.*;

public class ARDropRefImgActivity extends ARDropActivity {

    ArFragment arFragment;
    boolean shouldAddModel = true;

    private String productName = "";
    private String productDesc = "";
    private int image_id = 0;

    private TransformableNode currentNode = null;
    private AnchorNode currentAnchorNode = null;

    private final ArrayList<String> itemName = new ArrayList<>(Arrays.asList("MICHAEL KORS", "MICHAEL KORS", "MICHAEL KORS", "MICHAEL KORS", "BOSS"));
    private final ArrayList<String> RingitemName = new ArrayList<>(Arrays.asList("Delicacy In White Gold (18k)", "Kew In White Gold (18k)", "Diamond Cluster Ring", "Classic Round Cut Sterling Silver Ring", "Classic Round Cut Sterling Silver Ring Set"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ardroprefimg);

        // get parameters
        shouldAddModel = true;
        Intent intent = getIntent();
        productName = intent.getStringExtra("title");
        productDesc = intent.getStringExtra("desc");
        image_id = intent.getIntExtra("image", 0);

        initializeGallery();

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
//                    if (Constants.LOADED_REF_IMAGE_0.equals(augmentedImage.getName()) ) {
                    if (Constants.LOADED_REF_IMAGE_0.equals(augmentedImage.getName())
                            || Constants.LOADED_REF_IMAGE_2.equals(augmentedImage.getName())
                            || Constants.LOADED_REF_IMAGE_3.equals(augmentedImage.getName())) {
                        if (shouldAddModel) {
                            Uri uri = getARObjectByProductName(productName);
                            placeObject(arFragment, augmentedImage.createAnchor(augmentedImage.getCenterPose()), uri);
                            shouldAddModel = false;
                        }
                    }
                }
            }

    }
    public boolean setupAugmentedImagesDb(Config config, Session session) {
        AugmentedImageDatabase augmentedImageDatabase;
//        Bitmap bitmap = loadAugmentedImage(Constants.LOADED_REF_IMAGE_0);
//        if (bitmap == null) {
//            return false;
//        }
        augmentedImageDatabase = new AugmentedImageDatabase(session);
        augmentedImageDatabase.addImage(Constants.LOADED_REF_IMAGE_0, loadAugmentedImage(Constants.LOADED_REF_IMAGE_0));
//        augmentedImageDatabase.addImage(Constants.LOADED_REF_IMAGE_1, loadAugmentedImage(Constants.LOADED_REF_IMAGE_1));
        augmentedImageDatabase.addImage(Constants.LOADED_REF_IMAGE_2, loadAugmentedImage(Constants.LOADED_REF_IMAGE_2));
        augmentedImageDatabase.addImage(Constants.LOADED_REF_IMAGE_3, loadAugmentedImage(Constants.LOADED_REF_IMAGE_3));
        config.setAugmentedImageDatabase(augmentedImageDatabase);
        return true;
    }
    private Bitmap loadAugmentedImage(String imageFile) {
        try  {
            InputStream is = getAssets().open(imageFile);
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

//        node.getScaleController().setMaxScale(0.1f);
//        node.getScaleController().setMinScale(0.08f);

//        node.setLocalScale(getScaleByJewellCategory(jewellCategory));

        setNodeScaleByProductName(node, productName);

        node.setParent(anchorNode);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
        node.select();

        this.currentNode = node;
        this.currentAnchorNode = anchorNode;
    }

    private void initializeGallery() {
        LinearLayout gallery = findViewById(R.id.gallery_layout_refimg);

        ImageView iv0 = findViewById(R.id.iv0);
        ImageView iv1 = findViewById(R.id.iv1);
        ImageView iv2 = findViewById(R.id.iv2);
        ImageView iv3 = findViewById(R.id.iv3);
        ImageView iv4 = findViewById(R.id.iv4);

        Drawable highlight = getResources().getDrawable( R.drawable.highlight);

        if(itemName.contains(productName)){
            iv0.setImageResource(R.drawable.w1);
            iv1.setImageResource(R.drawable.w2);
            iv2.setImageResource(R.drawable.w3);
            iv3.setImageResource(R.drawable.w4);
            iv4.setImageResource(R.drawable.w5);

            if(image_id == R.drawable.w1){
                iv0.setBackground(highlight);
            }else if(image_id == R.drawable.w2){
                iv1.setBackground(highlight);
            }else if(image_id == R.drawable.w3){
                iv2.setBackground(highlight);
            }else if(image_id == R.drawable.w4){
                iv3.setBackground(highlight);
            }else if(image_id == R.drawable.w5){
                iv4.setBackground(highlight);
            }
        }else if(RingitemName.contains(productName)){
            iv0.setImageResource(R.drawable.ring1);
            iv1.setImageResource(R.drawable.ring2);
            iv2.setImageResource(R.drawable.ring3);
            iv3.setImageResource(R.drawable.ring4);
            iv4.setImageResource(R.drawable.ring5);

            if(image_id == R.drawable.ring1){
                iv0.setBackground(highlight);
            }else if(image_id == R.drawable.ring2){
                iv1.setBackground(highlight);
            }else if(image_id == R.drawable.ring3){
                iv2.setBackground(highlight);
            }else if(image_id == R.drawable.ring4){
                iv3.setBackground(highlight);
            }else if(image_id == R.drawable.ring5){
                iv4.setBackground(highlight);
            }
        }


//        ImageView iv0 = new ImageView(this);
//        iv0.setImageResource(R.drawable.w1);
//        iv0.setContentDescription("watch 1");
//        iv0.setOnClickListener(view -> {
////            arFragment.getArSceneView().getScene().removeChild(this.currentNode);
////            if (this.currentAnchorNode != null) {
////                this.currentAnchorNode.getAnchor().detach();
////            }
////            this.currentAnchorNode = null;
////            this.currentNode.setParent(null);
////            this.currentNode = null;
//            onClear();
//
//            shouldAddModel = true;
//        });
//        gallery.addView(iv0);
//
//        ImageView iv1 = new ImageView(this);
//        iv1.setImageResource(R.drawable.w2);
//        iv1.setContentDescription("watch 1");
//        iv1.setOnClickListener(view -> {
////            addObject(getARObjectByJewellCategory(jewellCategory));
//        });
//        gallery.addView(iv1);
//
//        ImageView iv2 = new ImageView(this);
//        iv2.setImageResource(R.drawable.w3);
//        iv2.setContentDescription("watch 3");
//        iv2.setOnClickListener(view -> {
////            addObject(getARObjectByJewellCategory(jewellCategory));
//        });
//        gallery.addView(iv2);
//
//        ImageView iv3 = new ImageView(this);
//        iv3.setImageResource(R.drawable.w4);
//        iv3.setContentDescription("watch 4");
//        iv3.setOnClickListener(view -> {
////            addObject(getARObjectByJewellCategory(jewellCategory));
//        });
//        gallery.addView(iv3);
//
//        ImageView iv4 = new ImageView(this);
//        iv4.setImageResource(R.drawable.w5);
//        iv4.setContentDescription("watch 5");
//        iv4.setOnClickListener(view -> {
////            addObject(getARObjectByJewellCategory(jewellCategory));
//        });
//        gallery.addView(iv4);
    }

    private void onClear() {
        List<Node> children = new ArrayList<>(arFragment.getArSceneView().getScene().getChildren());
        for (Node node : children) {
            if (node instanceof AnchorNode) {
                if (((AnchorNode) node).getAnchor() != null) {
                    ((AnchorNode) node).getAnchor().detach();
                }
            }
            if (!(node instanceof Camera) && !(node instanceof Sun)) {
                node.setParent(null);
            }
        }
    }
}