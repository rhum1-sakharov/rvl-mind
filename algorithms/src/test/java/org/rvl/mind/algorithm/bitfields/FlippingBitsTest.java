package org.rvl.mind.algorithm.bitfields;

import static com.google.common.truth.Truth.*;

import org.junit.jupiter.api.Test;

class FlippingBitsTest {

  private FlippingBits flippingBits = new FlippingBits();

  @Test
  void givenLong_thenReturnFlippingBitsUnsignedLong() {
    assertThat(flippingBits.flip(2147483647L)).isEqualTo(2147483648L);
    assertThat(flippingBits.flip(1L)).isEqualTo(4294967294L);
    assertThat(flippingBits.flip(0L)).isEqualTo(4294967295L);
  }

}