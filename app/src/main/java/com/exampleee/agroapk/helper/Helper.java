package com.exampleee.agroapk.helper;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.exampleee.agroapk.R;

public class Helper {

    public static boolean fromNotification = false;

    public static void setTool(String title, Activity activity) {

        TextView txtTitle = activity.findViewById(R.id.tool_title);
        txtTitle.setText(title);

        ImageView toolBack = activity.findViewById(R.id.tool_back);
        toolBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }
}
