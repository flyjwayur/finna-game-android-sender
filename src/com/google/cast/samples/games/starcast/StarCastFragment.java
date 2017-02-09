// Copyright 2015 Google Inc. All Rights Reserved.
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
//     http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.google.cast.samples.games.starcast;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * The main activity for StarCast game.
 */
public class StarCastFragment extends Fragment {
    private static final String TAG = "StarCastFragment";

    public static final int MESSAGE_TYPE_DIRECTION = 1;
    public static final String MESSAGE_UP = "UP";
    public static final String MESSAGE_DOWN = "DOWN";
    public static final String MESSAGE_LEFT = "LEFT";
    public static final String MESSAGE_RIGHT = "RIGHT";
    private static final String MESSAGE_FIELD_DIRECTION = "direction";

    private Button upButton;
    private Button downButton;
    private Button leftButton;
    private Button rightButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.starcast_main, container, false);

        upButton = ButtonWithClickListener((Button) view.findViewById(R.id.button_up), MESSAGE_UP);
        downButton = ButtonWithClickListener((Button) view.findViewById(R.id.button_down), MESSAGE_DOWN);
        leftButton = ButtonWithClickListener((Button) view.findViewById(R.id.button_left), MESSAGE_LEFT);
        rightButton = ButtonWithClickListener((Button) view.findViewById(R.id.button_right), MESSAGE_RIGHT);

        return view;
    }

    private Button ButtonWithClickListener(Button button, final String direction) {
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                StarcastApplication.getInstance().getSendMessageHandler().enqueueMessage(
                        MESSAGE_TYPE_DIRECTION, createDirectionMessage(direction));
            }

        });
        return button;
    }

    public static JSONObject createDirectionMessage(String direction) {
        JSONObject directionMessage = new JSONObject();
        try {
            directionMessage.put(MESSAGE_FIELD_DIRECTION, direction);
        } catch (JSONException e) {
            Log.e(TAG, "Error creating JSON direction message", e);
        }
        return directionMessage;
    }
}

