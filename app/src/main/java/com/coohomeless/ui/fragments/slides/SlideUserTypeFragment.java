package com.coohomeless.ui.fragments.slides;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.coohomeless.models.contributor.ContributorModel;
import com.coohomeless.models.organization.OrganizationModel;
import com.coohomeless.repository.ContributorRepository;
import com.coohomeless.repository.OrganizationRepository;
import com.coohomeless.ui.auth.BaseAuth;
import com.google.common.collect.ImmutableMap;
import com.google.firebase.auth.FirebaseUser;
import com.strongloop.android.loopback.RestAdapter;
import com.strongloop.android.loopback.callbacks.VoidCallback;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SlideUserTypeFragment extends Fragment implements View.OnClickListener {

    private RestAdapter adapter;
    private String userName;
    private String email;

    @BindView(R.id.img_ong) ImageView imgONG;
    @BindView(R.id.img_contributor) ImageView imgContributor;

    public static SlideUserTypeFragment newInstance() {
        return new SlideUserTypeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. Grab the shared RestAdapter instance.
        ApiAdapter apiAdapter = (ApiAdapter) getActivity().getApplication();
        this.adapter = apiAdapter.getApiAdapter();

    }

    @Override
    public void onStart() {
        super.onStart();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        this.userName = preferences.getString("userName", null);
        this.email = preferences.getString("email", null);

        Toast.makeText(getActivity(), "userName => " + this.userName, Toast.LENGTH_LONG).show();
        Toast.makeText(getActivity(), "email => " + this.email, Toast.LENGTH_LONG).show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.slide_user_type, container, false);
        ButterKnife.bind(this, v);

        imgONG.setOnClickListener(this);
        imgContributor.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.img_ong) {
            Toast.makeText(getActivity(), "<--- ONG --->", Toast.LENGTH_SHORT).show();
            saveOrganization();
        } else if (id == R.id.img_contributor) {
            Toast.makeText(getActivity(), "<--- CONTRIBUTOR --->", Toast.LENGTH_SHORT).show();
            saveContributor();
        }
    }

    private void saveContributor() {
        ContributorRepository contributorRepo = this.adapter.createRepository(ContributorRepository.class);
        ContributorModel contributor = contributorRepo.createObject(ImmutableMap.of("name", "Contributor"));

        FirebaseUser authUser = BaseAuth.getAuthUser();

        if (authUser != null && authUser.getDisplayName() != null) {
            contributor.setName(authUser.getDisplayName());
            contributor.setEmail(authUser.getEmail());
        } else {
            contributor.setName(this.userName);
            contributor.setEmail(this.email);
        }

        contributor.save(new VoidCallback() {
            @Override
            public void onSuccess() {
                Toast.makeText(getActivity(), "SALVOOOOO !", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable t) {
                Log.e("SampleSlide", "Deu ruuim no Contributor.save.", t);
                Toast.makeText(getActivity(), "Deu ruuim Contributor model.", Toast.LENGTH_LONG).show();
            }
        });

        Toast.makeText(getActivity(), "Salvo:ONG= "+ contributor.toString(), Toast.LENGTH_SHORT).show();
    }

    private void saveOrganization() {
        OrganizationRepository ongRepo = this.adapter.createRepository(OrganizationRepository.class);
        OrganizationModel organization = ongRepo.createObject(ImmutableMap.of("name", "Organization"));

        FirebaseUser authUser = BaseAuth.getAuthUser();

        if (authUser != null && authUser.getDisplayName() != null) {
            organization.setName(authUser.getDisplayName());
            organization.setDescOrganization(authUser.getDisplayName());
            organization.setEmail(authUser.getEmail());
        } else {
            organization.setName(this.userName);
            organization.setDescOrganization(this.userName);
            organization.setEmail(this.email);
        }

        organization.save(new VoidCallback() {
            @Override
            public void onSuccess() {
                Toast.makeText(getActivity(), "SALVOOOOO !", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable t) {
                Log.e("SampleSlide", "Deu ruuim pra salva ONG model.", t);
                Toast.makeText(getActivity(), "Deu ruuim no ONG.save", Toast.LENGTH_SHORT).show();
            }
        });

        Toast.makeText(getActivity(), "Salvo:ONG= "+ organization.toString(), Toast.LENGTH_SHORT).show();
    }

}
