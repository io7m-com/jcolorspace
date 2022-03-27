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


package com.io7m.jcolorspace.core;

import com.io7m.jtensors.core.parameterized.vectors.PVector4D;
import com.io7m.junreachable.UnreachableCodeException;

import java.util.Objects;

import static com.io7m.jcolorspace.core.RGB.blue;
import static com.io7m.jcolorspace.core.RGB.green;
import static com.io7m.jcolorspace.core.RGB.red;

/**
 * Functions to convert values to and from the HSV color space.
 */

public final class HSV
{
  private HSV()
  {
    throw new UnreachableCodeException();
  }

  private static double clamp(
    final double x,
    final double min,
    final double max)
  {
    return Math.min(Math.max(x, min), max);
  }

  /**
   * Retrieve a color component, clamping it to the range {@code [0, 1]}.
   *
   * @param hsv The color vector
   *
   * @return The hue component of a color vector
   */

  public static double hue(
    final PVector4D<ColorSpaceTagHSVType> hsv)
  {
    return clamp(hsv.x(), 0.0, 1.0);
  }

  /**
   * Retrieve a color component, clamping it to the range {@code [0, 1]}.
   *
   * @param hsv The color vector
   *
   * @return The saturation component of a color vector
   */

  public static double saturation(
    final PVector4D<ColorSpaceTagHSVType> hsv)
  {
    return clamp(hsv.y(), 0.0, 1.0);
  }

  /**
   * Retrieve a color component, clamping it to the range {@code [0, 1]}.
   *
   * @param hsv The color vector
   *
   * @return The value component of a color vector
   */

  public static double value(
    final PVector4D<ColorSpaceTagHSVType> hsv)
  {
    return clamp(hsv.z(), 0.0, 1.0);
  }

  /**
   * Determine if two colors are "equivalent" under HSV. For colors with a
   * saturation of 0.0, two colors are equal if their value components are
   * equal. Otherwise, two colors are equal if all of their components are
   * equal.
   *
   * @param a The first color
   * @param b The second color
   *
   * @return {@code true} if the colors are equivalent
   */

  public static boolean equivalent(
    final PVector4D<ColorSpaceTagHSVType> a,
    final PVector4D<ColorSpaceTagHSVType> b)
  {
    if (saturation(a) == 0.0 && saturation(b) == 0.0) {
      return value(a) == value(b);
    }
    return Objects.equals(a, b);
  }

  /**
   * Convert a linear RGB color value to HSV.
   *
   * @param rgb A linear RGB value
   *
   * @return An HSV value
   */

  public static PVector4D<ColorSpaceTagHSVType> toHSV(
    final PVector4D<ColorSpaceTagLinearRGBType> rgb)
  {
    final double r = red(rgb);
    final double g = green(rgb);
    final double b = blue(rgb);

    double maxC = Math.max(r, g);
    if (b > maxC) {
      maxC = b;
    }

    double minC = Math.min(r, g);
    if (b < minC) {
      minC = b;
    }

    final double value = maxC;
    final double saturation;
    final var delta = maxC - minC;

    if (maxC != 0.0) {
      saturation = delta / maxC;
    } else {
      saturation = 0.0;
    }

    double hue;
    if (saturation == 0.0) {
      hue = 0.0;
    } else {
      final double redc = (maxC - r) / delta;
      final double greenc = (maxC - g) / delta;
      final double bluec = (maxC - b) / delta;

      if (r == maxC) {
        hue = bluec - greenc;
      } else if (g == maxC) {
        hue = 2.0 + redc - bluec;
      } else {
        hue = 4.0 + greenc - redc;
      }

      hue = hue / 6.0;
      if (hue < 0.0) {
        hue = hue + 1.0;
      }
    }

    return PVector4D.of(
      clamp(hue, 0.0, 1.0),
      clamp(saturation, 0.0, 1.0),
      clamp(value, 0.0, 1.0),
      rgb.w()
    );
  }

  /**
   * Convert an HSV value to a linear RGB value.
   *
   * @param hsv An HSV value
   *
   * @return An RGB value
   */

  public static PVector4D<ColorSpaceTagLinearRGBType> toRGB(
    final PVector4D<ColorSpaceTagHSVType> hsv)
  {
    final double hue = hue(hsv);
    final double saturation = saturation(hsv);
    final double value = value(hsv);

    /*
     * No saturation indicates a shade of grey.
     */

    if (saturation == 0.0) {
      return PVector4D.of(value, value, value, hsv.w());
    }

    /*
     * https://en.wikipedia.org/wiki/HSL_and_HSV#HSV_to_RGB
     */

    final var chroma = saturation * value;
    final var sector = (int) Math.round(hue * 6.0);
    final var x = chroma * (1.0 - (double) Math.abs((sector % 2) - 1));

    if (sector < 1) {
      return PVector4D.of(chroma, x, 0.0, hsv.w());
    }
    if (sector < 2) {
      return PVector4D.of(x, chroma, 0.0, hsv.w());
    }
    if (sector < 3) {
      return PVector4D.of(0.0, chroma, x, hsv.w());
    }
    if (sector < 4) {
      return PVector4D.of(0.0, x, chroma, hsv.w());
    }
    if (sector < 5) {
      return PVector4D.of(x, 0.0, chroma, hsv.w());
    }

    return PVector4D.of(chroma, 0.0, x, hsv.w());
  }
}
