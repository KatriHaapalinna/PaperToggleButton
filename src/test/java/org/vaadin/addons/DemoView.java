package org.vaadin.addons;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;

@Route("")
public class DemoView extends Div {
    boolean state = true;

    public DemoView() {
        PaperToggleButton toggle = new PaperToggleButton("Toggle this!");
        toggle.addChangeListener(e -> Notification
                .show("Toggle state changed to " + e.getToggleState()));
        add(toggle);
    }
}
