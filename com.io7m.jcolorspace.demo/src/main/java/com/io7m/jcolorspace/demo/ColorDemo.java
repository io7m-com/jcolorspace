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

import com.io7m.jcolorspace.core.HSV;
import com.io7m.jtensors.core.parameterized.vectors.PVector4D;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.io7m.jcolorspace.core.HSV.hue;
import static com.io7m.jcolorspace.core.HSV.saturation;
import static com.io7m.jcolorspace.core.HSV.value;
import static com.io7m.jcolorspace.core.RGB.blue;
import static com.io7m.jcolorspace.core.RGB.green;
import static com.io7m.jcolorspace.core.RGB.red;

/**
 * The main controller.
 */

public final class ColorDemo implements Initializable
{
  private final AtomicBoolean recursion;

  @FXML private Slider sliderR;
  @FXML private Slider sliderG;
  @FXML private Slider sliderB;
  @FXML private Slider sliderH;
  @FXML private Slider sliderS;
  @FXML private Slider sliderV;
  @FXML private TextField fieldR;
  @FXML private TextField fieldG;
  @FXML private TextField fieldB;
  @FXML private TextField fieldH;
  @FXML private TextField fieldS;
  @FXML private TextField fieldV;
  @FXML private Rectangle rectangle;

  /**
   * The main controller.
   */

  public ColorDemo()
  {
    this.recursion = new AtomicBoolean(false);
  }

  @Override
  public void initialize(
    final URL location,
    final ResourceBundle resources)
  {
    this.sliderR.valueProperty()
      .addListener((observable, oldValue, newValue) -> {
        this.colorChanged();
      });
    this.sliderG.valueProperty()
      .addListener((observable, oldValue, newValue) -> {
        this.colorChanged();
      });
    this.sliderB.valueProperty()
      .addListener((observable, oldValue, newValue) -> {
        this.colorChanged();
      });

    this.sliderH.valueProperty()
      .addListener((observable, oldValue, newValue) -> {
        this.hueChanged();
      });
    this.sliderS.valueProperty()
      .addListener((observable, oldValue, newValue) -> {
        this.hueChanged();
      });
    this.sliderV.valueProperty()
      .addListener((observable, oldValue, newValue) -> {
        this.hueChanged();
      });

    this.colorChanged();
  }

  private void colorChanged()
  {
    if (this.recursion.compareAndSet(false, true)) {
      try {
        final var r =
          this.sliderR.getValue() / 100.0;
        final var g =
          this.sliderG.getValue() / 100.0;
        final var b =
          this.sliderB.getValue() / 100.0;

        final var hsv =
          HSV.toHSV(PVector4D.of(r, g, b, 1.0));

        final var hue = hue(hsv);
        final var saturation = saturation(hsv);
        final var value = value(hsv);

        this.sliderH.setValue(hue * 100.0);
        this.sliderS.setValue(saturation * 100.0);
        this.sliderV.setValue(value * 100.0);

        this.fieldR.setText(String.format("%.2f", r));
        this.fieldG.setText(String.format("%.2f", g));
        this.fieldB.setText(String.format("%.2f", b));

        this.fieldH.setText(String.format("%.2f", hue));
        this.fieldS.setText(String.format("%.2f", saturation));
        this.fieldV.setText(String.format("%.2f", value));

        this.rectangle.fillProperty().set(Color.color(r, g, b));
      } finally {
        this.recursion.set(false);
      }
    }
  }

  private void hueChanged()
  {
    if (this.recursion.compareAndSet(false, true)) {
      try {
        final var hue =
          this.sliderH.getValue() / 100.0;
        final var saturation =
          this.sliderS.getValue() / 100.0;
        final var value =
          this.sliderV.getValue() / 100.0;

        final var rgb =
          HSV.toRGB(PVector4D.of(hue, saturation, value, 1.0));

        final var r = red(rgb);
        final var g = green(rgb);
        final var b = blue(rgb);

        this.sliderR.setValue(r * 100.0);
        this.sliderG.setValue(g * 100.0);
        this.sliderB.setValue(b * 100.0);

        this.fieldR.setText(String.format("%.2f", r));
        this.fieldG.setText(String.format("%.2f", g));
        this.fieldB.setText(String.format("%.2f", b));

        this.fieldH.setText(String.format("%.2f", hue));
        this.fieldS.setText(String.format("%.2f", saturation));
        this.fieldV.setText(String.format("%.2f", value));

        this.rectangle.fillProperty().set(Color.color(r, g, b));

        this.colorChanged();
      } finally {
        this.recursion.set(false);
      }
    }
  }
}
