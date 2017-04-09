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

    public static final int MESSAGE_TYPE_CONTROLBUTTONS = 1;
    public static final String MESSAGE_DIAGONAL_FLIP = "DIAGONAL";
    public static final String MESSAGE_ROW = "ROW";
    public static final String MESSAGE_COL = "COL";
    private static final String MESSAGE_FIELD_ROW_OR_COL = "rowOrCol";
    private static final String MESSAGE_FIELD_NUM_ROW_OR_COL = "numRowOrCol";

    private Button diagonalFlipButton;
    private Button firstRowButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.button_control, container, false);
        diagonalFlipButton = ButtonWithClickListener((Button) view.findViewById(R.id.button_diagonal_flip),
                MESSAGE_DIAGONAL_FLIP, 0);
        firstRowButton = ButtonWithClickListener((Button) view.findViewById(R.id.rowBtn1),
                MESSAGE_ROW, 0);
        return view;
    }

    private Button ButtonWithClickListener(Button button, final String rowOrCol, final int numOfRowOrCol) {
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                StarcastApplication.getInstance().getSendMessageHandler().enqueueMessage(
                        MESSAGE_TYPE_CONTROLBUTTONS,
                        createControlButtonsMessage(rowOrCol, numOfRowOrCol));
            }

        });
        return button;
    }

    public static JSONObject createControlButtonsMessage(String rowOrCol, int numOfRowOrCol) {
        JSONObject controlButtonsMessage = new JSONObject();
        try {
            controlButtonsMessage.put(MESSAGE_FIELD_ROW_OR_COL, rowOrCol);
            controlButtonsMessage.put(MESSAGE_FIELD_NUM_ROW_OR_COL, numOfRowOrCol);
        } catch (JSONException e) {
            Log.e(TAG, "Error creating JSON direction message", e);
        }
        Log.i(TAG, "JSON message"  + controlButtonsMessage.toString());
        return controlButtonsMessage;
    }
}
