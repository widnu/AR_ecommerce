package com.mobile.info.ar_ecommerce_assignment2.sceneform;

import android.util.Log;

import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.core.Config;
import com.google.ar.core.Session;

public class CustomArFragment extends ArFragment {

    @Override
    protected Config getSessionConfiguration(Session session) {
        getPlaneDiscoveryController().setInstructionView(null);
        Config config = new Config(session);
        config.setUpdateMode(Config.UpdateMode.LATEST_CAMERA_IMAGE);
        config.setFocusMode(Config.FocusMode.AUTO);
        session.configure(config);
        getArSceneView().setupSession(session);

        if ((((ARDropRefImgActivity) getActivity()).setupAugmentedImagesDb(config, session))) {
            Log.d("SetupAugImgDb", "Success");
        } else {
            Log.e("SetupAugImgDb","Faliure setting up db");
        }

        return config;
    }

}
