package io.j99.idea.vue.settings;

import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

@State(name = "VueProjectSettingsComponent",
        storages = {
                @Storage(id = "default", file = StoragePathMacros.PROJECT_FILE),
                @Storage(id = "dir", file = StoragePathMacros.PROJECT_CONFIG_DIR + "/vue_for_idea.xml", scheme = StorageScheme.DIRECTORY_BASED)
        })
public class SettingStorage implements PersistentStateComponent<SettingStorage> {
    public String builtinRulesPath = "";
    public String vueExePath = "";
    public String nodeInterpreter="";
    public boolean treatAllIssuesAsWarnings;


    public static SettingStorage getInstance() {
        SettingStorage settingStorage = ServiceManager.getService(SettingStorage.class);
        return settingStorage;
    }

    @Nullable
    @Override
    public SettingStorage getState() {
        return this;
    }

    @Override
    public void loadState(SettingStorage state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public String getVersion() {
        return nodeInterpreter + vueExePath + builtinRulesPath;
    }
}
