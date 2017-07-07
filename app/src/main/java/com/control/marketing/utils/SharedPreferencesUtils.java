package com.control.marketing.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.control.marketing.common.McApplication;

public class SharedPreferencesUtils {

    private static final String SP_NAME = "SP_MC";
    public static final String SP_LOGIN_ACCOUNT = "LOGIN_ACCOUNT";
    public static final String SP_LOGIN_PASSWORD = "LOGIN_PASSWORD";
    public static final String SP_LOGIN_REMEMBER_PASSWORD_AND_ACCOUNT = "REMEMBER_PASSWORD_AND_ACCOUNT";


    private static SharedPreferencesUtils instance = new SharedPreferencesUtils();
    public SharedPreferencesUtils() {

    }

    private static synchronized void syncInit() {
        if (instance == null) {
            instance = new SharedPreferencesUtils();
        }
    }

    public static SharedPreferencesUtils getInstance() {
        if (instance == null) {
            syncInit();
        }
        return instance;
    }

    private SharedPreferences getSp() {
        return McApplication.getInstance().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public int getInt(String key, int def) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null)
                def = sp.getInt(key, def);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return def;
    }

    public void putInt(String key, int val) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null) {
                SharedPreferences.Editor e = sp.edit();
                e.putInt(key, val);
                e.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long getLong(String key, long def) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null)
                def = sp.getLong(key, def);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return def;
    }

    public void putLong(String key, long val) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null) {
                SharedPreferences.Editor e = sp.edit();
                e.putLong(key, val);
                e.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getString(String key, String def) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null)
                def = sp.getString(key, def);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return def;
    }

    public void putString(String key, String val) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null) {
                SharedPreferences.Editor e = sp.edit();
                e.putString(key, val);
                e.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean getBoolean(String key, boolean def) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null)
                def = sp.getBoolean(key, def);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return def;
    }

    public float getFloat(String key, float def) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null)
                def = sp.getFloat(key, def);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return def;
    }

    public void putFloat(String key, float val) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null) {
                SharedPreferences.Editor e = sp.edit();
                e.putFloat(key, val);
                e.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void putBoolean(String key, boolean val) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null) {
                SharedPreferences.Editor e = sp.edit();
                e.putBoolean(key, val);
                e.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除一个存储，统一调用
     * @param key
     */
    public void remove(String key) {
        try {
            SharedPreferences sp = getSp();
            if (sp != null) {
                SharedPreferences.Editor e = sp.edit();
                e.remove(key);
                e.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
