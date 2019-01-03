package org.vaadin.addons;

import java.util.Objects;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.shared.Registration;

@Tag("paper-toggle-button")
@HtmlImport("bower_components/paper-toggle-button/paper-toggle-button.html")
public class PaperToggleButton extends Component implements HasStyle {

    private Label captionLabel;

    public PaperToggleButton() {
        getElement().synchronizeProperty("checked", "change");
    }

    public PaperToggleButton(String caption) {
        this();
        setCaption(caption);
    }

    public PaperToggleButton(String caption,
            ComponentEventListener<ToggleChangeEvent> listener) {
        this(caption);
        addChangeListener(listener);
    }

    /**
     * Adds a listener that is notified when toggle state changes due to user
     * interaction
     */
    public Registration addChangeListener(
            ComponentEventListener<ToggleChangeEvent> listener) {
        return addListener(ToggleChangeEvent.class, listener);
    }

    /**
     * Sets the caption for the toggle component
     */
    public void setCaption(final String caption) {
        getElement().removeAllChildren();
        captionLabel = new Label(Objects.toString(caption, ""));
        getElement().appendChild(captionLabel.getElement());
    }

    /**
     * Returns the caption of the toggle component
     */
    public String getCaption() {
        return captionLabel.getText();
    }

    /**
     * Toggle the state of the component
     */
    public void toggle() {
        getElement().setProperty("checked", !getToggleState());
    }

    /**
     * Returns the state of the toggle component: true if toggle is in active
     * state, otherwise false
     */
    public boolean getToggleState() {
        return new Boolean(getElement().getProperty("checked"));
    }

    /**
     * Sets the state of the toggle component
     */
    public void setToggleState(boolean state) {
        getElement().setProperty("checked", state);
    }

    @DomEvent("change")
    static public class ToggleChangeEvent
            extends ComponentEvent<PaperToggleButton> {
        private boolean checked = false;

        public ToggleChangeEvent(PaperToggleButton source, boolean fromClient) {
            super(source, true);
            checked = source.getToggleState();
        }

        public boolean getToggleState() {
            return checked;
        }
    }
}
