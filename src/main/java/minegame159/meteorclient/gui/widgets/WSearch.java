package minegame159.meteorclient.gui.widgets;

import minegame159.meteorclient.gui.GuiConfig;
import minegame159.meteorclient.modules.Module;
import minegame159.meteorclient.modules.ModuleManager;
import net.minecraft.util.Pair;

import java.util.List;

public class WSearch extends WWindow {
    private WTextBox filter;

    public WSearch() {
        super("Search", false, GuiConfig.WindowType.Search);

        onDragged = window -> {
            GuiConfig.WindowConfig winConfig = GuiConfig.INSTANCE.getWindowConfig(type, false);
            winConfig.setPos(x, y);
        };

        initWidgets(true);
    }

    private void initWidgets(boolean first) {
        boolean focused = filter != null && filter.isFocused();

        filter = add(new WTextBox(filter != null ? filter.text : "", 70)).fillX().expandX().getWidget();
        filter.setFocused(focused || (first && isExpanded()));
        filter.action = textBox -> {
            clear();
            initWidgets(false);
        };
        row();

        if (!filter.text.isEmpty()) {
            // Titles
            List<Pair<Module, Integer>> modules = ModuleManager.INSTANCE.searchTitles(filter.text);
            if (modules.size() > 0) {
                add(new WHorizontalSeparator("Modules")).fillX().expandX();
                row();
            }
            for (Pair<Module, Integer> pair : modules) {
                add(new WModule(pair.getLeft())).space(0);
                row();
            }

            // Setting titles
            modules = ModuleManager.INSTANCE.searchSettingTitles(filter.text);
            add(new WHorizontalSeparator("Settings")).fillX().expandX();
            row();
            for (Pair<Module, Integer> pair : modules) {
                add(new WModule(pair.getLeft()));
                row();
            }
        }
    }
}
