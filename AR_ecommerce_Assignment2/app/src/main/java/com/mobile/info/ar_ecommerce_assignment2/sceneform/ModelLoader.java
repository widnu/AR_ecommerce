package com.mobile.info.ar_ecommerce_assignment2.sceneform;


import android.net.Uri;
import android.util.Log;

import com.google.ar.core.Anchor;

import com.google.ar.sceneform.rendering.ModelRenderable;


import java.lang.ref.WeakReference;

public class ModelLoader {
    private final WeakReference<ARDropObjectActivity> owner;
    private static final String TAG = "ModelLoader";

    ModelLoader(WeakReference<ARDropObjectActivity> owner) {
        this.owner = owner;
    }

    void loadModel(Anchor anchor, Uri uri) {
        if (owner.get() == null) {
            Log.d(TAG, "Activity is null.  Cannot load model.");
            return;
        }
        ModelRenderable.builder()
                .setSource(owner.get(), uri)
                .build()
                .handle((renderable, throwable) -> {
                    ARDropObjectActivity activity = owner.get();
                    if (activity == null) {
                        return null;
                    } else if (throwable != null) {
                        activity.onException(throwable);
                    } else {
                        activity.addNodeToScene(anchor, renderable);
                    }
                    return null;
                });

        return;
    }


}
