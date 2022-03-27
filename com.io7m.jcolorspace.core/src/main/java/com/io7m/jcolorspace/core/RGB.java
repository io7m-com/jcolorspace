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

/**
 * Functions to convert values to and from the linear RGB color space.
 */

public final class RGB
{
  private RGB()
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
   * @param rgb The color vector
   *
   * @return The red component of a color vector
   */

  public static double red(
    final PVector4D<ColorSpaceTagLinearRGBType> rgb)
  {
    return clamp(rgb.x(), 0.0, 1.0);
  }

  /**
   * Retrieve a color component, clamping it to the range {@code [0, 1]}.
   *
   * @param rgb The color vector
   *
   * @return The green component of a color vector
   */

  public static double green(
    final PVector4D<ColorSpaceTagLinearRGBType> rgb)
  {
    return clamp(rgb.y(), 0.0, 1.0);
  }

  /**
   * Retrieve a color component, clamping it to the range {@code [0, 1]}.
   *
   * @param rgb The color vector
   *
   * @return The blue component of a color vector
   */

  public static double blue(
    final PVector4D<ColorSpaceTagLinearRGBType> rgb)
  {
    return clamp(rgb.z(), 0.0, 1.0);
  }
}
