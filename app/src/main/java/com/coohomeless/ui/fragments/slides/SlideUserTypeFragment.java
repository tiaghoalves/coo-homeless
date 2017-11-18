package com.coohomeless.ui.fragments.slides;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.coohomeless.R;
import com.coohomeless.adapters.ApiAdapter;
import com.coohomeless.models.user.UserModel;
import com.coohomeless.repository.UserRepository;
import com.strongloop.android.loopback.RestAdapter;
import com.strongloop.android.loopback.callbacks.VoidCallback;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SlideUserTypeFragment extends Fragment implements View.OnClickListener {
    private RestAdapter adapter;
//    private UserRepository userRepository;
    private UserModel userModel;

    @BindView(R.id.img_ong) ImageView imgONG;
    @BindView(R.id.img_contributor) ImageView imgContributor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.slide_user_type, container, false);
        ButterKnife.bind(this, v);

        // 1. Grab the shared RestAdapter instance.
        ApiAdapter apiAdapter = (ApiAdapter) getActivity().getApplication();
        this.adapter = apiAdapter.getApiAdapter();
//        this.userRepository = this.adapter.createRepository(UserRepository.class);
        this.userModel = (UserModel) getActivity().getIntent().getExtras().getSerializable("userModel");

        imgONG.setOnClickListener(this);
        imgContributor.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.img_ong) {
            this.userModel.setUserType(1);
        } else if (id == R.id.img_contributor) {
            this.userModel.setUserType(0);
        }

        this.userModel.save(new VoidCallback() {
            @Override
            public void onSuccess() {
                Toast.makeText(getActivity(), "SAVED !", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable t) {
                Log.e("SampleSlide", "Cannot save Note model.", t);
                Toast.makeText(getActivity(), "Cannot save User model.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
