/*
 * Copyright (C) 2019 The ConquerOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.conquer.settings.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.Preference.OnPreferenceChangeListener;
import androidx.preference.SwitchPreference;
import android.provider.Settings;

import com.android.internal.logging.nano.MetricsProto; 
import com.android.settings.SettingsPreferenceFragment;

import com.conquer.settings.R;

import com.conquer.settings.preferences.CustomSeekBarPreference;

public class Lockscreen extends SettingsPreferenceFragment {

    public static final String TAG = "Lockscreen";

    private static final String CLOCK_FONT_SIZE  = "lockclock_font_size";
    private static final String DATE_FONT_SIZE  = "lockdate_font_size";
    private static final String LOCK_CLOCK_FONTS = "lock_clock_fonts";
    private static final String LOCK_DATE_FONTS = "lock_date_fonts";
    private static final String LOCK_OWNERINFO_FONTS = "lock_ownerinfo_fonts";
    private static final String LOCKOWNER_FONT_SIZE = "lockowner_font_size";

    private CustomSeekBarPreference mClockFontSize;
    private CustomSeekBarPreference mDateFontSize;
    private ListPreference mLockClockFonts;
    private ListPreference mLockDateFonts;
    private CustomSeekBarPreference mOwnerInfoFontSize;
    private ListPreference mLockOwnerInfoFonts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.conquer_settings_lockscreen);

        // Lockscren Clock Fonts
        mLockClockFonts = (ListPreference) findPreference(LOCK_CLOCK_FONTS);
        mLockClockFonts.setValue(String.valueOf(Settings.System.getInt(
                getContentResolver(), Settings.System.LOCK_CLOCK_FONTS, 28)));
        mLockClockFonts.setSummary(mLockClockFonts.getEntry());
        mLockClockFonts.setOnPreferenceChangeListener(this);

        // Lock Clock Size
        mClockFontSize = (CustomSeekBarPreference) findPreference(CLOCK_FONT_SIZE);
        mClockFontSize.setValue(Settings.System.getInt(getContentResolver(),
                Settings.System.LOCKCLOCK_FONT_SIZE, 54));
        mClockFontSize.setOnPreferenceChangeListener(this);

        // Lockscren Date Fonts
        mLockDateFonts = (ListPreference) findPreference(LOCK_DATE_FONTS);
        mLockDateFonts.setValue(String.valueOf(Settings.System.getInt(
                getContentResolver(), Settings.System.LOCK_DATE_FONTS, 28)));
        mLockDateFonts.setSummary(mLockDateFonts.getEntry());
        mLockDateFonts.setOnPreferenceChangeListener(this);

        // Lock Date Size
        mDateFontSize = (CustomSeekBarPreference) findPreference(DATE_FONT_SIZE);
        mDateFontSize.setValue(Settings.System.getInt(getContentResolver(),
                Settings.System.LOCKDATE_FONT_SIZE, 18));
        mDateFontSize.setOnPreferenceChangeListener(this);

        // Lockscreen Owner Info Fonts
        mLockOwnerInfoFonts = (ListPreference) findPreference(LOCK_OWNERINFO_FONTS);
        mLockOwnerInfoFonts.setValue(String.valueOf(Settings.System.getInt(
                getContentResolver(), Settings.System.LOCK_OWNERINFO_FONTS, 28)));
        mLockOwnerInfoFonts.setSummary(mLockOwnerInfoFonts.getEntry());
        mLockOwnerInfoFonts.setOnPreferenceChangeListener(this);

        // Lockscreen Owner Info Size
        mOwnerInfoFontSize = (CustomSeekBarPreference) findPreference(LOCKOWNER_FONT_SIZE);
        mOwnerInfoFontSize.setValue(Settings.System.getInt(getContentResolver(),
                Settings.System.LOCKOWNER_FONT_SIZE, 18));
        mOwnerInfoFontSize.setOnPreferenceChangeListener(this);
    }

    public boolean onPreferenceChange(Preference preference, Object newValue) {
        ContentResolver resolver = getActivity().getContentResolver();
        if (preference == mLockClockFonts) {
            Settings.System.putInt(getContentResolver(), Settings.System.LOCK_CLOCK_FONTS,
                    Integer.valueOf((String) newValue));
            mLockClockFonts.setValue(String.valueOf(newValue));
            mLockClockFonts.setSummary(mLockClockFonts.getEntry());
            return true;
        } else if (preference == mClockFontSize) {
            int top = (Integer) newValue;
            Settings.System.putInt(getContentResolver(),
                    Settings.System.LOCKCLOCK_FONT_SIZE, top*1);
            return true;
        } else if (preference == mDateFontSize) {
            int top = (Integer) newValue;
            Settings.System.putInt(getContentResolver(),
                    Settings.System.LOCKDATE_FONT_SIZE, top*1);
            return true;
       } else if (preference == mLockOwnerInfoFonts) {
            Settings.System.putInt(getContentResolver(), Settings.System.LOCK_OWNERINFO_FONTS,
                    Integer.valueOf((String) newValue));
            mLockOwnerInfoFonts.setValue(String.valueOf(newValue));
            mLockOwnerInfoFonts.setSummary(mLockOwnerInfoFonts.getEntry());
            return true;
        } else if (preference == mOwnerInfoFontSize) {
            int top = (Integer) newValue;
            Settings.System.putInt(getContentResolver(),
                    Settings.System.LOCKOWNER_FONT_SIZE, top*1);
            return true;
        }
        return false;
    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.CONQUEROS;
    }
}
