package com.coohomeless.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.coohomeless.R;
import com.coohomeless.models.user.UserModel;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntro2Fragment;

public class IntroActivity extends AppIntro {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntro2Fragment.newInstance("Bem vindo",
                "Estamos feliz com você aqui e ansiosos em ver sua colaboração na plataforma Coo Homeless.",
                R.drawable.doormat,
                ContextCompat.getColor(this, R.color.windowBackground),
                ContextCompat.getColor(this, R.color.colorSecundaryPink),
                ContextCompat.getColor(this, R.color.colorSecundaryPink)));
        addSlide(AppIntro2Fragment.newInstance("Obrigado !",
                "Agora temos todos os dados que precisamos, faça agora sua contribuição.",
                R.drawable.ic_contributor,
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
