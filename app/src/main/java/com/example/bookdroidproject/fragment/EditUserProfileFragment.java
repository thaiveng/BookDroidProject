package com.example.bookdroidproject.fragment;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.asksira.bsimagepicker.BSImagePicker;
import com.bumptech.glide.Glide;
import com.example.bookdroidproject.R;

import java.io.File;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditUserProfileFragment extends Fragment implements BSImagePicker.OnSingleImageSelectedListener,
        BSImagePicker.OnMultiImageSelectedListener, BSImagePicker.ImageLoaderDelegate{


    TextView tvCancel;
    TextView tvDone;

    CircleImageView imageView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_profile_fragment, container, false);

        tvCancel = view.findViewById(R.id.tv_cancel_ScreenEditProfile);
        tvDone = view.findViewById(R.id.tv_done_ScreenEditProfile);



        // set action when click on cancel
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadFragment(new UserProfileFragment());
            }
        });



        imageView = view.findViewById(R.id.img_user_edit);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BSImagePicker pickerDialog = new BSImagePicker.Builder("com.asksira.imagepickersheetdemo.fileprovider")
                        .build();
                pickerDialog.show(getChildFragmentManager(), "picker");
            }
        });
        return view;
    }
    private void loadFragment(Fragment fragment){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction tf = fm.beginTransaction();
        tf.replace(R.id.container_home,fragment);
        tf.commit();
    }

    @Override
    public void loadImage(File imageFile, ImageView ivImage) {
        Glide.with(getActivity()).load(imageFile).into(ivImage);
    }

    @Override
    public void onMultiImageSelected(List<Uri> uriList, String tag) {

    }

    @Override
    public void onSingleImageSelected(Uri uri, String tag) {
        Glide.with(getActivity()).load(uri).into(imageView);
    }
}
