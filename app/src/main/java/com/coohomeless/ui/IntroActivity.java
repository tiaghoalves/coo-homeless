package com.coohomeless.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.coohomeless.R;
import com.coohomeless.models.user.UserModel;
import com.coohomeless.ui.fragments.slides.SlideUserTypeFragment;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntro2Fragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class IntroActivity extends AppIntro {

    private UserModel userModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
//        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
//        String userJSON = sharedPref.getString("userJSON", "");
//        this.userModel = gson.fromJson(userJSON, UserModel.class);

        addSlide(AppIntro2Fragment.newInstance("Bem vindo",
                "Estamos feliz com você aqui e ansiosos em ver sua colaboração na plataforma Coo Homeless.",
                R.drawable.logo_only,
                ContextCompat.getColor(this, R.color.windowBackground),
                ContextCompat.getColor(this, R.color.colorSecundaryPink),
                ContextCompat.getColor(this, R.color.colorSecundaryPink)));
        addSlide(SlideUserTypeFragment.newInstance());
        addSlide(AppIntro2Fragment.newInstance("Obrigado !",
                "Muito bem! Agora temos todos os dados que precisamos, faça agora sua contribuição.",
                R.drawable.logo_only,
                ContextCompat.getColor(this, R.color.windowBackground),
                ContextCompat.getColor(this, R.color.colorSecundaryPink),
                ContextCompat.getColor(this, R.color.colorSecundaryPink)));

        showSkipButton(false);
        setFlowAnimation();

        setBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        setSeparatorColor(ContextCompat.getColor(this, R.color.colorPrimary));
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent toMenu = new Intent(IntroActivity.this, MenuActivity.class);
        startActivity(toMenu);
        setSlideOverAnimation();
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
    }

}
