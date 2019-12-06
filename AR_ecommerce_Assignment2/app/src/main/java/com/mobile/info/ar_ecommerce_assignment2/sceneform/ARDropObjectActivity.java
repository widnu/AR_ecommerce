package com.mobile.info.ar_ecommerce_assignment2.sceneform;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.ar.core.Anchor;
import com.google.ar.core.Frame;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.core.Trackable;
import com.google.ar.core.TrackingState;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
import com.mobile.info.ar_ecommerce_assignment2.R;

import java.lang.ref.WeakReference;
import java.util.List;

public class ARDropObjectActivity extends ARDropActivity {
    private ArFragment fragment;
    private PointerDrawable pointer = new PointerDrawable();
    private boolean isTracking;
    private boolean isHitting;
    private ModelLoader modelLoader;
    ArFragment arFragment;
    boolean shouldAddModel = true;

    private String jewellCategory = "";
    private String productName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ardropobject);

        Intent intent = getIntent();
        jewellCategory = intent.getStringExtra("JEWELL_CATEGORY");
        productName = intent.getStringExtra("PRODUCT_NAME");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        arAndroidOnTheFloor();

    }

    private void arAndroidOnTheFloor() {
        fragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.sceneform_fragment);
        fragment.getArSceneView().getScene().addOnUpdateListener(frameTime -> {
            fragment.onUpdate(frameTime);
            onUpdate();
        });
        modelLoader = new ModelLoader(new WeakReference<>(this));
        initializeGallery();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {/* Inflate the menu; this adds items to the action bar if it is present.*/
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {/* Handle action bar item clicks here. The action bar will automatically handle clicks on the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.*/
        int id = item.getItemId();/*noinspection SimplifiableIfStatement*/
        if (id == R.id.action_settings) return true;
        return super.onOptionsItemSelected(item);
    }

    private void onUpdate() {
        boolean trackingChanged = updateTracking();
        View contentView = findViewById(android.R.id.content);
        if (trackingChanged) {
            if (isTracking) contentView.getOverlay().add(pointer);
            else contentView.getOverlay().remove(pointer);
            contentView.invalidate();
        }
        if (isTracking) {
            boolean hitTestChanged = updateHitTest();
            if (hitTestChanged) {
                pointer.setEnabled(isHitting);
                contentView.invalidate();
            }
        }
    }

    private boolean updateTracking() {
        Frame frame = fragment.getArSceneView().getArFrame();
        boolean wasTracking = isTracking;
        isTracking = frame != null && frame.getCamera().getTrackingState() == TrackingState.TRACKING;
        return isTracking != wasTracking;
    }

    private boolean updateHitTest() {
        Frame frame = fragment.getArSceneView().getArFrame();
        android.graphics.Point pt = getScreenCenter();
        List<HitResult> hits;
        boolean wasHitting = isHitting;
        isHitting = false;
        if (frame != null) {
            hits = frame.hitTest(pt.x, pt.y);
            for (HitResult hit : hits) {
                Trackable trackable = hit.getTrackable();
                if (trackable instanceof Plane && ((Plane) trackable).isPoseInPolygon(hit.getHitPose())) {
                    isHitting = true;
                    break;
                }
            }
        }
        return wasHitting != isHitting;
    }

    private android.graphics.Point getScreenCenter() {
        View vw = findViewById(android.R.id.content);
        return new android.graphics.Point(vw.getWidth() / 2, vw.getHeight() / 2);
    }

    private void initializeGallery() {
        LinearLayout gallery = findViewById(R.id.gallery_layout);

        ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.w1);
        iv.setContentDescription("watch 1");
        iv.setOnClickListener(view -> {
            addObject(getARObjectByJewellCategory(jewellCategory));
        });
        gallery.addView(iv);

        iv = new ImageView(this);
        iv.setImageResource(R.drawable.w2);
        iv.setContentDescription("watch 1");
        iv.setOnClickListener(view -> {
            addObject(getARObjectByJewellCategory(jewellCategory));
        });
        gallery.addView(iv);

        iv = new ImageView(this);
        iv.setImageResource(R.drawable.w3);
        iv.setContentDescription("watch 3");
        iv.setOnClickListener(view -> {
            addObject(getARObjectByJewellCategory(jewellCategory));
        });
        gallery.addView(iv);
    }

    private void addObject(Uri model) {
        Frame frame = fragment.getArSceneView().getArFrame();
        android.graphics.Point pt = getScreenCenter();
        List<HitResult> hits;
        if (frame != null) {
            hits = frame.hitTest(pt.x, pt.y);
            for (HitResult hit : hits) {
                Trackable trackable = hit.getTrackable();
                if (trackable instanceof Plane && ((Plane) trackable).isPoseInPolygon(hit.getHitPose())) {
                    modelLoader.loadModel(hit.createAnchor(), model);
                    break;
                }
            }
        }
    }

    public void addNodeToScene(Anchor anchor, ModelRenderable renderable) {
        AnchorNode anchorNode = new AnchorNode(anchor);
        TransformableNode node = new TransformableNode(fragment.getTransformationSystem());
        node.setRenderable(renderable);
        node.setParent(anchorNode);
        fragment.getArSceneView().getScene().addChild(anchorNode);
        node.select();/*        startAnimation(node, renderable);*/
    }

    public void onException(Throwable throwable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setMessage(throwable.getMessage()).setTitle("Codelab error!");
        AlertDialog dialog = builder.create();
        dialog.show();
        return;
    }

    /*    public void startAnimation(TransformableNode node, ModelRenderable renderable){ if(renderable==null || renderable.getAnimationDataCount() == 0) { return; } for(int i = 0;i < renderable.getAnimationDataCount();i++){ AnimationData animationData = renderable.getAnimationData(i); } ModelAnimator animator = new ModelAnimator(renderable.getAnimationData(0), renderable); animator.start(); node.setOnTapListener( (hitTestResult, motionEvent) -> { togglePauseAndResume(animator); }); } public void togglePauseAndResume(ModelAnimator animator) { if (animator.isPaused()) { animator.resume(); } else if (animator.isStarted()) { animator.pause(); } else { animator.start(); } }*/




}
