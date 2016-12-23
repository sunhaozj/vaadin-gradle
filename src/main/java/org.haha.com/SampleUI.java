package org.haha.com;

import com.google.common.collect.Lists;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.tokenfield.TokenField;

import javax.servlet.annotation.WebServlet;
import java.util.List;

@Widgetset("AppWidgetset")
@Theme("valo")
public class SampleUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me", FontAwesome.ADJUST);
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent e) {
                layout.addComponent(new Label("Thanks " + name.getValue()
                        + ", it works!"));
            }
        });

        TokenField tokenField = new TokenField("", TokenField.InsertPosition.AFTER);
        tokenField.setContainerDataSource(new IndexedContainer(options()));
        layout.addComponents(name, button, tokenField);
        layout.setMargin(true);
        layout.setSpacing(true);

        setContent(layout);
    }

    private List<String> options() {
        List<String> result = Lists.newArrayList();
        for(int i  = 0; i< 100; i++){
            result.add(Integer.toString(i) + Integer.toString(i) + Integer.toString(i));
        }
        return result;
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = SampleUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}