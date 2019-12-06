package com.mobile.info.ar_ecommerce_assignment2.sceneform;

import android.net.Uri;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.ux.TransformableNode;
import com.mobile.info.ar_ecommerce_assignment2.Constants;
import com.mobile.info.ar_ecommerce_assignment2.R;

public abstract class ARDropActivity extends AppCompatActivity{

    protected Uri getARObjectByJewellCategory(String jewellCategory){
        String arObjectName = "";
        if(jewellCategory.equals("Watch")){
            arObjectName = "model.sfb";
        }else if(jewellCategory.equals("Ring")){
            arObjectName = "ring pop.sfb";
        }

        return Uri.parse(arObjectName);
    }

    protected Uri getARObjectByProductName(String productName) {
        String arObjectName = "";

        if (productName.equals(Constants.WATCH_DETAIL_0)) {
            arObjectName = "watch_0.sfb";
        }else if (productName.equals(Constants.WATCH_DETAIL_1)) {
            arObjectName = "1345 Analog Clock.sfb";
        }

//        else if (productName.equals("ring_0")) {
//            arObjectName = "ring_0.sfb";
//        }

        return Uri.parse(arObjectName);
    }

    protected Vector3 getScaleByJewellCategory(String jewellCategory){
        if(jewellCategory.equals("Watch")){
            return new Vector3(0.05f, 0.05f, 0.05f);
        }else if(jewellCategory.equals("Ring")){
            return new Vector3(0.25f, 0.25f, 0.25f);
        }else{
            return new Vector3(0.25f, 0.25f, 0.25f);
        }
    }

    protected void setNodeScaleByJewellCategory(TransformableNode node, String jewellCategory){
        if(jewellCategory.equals("Watch")){
            node.getScaleController().setMaxScale(0.15f);
            node.getScaleController().setMinScale(0.1f);
        }else if(jewellCategory.equals("Ring")){
            node.getScaleController().setMaxScale(0.03f);
            node.getScaleController().setMinScale(0.01f);
        }
    }

    protected void setNodeScaleByProductName(TransformableNode node, String productName){
        if(productName.equals("Watch")){
            node.getScaleController().setMaxScale(0.15f);
            node.getScaleController().setMinScale(0.1f);
        }else if(productName.equals("Ring")){
            node.getScaleController().setMaxScale(0.03f);
            node.getScaleController().setMinScale(0.01f);
        }
    }


}
