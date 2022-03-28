/*
 * Copyright © 2022 Mark Raynsford <code@io7m.com> https://www.io7m.com
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR
 * IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */


package com.io7m.jcolorspace.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The main demo application.
 */

public final class ColorDemoApplication extends Application
{
  /**
   * The main demo application.
   */

  public ColorDemoApplication()
  {

  }

  @Override
  public void start(final Stage stage)
    throws Exception
  {
    final var mainXML =
      ColorDemoApplication.class.getResource("mainWindow.fxml");
    final var loader =
      new FXMLLoader(mainXML);

    final VBox pane = loader.load();
    final var controller = (ColorDemo) loader.getController();

    stage.setTitle("Color Demo");
    stage.setMinWidth(600.0);
    stage.setMaxWidth(600.0);
    stage.setMinHeight(400.0);
    stage.setMaxHeight(400.0);
    stage.setScene(new Scene(pane));
    stage.show();
  }
}
