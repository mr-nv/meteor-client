package minegame159.meteorclient.gui.screens;

import minegame159.meteorclient.friends.Friend;
import minegame159.meteorclient.gui.widgets.WButton;
import minegame159.meteorclient.settings.*;
import minegame159.meteorclient.utils.Color;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EditFriendScreen extends WindowScreen {
    private Friend friend;

    public final Map<String, List<Setting<?>>> settingGroups = new LinkedHashMap<>(1);
    public final List<Setting<?>> settings = new ArrayList<>(1);

    public EditFriendScreen(Friend friend) {
        super(friend.name, true);
        this.friend = friend;

        Settings s = new Settings();
        SettingGroup sg = s.getDefaultGroup();
        
        sg.add(new BoolSetting.Builder()
                .name("trusted")
                .description("Do you trust this person?")
                .defaultValue(true)
                .onChanged(aBoolean -> friend.trusted = aBoolean)
                .onModuleActivated(booleanSetting -> friend.trusted = booleanSetting.get())
                .build()
        );

        sg.add(new ColorSetting.Builder()
                .name("color")
                .description("Color.")
                .defaultValue(new Color(0, 255, 180))
                .onChanged(color -> friend.color = color)
                .onModuleActivated(colorSetting -> friend.color = colorSetting.get())
                .build()
        );

        sg.add(new BoolSetting.Builder()
                .name("attack")
                .description("Should modules attack this person?")
                .defaultValue(false)
                .onChanged(aBoolean -> friend.attack = aBoolean)
                .onModuleActivated(booleanSetting -> friend.attack = booleanSetting.get())
                .build()
        );

        sg.add(new BoolSetting.Builder()
                .name("show-in-tracers")
                .description("Show in tracers.")
                .defaultValue(true)
                .onChanged(aBoolean -> friend.showInTracers = aBoolean)
                .onModuleActivated(booleanSetting -> friend.showInTracers = booleanSetting.get())
                .build()
        );

        add(s.createTable()).fillX().expandX();
        row();
        
        add(new WButton("Back")).fillX().expandX().getWidget().action = button1 -> onClose();
    }
}
