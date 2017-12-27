package com.undabot.babic.app.injection.fragment;

import com.undabot.babic.app.injection.activity.ActivityComponent;
import com.undabot.babic.app.injection.fragment.module.FragmentPresenterModule;
import com.undabot.babic.app.injection.scope.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(
        dependencies = {
                ActivityComponent.class
        },
        modules = {
                FragmentPresenterModule.class
        }
)
public interface FragmentComponent extends FragmentComponentInjects,
                                           FragmentComponentExposes { }
