package com.undabot.babic.app.injection.fragment;

import com.undabot.babic.app.injection.activity.ActivityComponentExposes;
import com.undabot.babic.app.injection.fragment.module.FragmentPresenterModule;

public interface FragmentComponentExposes extends ActivityComponentExposes,
                                                  FragmentPresenterModule.Exposes { }
