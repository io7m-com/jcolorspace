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


package com.io7m.jcolorspace.tests;

import com.io7m.jcolorspace.core.ColorSpaceTagLinearRGBType;
import com.io7m.jcolorspace.core.HSV;
import com.io7m.jtensors.core.parameterized.vectors.PVector4D;
import com.io7m.junreachable.UnreachableCodeException;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.DoubleRange;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static com.io7m.jcolorspace.core.HSV.hue;
import static com.io7m.jcolorspace.core.HSV.saturation;
import static com.io7m.jcolorspace.core.HSV.value;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class HSVTest
{
  @Test
  public void testSample0_V4()
  {
    final var rgb =
      PVector4D.<ColorSpaceTagLinearRGBType>of(1.0, 0.0, 0.0, 1.0);
    final var hsv =
      HSV.toHSV(rgb);

    assertEquals(0.0, hue(hsv));
    assertEquals(1.0, saturation(hsv));
    assertEquals(1.0, value(hsv));
    assertEquals(1.0, hsv.w());

    assertEquals(rgb, HSV.toRGB(hsv));
  }

  @Test
  public void testSample1_V4()
  {
    final var rgb =
      PVector4D.<ColorSpaceTagLinearRGBType>of(0.0, 1.0, 0.0, 1.0);
    final var hsv =
      HSV.toHSV(rgb);

    assertEquals(0.3333333333333333, hue(hsv));
    assertEquals(1.0, saturation(hsv));
    assertEquals(1.0, value(hsv));
    assertEquals(1.0, hsv.w());

    assertEquals(rgb, HSV.toRGB(hsv));
  }

  @Test
  public void testSample2_V4()
  {
    final var rgb =
      PVector4D.<ColorSpaceTagLinearRGBType>of(0.0, 0.0, 1.0, 1.0);
    final var hsv =
      HSV.toHSV(rgb);

    assertEquals(0.6666666666666666, hue(hsv));
    assertEquals(1.0, saturation(hsv));
    assertEquals(1.0, value(hsv));
    assertEquals(1.0, hsv.w());

    assertEquals(rgb, HSV.toRGB(hsv));
  }

  @Test
  public void testSample3_V4()
  {
    final var rgb =
      PVector4D.<ColorSpaceTagLinearRGBType>of(1.0, 1.0, 1.0, 1.0);
    final var hsv =
      HSV.toHSV(rgb);

    assertEquals(0.0, hue(hsv));
    assertEquals(0.0, saturation(hsv));
    assertEquals(1.0, value(hsv));
    assertEquals(1.0, hsv.w());

    assertEquals(rgb, HSV.toRGB(hsv));
  }

  @Test
  public void testSample4_V4()
  {
    final var rgb =
      PVector4D.<ColorSpaceTagLinearRGBType>of(0.0, 0.0, 0.0, 1.0);
    final var hsv =
      HSV.toHSV(rgb);

    assertEquals(0.0, hue(hsv));
    assertEquals(0.0, saturation(hsv));
    assertEquals(0.0, value(hsv));
    assertEquals(1.0, hsv.w());

    assertEquals(rgb, HSV.toRGB(hsv));
  }

  @Test
  public void testSample5_V4()
  {
    final var rgb =
      PVector4D.<ColorSpaceTagLinearRGBType>of(1.0, 1.0, 0.0, 1.0);
    final var hsv =
      HSV.toHSV(rgb);

    assertEquals(0.16666666666666666, hue(hsv));
    assertEquals(1.0, saturation(hsv));
    assertEquals(1.0, value(hsv));
    assertEquals(1.0, hsv.w());

    assertEquals(rgb, HSV.toRGB(hsv));
  }

  @Test
  public void testSample6_V4()
  {
    final var rgb =
      PVector4D.<ColorSpaceTagLinearRGBType>of(0.0, 1.0, 1.0, 1.0);
    final var hsv =
      HSV.toHSV(rgb);

    assertEquals(0.5, hue(hsv));
    assertEquals(1.0, saturation(hsv));
    assertEquals(1.0, value(hsv));
    assertEquals(1.0, hsv.w());

    assertEquals(rgb, HSV.toRGB(hsv));
  }

  @Test
  public void testSample7_V4()
  {
    final var rgb =
      PVector4D.<ColorSpaceTagLinearRGBType>of(1.0, 0.0, 1.0, 1.0);
    final var hsv =
      HSV.toHSV(rgb);

    assertEquals(0.8333333333333334, hue(hsv));
    assertEquals(1.0, saturation(hsv));
    assertEquals(1.0, value(hsv));
    assertEquals(1.0, hsv.w());

    assertEquals(rgb, HSV.toRGB(hsv));
  }

  @Property
  public void testEquivalencyDesaturated(
    @ForAll @DoubleRange(min = 0.0, max = 1.0) final double hue0,
    @ForAll @DoubleRange(min = 0.0, max = 1.0) final double hue1,
    @ForAll @DoubleRange(min = 0.0, max = 1.0) final double value)
  {
    assertTrue(
      HSV.equivalent(
        PVector4D.of(hue0, 0.0, value, 1.0),
        PVector4D.of(hue1, 0.0, value, 1.0)
      )
    );
  }

  @Property
  public void testEquivalencyEqual(
    @ForAll @DoubleRange(min = 0.0, max = 1.0) final double hue,
    @ForAll @DoubleRange(min = 0.0, max = 1.0) final double saturation,
    @ForAll @DoubleRange(min = 0.0, max = 1.0) final double value)
  {
    assertTrue(
      HSV.equivalent(
        PVector4D.of(hue, saturation, value, 1.0),
        PVector4D.of(hue, saturation, value, 1.0)
      )
    );
  }

  @Test
  public void testUnreachable()
  {
    assertThrows(UnreachableCodeException.class, () -> {
      try {
        final var c = HSV.class.getDeclaredConstructor();
        c.setAccessible(true);
        c.newInstance();
      } catch (final InvocationTargetException e) {
        throw e.getCause();
      }
    });
  }
}
