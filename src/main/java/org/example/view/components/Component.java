package org.example.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public abstract class Component {
    protected final Pane container;

    protected Component(Pane container) {
        this.container = container;
        setAlignmentIfSupported(Pos.CENTER);
        container.setPadding(new Insets(10));
    }

    public Node getNode() {
        return container;
    }

    protected void applyStyleClass(String styleClass) {
        container.getStyleClass().add(styleClass);
    }

    private void setAlignmentIfSupported(Pos alignment) {
        if (container instanceof VBox) {
            ((VBox) container).setAlignment(alignment);
        } else if (container instanceof HBox) {
            ((HBox) container).setAlignment(alignment);
        }
        // Можно добавить поддержку других контейнеров, например, StackPane
    }
}