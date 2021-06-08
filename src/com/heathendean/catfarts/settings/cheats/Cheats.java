package com.heathendean.catfarts.settings.cheats;


import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.heathendean.catfarts.R;
import com.heathendean.catfarts.settings.Settings;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

public class Cheats extends AppCompatActivity {

    private static View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = this.getWindow().getDecorView().findViewById(android.R.id.content);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
        }
        setContentView(R.layout.cheats_activity);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.cheats_fragment, container, false);
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            getActivity().findViewById(R.id.testMale01).setOnTouchListener(new MyTouchListener());
            getActivity().findViewById(R.id.testMale02).setOnTouchListener(new MyTouchListener());
            getActivity().findViewById(R.id.testMale03).setOnTouchListener(new MyTouchListener());
            getActivity().findViewById(R.id.testMale04).setOnTouchListener(new MyTouchListener());
            getActivity().findViewById(R.id.testFemale01).setOnDragListener(new MyDragListener());
            getActivity().findViewById(R.id.testFemale02).setOnDragListener(new MyDragListener());
            getActivity().findViewById(R.id.testFemale03).setOnDragListener(new MyDragListener());
            getActivity().findViewById(R.id.testFemale04).setOnDragListener(new MyDragListener());
            getActivity().findViewById(R.id.testFemale05).setOnDragListener(new MyDragListener());
            getActivity().findViewById(R.id.testFemale06).setOnDragListener(new MyDragListener());
            getActivity().findViewById(R.id.testFemale07).setOnDragListener(new MyDragListener());
            getActivity().findViewById(R.id.testFemale08).setOnDragListener(new MyDragListener());
        }
    }


    private static final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                String viewId = String.valueOf(view.getId());
                ClipData data = ClipData.newPlainText("id", viewId);
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                return true;
            } else {
                return false;
            }
        }
    }

    static class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View femaleSnapBox, DragEvent maleSnapBox){
            switch (maleSnapBox.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    Log.d("Cheats.java","Male is being dragged.");
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    Log.d("Cheats.java","Male is in female space.");
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    Log.d("Cheats.java","Male is out of female space.");
                    break;
                case DragEvent.ACTION_DROP:
                    Log.d("Cheats.java","Male was dropped into female.");
                    ClipData cd = maleSnapBox.getClipData();
                    Log.d("Cheats.java", "Dragged ID: " + cd.getItemAt(0).getText().toString());
                    int draggedComponentID = Integer.parseInt(cd.getItemAt(0).getText().toString());
                    View draggedView = rootView.findViewById(draggedComponentID);
                    femaleSnapBox.setBackground(draggedView.getBackground());
                    femaleSnapBox.setContentDescription(draggedView.getContentDescription());
                    femaleSnapBox.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    Log.d("Cheats.java","Male is no longer being dragged.");
                default:
                    break;
            }
            return true;
        }
    }

    public void clear_females(){
        Drawable blank_background = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_white_female, null);
        rootView.findViewById(R.id.testFemale01).setBackground(blank_background);
        rootView.findViewById(R.id.testFemale02).setBackground(blank_background);
        rootView.findViewById(R.id.testFemale03).setBackground(blank_background);
        rootView.findViewById(R.id.testFemale04).setBackground(blank_background);
        rootView.findViewById(R.id.testFemale05).setBackground(blank_background);
        rootView.findViewById(R.id.testFemale06).setBackground(blank_background);
        rootView.findViewById(R.id.testFemale07).setBackground(blank_background);
        rootView.findViewById(R.id.testFemale08).setBackground(blank_background);
    }

    public void check_cheatcode(View view){
        Log.d("Cheats.java","Testing Cheatcode...");
        List<Integer> cheatcode = new ArrayList<>();
        clear_females();
        cheatcode.add(Integer.parseInt(rootView.findViewById(R.id.testFemale01).getContentDescription().toString()));
        cheatcode.add(Integer.parseInt(rootView.findViewById(R.id.testFemale02).getContentDescription().toString()));
        cheatcode.add(Integer.parseInt(rootView.findViewById(R.id.testFemale03).getContentDescription().toString()));
        cheatcode.add(Integer.parseInt(rootView.findViewById(R.id.testFemale04).getContentDescription().toString()));
        cheatcode.add(Integer.parseInt(rootView.findViewById(R.id.testFemale05).getContentDescription().toString()));
        cheatcode.add(Integer.parseInt(rootView.findViewById(R.id.testFemale06).getContentDescription().toString()));
        cheatcode.add(Integer.parseInt(rootView.findViewById(R.id.testFemale07).getContentDescription().toString()));
        cheatcode.add(Integer.parseInt(rootView.findViewById(R.id.testFemale08).getContentDescription().toString()));
        Log.d("Cheats.java", cheatcode.toString());
    }

    public void back_to_settings(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

}